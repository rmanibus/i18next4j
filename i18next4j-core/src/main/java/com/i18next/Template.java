package com.i18next;


import java.util.Map;

public class Template {

    final Token[] tokens;

    public Template(Token[] tokens) {
        this.tokens = tokens;
    }

    public String render(Map<String, String> options) {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            if (token.type() == Token.Type.TEXT) {
                sb.append(token.value());
            } else {
                sb.append(options.get(token.value()));
            }
        }
        return sb.toString();
    }
}
