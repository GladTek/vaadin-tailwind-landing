package com.gladtek.vaadin.i18n;

import com.vaadin.flow.i18n.I18NProvider;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

@Component
public class CustomI18NProvider implements I18NProvider {

    private final Map<String, ResourceBundle> bundles = new HashMap<>();

    public CustomI18NProvider() {
        bundles.put("en", ResourceBundle.getBundle("messages", Locale.ENGLISH));
        bundles.put("fr", ResourceBundle.getBundle("messages", Locale.FRENCH));
        bundles.put("ar", ResourceBundle.getBundle("messages", Locale.forLanguageTag("ar")));
        bundles.put("ja", ResourceBundle.getBundle("messages", Locale.JAPANESE));
    }

    @Override
    public List<Locale> getProvidedLocales() {
        return List.of(Locale.ENGLISH, Locale.forLanguageTag("ar"), Locale.JAPANESE, Locale.FRENCH);
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params) {
        ResourceBundle bundle = bundles.get(locale.getLanguage());
        if (bundle == null) {
            bundle = bundles.get("en");
        }

        if (!bundle.containsKey(key)) {
            return "!" + key;
        }

        String value = bundle.getString(key);
        if (params.length > 0) {
            return MessageFormat.format(value, params);
        }
        return value;
    }
}
