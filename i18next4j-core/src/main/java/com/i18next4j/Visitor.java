package com.i18next4j;

import java.util.LinkedList;

public interface Visitor {
    Token visit(LinkedList<String> splitList);
}
