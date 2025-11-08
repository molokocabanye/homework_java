package org.example;

public class Product {
    String name;
    String creationDateTime;
    String manufacturer;
    String countryOfProduction;
    int price;
    boolean status;

    public Product(String name, String creationDateTime, String manufacturer,
                   String countryOfProduction, int price, boolean status) {
        this.name = name;
        this.creationDateTime = creationDateTime;
        this.manufacturer = manufacturer;
        this.countryOfProduction = countryOfProduction;
        this.price = price;
        this.status = status;
    }

    public String getInfo() {
        return "Product{" +
                "name='" + name + '\'' +
                ", creationDateTime='" + creationDateTime + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", countryOfProduction='" + countryOfProduction + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
