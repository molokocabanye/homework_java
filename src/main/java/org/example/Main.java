package org.example;

public class Main {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("Samsung S24 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 3400, true);
        productsArray[2] = new Product("Samsung S23 Ultra", "01.02.2023",
                "Samsung Corp.", "Korea", 2111, true);
        productsArray[3] = new Product("Samsung S22 Ultra", "01.02.2022",
                "Samsung Corp.", "Korea", 1900, false);
        productsArray[4] = new Product("IPhone 17", "02.02.2025",
                "Apple corp.", "USA", 12000, false);

        for (int i = 0; i < productsArray.length; i++) {
            System.out.println(productsArray[i].getInfo());
        }
    }
}