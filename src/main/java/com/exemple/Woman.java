package com.exemple;
import java.util.HashMap;
import com.exemple.DayOfWeek;

final class Woman extends Human {
    public Woman(String name, String surname, int year, int iq, HashMap<com.exemple.DayOfWeek, String> schedule) {
        super(name, surname, year, iq, schedule);
    }

    public void addScheduleItem(DayOfWeek day, String activity) {
        if (getSchedule() == null) {
            setSchedule(new HashMap<>());
        }
        getSchedule().put(day, activity);
    }
    @Override
    public void greetPet() {
        if (getFamily() != null && getFamily().getPet() != null) {
            System.out.println("Привіт " + getFamily().getPet().getNickname());
        } else {
            System.out.println("У мене немає домашньої тварини.");
        }
    }

    public void makeupTips() {
        System.out.println("Я " + getName() + " " + getSurname() + ", я крашусь");
    }
}
