package com.i18next4j;

import java.util.LinkedList;

public class TextVisitor implements Visitor {

    private static TextVisitor TEXT_VISITOR = new TextVisitor();

    public static TextVisitor getInstance() {
        return TEXT_VISITOR;
    }

    private TextVisitor() {

    }

    @Override
    public Token visit(LinkedList<String> splitList) {
        return new Token(splitList.pollFirst(), Token.Type.TEXT);
    }
}
