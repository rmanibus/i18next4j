package com.i18next4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tokenizer {


    final List<String> delimiters;
    final Map<String, Visitor> visitors;

    public static Tokenizer create() {
        return create(I18NextOptions.InterpolationOptions.defaultInstance());
    }

    public static Tokenizer create(I18NextOptions.InterpolationOptions options) {
        final InterpolationVisitor paramVisitor = new InterpolationVisitor(options.getPrefix(), options.getSuffix(), Token.Type.PARAMETER);
        final InterpolationVisitor nestedVisitor = new InterpolationVisitor("$t(", ")", Token.Type.NESTED);
        return new Tokenizer(List.of(paramVisitor, nestedVisitor));
    }

    Tokenizer(Collection<InterpolationVisitor> visitors) {
        List<String> delimiters = new ArrayList<>();
        Map<String, Visitor> visitorsMap = new HashMap<>();

        for (InterpolationVisitor visitor : visitors) {
            delimiters.add(visitor.getPrefix());
            delimiters.add(visitor.getSuffix());
            visitorsMap.put(visitor.getPrefix(), visitor);
            visitorsMap.put(visitor.getSuffix(), visitor);
        }
        this.delimiters = List.copyOf(delimiters);
        this.visitors = Map.copyOf(visitorsMap);

    }


    public Token[] tokenize(String template) {

        List<Token> fragments = new ArrayList<>();

        Splitter splitter = new Splitter(delimiters);
        String[] split = splitter.split(template);
        LinkedList<String> splitList = new LinkedList<>(List.of(split));
        while (!splitList.isEmpty()) {
            Token token = visitors.getOrDefault(splitList.peekFirst(), TextVisitor.getInstance()).visit(splitList);
            fragments.add(token);
        }
        return fragments.toArray(new Token[0]);
    }

}
