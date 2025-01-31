package com.effective.chapter02.item06;

public class RomanNumerals1 {
    public static boolean isRomanNumeral(String str) {
        return str.matches("^(?=.)M*(C[MD] |D?C{0,3})(X[CL]|L?X{0,3}(I[XV]|V?I{0,3})$)");
    }
}
