package com.exemple;

    public class Fish extends Pet {
        private String color;

        public Fish(String nickname, int age, int trickLevel, String[] habits) {
            super("Fish", nickname, age, trickLevel, habits);
            setColorBasedOnSpecies();
        }

        public Fish(String nickname) {
            super("Fish", nickname, 0, 0, null);
            setColorBasedOnSpecies();
        }

        public Fish(String nickname, int age) {
            super("Fish", nickname, age, 0, null);
            setColorBasedOnSpecies();
        }

        public Fish(String nickname, int age, int trickLevel) {
            super("Fish", nickname, age, trickLevel, null);
            setColorBasedOnSpecies();
        }

        private void setColorBasedOnSpecies() {
            if (getSpecies() == null || "UNKNOWN".equals(getSpecies())) {
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