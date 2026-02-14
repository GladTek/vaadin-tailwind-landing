package com.gladtek.vaadin.components.common;

import com.gladtek.vaadin.data.model.Plan;
import com.vaadin.flow.component.html.*;

public class PricingCard extends Div {

    private final Plan plan;
    private final Span amount;
    private final Span period;

    public PricingCard(Plan plan) {
        this.plan = plan;
        addClassNames("pricing-card");
        if (plan.popular()) {
            addClassNames("pricing-card-popular");
            Span badge = new Span("MOST POPULAR");
            badge.addClassNames("pricing-badge");
            add(badge);
        }

        H3 h = new H3(getTranslation(plan.nameKey()));
        h.addClassNames("text-xl", "font-bold", "text-slate-900", "mb-4");

        Div priceDiv = new Div();
        priceDiv.addClassNames("flex", "items-baseline", "mb-6");
        amount = new Span(plan.monthlyPrice());
        amount.addClassNames("pricing-amount");
        period = new Span(getTranslation("pricing.period.monthly"));
        period.addClassNames("pricing-period");
        priceDiv.add(amount, period);

        Paragraph p = new Paragraph(getTranslation(plan.descriptionKey()));
        p.addClassNames("text-slate-600", "text-sm", "mb-8");

        UnorderedList featureList = new UnorderedList();
        featureList.addClassNames("space-y-4", "mb-10", "flex-grow");

        for (String fKey : plan.featureKeys()) {
            ListItem li = new ListItem();
            li.addClassNames("flex", "items-center", "text-sm", "text-slate-600");
            Span check = new Span("✓");
            check.addClassNames("pricing-check");
            check.getElement().setAttribute("aria-hidden", "true");
            li.add(check, new Span(getTranslation(fKey)));
            featureList.add(li);
        }

        Anchor cta = new Anchor("https://www.gladtek.com", getTranslation("pricing.plan.cta"));
        cta.setTarget("_blank");
        cta.addClassNames("pricing-cta");
        if (plan.popular()) {
            cta.addClassNames("pricing-cta-popular");
        }

        add(h, priceDiv, p, featureList, cta);
    }

    public void setYearly(boolean yearly) {
        amount.setText(yearly ? plan.yearlyPrice() : plan.monthlyPrice());
        period.setText(yearly ? getTranslation("pricing.period.yearly") : getTranslation("pricing.period.monthly"));
    }
}
