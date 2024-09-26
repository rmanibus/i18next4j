package com.i18next;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.i18next.Token.Type.PARAMETER;
import static com.i18next.Token.Type.TEXT;

public class TokenizerTest {

    @Test
    public void extractParameterToken() throws BackendException {
        String template = "{{value}}";
        Tokenizer tokenizer = new Tokenizer(I18NextOptions.InterpolationOptions.defaultInstance());
        Token[] tokens = tokenizer.tokenize(template);
        Assertions.assertEquals(1, tokens.length);
        Assertions.assertEquals("value", tokens[0].value());
        Assertions.assertEquals(PARAMETER, tokens[0].type());
    }

    @Test
    public void extractTextToken() throws BackendException {
        String template = "value";
        Tokenizer tokenizer = new Tokenizer(I18NextOptions.InterpolationOptions.defaultInstance());
        Token[] tokens = tokenizer.tokenize(template);
        Assertions.assertEquals(1, tokens.length);
        Assertions.assertEquals("value", tokens[0].value());
        Assertions.assertEquals(TEXT, tokens[0].type());
    }

    @Test
    public void extractMultipleTokens() throws BackendException {
        String template = "{{aa}} replace this {{value}} by this {{other}} whatever";
        Tokenizer tokenizer = new Tokenizer(I18NextOptions.InterpolationOptions.defaultInstance());
        Token[] tokens = tokenizer.tokenize(template);
        Assertions.assertEquals(6, tokens.length);
    }

    @Test
    public void startTokenWithoutEndToken() throws BackendException {
        String template = "start token {{ without end token";
        Tokenizer tokenizer = new Tokenizer(I18NextOptions.InterpolationOptions.defaultInstance());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize(template);
        });
    }

    @Test
    public void endTokenWithoutStartToken() throws BackendException {
        String template = "end token }} without start token";
        Tokenizer tokenizer = new Tokenizer(I18NextOptions.InterpolationOptions.defaultInstance());
        Token[] tokens = tokenizer.tokenize(template);
        System.out.println(Arrays.toString(tokens));
    }
}
