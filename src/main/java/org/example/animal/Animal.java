package org.example.animal;

public class Animal {
    public String name;
    public static int animalCount = 0;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public String run(int distance) {
        return name + " пробежал " + distance + " м.";
    }

    public String swim(int distance) {
        return name + " проплыл " + distance + " м.";
    }
}