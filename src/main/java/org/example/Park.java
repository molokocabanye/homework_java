package org.example;

public class Park {
    String parkName;
    Attraction[] attractions;

    public Park(String parkName, Attraction[] attractions) {
        this.parkName = parkName;
        this.attractions = attractions;
    }

    public static class Attraction {
        String name;
        String workingTime;
        int price;

        public Attraction(String name, String workingTime, int price) {
            this.name = name;
            this.workingTime = workingTime;
            this.price = price;
        }
    }
}
