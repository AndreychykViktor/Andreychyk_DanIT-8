package com.exemple;

public class Main {
    public static void main(String[] args) {


        Pet dog = new Dog("Рекс", 3, 70, new String[]{"сидіти", "давати лапу"});
        Pet cat = new DomesticCat("Мурка", 2, 50, new String[]{"стрибати", "спати"});
        Pet fish = new Fish("Немо", 1, 30, new String[]{"плавати"});
        Pet roboCat = new RoboCat("Робік", 5, 90, new String[]{"підзаряджатися", "грати музику"});

        Man father = new Man("Іван", "Петренко", 1980, 120,
                new String[][]{{"понеділок", "робота"}, {"вівторок", "спортзал"}});

        Woman mother = new Woman("Марія", "Петренко", 1985, 125,
                new String[][]{{"середа", "йога"}, {"п'ятниця", "зустріч з подругами"}});

        Human son = new Human("Петро", "Петренко", 2010, 100,
                new String[][]{{"субота", "футбол"}, {"неділя", "комп'ютерні ігри"}});

        Human daughter = new Human("Ольга", "Петренко", 2012, 110,
                new String[][]{{"понеділок", "малювання"}, {"четвер", "танці"}});


        Family family = new Family(mother, father);


        family.addChild(son);
        family.addChild(daughter);
        family.setPet(dog);


        System.out.println("\n===== Інформація про сім'ю =====");
        System.out.println(family);
        System.out.println("\nКількість членів сім'ї: " + family.countFamily());

        System.out.println("\n===== Привітання тварин =====");
        father.greetPet();
        mother.greetPet();
        son.greetPet();

        System.out.println("\n===== Опис тварин =====");
        father.describePet();

        System.out.println("\n===== Спеціальні методи =====");
        father.repairCar();
        mother.makeupTips();

        System.out.println("\n===== Видалення дитини =====");
        family.deleteChild(0);
        System.out.println("Після видалення дитини:");
        System.out.println("Кількість членів сім'ї: " + family.countFamily());
        System.out.println(family);

        System.out.println("\n===== Поведінка тварин =====");
        dog.respond();
        cat.respond();
        fish.respond();
        roboCat.respond();

        System.out.println("\n===== Витівки тварин =====");
        if (dog instanceof Foulable) {
            ((Foulable) dog).foul();
        }
        if (cat instanceof Foulable) {
            ((Foulable) cat).foul();
        }
        if (roboCat instanceof Foulable) {
            ((Foulable) roboCat).foul();
        }
    }
}