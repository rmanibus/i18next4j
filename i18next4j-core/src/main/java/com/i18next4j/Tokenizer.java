package com.i18next4j;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    final I18NextOptions.InterpolationOptions options;

    Tokenizer(I18NextOptions.InterpolationOptions options) {
        this.options = options;
    }

    final static String[] DELIMITERS = new String[]{"{{", "}}", "$t(", ")"};


    public Token[] tokenize(String template) {

        List<Token> fragments = new ArrayList<>();

        String tmp = template;

        while (true) {
            int start = tmp.indexOf(options.getPrefix());
            if (start == -1) {
                break;
            }
            int end = tmp.indexOf(options.getSuffix());
            if (end == -1) {
                throw new IllegalArgumentException("No end token found for start token");
            }
            if (start != 0) {
                fragments.add(new Token(tmp.substring(0, start), Token.Type.TEXT));
            }
            fragments.add(new Token(tmp.substring(start + options.getPrefix().length(), end), Token.Type.PARAMETER));
            tmp = tmp.substring(end + options.getSuffix().length());
        }
        if (tmp.contains(options.getPrefix())) {
            throw new IllegalArgumentException("No start token found for end token");
        }
        if (!tmp.isEmpty()) {
            fragments.add(new Token(tmp, Token.Type.TEXT));
        }
        return fragments.toArray(new Token[0]);
    }
}
