package com.gladtek.vaadin;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@StyleSheet("./styles.css")
@PWA(name = "GladTek Landing", shortName = "GladTek", iconPath = "icons/icon.png")
public class Application implements AppShellConfigurator {

    @Override
    public void configurePage(com.vaadin.flow.server.AppShellSettings settings) {
        settings.addLink("preconnect", "https://api.dicebear.com");
        settings.addLink("dns-prefetch", "https://api.dicebear.com");
        settings.addMetaTag("description", "GladTek - Expert Jahia & Vaadin development services. We specialize in custom modules, SEO audits, and web application development.");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
