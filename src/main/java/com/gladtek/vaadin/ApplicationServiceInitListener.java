package com.gladtek.vaadin;

import com.vaadin.flow.component.Direction;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.communication.IndexHtmlRequestListener;
import com.vaadin.flow.server.communication.IndexHtmlResponse;
import jakarta.servlet.http.Cookie;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Set;

@Component
public class ApplicationServiceInitListener implements VaadinServiceInitListener, IndexHtmlRequestListener {

    private static final Set<String> SUPPORTED_LANGUAGES = Set.of("en", "fr", "ar", "ja");
    private static final String COOKIE_NAME = "app-locale";

    @Value("${app.meta.og.title}")
    private String ogTitle;

    @Value("${app.meta.og.description}")
    private String ogDescription;

    @Value("${app.meta.og.image}")
    private String ogImage;

    @Value("${app.meta.og.url}")
    private String ogUrl;

    @Value("${app.meta.og.type}")
    private String ogType;

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addIndexHtmlRequestListener(this);

        // Restore persisted user locale on UI initialization
        event.getSource().addUIInitListener(uiInitEvent -> {
            UI ui = uiInitEvent.getUI();
            VaadinRequest request = VaadinService.getCurrentRequest();
            if (request != null && request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (COOKIE_NAME.equals(cookie.getName())) {
                        String lang = cookie.getValue();
                        if (lang != null && SUPPORTED_LANGUAGES.contains(lang.toLowerCase())) {
                            Locale locale = Locale.forLanguageTag(lang.toLowerCase());
                            ui.getSession().setLocale(locale);
                            if ("ar".equalsIgnoreCase(lang)) {
                                ui.setDirection(Direction.RIGHT_TO_LEFT);
                            } else {
                                ui.setDirection(Direction.LEFT_TO_RIGHT);
                            }
                        }
                        break;
                    }
                }
            }
        });
    }

    @Value("classpath:seo/schema.json")
    private Resource schemaTemplate;

    @Override
    public void modifyIndexHtmlResponse(IndexHtmlResponse response) {
        Document document = response.getDocument();
        Element head = document.head();

        // 1. Canonical Link
        Element canonical = document.createElement("link");
        canonical.attr("rel", "canonical");
        canonical.attr("href", ogUrl);
        head.appendChild(canonical);

        // 2. Open Graph Meta Tags
        addPropertyMeta(document, "og:title", ogTitle);
        addPropertyMeta(document, "og:description", ogDescription);
        addPropertyMeta(document, "og:image", ogImage);
        addPropertyMeta(document, "og:url", ogUrl);
        addPropertyMeta(document, "og:type", ogType);
        addPropertyMeta(document, "og:site_name", "GladTek");

        // 3. Twitter Card Meta Tags
        addNameMeta(document, "twitter:card", "summary_large_image");
        addNameMeta(document, "twitter:title", ogTitle);
        addNameMeta(document, "twitter:description", ogDescription);
        addNameMeta(document, "twitter:image", ogImage);

        // 4. Schema.org JSON-LD Structured Data
        try {
            String jsonLdText = StreamUtils.copyToString(schemaTemplate.getInputStream(), StandardCharsets.UTF_8);
            String formattedJsonLd = jsonLdText.replace("{{APP_URL}}", ogUrl != null ? ogUrl.replaceAll("/+$", "") : "https://vaadin-landing.gladtek.com");

            Element jsonLd = document.createElement("script");
            jsonLd.attr("type", "application/ld+json");
            jsonLd.text(formattedJsonLd);
            head.appendChild(jsonLd);
        } catch (IOException e) {
            // Fallback or log if resource is unreadable
        }
    }

    private void addPropertyMeta(Document document, String property, String content) {
        Element head = document.head();
        Element meta = document.createElement("meta");
        meta.attr("property", property);
        meta.attr("content", content);
        head.appendChild(meta);
    }

    private void addNameMeta(Document document, String name, String content) {
        Element head = document.head();
        Element meta = document.createElement("meta");
        meta.attr("name", name);
        meta.attr("content", content);
        head.appendChild(meta);
    }
}
