package ru.AnimalExplore;

public class Cat extends Animal implements  Run, Jump, Voice  {
    private String name;
    public String color;
    public static final String voice = "Meome";
    public static final int NUMBER_OF_LEGS = 4;

    public int age;

    public Cat(String name) {
        this.name = name;
        this.age = 1;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    // Private Method.
    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Implements from interface voice.
     */
    @Override
    public String voice() {
        return voice;
    }

    /**
     * Implements from Animal.
     */
    @Override
    public int getNumberOfLegs() {
        return NUMBER_OF_LEGS;
    }
}
