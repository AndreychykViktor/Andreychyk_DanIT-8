package com.exemple;

public enum DayOfWeek {
    MONDAY("понеділок"),
    TUESDAY("вівторок"),
    WEDNESDAY("середа"),
    THURSDAY("четвер"),
    FRIDAY("п'ятниця"),
    SATURDAY("субота"),
    SUNDAY("неділя");



    private final String ukrName;

    DayOfWeek(String ukrName) {
        this.ukrName = ukrName;
    }

    public String getUkrName() {
        return ukrName;
    }
    @Override
    public String toString() {
        return ukrName;
    }
}