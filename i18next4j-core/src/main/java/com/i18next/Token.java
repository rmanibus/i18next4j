package com.i18next;

public record Token(String value, Type type) {

    enum Type {
        TEXT,
        PARAMETER
    }
}
