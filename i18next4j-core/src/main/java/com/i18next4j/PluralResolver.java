package com.i18next4j;


import net.xyzsd.plurals.PluralCategory;
import net.xyzsd.plurals.PluralRule;
import net.xyzsd.plurals.PluralRuleType;

import java.util.Locale;

public class PluralResolver {

    public String getPluralKey(String originalKey, String language, long count) {
        PluralRule rule = PluralRule.createOrDefault(new Locale(language), PluralRuleType.CARDINAL);
        PluralCategory category = rule.select(count);
        return originalKey + "_" + category.name().toLowerCase();
    }
}
