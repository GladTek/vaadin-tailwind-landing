package com.gladtek.vaadin.components.common;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;

public class SectionHeader extends Div {

    private final H2 titleElement;

    public SectionHeader(String title, String description) {
        this(null, title, description, true);
    }

    public SectionHeader(String badge, String title, String description, boolean centered) {
        addClassNames("mb-16");
        if (centered) {
            addClassNames("text-center", "max-w-3xl", "mx-auto");
        } else {
            addClassNames("text-left", "max-w-2xl");
        }

        if (badge != null && !badge.isEmpty()) {
            Span badgeSpan = new Span(badge);
            badgeSpan.addClassNames("inline-flex", "items-center", "gap-x-1.5", "py-1", "px-3", 
                                   "rounded-full", "text-sm", "font-medium", "bg-sky-100", 
                                   "text-sky-700", "border", "border-sky-200", "mb-6");
            add(badgeSpan);
        }

        titleElement = new H2(title);
        titleElement.addClassNames("text-3xl", "md:text-4xl", "font-bold", "text-slate-900", "mb-4");
        
        Paragraph p = new Paragraph(description);
        p.addClassNames("text-lg", "text-slate-600");
        
        add(titleElement, p);
    }

    public void setTitleId(String id) {
        titleElement.setId(id);
    }
}
