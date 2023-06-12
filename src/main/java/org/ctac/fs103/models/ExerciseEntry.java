package org.ctac.fs103.models;

import java.time.LocalDate;
public class ExerciseEntry {
    private String exercise;
    private int duration;
    private int caloriesBurned;
    private String category;
    private LocalDate date;

    public ExerciseEntry(String exercise, int duration, int caloriesBurned, String category, LocalDate date) {
        this.exercise = exercise;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.category = category;
        this.date = date;
    }

    public String getExercise() {
        return exercise;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "{" +
                exercise +
                "," + duration +
                "," + caloriesBurned +
                "," + category +
                "," + date +
                '}';
    }
}
