package com.exemple;

import java.util.Arrays;
import java.util.Objects;

public class Pet {
    private Species species;
    private String nickname;
    private int age;
    private int trickLevel;
    private String[] habits;

    public Pet(String dog, String rex, int i, int i1, String[] strings) {

        setSpeciesBasedOnClass();
    }

    public Pet(String nickname) {
        this.nickname = nickname;
        setSpeciesBasedOnClass();
    }

    public Pet(String nickname, int age, int trickLevel, String[] habits) {
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
        setSpeciesBasedOnClass();
    }

    private void setSpeciesBasedOnClass() {
        if (this instanceof Fish) {
            this.species = Species.FISH;
        } else if (this instanceof DomesticCat) {
            this.species = Species.CAT;
        } else if (this instanceof Dog) {
            this.species = Species.DOG;
        } else if (this instanceof RoboCat) {
            this.species = Species.CAT;
        } else {
            this.species = Species.UNKNOWN;
        }
    }


    public void eat() {
        System.out.println("Я їм!");
    }

    public void respond() {
        System.out.println("Привіт, хазяїн. Я - " + nickname + ". Я скучив!");
    }

    @Override
    public String toString() {
        return "Pet{species='" + (species != null ? formatSpecies(species) : null) + "', nickname='" + nickname + "', age=" + age + ", trickLevel=" + trickLevel + ", habits=" + Arrays.toString(habits) + "}";
    }


    private String formatSpecies(Species species) {
        String name = species.name();
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return age == pet.age && trickLevel == pet.trickLevel && Objects.equals(species, pet.species) && Objects.equals(nickname, pet.nickname) && Arrays.equals(habits, pet.habits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(species, nickname, age, trickLevel);
        result = 31 * result + Arrays.hashCode(habits);
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing Pet object: " + this.toString());
        super.finalize();
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(int trickLevel) {
        this.trickLevel = trickLevel;
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }


}
