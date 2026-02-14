package com.gladtek.vaadin;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.communication.IndexHtmlRequestListener;
import com.vaadin.flow.server.communication.IndexHtmlResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ApplicationServiceInitListener implements VaadinServiceInitListener, IndexHtmlRequestListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addIndexHtmlRequestListener(this);
    }


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
    public void modifyIndexHtmlResponse(IndexHtmlResponse response) {
        Document document = response.getDocument();
        // Open Graph Meta Tags that require 'property' attribute @Meta from Vaadin use only name.
        addMetaTag(document, "og:title", ogTitle);
        addMetaTag(document, "og:description", ogDescription);
        addMetaTag(document, "og:image", ogImage);
        addMetaTag(document, "og:url", ogUrl);
        addMetaTag(document, "og:type", ogType);
    }

    private void addMetaTag(Document document, String property, String content) {
        Element head = document.head();
        Element meta = document.createElement("meta");
        meta.attr("property", property);
        meta.attr("content", content);
        head.appendChild(meta);
    }
}
