package org.ctac.fs103.utilities;

import org.ctac.fs103.models.CalorieEntry;
import org.ctac.fs103.models.ExerciseEntry;
import org.ctac.fs103.models.SleepEntry;
import org.ctac.fs103.models.User;
import org.ctac.fs103.services.UserService;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class FileManager {
    UserService userService;

    public FileManager(UserService userService) {
        this.userService = userService;
    }


    public void saveData() {
        try {
            // Write the users and their health data to a file(s)
            FileWriter writer = new FileWriter("userdata.txt");
            for (Map.Entry<String, User> entry : userService.getUsers().entrySet()) {
                writer.write(entry.getValue().getUsername() + "," + entry.getValue().getCalorieEntries() + "," + entry.getValue().getExerciseEntries() + "," + entry.getValue().getSleepEntries() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\[");
                String username = parts[0];
                User user = userService.getUserByUsername(username);
                if (user == null) {
                    user = new User(username);
                    userService.getUsers().put(username,user);
                }

                // Parse calorie entries
                String calorieEntriesStr = parts[1];
                String[] calorieEntriesArr = calorieEntriesStr.substring(1, calorieEntriesStr.length() - 1).split("\\},\\s*\\{");
                for (String entry : calorieEntriesArr) {
                    String[] splitEntry = entry.split(",\\{");
                    String [] result = splitEntry[0].split(",");
                    String foodName = result[0].replaceAll("\\{", "");
                    int calories = Integer.parseInt(result[1]);
                    LocalDate date = LocalDate.parse(result[2].replaceAll("}", ""));
                    CalorieEntry calorieEntry = new CalorieEntry(foodName, calories, date);

                    if (user.getCalorieEntries().size() == 0) {
                        user.getCalorieEntries().add(calorieEntry);
                    }
                }

                // Parse exercise entries
                String exerciseEntriesStr = parts[2];
                String[] exerciseEntriesArr = exerciseEntriesStr.substring(1, exerciseEntriesStr.length() - 1).split("\\},\\s*\\{");
                for (String entry : exerciseEntriesArr) {
                    String[] splitEntry = entry.split(",\\{");
                    String [] result = splitEntry[0].split(",");
                    String exerciseName = result[0].replaceAll("\\{", "");;
                    int duration = Integer.parseInt(result[1]);
                    int caloriesBurned = Integer.parseInt(result[2]);
                    String category = result[3];
                    LocalDate date = LocalDate.parse(result[4].replaceAll("}", ""));
                    ExerciseEntry exerciseEntry = new ExerciseEntry(exerciseName, duration, caloriesBurned, category, date);

                    if (user.getExerciseEntries().size() == 0) {
                     user.getExerciseEntries().add(exerciseEntry);
                    }
                }

                // Parse sleep entries
                String sleepEntriesStr = parts[3];
                String[] sleepEntriesArr = sleepEntriesStr.substring(1, sleepEntriesStr.length() - 1).split("\\},\\s*\\{");
                for (String entry : sleepEntriesArr) {
                    String[] splitEntry = entry.split(",\\{");
                    String [] result = splitEntry[0].split(",");
                    LocalTime startTime = LocalTime.parse(result[0].replaceAll("\\{", ""));
                    LocalTime endTime = LocalTime.parse(result[1]);
                    LocalDate date = LocalDate.parse(result[3].replaceAll("}", ""));
                    SleepEntry sleepEntry = new SleepEntry(startTime, endTime, date);
                    if (user.getSleepEntries().size() == 0) {
                        user.getSleepEntries().add(sleepEntry);
                    }
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
