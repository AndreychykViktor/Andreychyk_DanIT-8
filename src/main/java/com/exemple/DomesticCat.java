package com.exemple;

    public class DomesticCat extends Pet implements Foulable {
        private String breed;

        public DomesticCat(String nickname, int age, int trickLevel, String[] habits) {
            super("DomesticCat", nickname, age, trickLevel, habits);
            setBreedBasedOnSpecies();
        }

        public DomesticCat(String nickname, int age, int trickLevel) {
            super("DomesticCat", nickname, age, trickLevel, null);
            setBreedBasedOnSpecies();
        }

        public DomesticCat(String nickname, int age) {
            super("DomesticCat", nickname, age, 0, null);
            setBreedBasedOnSpecies();
        }

        public DomesticCat(String nickname) {
            super("DomesticCat", nickname, 0, 0, null);
            setBreedBasedOnSpecies();
        }

        private void setBreedBasedOnSpecies() {
            if (getSpecies() == null || "UNKNOWN".equals(getSpecies())) {
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