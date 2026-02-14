package com.gladtek.vaadin.components.layout;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;

/**
 * SkipLinks provides accessibility features that allow keyboard users to 
 * bypass content and jump straight to key sections like navigation, 
 * main content, or the footer.
 */
public class SkipLink extends Div {

    public SkipLink() {
        addClassNames("skip-links-container");
        getElement().setAttribute("role", "navigation");
        getElement().setAttribute("aria-label", "Quick Navigation");
        
        add(createSkipLink("#navigation", "Skip to navigation"));
        add(createSkipLink("#main-content", "Skip to main content"));
        add(createSkipLink("#footer", "Skip to footer"));
    }

    private Anchor createSkipLink(String href, String text) {
        Anchor link = new Anchor(href, text);
        link.addClassNames("skip-link");
        return link;
    }
}
