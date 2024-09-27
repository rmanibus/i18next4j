package com.i18next4j;

import com.i18next4j.tokenizer.token.Token;
import net.xyzsd.plurals.PluralRule;
import net.xyzsd.plurals.PluralRuleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

import static com.i18next4j.tokenizer.token.Token.Type.PARAMETER;
import static com.i18next4j.tokenizer.token.Token.Type.TEXT;

public class TemplateTest {

    @Test
    public void renderParameter() throws BackendException {
        Token token = new Token("param", PARAMETER);
        Template template = new Template(new Token[]{token}, null);
        String result = template.render(Map.of("param", "value"));
        Assertions.assertEquals("value", result);
    }

    @Test
    public void renderText() throws BackendException {
        Token token = new Token("value", TEXT);
        Template template = new Template(new Token[]{token}, null);
        String result = template.render(Map.of());
        Assertions.assertEquals("value", result);
    }

    @Test
    public void aaa() throws BackendException {
        Locale locale = new Locale("en", "US");
        PluralRule rule = PluralRule.createOrDefault(locale, PluralRuleType.ORDINAL);
        System.out.println(rule.select(0));
        System.out.println(rule.select(1));
        System.out.println(rule.select(2));
        System.out.println(rule.select(21));

    }
}
