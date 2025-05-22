package com.exemple;

import java.text.ParseException;

import static com.exemple.DayOfWeek.*;


public class Main {
    private static void createTestFamilies(FamilyService familyService) {
        try {
            Pet dog = new Dog("Рекс", 3, 70, new String[]{"сидіти", "давати лапу"});
            Pet cat = new DomesticCat("Мурка", 2, 50, new String[]{"стрибати", "спати"});
            Pet fish = new Fish("Немо", 1, 30, new String[]{"плавати"});

            Man father = new Man("Іван", "Петренко", 1980, 120,
                    new String[][]{{MONDAY.toString(), "робота"}, {TUESDAY.toString(), "спортзал"}});
            Woman mother = new Woman("Марія", "Петренко", 1985, 125,
                    new String[][]{{WEDNESDAY.toString(), "йога"}, {FRIDAY.toString(), "зустріч з подругами"}});
            Human son = new Human("Петро", "Петренко", "01/01/2010", 100);
            Human daughter = new Human("Ольга", "Петренко", "01/01/2012", 110);
            Human adoptedChild = new Human("Андрій", "Сидоренко", "30/09/2012", 105);

            // 2 2
            Family family1 = new Family(mother, father);
            family1.addChild(son);
            family1.addChild(daughter);
            family1.setPet(dog);

            // 2 1
            Family family2 = new Family(mother, father);
            family2.addChild(daughter);
            family2.setPet(cat);

            // 2 3
            Family family3 = new Family(mother, father);
            family3.addChild(son);
            family3.addChild(daughter);
            family3.addChild(adoptedChild);
            family3.setPet(fish);


            familyService.addFamily(family1);
            familyService.addFamily(family2);
            familyService.addFamily(family3);

        } catch (ParseException e) {
            System.err.println("Error creating families: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        FamilyService familyService = new FamilyService();
        createTestFamilies(familyService);

        System.out.println("\n===== Displaying All Families =====");
        familyService.displayAllFamilies();

        System.out.println("\n===== Families Bigger Than 3 =====");
        familyService.getFamiliesBiggerThan(3);

        System.out.println("\n===== Families Less Than 4 =====");
        familyService.getFamiliesLessThan(4);

        System.out.println("\n===== Count of Families With 4 Members =====");
        int countFamilies = familyService.countFamiliesWithMemberNumber(4);
        System.out.println("Number of families with 4 members: " + countFamilies);

        System.out.println("\n===== Deleting Children Older Than 10 =====");
        System.out.println("Before deletion:");
        familyService.displayAllFamilies();

        familyService.deleteAllChildrenOlderThan(10);

        System.out.println("\nAfter deletion of children older than 10:");
        familyService.displayAllFamilies();

        System.out.println("\n===== Getting Family By ID =====");
        Family firstFamily = familyService.getFamilyById(0);
        System.out.println("First family: " + firstFamily);

        System.out.println("\n===== Deleting Family By ID =====");
        boolean deleted = familyService.deleteFamily(2);
        System.out.println("Family deleted: " + deleted);

        System.out.println("\n===== Remaining Families =====");
        familyService.displayAllFamilies();
    }


}