package org.example.animal;

public class Cat extends Animal {
    public boolean isHungry;
    public static int catCount = 0;

    public Cat(String name) {
        super(name);
        this.isHungry = true;
        catCount++;
    }

    @Override
    public String run(int distance) {
        if (distance > 200) {
            return "Кот не может пробежать больше 200 м";
        }
        return "Кот " + name + " пробежал " + distance + " м.";
    }

    @Override
    public String swim(int distance) {
        return "Кот не умеет плавать";
    }

    public void eatFromBowl(Bowl bowl) {
        if (bowl.foodAmount >= 10) {
            bowl.foodAmount -= 10;
            isHungry = false;
        }
    }
}