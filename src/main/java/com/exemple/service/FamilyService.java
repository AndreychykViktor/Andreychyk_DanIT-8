package com.exemple.service;

import com.exemple.*;
import com.exemple.dao.FamilyDao;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;


import java.text.ParseException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class FamilyService {
    private final FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }

    private String prettyFormat(Family family) {
        StringBuilder sb = new StringBuilder();
        sb.append("Мати: ").append(family.getMother()).append(System.lineSeparator());
        sb.append("Батько: ").append(family.getFather()).append(System.lineSeparator());
        sb.append("Діти: [").append(System.lineSeparator());
        for (Human child : family.getChildren()) {
            sb.append("  ").append(child).append(System.lineSeparator());
        }
        sb.append("]").append(System.lineSeparator());
        sb.append("Тварина: ").append(family.getPet()).append(System.lineSeparator());
        return sb.toString();
    }

    public void displayAllFamilies() {
        List<Family> families = familyDao.getAllFamilies();
        for (int i = 0; i < families.size(); i++) {
            System.out.println((i+1) + ": " + families.get(i));
        }
    }

    public List<Family> getFamiliesBiggerThan(int count) {
        List<Family> result = new ArrayList<>();
        for (Family f : familyDao.getAllFamilies()) {
            if (f.countFamily() > count) result.add(f);
        }
        return result;
    }

    public List<Family> getFamiliesLessThan(int count) {
        List<Family> result = new ArrayList<>();
        for (Family f : familyDao.getAllFamilies()) {
            if (f.countFamily() < count) result.add(f);
        }
        return result;
    }

    public int countFamiliesWithMemberNumber(int count) {
        int res = 0;
        for (Family f : familyDao.getAllFamilies()) {
            if (f.countFamily() == count) res++;
        }
        return res;
    }

    public Family createNewFamily(Human mother, Human father) {
        Family family = new Family(mother, father);
        familyDao.saveFamily(family);
        return family;
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    public Family bornChild(Family family, String boyName, String girlName) {
        Random rand = new Random();
        boolean isBoy = rand.nextBoolean();
        String name = isBoy ? boyName : girlName;
        long birthDate = new Date().getTime();
        Human child = new Human(name, family.getFather().getSurname(), birthDate, 0, new HashMap<>());
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    public Family adoptChild(Family family, Human child) {
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    public void deleteAllChildrenOlderThen(int age) {
        for (Family family : familyDao.getAllFamilies()) {
            List<Human> toRemove = new ArrayList<>();
            for (Human child : family.getChildren()) {
                LocalDate birthDate = new Date(child.getYear()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int years = Period.between(birthDate, LocalDate.now()).getYears();
                if (years > age) toRemove.add(child);
            }
            for (Human child : toRemove) family.deleteChild(child);
            familyDao.saveFamily(family);
        }
    }

    public int count() {
        return familyDao.getAllFamilies().size();
    }

    public Family getFamilyById(int id) {
        return familyDao.getFamilyByIndex(id);
    }

    public Set<Pet> getPets(int familyIndex) {
        Family family = familyDao.getFamilyByIndex(familyIndex);
        return family != null ? family.getPets() : Collections.emptySet();
    }

    public void addPet(int familyIndex, Pet pet) {
        Family family = familyDao.getFamilyByIndex(familyIndex);
        if (family != null) {
            family.addPet(pet);
            familyDao.saveFamily(family);
        }
    }


    public void saveToFile(String filename) throws IOException {
        File dir = new File("database");
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Family family : familyDao.getAllFamilies()) {
                writer.write(prettyFormat(family));
                writer.write(System.lineSeparator());
                writer.write("--------------------------------------------------");
                writer.write(System.lineSeparator());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException, ParseException {
        List<Family> currentFamilies = new ArrayList<>(familyDao.getAllFamilies());
        for (Family f : currentFamilies) {
            familyDao.deleteFamily(f);
        }

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
                        familyDao.saveFamily(family);
                        mother = null;
                        father = null;
                        children = new ArrayList<>();
                        pet = null;
                    }
                }
            }
        }
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


}