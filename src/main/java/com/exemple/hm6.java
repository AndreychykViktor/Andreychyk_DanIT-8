package com.exemple;

import java.util.Arrays;
import java.util.Objects;

public class hm6 {
    public enum Species {
        DOG, CAT, BIRD, FISH, HAMSTER, RABBIT, TURTLE, UNKNOWN
    }

    public enum OutWorking {
        Play_Chess, Wolking, Visit_Grandma, Gim, Romantic_Dinner
    }

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    interface Foulable {
        void foul();
    }

    abstract class Pet {
        private Species species;
        private String nickname;
        private int age;
        private int trickLevel;
        private String[] habits;

        public Pet() {
            // Default constructor
            setSpeciesBasedOnClass();
        }

        public Pet(String nickname) {
            this.nickname = nickname;
            setSpeciesBasedOnClass();
        }

        public Pet(String nickname, int age, int trickLevel, String[] habits) {
            this.nickname = nickname;
            this.age = age;
            this.trickLevel = trickLevel;
            this.habits = habits;
            setSpeciesBasedOnClass();
        }

        private void setSpeciesBasedOnClass() {
            if (this instanceof Fish) {
                this.species = Species.FISH;
            } else if (this instanceof DomesticCat) {
                this.species = Species.CAT;
            } else if (this instanceof Dog) {
                this.species = Species.DOG;
            } else if (this instanceof RoboCat) {
                this.species = Species.CAT; // RoboCat is still a cat type
            } else {
                this.species = Species.UNKNOWN;
            }
        }


        public void eat() {
            System.out.println("Я їм!");
        }

        public void respond() {
            System.out.println("Привіт, хазяїн. Я - " + nickname + ". Я скучив!");
        }

        @Override
        public String toString() {
            return "Pet{species='" + (species != null ? formatSpecies(species) : null) + "', nickname='" + nickname + "', age=" + age + ", trickLevel=" + trickLevel + ", habits=" + Arrays.toString(habits) + "}";
        }


        private String formatSpecies(Species species) {
            String name = species.name();
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pet)) return false;
            Pet pet = (Pet) o;
            return age == pet.age && trickLevel == pet.trickLevel && Objects.equals(species, pet.species) && Objects.equals(nickname, pet.nickname) && Arrays.equals(habits, pet.habits);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(species, nickname, age, trickLevel);
            result = 31 * result + Arrays.hashCode(habits);
            return result;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing Pet object: " + this.toString());
            super.finalize();
        }

        public Species getSpecies() {
            return species;
        }

        public void setSpecies(Species species) {
            this.species = species;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getTrickLevel() {
            return trickLevel;
        }

        public void setTrickLevel(int trickLevel) {
            this.trickLevel = trickLevel;
        }

        public String[] getHabits() {
            return habits;
        }

        public void setHabits(String[] habits) {
            this.habits = habits;
        }


    }

    class Fish extends Pet {
        private String color;

        public Fish(String nickname, int age, int trickLevel, String[] habits) {
            super(nickname, age, trickLevel, habits);
            setColorBasedOnSpecies();
        }

        public Fish(String nickname) {
            super(nickname);
            setColorBasedOnSpecies();
        }

        public Fish(String nickname, int age) {
            super(nickname, age, 0, null);
            setColorBasedOnSpecies();
        }

        public Fish(String nickname, int age, int trickLevel) {
            super(nickname, age, trickLevel, null);
            setColorBasedOnSpecies();
        }

        private void setColorBasedOnSpecies() {
            if (getSpecies() == Species.UNKNOWN) {
                this.color = "UNKNOWN";
            } else {
                this.color = "Blue";
            }
        }

        @Override
        public void respond() {
            System.out.println(getNickname() + ": Буль буль");
        }
    }

    class DomesticCat extends Pet implements Foulable {
        private String breed;

        public DomesticCat(String nickname, int age, int trickLevel, String[] habits) {
            super(nickname, age, trickLevel, habits);
            setBreedBasedOnSpecies();
        }

        public DomesticCat(String nickname, int age, int trickLevel) {
            super(nickname, age, trickLevel, null);
            setBreedBasedOnSpecies();
        }

        public DomesticCat(String nickname, int age) {
            super(nickname, age, 0, null);
            setBreedBasedOnSpecies();
        }

        public DomesticCat(String nickname) {
            super(nickname);
            setBreedBasedOnSpecies();
        }

        private void setBreedBasedOnSpecies() {
            if (getSpecies() == Species.UNKNOWN) {
                this.breed = "UNKNOWN";
            } else {
                this.breed = "British Shorthair";
            }
        }

        @Override
        public void respond() {
            System.out.println("Мяу. Я - " + getNickname() + ". Я скучив!");
        }

        @Override
        public void foul() {
            System.out.println("Я - " + getNickname() + ". Треба сховати сліди злочину");
        }
    }

    class Dog extends Pet implements Foulable {
        private String breed;

        public Dog(String nickname, int age, int trickLevel, String[] habits) {
            super(nickname, age, trickLevel, habits);
            setBreedBasedOnSpecies();
        }

        public Dog(String nickname) {
            super(nickname);
            setBreedBasedOnSpecies();
        }

        public Dog(String nickname, int age) {
            super(nickname, age, 0, null);
            setBreedBasedOnSpecies();
        }

        public Dog(String nickname, int age, int trickLevel) {
            super(nickname, age, trickLevel, null);
            setBreedBasedOnSpecies();
        }

        private void setBreedBasedOnSpecies() {
            if (getSpecies() == Species.UNKNOWN) {
                this.breed = "UNKNOWN";
            } else {
                this.breed = "Pitbull";
            }
        }

        @Override
        public void respond() {
            System.out.println("Гав-гав! Я - " + getNickname() + ". Я скучив!");
        }

        @Override
        public void foul() {
            System.out.println("Я - " + getNickname() + ". Я розбив мамин вазон");
        }
    }

    class RoboCat extends Pet implements Foulable {
        private String color;

        public RoboCat(String nickname, int age, int trickLevel, String[] habits) {
            super(nickname, age, trickLevel, habits);
            setColorBasedOnSpecies();
        }

        public RoboCat(String nickname) {
            super(nickname);
            setColorBasedOnSpecies();
        }

        public RoboCat(String nickname, int age) {
            super(nickname, age, 0, null);
            setColorBasedOnSpecies();
        }

        public RoboCat(String nickname, int age, int trickLevel) {
            super(nickname, age, trickLevel, null);
            setColorBasedOnSpecies();
        }

        private void setColorBasedOnSpecies() {
            if (getSpecies() == Species.UNKNOWN) {
                this.color = "UNKNOWN";
            } else {
                this.color = "Silver";
            }
        }

        @Override
        public void respond() {
            System.out.println("Біп-біп! Я - " + getNickname() + ". Я скучив!");
        }

        @Override
        public void foul() {
            System.out.println("Я - " + getNickname() + ". Я взламав WIFI сусіда");
        }
    }

    public class Human {
        private String name;
        private String surname;
        private int year;
        private int iq;
        private String[][] schedule;
        private Family family;
        private Pet pet;


        public Human(String name, String surname, int year, int iq, String[][] schedule) {
            this.name = name;
            this.surname = surname;
            this.year = year;
            this.iq = iq;
            this.schedule = schedule;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getIq() {
            return iq;
        }

        public void setIq(int iq) {
            this.iq = iq;
        }

        public String[][] getSchedule() {
            return schedule;
        }

        public void setSchedule(String[][] schedule) {
            this.schedule = schedule;
        }

        public Family getFamily() {
            return family;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }

        public String getNickname() {
            return pet.getNickname();
        }

        public void setFamily(hm6.Family family) {
            this.family = family;
        }

        // Greet pet method
        public void greetPet() {
            if (family != null && family.getPet() != null) {
                System.out.println("Привіт, " + family.getPet().getNickname());
            } else {
                System.out.println("У мене немає улюбленця.");
            }
        }

        // Describe pet method
        public void describePet() {
            if (family != null && family.getPet() != null) {
                Pet pet = family.getPet();
                String cleverness = pet.getTrickLevel() > 50 ? "дуже хитрий" : "майже не хитрий";
                System.out.println("У мене є " + pet.getSpecies() + ", їй " + pet.getAge() + " років, він " + cleverness + ".");
            } else {
                System.out.println("У мене немає улюбленця.");
            }
        }

        @Override
        public String toString() {
            return "Human{name='" + name + "', surname='" + surname + "', year=" + year + ", iq=" + iq + ", schedule=" + Arrays.deepToString(schedule) + "}";
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing Human object: " + this.toString());
            super.finalize();
        }


        // equals and hashCode methods
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Human)) return false;
            Human human = (Human) o;
            return year == human.year && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Arrays.deepEquals(schedule, human.schedule);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(name, surname, year, iq);
            result = 31 * result + Arrays.deepHashCode(schedule);
            return result;
        }

    }

    final class Man extends Human {
        public Man(String name, String surname, int year, int iq, String[][] schedule) {
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

        public void repairCar() {
            System.out.println("Я " + getName() + " " + getSurname() + ", ремонтую машину");
        }
    }

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

    public class Family {
        private Human mother;
        private Human father;
        private Human[] children;
        private Pet pet;


        public Family(Human mother, Human father) {
            this.mother = mother;
            this.father = father;
            this.children = new Human[0];
            this.mother.setFamily(this);
            this.father.setFamily(this);
        }

        // Getters and setters
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

        public Human[] getChildren() {
            return children;
        }

        public void setChildren(Human[] children) {
            this.children = children;
        }

        public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Mother: ").append(mother).append("\n");
            sb.append("Father: ").append(father).append("\n");
            sb.append("Children: ");
            if (children == null || children.length == 0) {
                sb.append("No children\n");
            } else {
                for (Human child : children) {
                    sb.append(child).append("\n");
                }
            }
            sb.append("Pet: ");
            if (pet == null) {
                sb.append("No pet");
            } else {
                sb.append(pet);
            }
            return sb.toString();
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Finalizing object: " + this);
            super.finalize();
        }

        // Add a child to the family
        public void addChild(Human child) {
            if (children == null) {
                children = new Human[1];
                children[0] = child;
            } else {
                Human[] newChildren = new Human[children.length + 1];
                System.arraycopy(children, 0, newChildren, 0, children.length);
                newChildren[children.length] = child;
                children = newChildren;
            }
            child.setFamily(this);
        }

        // Delete a child by reference
        public boolean deleteChild(Human child) {
            if (children == null || children.length == 0) {
                return false;
            }

            int index = -1;
            for (int i = 0; i < children.length; i++) {
                if (children[i].equals(child)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                return false;
            }

            return deleteChild(index);
        }

        // Delete a child by index
        public boolean deleteChild(int index) {
            if (children == null || index < 0 || index >= children.length) {
                return false;
            }

            Human[] newChildren = new Human[children.length - 1];
            for (int i = 0, j = 0; i < children.length; i++) {
                if (i != index) {
                    newChildren[j++] = children[i];
                } else {
                    children[i].setFamily(null); // Remove family reference
                }
            }

            children = newChildren;
            return true;
        }

        // Count family members
        public int countFamily() {
            int count = 2; // Mother and father
            if (children != null) {
                count += children.length;
            }
            return count;
        }

    }
}
