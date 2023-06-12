package org.ctac.fs103.services;

import org.ctac.fs103.models.CalorieEntry;
import org.ctac.fs103.models.ExerciseEntry;
import org.ctac.fs103.models.SleepEntry;
import org.ctac.fs103.models.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private Map<String, User> users;
    private Scanner scanner;
    private User currentUser;

    public UserService() {
        this.scanner = new Scanner(System.in);
        this.users = new HashMap<>();
        User user1 = new User("stevo6895");
        User user2 = new User("alfastep");

        user1.getCalorieEntries().add(new CalorieEntry("pizza", 700, LocalDate.of(2023, 6, 10)));
        user1.getCalorieEntries().add(new CalorieEntry("apple", 50, LocalDate.of(2023, 6, 10)));
        user1.getCalorieEntries().add(new CalorieEntry("oatmeal", 115, LocalDate.of(2023, 6, 10)));

        user2.getCalorieEntries().add(new CalorieEntry("hamburger", 550, LocalDate.of(2023, 6, 11)));
        user2.getCalorieEntries().add(new CalorieEntry("granola bar", 50, LocalDate.of(2023, 6, 11)));
        user2.getCalorieEntries().add(new CalorieEntry("spaghetti", 400, LocalDate.of(2023, 6, 11)));

        user1.getExerciseEntries().add(new ExerciseEntry("running", 60, 200, "cardio", LocalDate.of(2023, 6, 10)));
        user1.getExerciseEntries().add(new ExerciseEntry("bench press", 20, 50, "strength training", LocalDate.of(2023, 6, 10)));
        user1.getExerciseEntries().add(new ExerciseEntry("squats", 10, 40, "strength training", LocalDate.of(2023, 6, 10)));

        user2.getExerciseEntries().add(new ExerciseEntry("running", 60, 200, "cardio", LocalDate.of(2023, 6, 11)));
        user2.getExerciseEntries().add(new ExerciseEntry("bicep curls", 20, 50, "strength training", LocalDate.of(2023, 6, 11)));
        user2.getExerciseEntries().add(new ExerciseEntry("dumbbell fly", 10, 40, "strength training", LocalDate.of(2023, 6, 11)));

        user1.getSleepEntries().add(new SleepEntry(LocalTime.of(23,0), LocalTime.of(6,0), LocalDate.of(2023, 6, 10)));
        user2.getSleepEntries().add(new SleepEntry(LocalTime.of(22,0), LocalTime.of(6,0), LocalDate.of(2023, 6, 11)));

        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }

    public void addUser(String username, User user) {
        users.put(username, user);
    }

    private String promptUsername() {
        System.out.print("Enter your username: ");
        return scanner.nextLine();
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public boolean verifyUser(String username) {
        return users.containsKey(username);
    }

    public void login() {
        String username = promptUsername();

        User user = getUserByUsername(username);

        if (username != null && verifyUser(username)) {
            // User is logged in successfully
            currentUser = user;
            System.out.println("Logged in as " + username);
        } else {
            // Invalid username or password
            System.out.println("Invalid username or password");
        }
    }

    public void register() {
        String username = promptUsername();

        if (!verifyUser(username)) {
            users.put(username, new User(username));

            // Inform the user that the registration was successful
            System.out.println("Registration successful. Please log in with your new account.");
        } else {
            // Username is not valid (e.g., already exists)
            System.out.println("Username is already taken. Please choose a different username.");
        }
    }

    public void logout() {
        // Logic for user logout
        // Perform any necessary cleanup or session management
        System.out.println("Logged out");
    }

    public void exit() {
        // Logic to exit the application
        System.out.println("Exiting the application");
        System.exit(0);
    }
}
