package com.gladtek.vaadin.components.layout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.server.VaadinService;

import java.util.Locale;

public class LanguageSwitcher extends Div {

    public LanguageSwitcher() {
        addClassNames("flex", "items-center");

        Select<Locale> select = new Select<>();
        select.addClassNames("language-select");
        
        I18NProvider i18nProvider = VaadinService.getCurrent().getInstantiator().getI18NProvider();
        select.setItems(i18nProvider.getProvidedLocales());
        
        Locale currentLocale = UI.getCurrent().getLocale();
        select.setValue(currentLocale);

        select.setRenderer(new ComponentRenderer<>(locale -> {
            Div container = new Div();
            container.addClassNames("flex", "items-center", "gap-2");

            Image flag = new Image(getFlagUrl(locale), locale.getDisplayLanguage());
            flag.addClassNames("w-8", "h-4", "rounded-sm", "shadow-xs");
            
            Span label = new Span(locale.getLanguage().toUpperCase());
            label.addClassNames("text-sm", "font-medium");
            
            container.add(flag, label);
            return container;
        }));

        select.addValueChangeListener(e -> {
            Locale nextLocale = e.getValue();
            UI.getCurrent().getSession().setLocale(nextLocale);
            
            // Handle RTL for Arabic
            if (nextLocale.getLanguage().equals("ar")) {
                UI.getCurrent().setDirection(com.vaadin.flow.component.Direction.RIGHT_TO_LEFT);
            } else {
                UI.getCurrent().setDirection(com.vaadin.flow.component.Direction.LEFT_TO_RIGHT);
            }
            
            UI.getCurrent().getPage().reload();
        });

        add(select);
    }

    private String getFlagUrl(Locale locale) {
        return switch (locale.getLanguage()) {
            case "fr" -> "https://flagcdn.com/w40/fr.png";
            case "ar" -> "https://flagcdn.com/w40/tn.png";
            case "ja" -> "https://flagcdn.com/w40/jp.png";
            default -> "https://flagcdn.com/w40/gb.png";
        };
    }
}
