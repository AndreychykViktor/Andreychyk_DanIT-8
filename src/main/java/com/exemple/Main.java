package com.exemple;

import com.exemple.controller.FamilyController;
import com.exemple.dao.CollectionFamilyDao;
import com.exemple.service.FamilyService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import static com.exemple.DayOfWeek.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FamilyController controller =
            new FamilyController(new FamilyService(new CollectionFamilyDao()));

    private static void createFamilies() {
        try {
            Pet dog = new Dog("Рекс", 3, 70, new String[]{"сидіти", "давати лапу"});
            Pet cat = new DomesticCat("Мурка", 2, 50, new String[]{"стрибати", "спати"});
            Pet fish = new Fish("Немо", 1, 30, new String[]{"плавати"});

            HashMap<DayOfWeek, String> fatherSchedule = new HashMap<>();
            Man father = new Man("Іван", "Петренко", 1980, 120, fatherSchedule);
            father.addScheduleItem(MONDAY, "робота");
            father.addScheduleItem(TUESDAY, "спортзал");

            HashMap<DayOfWeek, String> motherSchedule = new HashMap<>();
            Woman mother = new Woman("Марія", "Петренко", 1985, 125, motherSchedule);
            mother.addScheduleItem(MONDAY, "йога");
            mother.addScheduleItem(FRIDAY, "зустріч з подругами");

            Human son = new Human("Петро", "Петренко", "01/01/2010", 100);
            Human daughter = new Human("Ольга", "Петренко", "01/01/2012", 110);
            Human adoptedChild = new Human("Андрій", "Сидоренко", "30/09/2012", 105);

            Family family1 = controller.createNewFamily(mother, father);
            controller.adoptChild(family1, son);
            controller.adoptChild(family1, daughter);
            controller.addPet(controller.getAllFamilies().indexOf(family1), dog);

            Family family2 = controller.createNewFamily(mother, father);
            controller.adoptChild(family2, daughter);
            controller.addPet(controller.getAllFamilies().indexOf(family2), cat);

            Family family3 = controller.createNewFamily(mother, father);
            controller.adoptChild(family3, son);
            controller.adoptChild(family3, daughter);
            controller.adoptChild(family3, adoptedChild);
            controller.addPet(controller.getAllFamilies().indexOf(family3), fish);

        } catch (ParseException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Меню ===");
        System.out.println("1. Заповнити тестовими даними");
        System.out.println("2. Відобразити весь список сімей");
        System.out.println("3. Відобразити список сімей, де кількість людей більша за задану");
        System.out.println("4. Відобразити список сімей, де кількість людей менша за задану");
        System.out.println("5. Підрахувати кількість сімей, де кількість членів дорівнює");
        System.out.println("6. Створити нову родину");
        System.out.println("7. Видалити сім'ю за індексом");
        System.out.println("8. Редагувати сім'ю за індексом");
        System.out.println("9. Видалити всіх дітей старше віку");
        System.out.println("10. Зберегти сім'ї у файл");
        System.out.println("11. Завантажити сім'ї з файлу");
        System.out.println("exit. Вийти");
        System.out.print("Оберіть команду: ");
    }

    private static void printMenuForEdit() {
        System.out.println("\n=== Меню ===");
        System.out.println("1. Народити дитину");
        System.out.println("2. Усиновити дитину");
        System.out.println("3. Повернутися до головного меню");
        System.out.print("Оберіть команду: ");
    }

    private static void createFamily() {
        try {
            System.out.println("Введіть ім'я матері: ");
            String motherName = scanner.nextLine();
            System.out.println("Введіть прізвище матері: ");
            String motherSurname = scanner.nextLine();
            System.out.println("Введіть рік народження матері (дд/мм/рррр): ");
            long motherYear = Long.parseLong(scanner.nextLine());
            System.out.println("Введіть IQ матері: ");
            int motherIQ = Integer.parseInt(scanner.nextLine());
            HashMap<DayOfWeek, String> motherSchedule = new HashMap<>();

            System.out.println("Введіть ім'я батька: ");
            String fatherName = scanner.nextLine();
            System.out.println("Введіть прізвище батька: ");
            String fatherSurname = scanner.nextLine();
            System.out.println("Введіть рік народження батька (дд/мм/рррр): ");
            long fatherYear = Long.parseLong(scanner.nextLine());
            System.out.println("Введіть IQ батька: ");
            int fatherIQ = Integer.parseInt(scanner.nextLine());
            HashMap<DayOfWeek, String> fatherSchedule = new HashMap<>();

            Human mother = new Human(motherName, motherSurname, motherYear, motherIQ, motherSchedule);
            Human father = new Human(fatherName, fatherSurname, fatherYear, fatherIQ, fatherSchedule);

            Family family = controller.createNewFamily(mother, father);

            System.out.println("Сім'ю створено.");
        } catch (Exception e) {
            System.out.println("Помилка при створенні сім'ї: " + e.getMessage());
        }
    }

    private static void editFamily() {
        System.out.print("Введіть номер сім'ї для редагування: ");
        int familyIndex = Integer.parseInt(scanner.nextLine());
        Family family = controller.getFamilyById(familyIndex);
        if (family == null) {
            System.out.println("Сім'я не знайдена.");
            return;
        }
        while (true) {
            printMenuForEdit();
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    System.out.print("Введіть ім'я хлопчика: ");
                    String boyName = scanner.nextLine();
                    System.out.print("Введіть ім'я дівчинки: ");
                    String girlName = scanner.nextLine();
                    controller.bornChild(family, boyName, girlName);
                    System.out.println("Дитину додано.");
                    break;
                case "2":
                    System.out.print("Введіть ім'я дитини: ");
                    String childName = scanner.nextLine();
                    System.out.print("Введіть прізвище дитини: ");
                    String childSurname = scanner.nextLine();
                    System.out.print("Введіть дату народження дитини (дд/мм/рррр): ");
                    String childBirthDate = scanner.nextLine();
                    System.out.print("Введіть IQ дитини: ");
                    int childIQ = Integer.parseInt(scanner.nextLine());
                    Human adoptedChild = null;
                    try {
                        adoptedChild = new Human(childName, childSurname, childBirthDate, childIQ);
                    } catch (ParseException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    if (adoptedChild != null) {
                        controller.adoptChild(family, adoptedChild);
                        System.out.println("Дитину усиновлено.");
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Невідома команда.");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();
            try {
                switch (input) {
                    case "1":
                        createFamilies();
                        System.out.println("Тестові сім'ї створено.");
                        break;
                    case "2":
                        controller.displayAllFamilies();
                        break;
                    case "3":
                        System.out.print("Введіть мінімальну кількість людей: ");
                        int min = Integer.parseInt(scanner.nextLine());
                        controller.getFamiliesBiggerThan(min)
                                .forEach(System.out::println);
                        break;
                    case "4":
                        System.out.print("Введіть максимальну кількість людей: ");
                        int max = Integer.parseInt(scanner.nextLine());
                        controller.getFamiliesLessThan(max)
                                .forEach(System.out::println);
                        break;
                    case "5":
                        System.out.print("Введіть кількість членів сім'ї: ");
                        int count = Integer.parseInt(scanner.nextLine());
                        int result = controller.countFamiliesWithMemberNumber(count);
                        System.out.println("Кількість сімей: " + result);
                        break;
                    case "6":
                        createFamily();
                        break;
                    case "7":
                        System.out.print("Введіть номер сім'ї для видалення: ");
                        int delId = Integer.parseInt(scanner.nextLine());
                        controller.deleteFamilyByIndex(delId);
                        System.out.println("Сім'ю видалено.");
                        break;
                    case "8":
                        editFamily();
                        break;
                    case "9":
                        System.out.print("Введіть вік: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        controller.deleteAllChildrenOlderThen(age);
                        System.out.println("Видалено дітей старше " + age + " років.");
                        break;
                    case "10":
                        System.out.print("Введіть ім'я файлу для збереження: ");
                        String saveFile = scanner.nextLine();
                        controller.saveToFile(saveFile);
                        System.out.println("Дані збережено.");
                        break;
                    case "11":
                        System.out.print("Введіть ім'я файлу для завантаження: ");
                        String loadFile = scanner.nextLine();
                        controller.loadFromFile(loadFile);
                        System.out.println("Дані завантажено.");
                        break;
                    case "exit":
                        System.out.println("Вихід з програми.");
                        return;
                    default:
                        System.out.println("Невідома команда.");
                }
            } catch (Exception e) {
                System.out.println("Сталася помилка: " + e.getMessage());
            }
        }
    }
}