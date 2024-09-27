package com.i18next4j.tokenizer.token;

import java.util.LinkedList;

public interface TokenBuilder {
    Token visit(LinkedList<String> splitList);
}
