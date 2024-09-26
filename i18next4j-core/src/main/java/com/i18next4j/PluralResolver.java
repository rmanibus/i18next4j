package com.i18next4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.i18next4j.PluralResolver.LanguageSet.create;

public class PluralResolver {

    static class Rule {
        final int numbers;
        final int plurals;

        Rule(int numbers, int plurals) {
            this.numbers = numbers;
            this.plurals = plurals;
        }
    }

    static class LanguageSet {
        final String[] lngs;
        final int[] nr;
        final int fc;

        LanguageSet(String[] lngs, int[] nr, int fc) {
            this.lngs = lngs;
            this.nr = nr;
            this.fc = fc;
        }

        static LanguageSet create(String[] lngs, int[] nr, int fc) {
            return new LanguageSet(lngs, nr, fc);
        }
    }

    final static Set<LanguageSet> sets = Set.of(
            create(new String[]{"ach", "ak", "am", "arn", "br", "fil", "gun", "ln", "mfe", "mg", "mi", "oc", "pt", "pt-BR", "tg", "ti", "tr", "uz", "wa"}, new int[]{1, 2}, 1),
            create(new String[]{"af", "an", "ast", "az", "bg", "bn", "ca", "da", "de", "dev", "el", "en", "eo", "es", "es-AR", "et", "eu", "fi", "fo", "fur", "fy", "gl", "gu", "ha", "hi", "hu", "hy", "ia", "it", "kn", "ku", "lb", "mai", "ml", "mn", "mr", "nah", "nap", "nb", "ne", "nl", "nn", "no", "nso", "pa", "pap", "pms", "ps", "pt-PT", "rm", "sco", "se", "si", "so", "son", "sq", "sv", "sw", "ta", "te", "tk", "ur", "yo"}, new int[]{1, 2}, 2),
            create(new String[]{"ay", "bo", "cgg", "fa", "id", "ja", "jbo", "ka", "kk", "km", "ko", "ky", "lo", "ms", "sah", "su", "th", "tt", "ug", "vi", "wo", "zh"}, new int[]{1}, 3),
            create(new String[]{"be", "bs", "dz", "hr", "ru", "sr", "uk"}, new int[]{1, 2, 5}, 4),
            create(new String[]{"ar"}, new int[]{0, 1, 2, 3, 11, 100}, 5),
            create(new String[]{"cs", "sk"}, new int[]{1, 2, 5}, 6),
            create(new String[]{"csb", "pl"}, new int[]{1, 2, 5}, 7),
            create(new String[]{"cy"}, new int[]{1, 2, 3, 8}, 8),
            create(new String[]{"fr"}, new int[]{1, 2}, 9),
            create(new String[]{"ga"}, new int[]{1, 2, 3, 7, 11}, 10),
            create(new String[]{"gd"}, new int[]{1, 2, 3, 20}, 11),
            create(new String[]{"is"}, new int[]{1, 2}, 12),
            create(new String[]{"jv"}, new int[]{0, 1}, 13),
            create(new String[]{"kw"}, new int[]{1, 2, 3, 4}, 14),
            create(new String[]{"lt"}, new int[]{1, 2, 10}, 15),
            create(new String[]{"lv"}, new int[]{1, 2, 0}, 16),
            create(new String[]{"mk"}, new int[]{1, 2}, 17),
            create(new String[]{"mnk"}, new int[]{0, 1, 2}, 18),
            create(new String[]{"mt"}, new int[]{1, 2, 11, 20}, 19),
            create(new String[]{"or"}, new int[]{2, 1}, 20),
            create(new String[]{"ro"}, new int[]{1, 2, 20}, 21),
            create(new String[]{"sl"}, new int[]{5, 1, 2, 3}, 22),
            create(new String[]{"he", "iw"}, new int[]{1, 2, 20, 21}, 23));

    final static Map<String, Rule> rules;

    static {
        Map<String, Rule> tmpRules = new HashMap<>();
        sets.forEach(set -> {
            for (String lng : set.lngs) {
                tmpRules.put(lng, new Rule(set.nr[0], set.nr[1]));
            }
        });
        rules = Map.copyOf(tmpRules);
    }
}
