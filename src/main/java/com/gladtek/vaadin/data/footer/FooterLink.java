package com.gladtek.vaadin.data.footer;

public record FooterLink(String labelKey, String url) {
    public FooterLink(String labelKey) {
        this(labelKey, "#");
    }
}
