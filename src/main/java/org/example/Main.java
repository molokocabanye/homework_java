package org.example;

class MyArraySizeException extends Exception {
    MyArraySizeException(String msg) {
        super(msg);
    }
}

class MyArrayDataException extends Exception {
    int row;
    int col;
    String value;

    MyArrayDataException(int r, int c, String v) {
        super("В ячейке [" + r + "][" + c + "] лежит не число: '" + v + "'");
        row = r;
        col = c;
        value = v;
    }
}

public class Main {

    static int sumArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4) {
            throw new MyArraySizeException("Нужно 4 строки, а есть " + arr.length);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException("В строке " + i + " нужно 4 столбца, а есть " + arr[i].length);
            }
        }

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    result += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, arr[i][j]);
                }
            }
        }

        return result;
    }

    static void showArrayIndexError() {
        int[] numbers = {10, 20, 30, 40, 50};

        try {
            System.out.println("Берем элемент с индексом 10...");
            int x = numbers[10];
            System.out.println("Значение: " + x);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка! Такого индекса нет: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Тест 1 - Хороший массив:");
        String[][] goodArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = sumArray(goodArray);
            System.out.println("Сумма: " + sum);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\nТест 2 - Массив не 4x4:");
        String[][] smallArray = {
                {"1", "2"},
                {"3", "4"}
        };

        try {
            int sum = sumArray(smallArray);
            System.out.println("Сумма: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

        System.out.println("\nТест 3 - В массиве есть текст:");
        String[][] textArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "семь", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = sumArray(textArray);
            System.out.println("Сумма: " + sum);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }

        System.out.println("\nТест 4 - ArrayIndexOutOfBoundsException:");
        showArrayIndexError();
    }
}