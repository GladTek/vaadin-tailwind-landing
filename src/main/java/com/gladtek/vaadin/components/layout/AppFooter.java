package com.gladtek.vaadin.components.layout;

import com.gladtek.vaadin.data.footer.FooterColumn;
import com.gladtek.vaadin.data.footer.FooterLink;
import com.vaadin.flow.component.html.*;
import java.util.List;

public class AppFooter extends Footer {

    public AppFooter() {
        addClassNames("footer");
        setId("footer");

        Div container = new Div();
        container.addClassNames("footer-container");

        Div grid = new Div();
        grid.addClassNames("footer-grid");

        // Brand
        Div brand = new Div();
        Span logo = new Span("VAADIN LANDING");
        logo.addClassNames("footer-logo");
        Paragraph desc = new Paragraph(getTranslation("footer.brand.desc"));
        desc.addClassNames("footer-description");
        brand.add(logo, desc);

        grid.add(brand);

        List.of(
            new FooterColumn("footer.col.product", 
                new FooterLink("footer.link.features", "#features"), 
                new FooterLink("footer.link.security"), 
                new FooterLink("footer.link.pricing", "#pricing"), 
                new FooterLink("footer.link.enterprise")),
            new FooterColumn("footer.col.resources", 
                new FooterLink("footer.link.documentation"), 
                new FooterLink("footer.link.api"), 
                new FooterLink("footer.link.guides"), 
                new FooterLink("footer.link.blog", "https://www.gladtek.com/blog")),
            new FooterColumn("footer.col.legal", 
                new FooterLink("footer.link.privacy"), 
                new FooterLink("footer.link.terms"), 
                new FooterLink("footer.link.cookie"), 
                new FooterLink("footer.link.gdpr"))
        ).forEach(col -> grid.add(createColumn(col)));

        Div bottom = new Div();
        bottom.addClassNames("footer-bottom");

        Span copy = new Span(getTranslation("footer.copy"));
        copy.addClassNames("footer-copy");

        Div social = new Div();
        social.addClassNames("footer-social");
        social.add(
            new FooterLinkComponent(new FooterLink("footer.social.twitter", "https://twitter.com/gladtek")),
            new FooterLinkComponent(new FooterLink("footer.social.github", "https://github.com/gladtek")),
            new FooterLinkComponent(new FooterLink("footer.social.linkedin", "https://linkedin.com/company/gladtek"))
        );

        bottom.add(copy, social);

        container.add(grid, bottom);
        add(container);
    }

    private Div createColumn(FooterColumn column) {
        Div col = new Div();
        H3 h = new H3(getTranslation(column.titleKey()));
        h.addClassNames("footer-column-title");

        Div linkList = new Div();
        linkList.addClassNames("footer-link-list");

        for (FooterLink l : column.links()) {
            linkList.add(new FooterLinkComponent(l));
        }

        col.add(h, linkList);
        return col;
    }

}
