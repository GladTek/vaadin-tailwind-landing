package com.gladtek.vaadin.components.section;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;

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

        Div form = new Div();
        form.addClassNames("mt-10", "flex", "flex-col", "sm:flex-row", "items-center", "justify-center", "gap-4", "max-w-md", "mx-auto");

        EmailField email = new EmailField();
        email.setPlaceholder(getTranslation("cta.placeholder"));
        email.addClassNames("w-full", "sm:w-64", "bg-white/10", "border-white/20", "text-white", "placeholder-white/50", "rounded-xl", "px-4", "py-3");
        email.getElement().setAttribute("aria-label", "Email address for subscription");

        Button submit = new Button(getTranslation("cta.notify"));
        submit.addClassNames("btn-primary", "w-full", "sm:w-auto", "h-12");
        submit.addClickListener(e -> {
            if (!email.isInvalid() && !email.getValue().isEmpty()) {
                Notification.show(getTranslation("cta.success"));
                email.clear();
            }
        });

        form.add(email, submit);

        box.add(title, desc, form);
        
        // Background decoration
        Div decor = new Div();
        decor.addClassNames("absolute", "-top-24", "left-1/2", "-z-10", "h-[64rem]", "w-[64rem]", "-translate-x-1/2", "opacity-20");
        decor.getStyle().set("background-image", "radial-gradient(50% 50% at 50% 50%, #4f46e5 0%, transparent 100%)");
        box.add(decor);

        container.add(box);
        add(container);
    }
}
