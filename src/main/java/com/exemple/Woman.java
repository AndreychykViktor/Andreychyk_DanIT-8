package com.exemple;

final class Woman extends Human {
    public Woman(String name, String surname, int year, int iq, String[][] schedule) {
        super(name, surname, year, iq, schedule);
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
