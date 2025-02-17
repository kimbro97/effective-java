package com.effective.chapter03.item10;

import java.util.Objects;

public final class CaseInsensitiveString {

    public static void main(String[] args) {
        String s = "polish";

        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");

        System.out.println(cis.equals(s)); // true
        System.out.println(s.equals(cis)); // false
    }

    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object object) {

//        if (object instanceof CaseInsensitiveString) {
//            return s.equalsIgnoreCase(
//                    ((CaseInsensitiveString) object).s
//            );
//        }
//        if (object instanceof String) {
//            return s.equalsIgnoreCase((String) object);
//        }
//        return false;
//
        return object instanceof CaseInsensitiveString &&
                ((CaseInsensitiveString) object).s.equalsIgnoreCase(this.s);
    }
}
