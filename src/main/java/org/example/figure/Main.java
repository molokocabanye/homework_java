package org.example.figure;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5, "Красный", "Черный");
        Rectangle rectangle = new Rectangle(4, 6, "Синий", "Белый");
        Triangle triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        System.out.println("Круг: ");
        circle.printInfo();

        System.out.println("Прямоугольник: ");
        rectangle.printInfo();

        System.out.println("Треугольник: ");
        triangle.printInfo();
    }
}
