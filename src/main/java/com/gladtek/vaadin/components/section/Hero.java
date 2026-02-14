package com.gladtek.vaadin.components.section;

import com.vaadin.flow.component.html.*;

public class Hero extends Section {

    public Hero() {
        addClassNames("hero");
        setId("main-content");

        // Background Glows
        Div bgGlow1 = new Div();
        bgGlow1.addClassNames("hero-glow-1");
        bgGlow1.getElement().setAttribute("aria-hidden", "true");

        Div bgGlow2 = new Div();
        bgGlow2.addClassNames("hero-glow-2");
        bgGlow2.getElement().setAttribute("aria-hidden", "true");

        Div container = new Div();
        container.addClassNames("hero-container");

        Div content = new Div();
        content.addClassNames("hero-content");

        Span badge = new Span(getTranslation("hero.badge"));
        badge.addClassNames("hero-badge");

        H1 title = new H1(getTranslation("hero.title"));
        title.addClassNames("hero-title");

        Paragraph desc = new Paragraph(getTranslation("hero.description"));
        desc.addClassNames("hero-description");

        Div ctaGroup = new Div();
        ctaGroup.addClassNames("hero-cta-group");

        Anchor primaryBtn = new Anchor("https://www.gladtek.com", getTranslation("hero.primary"));
        primaryBtn.setTarget("_blank");
        primaryBtn.addClassNames("btn-primary");

        Anchor docBtn = new Anchor("#", getTranslation("hero.secondary"));
        docBtn.addClassNames("btn-secondary");

        ctaGroup.add(primaryBtn, docBtn);

        content.add(badge, title, desc, ctaGroup);
        container.add(content);
        add(bgGlow1, bgGlow2, container);
    }
}
