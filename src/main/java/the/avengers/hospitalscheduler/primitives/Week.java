/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

/**
 * A Week is a collection of daily schedules with the appropriate available time
 * slots.
 *
 * @author user
 */
public class Week {

    /**
     * Every day gets its own daily schedule with time slots.
     */
    public Day[] days = new Day[6];

    public Week() {
        this.addDay(DayOfWeek.MONDAY);
        this.addDay(DayOfWeek.TUESDAY);
        this.addDay(DayOfWeek.WEDNESDAY);
        this.addDay(DayOfWeek.THURSDAY);
        this.addDay(DayOfWeek.FRIDAY);
        this.addDay(DayOfWeek.SATURDAY);
        // this.addDay(DayOfWeek.SUNDAY); // Closed on sunday.
    }

    private void addDay(DayOfWeek day) {
        this.days[day.getValue() - 1] = new Day(day);
    }

    public boolean isCompleted() {
        for (Day day : this.days) {
            if (!day.isCompleted()) {
                return false;
            }
        }
        return true;
    }
}
