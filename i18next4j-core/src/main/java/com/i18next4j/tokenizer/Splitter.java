package com.i18next4j.tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Splitter {

    final Set<String> delimiters;
    final Set<Character> startLetters;
    final int maxLength;

    public Splitter(Collection<String> delimiters) {
        if (delimiters == null || delimiters.isEmpty()) {
            throw new IllegalArgumentException("delimiters must not be empty");
        }
        this.delimiters = new HashSet<>(delimiters);
        this.startLetters = startLetters(delimiters);
        this.maxLength = maxLength(delimiters);

    }

    private Set<Character> startLetters(Collection<String> delimiters) {
        Set<Character> set = new HashSet<>();
        for (String delimiter : delimiters) {
            set.add(delimiter.charAt(0));
        }
        return set;
    }

    private int maxLength(Collection<String> delimiters) {

        int maxLength = 0;
        for (String delimiter : delimiters) {
            maxLength = Math.max(maxLength, delimiter.length());
        }
        return maxLength;
    }

    public String[] split(String template) {

        ArrayList<String> fragments = new ArrayList<>();

        String[] candidates = new String[maxLength];

        StringBuilder accumulator = new StringBuilder();

        for (Character c : template.toCharArray()) {
            accumulator.append(c);
            for (int index = maxLength - 1; index >= 0; index--) {
                String candidate = candidates[index];
                if (candidate != null) {
                    candidate += c;
                    if (delimiters.contains(candidate)) {
                        if (accumulator.length() != candidate.length()) {
                            fragments.add(accumulator.substring(0, accumulator.length() - candidate.length()));
                        }
                        fragments.add(candidate);
                        accumulator.setLength(0);
                        Arrays.fill(candidates, null);
                        continue;
                    }
                }
                if (index != maxLength - 1) {
                    candidates[index + 1] = candidate;
                }
            }
            if (startLetters.contains(c)) {
                candidates[0] = String.valueOf(c);
                String candidate = candidates[0];
                if (delimiters.contains(candidate)) {
                    if (accumulator.length() != candidate.length()) {
                        fragments.add(accumulator.substring(0, accumulator.length() - candidate.length()));
                    }
                    fragments.add(candidate);
                    accumulator.setLength(0);
                    Arrays.fill(candidates, null);
                }

            } else {
                candidates[0] = null;
            }
        }
        if (!accumulator.isEmpty()) {
            fragments.add(accumulator.toString());
        }
        return fragments.toArray(new String[0]);
    }

}
