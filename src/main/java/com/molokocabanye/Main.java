package com.molokocabanye;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(booleanSum(10, 1));
        checkNumber(0);
        System.out.println(booleanNumber(0));
        printStrings("Hello", 3);
        System.out.println(booleanYears(2020));
        arrayZeroOne();
        arrayHundred();
        arrayCycle();
        arrayMatrix();
        System.out.println(Arrays.toString(twoArguments(5, 1)));
    }

    //1
    public static void printThreeWords() {
        System.out.print("Orange\n" + "Banana\n" + "Apple\n");
    }

    //2
    public static void checkSumSign() {
        int a = 0;
        int b = 2;
        int result = (a + b);

        if (result >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    //3
    public static void printColor() {
        int value = 0;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    //4
    public static void compareNumbers() {
        int a = 9;
        int b = 8;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    //5
    public static boolean booleanSum(int a, int b) {
        int sum = (a + b);

        return sum >= 10 && sum <= 20;
    }

    //6
    public static void checkNumber(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    //7
    public static boolean booleanNumber(int number) {
        return number < 0;
    }

    //8
    public static void printStrings(String text, int count) {
        for (int i = 1; i <= count; i++) {
            System.out.println(text);
        }
    }

    //9
    public static boolean booleanYears(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }

    //10
    public static void arrayZeroOne() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else if (arr[i] == 1) {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //11
    public static void arrayHundred() {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //12
    public static void arrayCycle() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //13
    public static void arrayMatrix(){
        int [][] matrix = new int[5][5];

        int length = matrix.length;
        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                if (row == col || col == length - 1 - row) {
                    matrix[row][col] = 1;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print("[" + matrix[i][j] + "] ");
            }
            System.out.println();
        }
    }

    //14
    public static int[] twoArguments(int len, int initialValue){
        int[] arr = new int[len];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }

        return arr;
    }
}