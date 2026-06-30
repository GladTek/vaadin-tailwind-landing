package com.gladtek.vaadin.components.section;

import com.gladtek.vaadin.components.common.FeatureCard;
import com.gladtek.vaadin.components.common.SectionHeader;
import com.gladtek.vaadin.data.model.Feature;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.gladtek.vaadin.services.FeatureService;
import java.util.ArrayList;
import java.util.List;

public class Features extends Section {

    private String activeCategory = "all";

    public Features(FeatureService featureService) {
        addClassNames("features-section");
        setId("features");

        Div container = new Div();
        container.addClassNames("section-container");

        SectionHeader header = new SectionHeader(getTranslation("features.title"), getTranslation("features.description"));
        header.setTitleId("features-title");
        getElement().setAttribute("aria-labelledby", "features-title");

        // Tabs container
        Div tabsContainer = new Div();
        tabsContainer.addClassNames("flex", "justify-center", "gap-4", "mb-12");

        Button allBtn = new Button(getTranslation("features.cat.all"));
        Button coreBtn = new Button(getTranslation("features.cat.core"));
        Button devBtn = new Button(getTranslation("features.cat.dev"));

        List<Button> buttons = List.of(allBtn, coreBtn, devBtn);
        
        Div grid = new Div();
        grid.addClassNames("grid-3");

        List<Feature> features = featureService.getFeatures();
        List<FeatureCardWrapper> wrappers = new ArrayList<>();

        features.forEach(feature -> {
            FeatureCard card = new FeatureCard(feature);
            FeatureCardWrapper wrapper = new FeatureCardWrapper(card, feature.category());
            grid.add(wrapper);
            wrappers.add(wrapper);
        });

        Runnable updateActiveTab = () -> {
            buttons.forEach(btn -> {
                btn.removeClassName("btn-primary");
                btn.addClassNames("px-5", "py-2.5", "rounded-xl", "font-medium", "transition-all");
                if (btn == allBtn && activeCategory.equals("all") ||
                    btn == coreBtn && activeCategory.equals("core") ||
                    btn == devBtn && activeCategory.equals("dev")) {
                    btn.addClassName("btn-primary");
                    btn.removeClassName("bg-slate-100");
                } else {
                    btn.addClassNames("bg-slate-100", "text-slate-700", "hover:bg-slate-200");
                }
            });

            wrappers.forEach(w -> {
                if (activeCategory.equals("all") || w.getCategory().equals(activeCategory)) {
                    w.setVisible(true);
                } else {
                    w.setVisible(false);
                }
            });
        };

        allBtn.addClickListener(e -> {
            activeCategory = "all";
            updateActiveTab.run();
        });
        coreBtn.addClickListener(e -> {
            activeCategory = "core";
            updateActiveTab.run();
        });
        devBtn.addClickListener(e -> {
            activeCategory = "dev";
            updateActiveTab.run();
        });

        updateActiveTab.run();
        tabsContainer.add(allBtn, coreBtn, devBtn);

        container.add(header, tabsContainer, grid);
        add(container);
    }

    private static class FeatureCardWrapper extends Div {
        private final String category;

        public FeatureCardWrapper(FeatureCard card, String category) {
            this.category = category;
            addClassNames("transition-all", "duration-500", "transform");
            add(card);
        }

        public String getCategory() {
            return category;
        }
    }
}
