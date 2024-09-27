package com.i18next4j.tokenizer.token;

import java.util.LinkedList;

public class TextTokenBuilder implements TokenBuilder {

    private static TextTokenBuilder TEXT_VISITOR = new TextTokenBuilder();

    public static TextTokenBuilder getInstance() {
        return TEXT_VISITOR;
    }

    private TextTokenBuilder() {

    }

    @Override
    public Token visit(LinkedList<String> splitList) {
        return new Token(splitList.pollFirst(), Token.Type.TEXT);
    }
}
