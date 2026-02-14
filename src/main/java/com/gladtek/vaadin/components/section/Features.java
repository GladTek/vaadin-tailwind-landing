package com.gladtek.vaadin.components.section;

import com.gladtek.vaadin.components.common.FeatureCard;
import com.gladtek.vaadin.components.common.SectionHeader;
import com.gladtek.vaadin.data.model.Feature;
import com.vaadin.flow.component.html.*;
import com.gladtek.vaadin.services.FeatureService;
import java.util.List;

public class Features extends Section {

    public Features(FeatureService featureService) {
        addClassNames("features-section");
        setId("features");

        Div container = new Div();
        container.addClassNames("section-container");

        SectionHeader header = new SectionHeader(getTranslation("features.title"), getTranslation("features.description"));
        header.setTitleId("features-title");
        getElement().setAttribute("aria-labelledby", "features-title");

        Div grid = new Div();
        grid.addClassNames("grid-3");

        List<Feature> features = featureService.getFeatures();

        features.stream()
                .map(FeatureCard::new)
                .forEach(grid::add);

        container.add(header, grid);
        add(container);
    }
}
