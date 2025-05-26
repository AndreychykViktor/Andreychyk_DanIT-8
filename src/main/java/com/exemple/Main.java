package com.exemple;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.exemple.DayOfWeek;

import static com.exemple.DayOfWeek.*;

import com.exemple.FamilyOverflowException;
import com.exemple.FamilyService;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FamilyService familyService = new FamilyService();

    private static void createFamilies(FamilyService familyService) {
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

    private static void createFamily() throws ParseException {


        System.out.println("Введіть ім'я матері: ");
        String motherName = scanner.nextLine();
        System.out.println("Введіть прізвище матері: ");
        String motherSurname = scanner.nextLine();
        System.out.println("Введіть рік народження матері (дд/мм/рррр): ");
        long motherYear = Long.parseLong(scanner.nextLine());
        System.out.println("Введіть IQ матері: ");
        int motherIQ = Integer.parseInt(scanner.nextLine());
        System.out.println("Введіть розклад дня матері: ");
        HashMap<DayOfWeek, String> mother_schedule = new HashMap<>();
        while (true) {
            System.out.println("Введіть день тижня (або 'done' щоб завершити): ");
            String dayInput = scanner.nextLine().trim();
            if (dayInput.equalsIgnoreCase("done")) break;
            try {
                DayOfWeek day = DayOfWeek.valueOf(dayInput.toUpperCase());
                System.out.println("Введіть задачу на " + day + ": ");
                String task = scanner.nextLine();
                mother_schedule.put(day, task);
            } catch (IllegalArgumentException e) {
                System.out.println("НЕВІДОМИЙ ДЕНЬ ТИЖНЯ: " + dayInput);
            }
        }


        System.out.println("Введіть ім'я батька: ");
        String fatherName = scanner.nextLine();
        System.out.println("Введіть прізвище батька: ");
        String fatherSurname = scanner.nextLine();
        System.out.println("Введіть рік народження батька (дд/мм/рррр): ");
        long fatherYear = Long.parseLong(scanner.nextLine());
        System.out.println("Введіть IQ батька: ");
        int fatherIQ = Integer.parseInt(scanner.nextLine());
        HashMap<DayOfWeek, String> father_schedule = new HashMap<>();
        while (true) {
            System.out.println("Введіть день тижня (або 'done' щоб завершити): ");
            String dayInput = scanner.nextLine().trim();
            if (dayInput.equalsIgnoreCase("done")) break;
            try {
                DayOfWeek day = DayOfWeek.valueOf(dayInput.toUpperCase());
                System.out.println("Введіть задачу на " + day + ": ");
                String task = scanner.nextLine();
                mother_schedule.put(day, task);
            } catch (IllegalArgumentException e) {
                System.out.println("НЕВІДОМИЙ ДЕНЬ ТИЖНЯ: " + dayInput);
            }
        }


        System.out.println("Введіть ім'я дитини: ");
        String childName = scanner.nextLine();
        System.out.println("Введіть прізвище дитини: ");
        String childSurname = scanner.nextLine();
        System.out.println("Введіть дату народження дитини (дд/мм/рррр): ");
        String childBirthDate = scanner.nextLine();
        System.out.println("Введіть дату IQ дитини: ");
        int childIQ = Integer.parseInt(scanner.nextLine());


        System.out.println("Введіть ім'я тварини: ");
        String petName = scanner.nextLine();
        System.out.println("Введіть вік тварини: ");
        int petAge = Integer.parseInt(scanner.nextLine());
        System.out.println("Введіть на скільки розумна тварина: ");
        int petTrickLevel = Integer.parseInt(scanner.nextLine());
        System.out.println("Введіть вид тварини (Dog, DomesticCat, Fish): ");
        String petType = scanner.nextLine();
        Pet pet = null;
        switch (petType.toLowerCase()) {
            case "dog":
                pet = new Dog(petName, petAge, petTrickLevel, new String[]{"сидіти", "давати лапу"});
                break;
            case "domesticcat":
                pet = new DomesticCat(petName, petAge, petTrickLevel, new String[]{"стрибати", "спати"});
                break;
            case "fish":
                pet = new Fish(petName, petAge, petTrickLevel, new String[]{"плавати"});
                break;
            default:
                System.out.println("Невідомий тип тварини.");
                return;
        }
        Human mother = new Human(motherName, motherSurname, motherYear, motherIQ, new HashMap<>());
        Human father = new Human(fatherName, fatherSurname, fatherYear, fatherIQ, new HashMap<>());
        Human child = new Human(childName, childSurname, childBirthDate, childIQ);

        Family family = new Family(mother, father);
        family.addChild(child);
        family.setPet(pet);
        familyService.addFamily(family);
        System.out.println("Сім'ю створено.");

        System.out.println("Family creation not implemented yet.");
    }

    private static void editFamily() {

        System.out.print("Введіть номер сім'ї для редагування: ");
        int familyIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Family family = familyService.getFamilyByIndex(familyIndex);
        if (family == null) {
            System.out.println("Сім'я не знайдена.");
            return;
        }

        System.out.println("Редагування сім'ї: " + familyIndex);
        while (true) {
            printMenuForEdit();
            String input = scanner.nextLine().trim();
            try {
                switch (input) {
                    case "1":
                        System.out.print("Введіть ім'я дитини: ");
                        String childName = scanner.nextLine();
                        System.out.print("Введіть прізвище дитини: ");
                        String childSurname = scanner.nextLine();
                        System.out.print("Введіть дату народження дитини (дд/мм/рррр): ");
                        String childBirthDate = scanner.nextLine();
                        System.out.print("Введіть IQ дитини: ");
                        int childIQ = Integer.parseInt(scanner.nextLine());
                        Human child = new Human(childName, childSurname, childBirthDate, childIQ);
                        family.addChild(child);
                        break;
                    case "2":
                        System.out.print("Введіть ім'я дитини: ");
                        String adoptedChildName = scanner.nextLine();
                        System.out.print("Введіть прізвище дитини: ");
                        String adoptedChildSurname = scanner.nextLine();
                        System.out.print("Введіть дату народження дитини (дд/мм/рррр): ");
                        String adoptedChildBirthDate = scanner.nextLine();
                        System.out.print("Введіть IQ дитини: ");
                        int adoptedChildIQ = Integer.parseInt(scanner.nextLine());
                        Human adoptedChild = new Human(adoptedChildName, adoptedChildSurname, adoptedChildBirthDate, adoptedChildIQ);
                        family.addChild(adoptedChild);
                        break;
                    case "3":
                        return;
                    default:
                        System.out.println("Невідома команда.");
                }
            } catch (FamilyOverflowException e) {
                System.out.println("Помилка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Сталася помилка: " + e.getMessage());
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
                        createFamilies(familyService);
                        System.out.println("Тестові сім'ї створено.");
                        break;
                    case "2":
                        familyService.displayAllFamilies();
                        break;
                    case "3":
                        System.out.print("Введіть мінімальну кількість людей: ");
                        int min = Integer.parseInt(scanner.nextLine());
                        familyService.getFamiliesBiggerThan(min).forEach(f -> System.out.println(familyService.prettyFormat(f)));
                        break;
                    case "4":
                        System.out.print("Введіть максимальну кількість людей: ");
                        int max = Integer.parseInt(scanner.nextLine());
                        familyService.getFamiliesLessThan(max).forEach(f -> System.out.println(familyService.prettyFormat(f)));
                        break;
                    case "5":
                        System.out.print("Введіть кількість членів сім'ї: ");
                        int count = Integer.parseInt(scanner.nextLine());
                        int result = familyService.countFamiliesWithMemberNumber(count);
                        System.out.println("Кількість сімей: " + result);
                        break;
                    case "6":
                        createFamily();
                        break;
                    case "7":
                        System.out.print("Введіть номер сім'ї для видалення: ");
                        int delId = Integer.parseInt(scanner.nextLine()) - 1;
                        familyService.deleteFamily(delId);
                        System.out.println("Сім'ю видалено.");
                        break;
                    case "8":
                        editFamily();
                        break;
                    case "9":
                        System.out.print("Введіть вік: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        familyService.deleteAllChildrenOlderThan(age);
                        System.out.println("Видалено дітей старше " + age + " років.");
                        break;
                    case "10":
                        System.out.print("Введіть ім'я файлу для збереження: ");
                        String saveFile = scanner.nextLine();
                        familyService.saveToFile(saveFile);
                        System.out.println("Дані збережено.");
                        break;
                    case "11":
                        System.out.print("Завантажити з датабази? (так/ні): ");
                        String loadFromDb = scanner.nextLine().trim();
                        if (loadFromDb.equalsIgnoreCase("так")) {
                            System.out.print("Введіть ім'я файлу для завантаження: ");
                            String loadFileDB = scanner.nextLine();
                            familyService.loadFamiliesFromDB(loadFileDB);
                            System.out.println("Дані завантажено з бази даних.");
                            break;
                        }
                        System.out.print("Введіть ім'я файлу для завантаження: ");
                        String loadFile = scanner.nextLine();
                        familyService.loadFromFile(loadFile);
                        System.out.println("Дані завантажено.");
                        break;
                    case "exit":
                        System.out.println("Вихід з програми.");
                        return;
                    default:
                        System.out.println("Невідома команда.");
                }
            } catch (FamilyOverflowException e) {
                System.out.println("Помилка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Сталася помилка: " + e.getMessage());
            }
        }
    }
}