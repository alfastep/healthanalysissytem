package org.ctac.fs103.models;

import java.time.LocalDate;

public class CalorieEntry {
    private String food;
    private int calories;
    private LocalDate date;

    public CalorieEntry(String food, int calories, LocalDate date) {
        this.food = food;
        this.calories = calories;
        this.date = date;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                food +
                "," + calories +
                "," + date +
                '}';
    }
}
