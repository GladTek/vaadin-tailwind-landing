package com.gladtek.vaadin.components;


import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class NavBar extends Nav {

    public NavBar() {
        addClassNames("navbar");

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

        links.add(createLink("Features"), createLink("Solutions"), createLink("Pricing"), createLink("Company"));

        Div actions = new Div();
        actions.addClassNames("navbar-actions");

        Anchor login = new Anchor("#", "Sign in");
        login.addClassNames("navbar-signin");

        Anchor getStarted = new Anchor("#", "Get Started");
        getStarted.addClassNames("btn-primary", "text-sm", "py-2", "px-4");

        actions.add(login, getStarted);

        layout.add(logo, links, actions);
        container.add(layout);
        add(container);
    }

    private Anchor createLink(String text) {
        Anchor link = new Anchor("#", text);
        link.addClassNames("navbar-link");

        Div underline = new Div();
        underline.addClassNames("navbar-link-underline");
        link.add(underline);

        return link;
    }
}
