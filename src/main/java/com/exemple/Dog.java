package com.exemple;

public class Dog extends Pet implements Foulable {
    private String breed;

    public Dog(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        setBreedBasedOnSpecies();
    }

    public Dog(String nickname) {
        super(nickname);
        setBreedBasedOnSpecies();
    }

    public Dog(String nickname, int age) {
        super(nickname, age, 0, null);
        setBreedBasedOnSpecies();
    }

    public Dog(String nickname, int age, int trickLevel) {
        super(nickname, age, trickLevel, null);
        setBreedBasedOnSpecies();
    }

    private void setBreedBasedOnSpecies() {
        if (getSpecies() == Species.UNKNOWN) {
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
