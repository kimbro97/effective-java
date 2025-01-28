package com.effective.chapter02.item03;

import java.lang.reflect.Constructor;

public class ReflectionExample {
    public static void main(String[] args) {
        try {
            Elvis elvis1 = Elvis.INSTANCE;
            System.out.println("elvis1: " + elvis1);

            Constructor<Elvis> constructor = Elvis.class.getDeclaredConstructor();
            constructor.setAccessible(true);

            Elvis elvis2 = constructor.newInstance();
            System.out.println("elvis2: " + elvis2);

            System.out.println("Are they the same instance? " + (elvis1 == elvis2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
