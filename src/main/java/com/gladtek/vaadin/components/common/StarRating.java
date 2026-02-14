package com.gladtek.vaadin.components.common;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class StarRating extends Div {

    public StarRating(int rating) {
        this(rating, 5);
    }

    public StarRating(int rating, int max) {
        addClassNames("flex", "gap-1", "mb-4", "text-amber-400");
        getElement().setAttribute("aria-label", "Rated " + rating + " out of " + max + " stars");
        
        for (int i = 0; i < max; i++) {
            Icon star = (i < rating) ? VaadinIcon.STAR.create() : VaadinIcon.STAR_O.create();
            star.addClassNames("w-4", "h-4");
            star.getElement().setAttribute("aria-hidden", "true");
            add(star);
        }
    }
}
