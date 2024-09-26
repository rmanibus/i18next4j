package com.i18next4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.i18next4j.Token.Type.PARAMETER;
import static com.i18next4j.Token.Type.TEXT;

public class TokenizerTest {

    @Test
    public void extractParameterToken() throws BackendException {
        String template = "{{value}}";
        Tokenizer tokenizer = Tokenizer.create();
        Token[] tokens = tokenizer.tokenize(template);
        Assertions.assertEquals(1, tokens.length);
        Assertions.assertEquals("value", tokens[0].value());
        Assertions.assertEquals(PARAMETER, tokens[0].type());
    }

    @Test
    public void extractTextToken() throws BackendException {
        String template = "value";
        Tokenizer tokenizer = Tokenizer.create();
        Token[] tokens = tokenizer.tokenize(template);
        Assertions.assertEquals(1, tokens.length);
        Assertions.assertEquals("value", tokens[0].value());
        Assertions.assertEquals(TEXT, tokens[0].type());
    }

    @Test
    public void extractMultipleParameterTokens() throws BackendException {
        String template = "{{aa}} replace this {{value}} by this {{other}} whatever";
        Tokenizer tokenizer = Tokenizer.create();
        Token[] tokens = tokenizer.tokenize(template);
        Assertions.assertEquals(6, tokens.length);
    }

    @Test
    public void startTokenWithoutEndToken() throws BackendException {
        String template = "start token {{ without end token";
        Tokenizer tokenizer = Tokenizer.create();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize(template);
        });
    }

    @Test
    public void startTokenAtTheEnd() throws BackendException {
        String template = "start token {{";
        Tokenizer tokenizer = Tokenizer.create();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize(template);
        });
    }

    @Test
    public void endTokenWithoutStartToken() throws BackendException {
        String template = "end token }} without start token";
        Tokenizer tokenizer = Tokenizer.create();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tokenizer.tokenize(template);
        });
    }

    @Test
    public void extractMixedTokens() throws BackendException {
        String template = "replace this {{value}} by $t(test) this {{other}} whatever";
        Tokenizer tokenizer = Tokenizer.create();
        Token[] tokens = tokenizer.tokenize(template);
        System.out.println(Arrays.toString(tokens));
        Assertions.assertEquals(7, tokens.length);
    }
}
