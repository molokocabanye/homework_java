package org.example.animal;

public class Bowl {
    public int foodAmount;

    public Bowl(int foodAmount) {
        if (foodAmount < 0) {
            this.foodAmount = 0;
        } else {
            this.foodAmount = foodAmount;
        }
    }

    public void addFood(int food) {
        foodAmount += food;
    }
}