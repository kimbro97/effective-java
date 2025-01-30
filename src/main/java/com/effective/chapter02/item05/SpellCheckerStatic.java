package com.effective.chapter02.item05;

import java.util.List;

public class SpellCheckerStatic {
    private static final Lexicon dictionary = new Lexicon();

    private SpellCheckerStatic() {}

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return List.of();
    }
}
