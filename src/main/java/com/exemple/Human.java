package com.exemple;

import java.util.HashMap;
import java.util.Map;

public class Human {
    private String name;
    private String surname;
    private int year;
    private int iq;
    private Map<DayOfWeek, String> schedule;
    private Family family;

    public Human(String name, String surname, int year, int iq, Map<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.schedule = schedule != null ? schedule : new HashMap<>();
    }

    public Human(String name, String surname, int year, int iq, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.iq = iq;
        this.schedule = new HashMap<>();

        if (schedule != null) {
            for (String[] dayActivity : schedule) {
                try {
                    DayOfWeek day = DayOfWeek.valueOf(dayActivity[0].toUpperCase());
                    this.schedule.put(day, dayActivity[1]);
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }


    public void greetPet() {
        if (family != null && !family.getPets().isEmpty()) {
            Pet pet = family.getPets().iterator().next();
            System.out.println("Привіт, " + pet.getNickname());
        } else {
            System.out.println("У мене немає домашньої тварини.");
        }
    }

    public void describePet() {
        if (family != null && !family.getPets().isEmpty()) {
            Pet pet = family.getPets().iterator().next();
            System.out.println("У мене є " + pet.getSpecies() + ", йому " + pet.getAge() + " років, він " +
                    (pet.getTrickLevel() > 50 ? "дуже хитрий" : "майже не хитрий"));
        } else {
            System.out.println("У мене немає домашньої тварини.");
        }
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

   public Map<DayOfWeek, String> getSchedule() {
       return schedule;
   }

   public void setSchedule(Map<DayOfWeek, String> schedule) {
       this.schedule = schedule;
   }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + year +
                ", iq=" + iq +
                ", schedule=" + schedule +
                '}';
    }
}