package org.ctac.fs103.services;

import org.ctac.fs103.models.CalorieEntry;
import org.ctac.fs103.models.ExerciseEntry;
import org.ctac.fs103.models.SleepEntry;
import org.ctac.fs103.models.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SleepEntryService {
    private Scanner scanner;

    private User user;

    public SleepEntryService() {
        this.scanner = new Scanner(System.in);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void addEntry(SleepEntry entry) {
        user.getSleepEntries().add(entry);
    }

    public void showEntries() {
        for (SleepEntry entry : user.getSleepEntries()) {
            System.out.println(entry.toString());
        }
    }


    public LocalTime promptStartTime() {
        LocalTime startTime = null;
        boolean validFormat = false;

        while (!validFormat) {
            System.out.print("Enter time you went to sleep (in military time HH:mm): ");
            String startTimeStr = scanner.nextLine();

            try {
                startTime = LocalTime.parse(startTimeStr);
                validFormat = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter the time in HH:mm format.");
            }
        }

        return startTime;
    }

    public LocalTime promptEndTime() {
        LocalTime startTime = null;
        boolean validFormat = false;

        while (!validFormat) {
            System.out.print("Enter time you woke up (in military time HH:mm): ");
            String startTimeStr = scanner.nextLine();

            try {
                startTime = LocalTime.parse(startTimeStr);
                validFormat = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter the time in HH:mm format.");
            }
        }

        return startTime;
    }

    public void enterSleepEntry() {
        LocalTime startTime = promptStartTime();
        LocalTime endTime = promptEndTime();

        SleepEntry sleepEntry = new SleepEntry(startTime, endTime, LocalDate.now());
        addEntry(sleepEntry);

    }

    public void showMenu() {
        int choice = 0;
        do {
            System.out.println();
            System.out.println("Sleep Records:");
            System.out.println("1. New Entry");
            System.out.println("2. Show Entries");
            System.out.println("0. Exit");

            System.out.println();
            System.out.println("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> enterSleepEntry();
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
