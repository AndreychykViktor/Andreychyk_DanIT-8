package com.exemple;

public class DomesticCat extends Pet implements Foulable {
    private String breed;

    public DomesticCat(String nickname, int age, int trickLevel, String[] habits) {
        super(nickname, age, trickLevel, habits);
        setBreedBasedOnSpecies();
    }

    public DomesticCat(String nickname, int age, int trickLevel) {
        super(nickname, age, trickLevel, null);
        setBreedBasedOnSpecies();
    }

    public DomesticCat(String nickname, int age) {
        super(nickname, age, 0, null);
        setBreedBasedOnSpecies();
    }

    public DomesticCat(String nickname) {
        super(nickname);
        setBreedBasedOnSpecies();
    }

    private void setBreedBasedOnSpecies() {
        if (getSpecies() == Species.UNKNOWN) {
            this.breed = "UNKNOWN";
        } else {
            this.breed = "British Shorthair";
        }
    }

    @Override
    public void respond() {
        System.out.println("Мяу. Я - " + getNickname() + ". Я скучив!");
    }

    @Override
    public void foul() {
        System.out.println("Я - " + getNickname() + ". Треба сховати сліди злочину");
    }
}
