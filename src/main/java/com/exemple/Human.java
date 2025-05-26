package com.exemple;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Human {
    private String name;
    private String surname;
    private long birthDate;
    private int iq;
    private HashMap<DayOfWeek, String> schedule;
    private Family family;

    public Human(String name, String surname, long birthDate, int iq, HashMap<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.iq = iq;
        this.schedule = schedule != null ? schedule : new HashMap<>();
    }

public Human(String name, String surname, String birthDateStr, int iq, HashMap<DayOfWeek, String> schedule) throws java.text.ParseException {
    this.name = name;
    this.surname = surname;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = dateFormat.parse(birthDateStr);
    this.birthDate = date.getTime();
    this.iq = iq;
    this.schedule = schedule != null ? schedule : new HashMap<>();
}

    public Human(String name, String surname, long birthDate, int iq, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
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

    public Human(String name, String surname, String birthDateStr, int iq) throws java.text.ParseException {
        this.name = name;
        this.surname = surname;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(birthDateStr);
        this.birthDate = date.getTime();
        this.iq = iq;
    }

    public String describeAge() {
        LocalDate birthLocalDate = new Date(birthDate).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthLocalDate, currentDate);

        return String.format("%d роки, %d місяців, %d днів",
                period.getYears(), period.getMonths(), period.getDays());
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

    public long getYear() {
        return birthDate;
    }

    public void setYear(long birthDate) {
        this.birthDate = birthDate;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

   public HashMap<DayOfWeek, String> getSchedule() {
       return schedule;
   }

   public void setSchedule(HashMap<DayOfWeek, String> schedule) {
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateFormatted = formatter.format(new Date(birthDate));

        return "Human{" + "\n" +
                " Name='" + name + '\'' + ",\n" +
                " Surname='" + surname + '\'' +",\n" +
                " BirthDate=" + birthDateFormatted +",\n" +
                " ExactTime=" + describeAge() +",\n" +
                " Iq=" + iq +",\n" +
                " Schedule=" + schedule +",\n" +
                '}' + "\n";
    }

    public String prettyFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthDateFormatted = formatter.format(new Date(birthDate));

        return String.format("HUMAN %s %s, %s (%s), IQ: %d",
                name, surname, birthDateFormatted, describeAge(), iq);
    }
}