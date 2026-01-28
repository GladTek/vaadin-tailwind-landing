package com.gladtek.vaadin.components;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;

public class AppFooter extends Footer {

    public AppFooter() {
        addClassNames("footer");

        Div container = new Div();
        container.addClassNames("footer-container");

        Div grid = new Div();
        grid.addClassNames("footer-grid");

        // Brand
        Div brand = new Div();
        Span logo = new Span("VAADIN LANDING");
        logo.addClassNames("footer-logo");
        Paragraph desc = new Paragraph("Building the future of enterprise software with Java and Tailwind CSS.");
        desc.addClassNames("footer-description");
        brand.add(logo, desc);

        grid.add(brand);
        grid.add(createColumn("Product", "Features", "Security", "Pricing", "Enterprise"));
        grid.add(createColumn("Resources", "Documentation", "API Reference", "Guides", "Blog"));
        grid.add(createColumn("Legal", "Privacy Policy", "Terms of Service", "Cookie Policy", "GDPR"));

        Div bottom = new Div();
        bottom.addClassNames("footer-bottom");

        Span copy = new Span("© 2026 Vaadin Landing Inc. All rights reserved.");
        copy.addClassNames("footer-copy");

        Div social = new Div();
        social.addClassNames("footer-social");
        social.add(createSocialLink("Twitter"), createSocialLink("GitHub"), createSocialLink("LinkedIn"));

        bottom.add(copy, social);

        container.add(grid, bottom);
        add(container);
    }

    private Div createColumn(String title, String... links) {
        Div col = new Div();
        H3 h = new H3(title);
        h.addClassNames("footer-column-title");

        Div linkList = new Div();
        linkList.addClassNames("footer-link-list");

        for (String l : links) {
            Anchor a = new Anchor("#", l);
            a.addClassNames("footer-link");
            linkList.add(a);
        }

        col.add(h, linkList);
        return col;
    }

    private Anchor createSocialLink(String text) {
        Anchor a = new Anchor("#", text);
        a.addClassNames("footer-social-link");
        return a;
    }
}
