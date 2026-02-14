package com.gladtek.vaadin.components.common;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.shared.Registration;

public class PricingToggle extends Div {

    private boolean yearly = false;
    private final Div toggleBg;
    private final Div circle;
    private final Span monthlyLabel;
    private final Span yearlyLabel;

    public PricingToggle() {
        addClassNames("flex", "items-center", "justify-center", "gap-4", "mb-12");

        monthlyLabel = new Span(getTranslation("pricing.toggle.monthly"));
        monthlyLabel.addClassNames("text-sm", "font-semibold", "transition-colors");

        toggleBg = new Div();
        toggleBg.addClassNames("relative", "w-12", "h-6", "bg-slate-200", "rounded-full", "cursor-pointer", "transition-colors");
        toggleBg.addClickListener(e -> {
            yearly = !yearly;
            updateUI();
            fireEvent(new ValueChangeEvent(this, yearly));
        });

        circle = new Div();
        circle.addClassNames("absolute", "top-1", "start-1", "w-4", "h-4", "bg-white", "rounded-full", "shadow-sm", "transition-transform", "duration-200");
        toggleBg.add(circle);

        yearlyLabel = new Span(getTranslation("pricing.toggle.yearly"));
        yearlyLabel.addClassNames("text-sm", "font-semibold", "transition-colors");

        Span discount = new Span(getTranslation("pricing.toggle.discount"));
        discount.addClassNames("ms-2", "py-0.5", "px-2", "rounded-full", "bg-emerald-100", "text-emerald-700", "text-[10px]", "font-bold", "uppercase");
        
        Div yearlyGroup = new Div(yearlyLabel, discount);
        yearlyGroup.addClassNames("flex", "items-center");

        add(monthlyLabel, toggleBg, yearlyGroup);
        updateUI();
    }

    private void updateUI() {
        boolean isRtl = getLocale().getLanguage().equals("ar");
        
        if (yearly) {
            toggleBg.addClassNames("bg-sky-500");
            toggleBg.removeClassNames("bg-slate-200");
            circle.getStyle().set("transform", isRtl ? "translateX(-24px)" : "translateX(24px)");
        } else {
            toggleBg.addClassNames("bg-slate-200");
            toggleBg.removeClassNames("bg-sky-500");
            circle.getStyle().set("transform", "translateX(0)");
        }
        updateLabels();
    }

    private void updateLabels() {
        if (yearly) {
            yearlyLabel.addClassNames("text-slate-900");
            yearlyLabel.removeClassNames("text-slate-400");
            monthlyLabel.addClassNames("text-slate-400");
            monthlyLabel.removeClassNames("text-slate-900");
        } else {
            monthlyLabel.addClassNames("text-slate-900");
            monthlyLabel.removeClassNames("text-slate-400");
            yearlyLabel.addClassNames("text-slate-400");
            yearlyLabel.removeClassNames("text-slate-900");
        }
    }

    public boolean isYearly() {
        return yearly;
    }

    public static class ValueChangeEvent extends ComponentEvent<PricingToggle> {
        private final boolean yearly;
        public ValueChangeEvent(PricingToggle source, boolean yearly) {
            super(source, false);
            this.yearly = yearly;
        }
        public boolean isYearly() {
            return yearly;
        }
    }

    public Registration addValueChangeListener(ComponentEventListener<ValueChangeEvent> listener) {
        return addListener(ValueChangeEvent.class, listener);
    }
}
