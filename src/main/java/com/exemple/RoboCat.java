package com.exemple;

public class RoboCat extends Pet implements Foulable {
    private String color;

    public RoboCat(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        setColorBasedOnSpecies();
    }

    public RoboCat(String nickname) {
        super(nickname);
        setColorBasedOnSpecies();
    }

    public RoboCat(String nickname, int age) {
        super(nickname, age, 0, null);
        setColorBasedOnSpecies();
    }

    public RoboCat(String nickname, int age, int trickLevel) {
        super(nickname, age, trickLevel, null);
        setColorBasedOnSpecies();
    }

    private void setColorBasedOnSpecies() {
        if (getSpecies() == Species.UNKNOWN) {
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
