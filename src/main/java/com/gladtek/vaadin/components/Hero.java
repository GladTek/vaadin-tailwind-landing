package com.gladtek.vaadin.components;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;

public class Hero extends Section {

    public Hero() {
        addClassNames("hero");

        // Background Glows
        Div bgGlow1 = new Div();
        bgGlow1.addClassNames("hero-glow-1");

        Div bgGlow2 = new Div();
        bgGlow2.addClassNames("hero-glow-2");

        Div container = new Div();
        container.addClassNames("hero-container");

        Div content = new Div();
        content.addClassNames("hero-content");

        Span badge = new Span("New: Vaadin 25 is here");
        badge.addClassNames("hero-badge");

        H1 title = new H1();
        title.addClassNames("hero-title");
        Span titleBase = new Span("Modern Enterprise ");
        Span titleGradient = new Span("Cloud Platform");
        titleGradient.addClassNames("title-text-gradient");
        title.add(titleBase, titleGradient);

        Paragraph desc = new Paragraph("Build stunning full-stack applications with Java and Tailwind CSS. The professional way to scale your business logic without limits.");
        desc.addClassNames("hero-description");

        Div ctaGroup = new Div();
        ctaGroup.addClassNames("hero-cta-group");

        Anchor primaryBtn = new Anchor("#", "Start building for free");
        primaryBtn.addClassNames("btn-primary");

        Anchor docBtn = new Anchor("#", "Read documentation");
        docBtn.addClassNames("btn-secondary");

        ctaGroup.add(primaryBtn, docBtn);

        content.add(badge, title, desc, ctaGroup);
        container.add(content);
        add(bgGlow1, bgGlow2, container);
    }
}
