package com.effective.chapter02.item05;

import java.util.List;

public class SpellCheckerSingleton {
    private static final Lexicon dictionary = new Lexicon();

    private SpellCheckerSingleton() {}

    public static SpellCheckerSingleton INSTANCE = new SpellCheckerSingleton();

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return List.of();
    }
}
