package com.gladtek.vaadin.data.model;

public record Plan(String nameKey, String monthlyPrice, String yearlyPrice, String descriptionKey, boolean popular, String... featureKeys) {}
