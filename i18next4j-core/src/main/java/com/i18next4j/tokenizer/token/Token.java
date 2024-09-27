package com.i18next4j.tokenizer.token;

public record Token(String value, Type type) {

    public enum Type {
        TEXT,
        NESTED,
        PARAMETER
    }
}
