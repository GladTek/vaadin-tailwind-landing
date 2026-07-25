package com.gladtek.vaadin.components.layout;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.server.VaadinService;

import java.util.List;
import java.util.Locale;

/**
 * Modal Dialog Language Switcher Component
 * Displays a button in the navbar that triggers an enterprise modal selection dialog.
 * Fully keyboard-accessible via Tab, Enter, and Space keys.
 */
public class LanguageSwitcher extends Div {

    public LanguageSwitcher() {
        addClassNames("flex", "items-center");

        Locale currentLocale = UI.getCurrent().getLocale();

        // Navbar Trigger Button
        Div triggerBtn = new Div();
        triggerBtn.getElement().setAttribute("tabindex", "0");
        triggerBtn.getElement().setAttribute("role", "button");
        triggerBtn.getElement().setAttribute("aria-haspopup", "dialog");
        triggerBtn.getElement().setAttribute("aria-label", "Language selector, current language: " + currentLocale.getDisplayLanguage());

        triggerBtn.addClassNames(
            "flex", "items-center", "gap-2", "px-3", "py-1.5", "rounded-xl",
            "bg-slate-100/80", "dark:bg-slate-800/80",
            "hover:bg-slate-200/80", "dark:hover:bg-slate-700/80",
            "border", "border-slate-200/80", "dark:border-slate-700/80",
            "cursor-pointer", "transition-all", "duration-200", "active:scale-95", "shadow-xs",
            "focus-visible:outline-2", "focus-visible:outline-sky-500"
        );

        Image flag = new Image(getFlagUrl(currentLocale), currentLocale.getDisplayLanguage());
        flag.addClassNames("w-6", "h-4", "rounded-sm", "shadow-xs", "object-cover");

        Span label = new Span(currentLocale.getLanguage().toUpperCase());
        label.addClassNames("text-xs", "font-bold", "text-slate-800", "dark:text-slate-100");

        Icon chevron = VaadinIcon.CHEVRON_DOWN.create();
        chevron.setSize("12px");
        chevron.addClassNames("text-slate-400", "dark:text-slate-400");

        triggerBtn.add(flag, label, chevron);
        triggerBtn.addClickListener(e -> openLanguageModal());
        triggerBtn.getElement().addEventListener("keydown", e -> openLanguageModal())
            .setFilter("event.key === 'Enter' || event.key === ' '");

        add(triggerBtn);
    }

    private void openLanguageModal() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(true);
        dialog.setCloseOnEsc(true);
        dialog.getElement().setAttribute("aria-label", "Select Language & Region");
        dialog.getElement().executeJs("setTimeout(() => { if (document.documentElement.classList.contains('dark')) { $0.classList.add('dark'); const overlay = document.querySelector('vaadin-dialog-overlay'); if (overlay) overlay.classList.add('dark'); } }, 10)", dialog.getElement());

        // Modal Box Wrapper
        Div modalContent = new Div();
        modalContent.addClassNames("language-modal-content");

        // Header
        Div header = new Div();
        header.addClassNames("flex", "items-center", "justify-between", "mb-6", "pb-4", "border-b", "border-slate-200", "dark:border-slate-800");

        Div titleGroup = new Div();
        H3 title = new H3("Select Language / Region");
        title.addClassNames("language-modal-title");
        Paragraph subtitle = new Paragraph("Choose your preferred display language");
        subtitle.addClassNames("language-modal-subtitle");
        titleGroup.add(title, subtitle);

        Button closeBtn = new Button(VaadinIcon.CLOSE.create());
        closeBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ICON);
        closeBtn.addClassNames("text-slate-400", "hover:text-slate-600", "dark:hover:text-slate-200");
        closeBtn.getElement().setAttribute("aria-label", "Close modal");
        closeBtn.addClickListener(e -> dialog.close());

        header.add(titleGroup, closeBtn);

        // Language Grid
        Div grid = new Div();
        grid.addClassNames("grid", "grid-cols-1", "sm:grid-cols-2", "gap-3");

        I18NProvider i18nProvider = VaadinService.getCurrent().getInstantiator().getI18NProvider();
        List<Locale> locales = i18nProvider.getProvidedLocales();
        Locale currentLocale = UI.getCurrent().getLocale();

        for (Locale locale : locales) {
            boolean isSelected = locale.getLanguage().equalsIgnoreCase(currentLocale.getLanguage());
            Div langCard = createLanguageCard(locale, isSelected, dialog);
            grid.add(langCard);
        }

        modalContent.add(header, grid);
        dialog.add(modalContent);
        dialog.open();
    }

    private Div createLanguageCard(Locale locale, boolean isSelected, Dialog dialog) {
        Div card = new Div();
        card.getElement().setAttribute("tabindex", "0");
        card.getElement().setAttribute("role", "button");
        card.getElement().setAttribute("aria-label", getLanguageNativeName(locale) + ", " + getRegionName(locale) + (isSelected ? " (selected)" : ""));
        card.addClassNames("language-modal-card");

        if (isSelected) {
            card.addClassNames("selected");
        }

        Div leftGroup = new Div();
        leftGroup.addClassNames("flex", "items-center", "gap-3");

        Image flag = new Image(getFlagUrl(locale), locale.getDisplayLanguage());
        flag.addClassNames("w-8", "h-5", "rounded-sm", "shadow-xs", "object-cover");

        Div textGroup = new Div();
        Span name = new Span(getLanguageNativeName(locale));
        name.addClassNames("language-modal-card-name");

        Span region = new Span(getRegionName(locale));
        region.addClassNames("language-modal-card-region");
        textGroup.add(name, region);

        leftGroup.add(flag, textGroup);

        if (isSelected) {
            Icon check = VaadinIcon.CHECK_CIRCLE.create();
            check.setSize("18px");
            check.addClassNames("text-sky-600", "dark:text-sky-400");
            card.add(leftGroup, check);
        } else {
            card.add(leftGroup);
        }

        card.addClickListener(e -> selectLocale(locale, dialog));
        card.getElement().addEventListener("keydown", e -> selectLocale(locale, dialog))
            .setFilter("event.key === 'Enter' || event.key === ' '");

        return card;
    }

    private void selectLocale(Locale locale, Dialog dialog) {
        UI.getCurrent().getSession().setLocale(locale);

        if (locale.getLanguage().equals("ar")) {
            UI.getCurrent().setDirection(com.vaadin.flow.component.Direction.RIGHT_TO_LEFT);
        } else {
            UI.getCurrent().setDirection(com.vaadin.flow.component.Direction.LEFT_TO_RIGHT);
        }

        dialog.close();
        UI.getCurrent().getPage().reload();
    }

    private String getLanguageNativeName(Locale locale) {
        return switch (locale.getLanguage()) {
            case "fr" -> "Français";
            case "ar" -> "العربية";
            case "ja" -> "日本語";
            default -> "English";
        };
    }

    private String getRegionName(Locale locale) {
        return switch (locale.getLanguage()) {
            case "fr" -> "France / Europe";
            case "ar" -> "Middle East & N. Africa";
            case "ja" -> "Japan / Asia Pacific";
            default -> "International";
        };
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
