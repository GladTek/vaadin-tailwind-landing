package com.gladtek.vaadin.services;

import com.gladtek.vaadin.data.model.Testimonial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    public List<Testimonial> getTestimonials() {
        return List.of(
            new Testimonial("testimonial.1.text", "testimonial.1.author", "testimonial.1.role", "https://api.dicebear.com/9.x/lorelei/svg?seed=Andrea"),
            new Testimonial("testimonial.2.text", "testimonial.2.author", "testimonial.2.role", "https://api.dicebear.com/9.x/lorelei/svg?seed=Avery"),
            new Testimonial("testimonial.3.text", "testimonial.3.author", "testimonial.3.role", "https://api.dicebear.com/9.x/lorelei/svg?seed=Ryker")
        );
    }
}
