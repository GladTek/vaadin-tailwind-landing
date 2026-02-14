package com.gladtek.vaadin.components.layout;

import com.gladtek.vaadin.data.model.Theme;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import java.util.List;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.icon.Icon;

@JsModule("./theme.js")
public class ThemeSwitcher extends Div {

    private final List<Theme> themes = List.of(
        new Theme("Sky", "#0284c7", ""),
        new Theme("Emerald", "#059669", "theme-emerald"),
        new Theme("Amber", "#d97706", "theme-amber"),
        new Theme("Rose", "#e11d48", "theme-rose")
    );

    private final Div swatchesContainer = new Div();
    private boolean expanded = false;

    public ThemeSwitcher() {
        addClassNames("theme-switcher-fab");
        
        // Toggle Button
        Div toggle = new Div();
        toggle.addClassNames("theme-switcher-toggle");
        Icon paletteIcon = VaadinIcon.PAINT_ROLL.create();
        paletteIcon.setSize("18px");
        toggle.add(paletteIcon);
        
        // Swatches Container
        swatchesContainer.addClassNames("swatches-container");
        themes.forEach(this::createSwatch);

        toggle.addClickListener(e -> toggleExpansion());
        
        add(toggle, swatchesContainer);
        
        // Load saved theme on client side using the JS module
        getElement().executeJs("window.ThemeUtils.initTheme()");
    }

    private void toggleExpansion() {
        expanded = !expanded;
        if (expanded) {
            addClassName("expanded");
        } else {
            removeClassName("expanded");
        }
    }

    private void createSwatch(Theme theme) {
        Div swatch = new Div();
        swatch.addClassNames("theme-swatch");
        swatch.getStyle().set("background-color", theme.primaryColor());
        swatch.getElement().setAttribute("title", theme.name());
        
        swatch.addClickListener(e -> {
            applyTheme(theme);
            toggleExpansion(); // Close after selection
        });
        
        swatchesContainer.add(swatch);
    }

    private void applyTheme(Theme theme) {
        getElement().executeJs("window.ThemeUtils.applyTheme($0)", theme.className());
    }
}
