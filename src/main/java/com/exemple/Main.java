package com.exemple;

public class Main {
    public static void main(String[] args) {
        hm6 outerInstance = new hm6();

        // Створення домашніх тварин
        hm6.Pet dog = outerInstance.new Dog("Рекс", 3, 70, new String[]{"сидіти", "давати лапу"});
        hm6.Pet cat = outerInstance.new DomesticCat("Мурка", 2, 50, new String[]{"стрибати", "спати"});
        hm6.Pet fish = outerInstance.new Fish("Немо", 1, 30, new String[]{"плавати"});
        hm6.Pet roboCat = outerInstance.new RoboCat("Робік", 5, 90, new String[]{"підзаряджатися", "грати музику"});

        hm6.Man father = outerInstance.new Man("Іван", "Петренко", 1980, 120,
                new String[][]{{"понеділок", "робота"}, {"вівторок", "спортзал"}});

        hm6.Woman mother = outerInstance.new Woman("Марія", "Петренко", 1985, 125,
                new String[][]{{"середа", "йога"}, {"п'ятниця", "зустріч з подругами"}});

        hm6.Human son = outerInstance.new Human("Петро", "Петренко", 2010, 100,
                new String[][]{{"субота", "футбол"}, {"неділя", "комп'ютерні ігри"}});

        hm6.Human daughter = outerInstance.new Human("Ольга", "Петренко", 2012, 110,
                new String[][]{{"понеділок", "малювання"}, {"четвер", "танці"}});


        hm6.Family family = outerInstance.new Family(mother, father);


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
        if (dog instanceof hm6.Foulable) {
            ((hm6.Foulable) dog).foul();
        }
        if (cat instanceof hm6.Foulable) {
            ((hm6.Foulable) cat).foul();
        }
        if (roboCat instanceof hm6.Foulable) {
            ((hm6.Foulable) roboCat).foul();
        }
    }
}