package com.gladtek.vaadin.components.section;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class CTA extends Section {

    public CTA() {
        addClassNames("cta-section");

        Div container = new Div();
        container.addClassNames("section-container");

        Div box = new Div();
        box.addClassNames("cta-box");

        H2 title = new H2(getTranslation("cta.title"));
        title.addClassNames("text-3xl", "font-bold", "tracking-tight", "text-white", "sm:text-4xl");

        Paragraph desc = new Paragraph(getTranslation("cta.description"));
        desc.addClassNames("mx-auto", "mt-6", "max-w-xl", "text-lg", "leading-8", "text-slate-300");

        // Form wrapper container
        Div formWrapper = new Div();
        formWrapper.addClassNames("mt-10", "max-w-md", "mx-auto");

        Div formRow = new Div();
        formRow.addClassNames("flex", "flex-col", "sm:flex-row", "items-center", "justify-center", "gap-4");

        EmailField email = new EmailField();
        email.setPlaceholder(getTranslation("cta.placeholder"));
        email.addClassNames("w-full", "sm:w-64", "bg-white/10", "border-white/20", "text-white", "placeholder-white/50", "rounded-xl", "px-4", "py-3");
        email.getElement().setAttribute("aria-label", "Email address for subscription");
        email.setValueChangeMode(ValueChangeMode.EAGER);

        Span errorLabel = new Span();
        errorLabel.addClassNames("text-rose-400", "text-sm", "mt-2", "hidden");

        Button submit = new Button(getTranslation("cta.notify"));
        submit.addClassNames("btn-primary", "w-full", "sm:w-auto", "h-12");

        // Success View (hidden initially)
        Div successView = new Div();
        successView.addClassNames("hidden", "glass-panel", "bg-white/10", "border-white/10", "p-8", "rounded-2xl", "text-center", "animate-slideup");
        
        Div checkIcon = new Div();
        checkIcon.addClassNames("w-12", "h-12", "rounded-full", "bg-emerald-500/20", "text-emerald-400", "flex", "items-center", "justify-center", "mx-auto", "mb-4");
        checkIcon.getElement().setProperty("innerHTML", "<svg class=\"w-6 h-6\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" viewBox=\"0 0 24 24\"><path stroke-linecap=\"round\" stroke-linejoin=\"round\" d=\"M5 13l4 4L19 7\"></path></svg>");
        
        H3 successTitle = new H3("Thank You!");
        successTitle.addClassNames("text-white", "text-xl", "font-bold");
        Paragraph successMsg = new Paragraph(getTranslation("cta.success"));
        successMsg.addClassNames("text-slate-300", "mt-2");
        
        successView.add(checkIcon, successTitle, successMsg);

        submit.addClickListener(e -> {
            boolean isInvalid = email.isInvalid() || email.getValue().isEmpty() || !email.getValue().contains("@");
            if (isInvalid) {
                errorLabel.setText("Please enter a valid email address.");
                errorLabel.removeClassName("hidden");
            } else {
                errorLabel.addClassName("hidden");
                // Switch forms
                formRow.addClassName("hidden");
                errorLabel.addClassName("hidden");
                successView.removeClassName("hidden");
            }
        });

        // Hide error message when user starts typing again
        email.addValueChangeListener(e -> {
            if (!errorLabel.getClassNames().contains("hidden")) {
                errorLabel.addClassName("hidden");
            }
        });

        formRow.add(email, submit);
        formWrapper.add(formRow, errorLabel, successView);

        box.add(title, desc, formWrapper);
        
        // Background decoration
        Div decor = new Div();
        decor.addClassNames("absolute", "-top-24", "left-1/2", "-z-10", "h-[64rem]", "w-[64rem]", "-translate-x-1/2", "opacity-20");
        decor.getStyle().set("background-image", "radial-gradient(50% 50% at 50% 50%, #4f46e5 0%, transparent 100%)");
        box.add(decor);

        container.add(box);
        add(container);
    }
}
