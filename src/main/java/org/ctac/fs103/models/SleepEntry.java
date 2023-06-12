package org.ctac.fs103.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class SleepEntry {
    private LocalTime startTime;
    private LocalTime endTime;
    private long hoursSlept;
    private LocalDate date;

    public SleepEntry(LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.hoursSlept = 0;
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public long getHoursSlept() {
        return hoursSlept;
    }


    public void setHoursSlept(long hoursSlept) {
        this.hoursSlept = hoursSlept;
    }

    public long calculateHoursSlept() {
        Duration duration = Duration.between(startTime, endTime);

        if (startTime.isAfter(endTime)) {
            setHoursSlept(duration.toHours() + 24);
        } else {
            setHoursSlept(duration.toHours());
        }

        return getHoursSlept();
    }

    @Override
    public String toString() {
        return "{" +
                startTime +
                "," + endTime +
                "," + getHoursSlept() +
                "," + date +
                '}';
    }
}
