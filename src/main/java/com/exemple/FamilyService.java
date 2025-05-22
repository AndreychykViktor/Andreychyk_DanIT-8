package com.exemple;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyService {
    private List<Family> families = new ArrayList<>();


    public void displayAllFamilies() {
        families.forEach(System.out::println);
    }


    public List<Family> getFamiliesBiggerThan(int count) {
        List<Family> filteredFamilies = families.stream()
                .filter(family -> family.countFamily() > count)
                .collect(Collectors.toList());

        filteredFamilies.forEach(family ->
                System.out.println(family + ", members: " + family.countFamily()));

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