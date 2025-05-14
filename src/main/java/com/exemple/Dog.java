package com.exemple;

public class Dog extends Pet implements Foulable {
    private String breed;

    public Dog(String nickname, int age, int trickLevel, String[] habits) {
        super("Dog", nickname, age, trickLevel, habits);
        setBreedBasedOnSpecies();
    }

    public Dog(String nickname) {
        super("Dog", nickname, 0, 0, null);
        setBreedBasedOnSpecies();
    }

    public Dog(String nickname, int age) {
        super("Dog", nickname, age, 0, null);
        setBreedBasedOnSpecies();
    }

    public Dog(String nickname, int age, int trickLevel) {
        super("Dog", nickname, age, trickLevel, null);
        setBreedBasedOnSpecies();
    }

    private void setBreedBasedOnSpecies() {
        if (getSpecies() == null || "UNKNOWN".equals(getSpecies())) {
            this.breed = "UNKNOWN";
        } else {
            this.breed = "Pitbull";
        }
    }

    @Override
    public void respond() {
        System.out.println("Гав-гав! Я - " + getNickname() + ". Я скучив!");
    }

    @Override
    public void foul() {
        System.out.println("Я - " + getNickname() + ". Я розбив мамин вазон");
    }
}