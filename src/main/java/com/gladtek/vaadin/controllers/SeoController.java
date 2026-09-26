package com.gladtek.vaadin.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@RestController
public class SeoController {

    @Value("${app.url:https://vaadin-landing.gladtek.com}")
    private String configuredBaseUrl;

    @Value("classpath:seo/robots.txt")
    private Resource robotsTemplate;

    @Value("classpath:seo/sitemap.xml")
    private Resource sitemapTemplate;

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getRobotsTxt() throws IOException {
        String baseUrl = resolveBaseUrl();
        String template = StreamUtils.copyToString(robotsTemplate.getInputStream(), StandardCharsets.UTF_8);
        return template.replace("{{BASE_URL}}", baseUrl);
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String getSitemapXml() throws IOException {
        String baseUrl = resolveBaseUrl();
        String today = LocalDate.now().toString();
        String template = StreamUtils.copyToString(sitemapTemplate.getInputStream(), StandardCharsets.UTF_8);
        return template
            .replace("{{BASE_URL}}", baseUrl)
            .replace("{{LASTMOD}}", today);
    }

    private String resolveBaseUrl() {
        if (configuredBaseUrl != null && !configuredBaseUrl.isBlank()) {
            try {
                URI uri = URI.create(configuredBaseUrl.trim());
                if (uri.getScheme() != null && uri.getHost() != null) {
                    String port = uri.getPort() != -1 ? ":" + uri.getPort() : "";
                    return uri.getScheme() + "://" + uri.getHost() + port;
                }
            } catch (IllegalArgumentException ignored) {
                // Fallback to default
            }
        }
        return "https://vaadin-landing.gladtek.com";
    }
}
