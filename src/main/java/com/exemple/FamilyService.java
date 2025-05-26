package com.exemple;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;


public class FamilyService {
    private List<Family> families = new ArrayList<>();


    public void saveToFile(String filename) throws IOException {
        File dir = new File("database");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Family family : families) {
                writer.write(prettyFormat(family));
                writer.write(System.lineSeparator());
                writer.write("--------------------------------------------------");
                writer.write(System.lineSeparator());
            }
        }
    }

    //families.txt
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ParseException {
        families.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Human mother = null, father = null;
            List<Human> children = new ArrayList<>();
            Pet pet = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Мати:")) {
                    mother = parseHumanBlock(reader);

                } else if (line.startsWith("Батько:")) {
                    father = parseHumanBlock(reader);

                } else if (line.startsWith("Діти: [")) {
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (line.equals("]")) break;
                        children.add(parseHumanBlockFromChild(line, reader));
                    }

                } else if (line.startsWith("Тварина:")) {
                    pet = parsePetBlock(line);
                    if (mother != null && father != null) {
                        Family family = new Family(mother, father);
                        for (Human child : children) family.addChild(child);
                        if (pet != null) family.setPet(pet);
                        families.add(family);
                        mother = null;
                        father = null;
                        children = new ArrayList<>();
                        pet = null;
                    }
                }
            }
        }
    }
    public void loadFamiliesFromDB(String filename) throws IOException, ParseException {
        int itr = 0;
        List<Family> loadedFamilies = new ArrayList<>();
        File file = new File("database", filename);
        System.out.println("Trying to open file: " + file.getAbsolutePath());
        loadFromFile(filename);
    }

    private Human parseHumanBlock(BufferedReader reader) throws IOException, ParseException {
        String name = "", surname = "", birthDate = "";
        int iq = 0;
        HashMap<DayOfWeek, String> schedule = null;
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("Name='")) name = extractValue(line);
            else if (line.startsWith("Surname='")) surname = extractValue(line);
            else if (line.startsWith("BirthDate="))
                birthDate = line.substring(line.indexOf("=") + 1, line.indexOf(","));
            else if (line.startsWith("Iq=")) iq = Integer.parseInt(line.replaceAll("[^0-9]", ""));
            else if (line.startsWith("Schedule={")) schedule = parseSchedule(line);
            else if (line.startsWith("Schedule=null")) schedule = null;
            if (line.endsWith("}")) break;
        }
        long birthDateLong = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(birthDate).getTime();
        return new Human(name, surname, birthDateLong, iq, schedule);
    }

    private Human parseHumanBlockFromChild(String firstLine, BufferedReader reader) throws IOException, ParseException {
        String name = "", surname = "", birthDate = "";
        int iq = 0;
        HashMap<DayOfWeek, String> scheduleRaw = null;
        String line = firstLine;
        while (true) {
            line = line.trim();
            if (line.startsWith("Name='")) name = extractValue(line);
            else if (line.startsWith("Surname='")) surname = extractValue(line);
            else if (line.startsWith("BirthDate="))
                birthDate = line.substring(line.indexOf("=") + 1, line.indexOf(","));
            else if (line.startsWith("Iq=")) iq = Integer.parseInt(line.replaceAll("[^0-9]", ""));
            else if (line.startsWith("Schedule={")) scheduleRaw = parseSchedule(line);
            else if (line.startsWith("Schedule=null")) scheduleRaw = null;
            if (line.endsWith("}")) break;
            line = reader.readLine();
        }
        long birthDateLong = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(birthDate).getTime();
        HashMap<DayOfWeek, String> schedule = convertSchedule(scheduleRaw);
        return new Human(name, surname, birthDateLong, iq, schedule);
    }

    private HashMap<DayOfWeek, String> convertSchedule(HashMap<DayOfWeek, String> raw) {
        return raw == null ? null : new HashMap<>(raw);
    }


    private static final Map<String, DayOfWeek> UA_DAY_MAP;
    static {
        UA_DAY_MAP = new HashMap<>();
        UA_DAY_MAP.put("понеділок", DayOfWeek.MONDAY);
        UA_DAY_MAP.put("вівторок", DayOfWeek.TUESDAY);
        UA_DAY_MAP.put("середа", DayOfWeek.WEDNESDAY);
        UA_DAY_MAP.put("четвер", DayOfWeek.THURSDAY);
        UA_DAY_MAP.put("п'ятниця", DayOfWeek.FRIDAY);
        UA_DAY_MAP.put("субота", DayOfWeek.SATURDAY);
        UA_DAY_MAP.put("неділя", DayOfWeek.SUNDAY);
    }

    private HashMap<DayOfWeek, String> parseSchedule(String line) {
        HashMap<DayOfWeek, String> schedule = new HashMap<>();
        String content = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
        if (content.trim().isEmpty()) return schedule;
        String[] entries = content.split(",");
        for (String entry : entries) {
            String[] parts = entry.split("=");
            if (parts.length == 2) {
                String uaDay = parts[0].trim();
                String activity = parts[1].trim();
                DayOfWeek day = UA_DAY_MAP.get(uaDay);
                if (day != null) {
                    schedule.put(day, activity);
                }
            }
        }
        return schedule;
    }


    private Pet parsePetBlock(String line) {
        if (line.contains("null")) return null;
        if (line.contains("Dog")) {
            String nickname = extractPetField(line, "nickname");
            int age = Integer.parseInt(extractPetField(line, "age"));
            int trickLevel = Integer.parseInt(extractPetField(line, "trickLevel"));

            return new Dog(nickname, age, trickLevel, new String[]{"давати лапу", "сидіти"});
        }
        return null;
    }

    private String extractValue(String line) {
        int start = line.indexOf("'") + 1;
        int end = line.indexOf("'", start);
        return line.substring(start, end);
    }

    private String extractPetField(String line, String field) {
        int idx = line.indexOf(field + "=");
        if (idx == -1) return "";
        int start = idx + field.length() + 1;
        int end = line.indexOf(",", start);
        if (end == -1) end = line.indexOf("}", start);
        return line.substring(start, end).replaceAll("[^\\wа-яА-Я]", "").trim();
    }

    public void loadData(List<Family> families) {
        this.families = new ArrayList<>(families);
    }

    public String prettyFormat(Family family) {
        StringBuilder sb = new StringBuilder();
        sb.append("Сім'я: \n");
        sb.append("  Мати: ").append(family.getMother()).append("\n");
        sb.append("  Батько: ").append(family.getFather()).append("\n");
        sb.append("  Діти: ").append(family.getChildren()).append("\n");
        sb.append("  Тварина: ").append(family.getPet()).append("\n");
        return sb.toString();
    }

    public void displayAllFamilies() {
        for (int i = 0; i < families.size(); i++) {
            System.out.println((i + 1) + ". " + prettyFormat(families.get(i)));
            System.out.println();
        }
    }

    public Family getFamilyByIndex(int familyIndex) {
        if (familyIndex >= 0 && familyIndex < families.size()) {
            return families.get(familyIndex);
        }
        return null;
    }

    public List<Family> getFamiliesBiggerThan(int count) {
        List<Family> filteredFamilies = families.stream()
                .filter(family -> family.countFamily() > count)
                .collect(Collectors.toList());

        //filteredFamilies.forEach(family ->
        //System.out.println(family + ", members: " + family.countFamily()));

        return filteredFamilies;
    }


    public List<Family> getFamiliesLessThan(int count) {
        List<Family> filteredFamilies = families.stream()
                .filter(family -> family.countFamily() < count)
                .collect(Collectors.toList());

        filteredFamilies.forEach(family ->
                System.out.println(family + ", members: " + family.countFamily()));

        return filteredFamilies;
    }


    public int countFamiliesWithMemberNumber(int count) {
        return (int) families.stream()
                .filter(family -> family.countFamily() == count)
                .count();
    }


    public void deleteAllChildrenOlderThan(int age) {
        families.forEach(family -> {
            List<Human> children = family.getChildren();
            List<Human> childrenToRemove = children.stream()
                    .filter(child -> {
                        LocalDate birthDate = new Date(child.getYear())
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

                        LocalDate currentDate = LocalDate.now();
                        Period period = Period.between(birthDate, currentDate);

                        return period.getYears() > age;
                    })
                    .collect(Collectors.toList());

            childrenToRemove.forEach(family::deleteChild);
        });
    }


    public void addFamily(Family family) {
        families.add(family);
    }

    public List<Family> getAllFamilies() {
        return new ArrayList<>(families);
    }

    public Family getFamilyById(int id) {
        if (id >= 0 && id < families.size()) {
            return families.get(id);
        }
        return null;
    }

    public boolean deleteFamily(int id) {
        if (id >= 0 && id < families.size()) {
            families.remove(id);
            return true;
        }
        return false;
    }

    public boolean deleteFamily(Family family) {
        return families.remove(family);
    }
}