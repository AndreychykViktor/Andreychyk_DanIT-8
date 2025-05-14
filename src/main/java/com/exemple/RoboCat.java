package com.exemple;

public class RoboCat extends Pet implements Foulable {
    private String color;

    public RoboCat(String nickname, int age, int trickLevel, String[] habits) {
        super("RoboCat", nickname, age, trickLevel, habits);
        setColorBasedOnSpecies();
    }

    public RoboCat(String nickname) {
        super("RoboCat", nickname, 0, 0, null);
        setColorBasedOnSpecies();
    }

    public RoboCat(String nickname, int age) {
        super("RoboCat", nickname, age, 0, null);
        setColorBasedOnSpecies();
    }

    public RoboCat(String nickname, int age, int trickLevel) {
        super("RoboCat", nickname, age, trickLevel, null);
        setColorBasedOnSpecies();
    }

    private void setColorBasedOnSpecies() {
        if (getSpecies() == null || getSpecies().equals("UNKNOWN")) {
            this.color = "UNKNOWN";
        } else {
            this.color = "Silver";
        }
    }

    @Override
    public void respond() {
        System.out.println("Біп-біп! Я - " + getNickname() + ". Я скучив!");
    }

    @Override
    public void foul() {
        System.out.println("Я - " + getNickname() + ". Я взламав WIFI сусіда");
    }
}
