package com.gladtek.vaadin.components.common;

import com.gladtek.vaadin.data.model.Feature;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;

public class FeatureCard extends Div {

    public FeatureCard(Feature feature) {
        addClassNames("feature-card");

        Div iconWrapper = new Div();
        iconWrapper.addClassNames("feature-icon-wrapper", "feature-icon-" + feature.color());
        iconWrapper.getElement().setAttribute("aria-hidden", "true");
        
        Icon icon = feature.icon().create();
        icon.addClassNames("w-6", "h-6");
        iconWrapper.add(icon);

        H3 h = new H3(getTranslation(feature.titleKey()));
        h.addClassNames("feature-card-title");

        Paragraph p = new Paragraph(getTranslation(feature.descriptionKey()));
        p.addClassNames("feature-card-description");

        add(iconWrapper, h, p);
    }
}
