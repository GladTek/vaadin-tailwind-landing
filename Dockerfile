FROM maven:3.9-eclipse-temurin-21-alpine AS build
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY pom.xml package.json package-lock.json vite.config.ts tsconfig.json types.d.ts $HOME/
RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline

COPY src $HOME/src
RUN --mount=type=cache,target=/root/.m2 \
     mvn clean package -DskipTests

# Create a custom Java runtime
RUN $JAVA_HOME/bin/jlink \
         --add-modules java.base,java.logging,java.naming,java.desktop,java.management,java.security.jgss,java.instrument,java.sql,jdk.unsupported,java.net.http,java.scripting,java.rmi,java.xml,java.prefs,jdk.zipfs \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

FROM alpine:latest
ENV JAVA_HOME=/opt/java-runtime
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY --from=build /javaruntime $JAVA_HOME
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=prod"]