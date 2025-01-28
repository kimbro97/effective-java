package com.effective.chapter02.item03;

public class Elvis2 {
    public static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {}

    public static Elvis2 getInstance() {
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {}
}
