package com.i18next4j;

import com.i18next4j.tokenizer.Tokenizer;
import net.xyzsd.plurals.PluralCategory;
import net.xyzsd.plurals.PluralRule;
import net.xyzsd.plurals.PluralRuleType;
import net.xyzsd.plurals.PluralRules;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class I18NextEngine implements I18Next {

    private final I18NextOptions i18NextOptions;

    private final Map<String, Map<String, Map<String, Template>>> resources = new HashMap<>();

    private final Tokenizer tokenizer;

    private final PluralResolver pluralResolver = new PluralResolver();

    private I18NextEngine(I18NextOptions i18NextOptions) {
        this.i18NextOptions = i18NextOptions;
        this.tokenizer = Tokenizer.create(i18NextOptions.getInterpolationOptions());
    }


    @Override
    public String t(String key, Map<String, String> options) {
        String language = i18NextOptions.getLanguageDetector().detect();
        String namespace = getNamespace(key, options);
        return resources.computeIfAbsent(language, l -> new HashMap<>())
                .computeIfAbsent(namespace, n -> loadNamespaceResources(language, namespace))
                .get(getKey(key, language, options))
                .render(options);

    }

    private Map<String, Template> loadNamespaceResources(String language, String namespace) {
        Map<String, Template> templates = new HashMap<>();
        i18NextOptions.getBackend().read(language, namespace).forEach((key, value) -> {
            templates.put(key, new Template(tokenizer.tokenize(value), this));
        });
        return templates;
    }

    private String getKey(String originalKey, String language, Map<String, String> options) {
        if (!options.containsKey("count")) {
            return originalKey;
        }
        return pluralResolver.getPluralKey(originalKey, language, Long.parseLong(options.get("count")));
    }

    private String getNamespace(String key, Map<String, String> options) {

        if (key.contains(":")) {
            String[] parts = key.split(":");
            return parts[0];
        }

        if (options.containsKey("ns")) {
            return options.get("ns");
        }

        return i18NextOptions.getDefaultNs();
    }

    static class Builder {

        I18Next init(I18NextOptions options) {
            return new I18NextEngine(options);
        }
    }
}
