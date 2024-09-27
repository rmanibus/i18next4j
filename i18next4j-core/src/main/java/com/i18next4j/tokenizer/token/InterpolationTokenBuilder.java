package com.i18next4j.tokenizer.token;

import java.util.LinkedList;
import java.util.Objects;

public class InterpolationTokenBuilder implements TokenBuilder {

    final String prefix;
    final String suffix;
    final Token.Type type;

    public InterpolationTokenBuilder(String prefix, String suffix, Token.Type type) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.type = type;
    }

    @Override
    public Token visit(LinkedList<String> splitList) {
        if (!Objects.equals(splitList.pollFirst(), prefix)) {
            throw new IllegalArgumentException("No start token found for end token");
        }
        Token token = new Token(splitList.pollFirst(), type);
        if (!Objects.equals(splitList.pollFirst(), suffix)) {
            throw new IllegalArgumentException("No end token found for start token");
        }
        return token;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
