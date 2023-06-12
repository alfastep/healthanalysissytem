package org.ctac.fs103.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<SleepEntry> sleepEntries;
    private List<ExerciseEntry> exerciseEntries;
    List<CalorieEntry> calorieEntries;
    private String username;

    public User(String username) {
        this.username = username;
        this.sleepEntries = new ArrayList<>();
        this.exerciseEntries = new ArrayList<>();
        this.calorieEntries = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SleepEntry> getSleepEntries() {
        return sleepEntries;
    }

    public void setSleepEntries(List<SleepEntry> sleepEntries) {
        this.sleepEntries = sleepEntries;
    }

    public List<ExerciseEntry> getExerciseEntries() {
        return exerciseEntries;
    }

    public void setExerciseEntries(List<ExerciseEntry> exerciseEntries) {
        this.exerciseEntries = exerciseEntries;
    }

    public List<CalorieEntry> getCalorieEntries() {
        return calorieEntries;
    }

    public void setCalorieEntries(List<CalorieEntry> calorieEntries) {
        this.calorieEntries = calorieEntries;
    }

    public int totalHoursSlept() {
        int total = 0;
        for (SleepEntry entry : getSleepEntries()) {
            total += entry.getHoursSlept();
        }

        return total;
    }

    public int totalCaloriesBurned() {
        int total = 0;
        for (ExerciseEntry entry : getExerciseEntries()) {
            total += entry.getCaloriesBurned();
        }

        return total;
    }

    public int totalCalories() {
        int total = 0;
        for (CalorieEntry entry : getCalorieEntries()) {
            total += entry.getCalories();
        }

        return total;
    }
}
