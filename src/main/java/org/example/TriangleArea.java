package org.example;

public class TriangleArea {

    public static int calculateAreaByBaseAndHeight(int base, int height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base or height cannot be negative or zero");
        }
        return (base * height) / 2;
    }
}
