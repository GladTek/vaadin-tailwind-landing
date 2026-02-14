package com.gladtek.vaadin.components.section;

import com.gladtek.vaadin.components.common.SectionHeader;
import com.gladtek.vaadin.components.common.TestimonialCard;
import com.gladtek.vaadin.data.model.Testimonial;
import com.vaadin.flow.component.html.*;
import com.gladtek.vaadin.services.TestimonialService;
import java.util.List;

public class Testimonials extends Section {
    public Testimonials(TestimonialService testimonialService) {
        addClassNames("testimonials-section");
        setId("testimonials");

        Div container = new Div();
        container.addClassNames("section-container");

        SectionHeader header = new SectionHeader(getTranslation("testimonials.title"), getTranslation("testimonials.description"));
        header.setTitleId("testimonials-title");
        getElement().setAttribute("aria-labelledby", "testimonials-title");

        Div grid = new Div();
        grid.addClassNames("grid-3");

        List<Testimonial> testimonials = testimonialService.getTestimonials();

        testimonials.stream()
                    .map(TestimonialCard::new)
                    .forEach(grid::add);

        container.add(header, grid);
        add(container);
    }
}
