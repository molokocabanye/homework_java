package org.example.animal;

public class Dog extends Animal {
    public static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public String run(int distance) {
        if (distance > 500) {
            return "Собака не может пробежать больше 500 м";
        }
        return "Собака " + name + " пробежал " + distance + " м.";
    }

    @Override
    public String swim(int distance) {
        if (distance > 10) {
            return "Собака не может проплыть больше 10 м";
        }
        return "Собака " + name + " проплыл " + distance + " м.";
    }
}