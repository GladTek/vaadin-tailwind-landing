package com.gladtek.vaadin.components.section;

import com.gladtek.vaadin.data.model.Stat;
import com.vaadin.flow.component.html.*;
import java.util.List;

public class Stats extends Section {

    public Stats() {
        addClassNames("stats-section");

        Div container = new Div();
        container.addClassNames("section-container");

        Div grid = new Div();
        grid.addClassNames("grid", "grid-cols-2", "md:grid-cols-4", "gap-8", "text-center");

        List<Stat> stats = List.of(
            new Stat(getTranslation("stat.devs.value"), getTranslation("stat.devs.label")),
            new Stat(getTranslation("stat.uptime.value"), getTranslation("stat.uptime.label")),
            new Stat(getTranslation("stat.revenue.value"), getTranslation("stat.revenue.label")),
            new Stat(getTranslation("stat.support.value"), getTranslation("stat.support.label"))
        );

        stats.forEach(s -> grid.add(createStatItem(s)));

        container.add(grid);
        add(container);

        // Subtle background decoration
        Div decoration = new Div();
        decoration.addClassNames("absolute", "top-0", "left-0", "w-full", "h-full", "opacity-10", "pointer-events-none");
        decoration.getStyle().set("background-image", "radial-gradient(circle at 50% 50%, #4f46e5 0%, transparent 50%)");
        decoration.getElement().setAttribute("aria-hidden", "true");
        add(decoration);
    }

    private Div createStatItem(Stat stat) {
        Div item = new Div();
        item.addClassNames("stat-item");
        
        Div valDiv = new Div(new Span(stat.value()));
        valDiv.addClassNames("stat-value");
        
        Div labelDiv = new Div(new Span(stat.label()));
        labelDiv.addClassNames("stat-label");
        
        item.add(valDiv, labelDiv);
        return item;
    }
}
