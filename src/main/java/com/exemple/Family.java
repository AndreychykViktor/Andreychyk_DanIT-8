package com.exemple;

import java.util.*;

public class Family {
    private Human mother;
    private Human father;
    private List<Human> children;
    private Set<Pet> pets;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
        this.pets = new HashSet<>();
        this.mother.setFamily(this);
        this.father.setFamily(this);
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public void setPet(Pet pet) {
        this.pets.clear();
        this.pets.add(pet);
    }

    public Pet getPet() {
        return pets.isEmpty() ? null : pets.iterator().next();
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    @Override
    public String toString() {
        return "Family{" +
                "Mother: " + mother +
                ", Father: " + father +
                ", Children: " + (children.isEmpty() ? "No children" : children) +
                ", Pet: " + (pets.isEmpty() ? "No pet" : pets) +
                '}';
    }

    public void addChild(Human child) {
        children.add(child);
        child.setFamily(this);
    }

    public boolean deleteChild(Human child) {
        if (children.isEmpty()) {
            return false;
        }

        boolean isRemoved = children.remove(child);
        if (isRemoved) {
            child.setFamily(null);
        }
        return isRemoved;
    }

    public boolean deleteChild(int index) {
        if (children.isEmpty() || index < 0 || index >= children.size()) {
            return false;
        }

        Human child = children.get(index);
        children.remove(index);
        child.setFamily(null);
        return true;
    }

    public int countFamily() {
        return 2 + children.size();
    }
}