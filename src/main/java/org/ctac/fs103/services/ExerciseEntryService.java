package org.ctac.fs103.services;

import org.ctac.fs103.models.CalorieEntry;
import org.ctac.fs103.models.ExerciseEntry;
import org.ctac.fs103.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseEntryService {
    private Scanner scanner;
    private User user;
    public ExerciseEntryService() {
        this.scanner = new Scanner(System.in);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showEntries() {
        for (ExerciseEntry entry : user.getExerciseEntries()) {
            System.out.println(entry.toString());
        }
    }

    public void addEntry(ExerciseEntry entry) {
        user.getExerciseEntries().add(entry);
    }


    public String promptExercise() {
        System.out.print("Enter exercise: ");
        return scanner.nextLine();
    }

    public int promptDuration() {
        System.out.println("Enter the duration in minutes: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public int promptCaloriesBurned() {
        System.out.println("Enter the amount of calories burned: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String promptCategory() {
        System.out.print("Enter category for this exercise: ");
        return scanner.nextLine();
    }

    public void enterExerciseEntry() {
        String exercise = promptExercise();
        int duration = promptDuration();
        int caloriesBurned = promptCaloriesBurned();
        String category = promptCategory();


        addEntry(new ExerciseEntry(exercise, duration, caloriesBurned, category, LocalDate.now()));
    }

    public void showMenu() {
        int choice = 0;
        do {
            System.out.println();
            System.out.println("Exercise Activities Menu:");
            System.out.println("1. New Entry");
            System.out.println("2. Show Entries");
            System.out.println("0. Exit");

            System.out.println();
            System.out.println("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> enterExerciseEntry();
                    case 2 -> showEntries();
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
