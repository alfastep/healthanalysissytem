package org.ctac.fs103.services;

import org.ctac.fs103.models.CalorieEntry;
import org.ctac.fs103.models.ExerciseEntry;
import org.ctac.fs103.models.SleepEntry;
import org.ctac.fs103.models.User;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class HealthAnalysisService {
    private Scanner scanner;

    private User user;

    public HealthAnalysisService() {
        this.scanner = new Scanner(System.in);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void caloricBalance(LocalDate day) {
        Map<LocalDate, Integer> intake = new HashMap<>();
        Map<LocalDate, Integer> burned = new HashMap<>();

        for(CalorieEntry entry : user.getCalorieEntries()) {
            LocalDate date = entry.getDate();
            int calories = entry.getCalories();
            if (intake.containsKey(date)) {
                int existingValue = intake.get(date);
                calories += existingValue;
            }

            intake.put(date, calories);
        }

        for(ExerciseEntry entry : user.getExerciseEntries()) {
            LocalDate date = entry.getDate();
            int caloriesBurned = entry.getCaloriesBurned();
            if (burned.containsKey(date)) {
                int existingValue = intake.get(date);
                caloriesBurned += existingValue;
            }

            burned.put(date, caloriesBurned);
        }

        int caloric = intake.get(day) - burned.get(day);

        System.out.println("Daily Caloric Balance is " + caloric);

    }

    public void averageHoursOfSleep(LocalDate day) {
        LocalDate startDate = day.minusDays(7);

        long total = 0;

        for (SleepEntry entry : user.getSleepEntries()) {
            if (entry.getDate().isAfter(startDate) && !entry.getDate().isAfter(day)) {
                total += entry.getHoursSlept();
            }
        }

        System.out.println("Average hours slept in past week is " + total);
    }

    public void promptDate() {
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String input = scanner.nextLine();

        LocalDate date = LocalDate.parse(input);

        caloricBalance(date);
    }

    public void promptSleep() {
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String input = scanner.nextLine();

        LocalDate date = LocalDate.parse(input);

        averageHoursOfSleep(date);
    }

    public void displayExercises() {
        for (ExerciseEntry entry : user.getExerciseEntries()) {
            System.out.println("Exercise: " + entry.getExercise());
            System.out.println("Duration: " + entry.getDuration());
            System.out.println("Calories Burned: " + entry.getCaloriesBurned());
            System.out.println("Category: " + entry.getCategory());
            System.out.println("Date: " + entry.getDate());
            System.out.println();
        }
    }



    public void showMenu() {
        int choice = 0;
        do {
            System.out.println();
            System.out.println("Health Analysis:");
            System.out.println("1. Daily Caloric Balance");
            System.out.println("2. Sleep Analysis");
            System.out.println("3. Exercise Log");
            System.out.println("4. Health Summary");
            System.out.println("0. Exit");

            System.out.println();
            System.out.println("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> promptDate();
                    case 2 -> promptSleep();
                    case 3 -> displayExercises();
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                showMenu();
            }
        } while (choice != 0);
    }
}
