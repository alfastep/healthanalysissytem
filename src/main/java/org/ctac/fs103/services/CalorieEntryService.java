package org.ctac.fs103.services;

import org.ctac.fs103.models.CalorieEntry;
import org.ctac.fs103.models.User;
import org.ctac.fs103.utilities.FileManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalorieEntryService {
    private Scanner scanner;
    private User user;

    public CalorieEntryService() {
        this.scanner = new Scanner(System.in);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showEntries() {
        for (CalorieEntry entry : user.getCalorieEntries()) {
            System.out.println(entry.toString());
        }
    }

    public void addEntry(CalorieEntry entry) {
        user.getCalorieEntries().add(entry);
    }

    public String promptFood() {
        System.out.print("Enter food name: ");
        return scanner.nextLine();
    }

    public int promptCalories() {
        System.out.println("Enter amount of calories: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void enterCaloriesIntake() {
        String food = promptFood();
        int calories = promptCalories();

        addEntry(new CalorieEntry(food, calories, LocalDate.now()));
    }

    public void showMenu() {
        int choice = 0;
        do {
            System.out.println();
            System.out.println("Daily Calorie Intake Menu:");
            System.out.println("1. New Entry");
            System.out.println("2. Show Entries");
            System.out.println("0. Exit");

            System.out.println();
            System.out.println("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> enterCaloriesIntake();
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
