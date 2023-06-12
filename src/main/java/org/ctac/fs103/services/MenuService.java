package org.ctac.fs103.services;

import java.util.Scanner;

public class MenuService {
    private Scanner scanner;

    public MenuService() {
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. Daily Calorie Intake");
        System.out.println("4. Exercise Activities");
        System.out.println("5. Sleep Records");
        System.out.println("6. Health Data Analysis");
        System.out.println("7. Save");
        System.out.println("8. Log out");
        System.out.println("0. Exit");
    }

    public int getChoice() {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 0 || choice > 8) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number from 0 to 7.");
            return -1;
        }
        return choice;
    }

    public int showMainMenuAndGetChoice() {
        // Display the main menu
        showMainMenu();

        // Get the user's choice
        int choice = getChoice();

        return choice;
    }
}
