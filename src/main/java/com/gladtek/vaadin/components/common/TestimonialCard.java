package com.gladtek.vaadin.components.common;

import com.gladtek.vaadin.data.model.Testimonial;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;

public class TestimonialCard extends Div {

    public TestimonialCard(Testimonial testimonial) {
        addClassNames("testimonial-card");

        add(new StarRating(5));

        Paragraph p = new Paragraph("\"" + getTranslation(testimonial.quoteKey()) + "\"");
        add(p);

        Div author = new Div();
        author.addClassNames("testimonial-author");

        Image avatar = new Image(testimonial.avatarUrl(), getTranslation(testimonial.authorKey()));
        avatar.addClassNames("testimonial-avatar");

        Div info = new Div();
        info.addClassNames("flex", "flex-col");

        Span nameSpan = new Span(getTranslation(testimonial.authorKey()));
        nameSpan.addClassNames("testimonial-author-name");

        Span roleSpan = new Span(getTranslation(testimonial.roleKey()));
        roleSpan.addClassNames("testimonial-author-role");

        info.add(nameSpan, roleSpan);
        author.add(avatar, info);
        add(author);
    }
}
