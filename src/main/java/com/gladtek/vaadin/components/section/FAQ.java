package com.gladtek.vaadin.components.section;

import com.gladtek.vaadin.components.common.SectionHeader;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class FAQ extends Section {

    public FAQ() {
        addClassNames("faq-section");
        setId("faq");

        Div container = new Div();
        container.addClassNames("faq-container");

        SectionHeader header = new SectionHeader(getTranslation("faq.title"), getTranslation("faq.description"));
        header.setTitleId("faq-title");
        getElement().setAttribute("aria-labelledby", "faq-title");

        Div list = new Div();
        list.addClassNames("mt-12", "divide-y", "divide-slate-200");

        list.add(createAccordionItem("faq.q1.question", "faq.q1.answer"));
        list.add(createAccordionItem("faq.q2.question", "faq.q2.answer"));
        list.add(createAccordionItem("faq.q3.question", "faq.q3.answer"));
        list.add(createAccordionItem("faq.q4.question", "faq.q4.answer"));

        container.add(header, list);
        add(container);
    }

    private Div createAccordionItem(String questionKey, String answerKey) {
        Div item = new Div();
        item.addClassNames("faq-item");

        Div button = new Div();
        button.addClassNames("faq-question-btn", "bg-transparent", "border-none", "cursor-pointer");
        button.getElement().setAttribute("role", "button");
        button.getElement().setAttribute("tabindex", "0");
        
        Span qText = new Span(getTranslation(questionKey));
        Icon icon = VaadinIcon.CHEVRON_DOWN.create();
        icon.addClassNames("faq-icon", "w-5", "h-5");
        
        button.add(qText, icon);

        Paragraph answer = new Paragraph(getTranslation(answerKey));
        answer.addClassNames("faq-answer");

        button.addClickListener(e -> {
            boolean active = item.getClassNames().contains("active");
            if (active) {
                item.removeClassName("active");
                answer.removeClassName("expanded");
            } else {
                item.addClassName("active");
                answer.addClassName("expanded");
            }
        });

        item.add(button, answer);
        return item;
    }
}
