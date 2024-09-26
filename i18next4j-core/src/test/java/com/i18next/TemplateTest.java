package com.i18next;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.i18next.Token.Type.PARAMETER;
import static com.i18next.Token.Type.TEXT;

public class TemplateTest {

    @Test
    public void renderParameter() throws BackendException {
        Token token = new Token("param", PARAMETER);
        Template template = new Template(new Token[]{token});
        String result = template.render(Map.of("param", "value"));
        Assertions.assertEquals("value", result);
    }

    @Test
    public void renderText() throws BackendException {
        Token token = new Token("value", TEXT);
        Template template = new Template(new Token[]{token});
        String result = template.render(Map.of());
        Assertions.assertEquals("value", result);
    }
}
