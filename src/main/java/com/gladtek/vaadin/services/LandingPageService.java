package com.gladtek.vaadin.services;

import org.springframework.stereotype.Service;

@Service
public class LandingPageService {

    private final FeatureService featureService;
    private final PricingService pricingService;
    private final TestimonialService testimonialService;

    public LandingPageService(FeatureService featureService, 
                              PricingService pricingService, 
                              TestimonialService testimonialService) {
        this.featureService = featureService;
        this.pricingService = pricingService;
        this.testimonialService = testimonialService;
    }

    public FeatureService getFeatureService() {
        return featureService;
    }

    public PricingService getPricingService() {
        return pricingService;
    }

    public TestimonialService getTestimonialService() {
        return testimonialService;
    }
}
