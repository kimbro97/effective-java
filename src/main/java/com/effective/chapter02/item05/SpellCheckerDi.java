package com.effective.chapter02.item05;

import java.util.List;
import java.util.Objects;

public class SpellCheckerDi {
    private final Lexicon dictionary;

    public SpellCheckerDi(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return List.of();
    }
}
