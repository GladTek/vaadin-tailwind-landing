package com.gladtek.vaadin.components.layout;


import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class NavBar extends Nav {

    public NavBar() {
        addClassNames("navbar");
        setId("navigation");
        getElement().setAttribute("role", "navigation");
        getElement().setAttribute("aria-label", "Main Navigation");

        Div container = new Div();
        container.addClassNames("navbar-container");

        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassNames("navbar-layout");
        layout.setSpacing(false);
        layout.setPadding(false);


        Anchor logo = new Anchor("/", "VAADIN LANDING");
        logo.addClassNames("navbar-logo");

        Div links = new Div();
        links.addClassNames("navbar-links");

        links.add(
            createLink(getTranslation("nav.features"), "features"),
            createLink(getTranslation("nav.pricing"), "pricing"),
            createLink(getTranslation("nav.testimonials"), "testimonials")
        );

        Div actions = new Div();
        actions.addClassNames("navbar-actions");

        LanguageSwitcher langSwitcher = new LanguageSwitcher();

        Anchor login = new Anchor("https://www.gladtek.com", getTranslation("nav.signin"));
        login.setTarget("_blank");
        login.addClassNames("navbar-signin");

        Anchor getStarted = new Anchor("https://www.gladtek.com", getTranslation("nav.getstarted"));
        getStarted.setTarget("_blank");
        getStarted.addClassNames("btn-primary", "text-sm", "py-2", "px-4");

        actions.add(langSwitcher, login, getStarted);

        layout.add(logo, links, actions);
        container.add(layout);
        add(container);
    }

    private Anchor createLink(String text, String targetId) {
        Anchor link = new Anchor("#" + targetId, text);
        link.addClassNames("navbar-link");

        Div underline = new Div();
        underline.addClassNames("navbar-link-underline");
        link.add(underline);

        return link;
    }
}
