package org.example.figure;

public interface Figure {
    double getSquare();

    double getPerimeter();

    String getFillColor();

    String getBorderColor();

    default void printInfo() {
        System.out.println("Площадь: " + getSquare() +
                " | Периметр: " + getPerimeter() +
                " | Цвет фона: " + getFillColor() +
                " | Цвет границ: " + getBorderColor());
    }
}
