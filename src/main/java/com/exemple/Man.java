package com.exemple;
import com.exemple.DayOfWeek;
import java.util.HashMap;

final class Man extends Human {

   public Man(String name, String surname, int year, int iq, HashMap<DayOfWeek, String> schedule) {
       super(name, surname, year, iq, schedule);
   }

   public void addScheduleItem(DayOfWeek day, String activity) {
       if (getSchedule() == null) {
           setSchedule(new HashMap<DayOfWeek, String>());
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

    public void repairCar() {
        System.out.println("Я " + getName() + " " + getSurname() + ", ремонтую машину");
    }
}
