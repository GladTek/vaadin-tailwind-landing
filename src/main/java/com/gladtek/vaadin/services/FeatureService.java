package com.gladtek.vaadin.services;

import com.gladtek.vaadin.data.model.Feature;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {

    public List<Feature> getFeatures() {
        return List.of(
            new Feature("feature.global.title", "feature.global.desc", "sky", VaadinIcon.GLOBE),
            new Feature("feature.analytics.title", "feature.analytics.desc", "indigo", VaadinIcon.CHART),
            new Feature("feature.security.title", "feature.security.desc", "violet", VaadinIcon.SHIELD),
            new Feature("feature.scalable.title", "feature.scalable.desc", "emerald", VaadinIcon.EXPAND_SQUARE),
            new Feature("feature.integration.title", "feature.integration.desc", "orange", VaadinIcon.CONNECT),
            new Feature("feature.dx.title", "feature.dx.desc", "rose", VaadinIcon.CODE)
        );
    }
}
