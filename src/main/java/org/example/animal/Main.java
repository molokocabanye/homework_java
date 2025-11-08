package org.example.animal;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Барсик");
        Dog dog = new Dog("Бобик");

        System.out.println(cat.run(150));
        System.out.println(cat.run(250));
        System.out.println(cat.swim(10));

        System.out.println(dog.run(400));
        System.out.println(dog.run(600));
        System.out.println(dog.swim(5));
        System.out.println(dog.swim(15));

        Bowl bowl = new Bowl(25);

        Cat[] cats = {
                new Cat("Мурзик"),
                new Cat("Васька"),
                new Cat("Рыжик")
        };

        for (Cat c : cats) {
            c.eatFromBowl(bowl);
        }

        System.out.println("Сытость котов:");
        for (Cat c : cats) {
            System.out.println(c.name + ": " + (c.isHungry ? "голоден" : "сыт"));
        }
        System.out.println("Еды в миске осталось: " + bowl.foodAmount);

        bowl.addFood(20);
        System.out.println("После добавления еды: " + bowl.foodAmount);

        System.out.println("Всего животных: " + Animal.animalCount);
        System.out.println("Котов: " + Cat.catCount);
        System.out.println("Собак: " + Dog.dogCount);
    }
}