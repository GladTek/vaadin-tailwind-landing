package com.gladtek.vaadin.components.layout;

import com.gladtek.vaadin.data.footer.FooterLink;
import com.vaadin.flow.component.html.Anchor;

public class FooterLinkComponent extends Anchor {
    
    public FooterLinkComponent(FooterLink link) {
        super(link.url(), link.labelKey());
        
        // Resolve translation
        setText(getTranslation(link.labelKey()));
        
        if (link.url().startsWith("http")) {
            addClassNames("footer-social-link");
        } else {
            addClassNames("footer-link");
        }
    }
}
