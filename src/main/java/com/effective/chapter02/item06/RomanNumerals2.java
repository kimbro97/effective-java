package com.effective.chapter02.item06;

import java.util.regex.Pattern;

public class RomanNumerals2 {

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD] |D?C{0,3})(X[CL]|L?X{0,3}(I[XV]|V?I{0,3})$)");

    public static boolean isRomanNumeral(String str) {
        return ROMAN.matcher(str).matches();
    }
}
