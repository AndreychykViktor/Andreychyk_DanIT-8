package com.exemple;

public class Fish extends Pet {
    private String color;

    public Fish(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        setColorBasedOnSpecies();
    }

    public Fish(String nickname) {
        super(nickname);
        setColorBasedOnSpecies();
    }

    public Fish(String nickname, int age) {
        super(nickname, age, 0, null);
        setColorBasedOnSpecies();
    }

    public Fish(String nickname, int age, int trickLevel) {
        super(nickname, age, trickLevel, null);
        setColorBasedOnSpecies();
    }

    private void setColorBasedOnSpecies() {
        if (getSpecies() == Species.UNKNOWN) {
            this.color = "UNKNOWN";
        } else {
            this.color = "Blue";
        }
    }

    @Override
    public void respond() {
        System.out.println(getNickname() + ": Буль буль");
    }
}
