package org.ctac.fs103;

import org.ctac.fs103.models.User;
import org.ctac.fs103.services.*;
import org.ctac.fs103.utilities.FileManager;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        MenuService menuService = new MenuService();
        CalorieEntryService calorieEntryService = new CalorieEntryService();
        ExerciseEntryService exerciseEntryService = new ExerciseEntryService();
        SleepEntryService sleepEntryService = new SleepEntryService();
        HealthAnalysisService healthAnalysisService = new HealthAnalysisService();
        FileManager fileManager = new FileManager(userService);
        fileManager.loadData();

        while (true) {
            // Show the main menu and get the user's choice
            int choice = menuService.showMainMenuAndGetChoice();

            User currentUser = userService.getCurrentUser();

            // Use a switch statement to execute the action corresponding to the user's choice
            switch (choice) {
                case 1 -> {
                    userService.login();

                }
                case 2 -> userService.register();
                case 3 -> {
                    calorieEntryService.setUser(currentUser);
                    calorieEntryService.showMenu();
                    fileManager.saveData();
                }
                case 4 -> {
                    exerciseEntryService.setUser(currentUser);
                    exerciseEntryService.showMenu();
                    fileManager.saveData();
                }
                case 5 -> {
                    sleepEntryService.setUser(currentUser);
                    sleepEntryService.showMenu();
                    fileManager.saveData();
                }
                case 6 -> {
                    healthAnalysisService.setUser(currentUser);
                    healthAnalysisService.showMenu();
                }
                case 7 -> fileManager.saveData();
                case 8 -> userService.logout();
                case 0 -> userService.exit();
                default -> {
                    if (choice != -1) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            }

        }
    }
}