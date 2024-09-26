package com.i18next4j;

public record Token(String value, Type type) {

    enum Type {
        TEXT,
        NESTED,
        PARAMETER
    }
}
