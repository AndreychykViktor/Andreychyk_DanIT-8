package com.exemple;

import java.util.Arrays;
import java.util.Objects;

public class Human {
    private String name;
    private String surname;
    private int year;
    private int iq;
    private String[][] schedule;
    private Family family;
    private Pet pet;


    public Human(String name, String surname, int year, int iq, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.schedule = schedule;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public Family getFamily() {
        return family;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getNickname() {
        return pet.getNickname();
    }

    public void setFamily(Family family) {
        this.family = family;
    }


    public void greetPet() {
        if (family != null && family.getPet() != null) {
            System.out.println("Привіт, " + family.getPet().getNickname());
        } else {
            System.out.println("У мене немає улюбленця.");
        }
    }


    public void describePet() {
        if (family != null && family.getPet() != null) {
            Pet pet = family.getPet();
            String cleverness = pet.getTrickLevel() > 50 ? "дуже хитрий" : "майже не хитрий";
            System.out.println("У мене є " + pet.getSpecies() + ", їй " + pet.getAge() + " років, він " + cleverness + ".");
        } else {
            System.out.println("У мене немає улюбленця.");
        }
    }

    @Override
    public String toString() {
        return "Human{name='" + name + "', surname='" + surname + "', year=" + year + ", iq=" + iq + ", schedule=" + Arrays.deepToString(schedule) + "}";
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing Human object: " + this.toString());
        super.finalize();
    }


    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return year == human.year && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Arrays.deepEquals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, year, iq);
        result = 31 * result + Arrays.deepHashCode(schedule);
        return result;
    }

}
