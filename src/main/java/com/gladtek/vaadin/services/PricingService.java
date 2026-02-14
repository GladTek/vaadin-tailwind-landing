package com.gladtek.vaadin.services;

import com.gladtek.vaadin.data.model.Plan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    public List<Plan> getPlans() {
        return List.of(
            new Plan("plan.starter.name", "€0", "€0", "plan.starter.desc", false, 
                "plan.feature.community_support", "plan.feature.1_project", "plan.feature.basic_analytics"),
            new Plan("plan.pro.name", "€29", "€290", "plan.pro.desc", true, 
                "plan.feature.priority_support", "plan.feature.unlimited_projects", "plan.feature.advanced_analytics", "plan.feature.custom_domains"),
            new Plan("plan.enterprise.name", "€99", "€990", "plan.enterprise.desc", false, 
                "plan.feature.dedicated_support_24_7", "plan.feature.unlimited_everything", "plan.feature.enterprise_api", "plan.feature.custom_slas")
        );
    }
}
