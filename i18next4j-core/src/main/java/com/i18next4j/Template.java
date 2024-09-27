package com.i18next4j;


import com.i18next4j.tokenizer.token.Token;

import java.util.Map;

public class Template {

    final Token[] tokens;
    final I18Next i18Next;

    public Template(Token[] tokens, I18Next i18Next) {
        this.tokens = tokens;
        this.i18Next = i18Next;
    }

    public String render(Map<String, String> options) {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            switch (token.type()) {
                case TEXT -> sb.append(token.value());
                case PARAMETER -> sb.append(options.get(token.value()));
                case NESTED -> sb.append(i18Next.t(token.value(), options));
            }
        }
        return sb.toString();
    }
}
