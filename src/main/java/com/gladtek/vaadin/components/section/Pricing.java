package com.gladtek.vaadin.components.section;

import com.gladtek.vaadin.components.common.PricingCard;
import com.gladtek.vaadin.components.common.PricingToggle;
import com.gladtek.vaadin.components.common.SectionHeader;
import com.gladtek.vaadin.data.model.Plan;
import com.vaadin.flow.component.html.*;
import com.gladtek.vaadin.services.PricingService;
import java.util.ArrayList;
import java.util.List;

public class Pricing extends Section {

    public Pricing(PricingService pricingService) {
        addClassNames("pricing-section");
        setId("pricing");

        Div container = new Div();
        container.addClassNames("section-container");

        SectionHeader header = new SectionHeader(getTranslation("pricing.title"), getTranslation("pricing.description"));
        header.setTitleId("pricing-title");
        getElement().setAttribute("aria-labelledby", "pricing-title");

        PricingToggle toggle = new PricingToggle();
        
        Div grid = new Div();
        grid.addClassNames("grid-3");

        List<PricingCard> cards = new ArrayList<>();
        List<Plan> plans = pricingService.getPlans();

        plans.forEach(plan -> {
            PricingCard card = new PricingCard(plan);
            cards.add(card);
            grid.add(card);
        });

        toggle.addValueChangeListener(e -> {
            cards.forEach(card -> card.setYearly(e.isYearly()));
        });

        container.add(header, toggle, grid);
        add(container);
    }
}
