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
 *
 * @author user
 */
public class Week {

    public Schedule[] days = new Schedule[6];

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
        Schedule s = new Schedule();
        this.days[day.getValue() - 1] = s;
        this.addSlots(day);
    }

    private void addSlots(DayOfWeek day) {

        // Only open for half a day on Thursday and Saturday (16 slots) else 32.
        int iSlots = (day.equals(DayOfWeek.THURSDAY) || day.equals(DayOfWeek.SATURDAY)) ? 16 : 32;
        ScheduleTimeSlot[] slots = new ScheduleTimeSlot[iSlots];

        for (int i = 0; i < slots.length; i++) {
            slots[i] = new ScheduleTimeSlot();
            // System.out.println("Setting tStart of slot i=" + i + " max=" + slots.length + " slot[i] == null? " + (slots[i] == null));
            slots[i].tStart = this.calculateStartOfSlot(day, i, slots[i].duration);
        }
        this.days[day.getValue() - 1].timeSlots = slots;
    }

    private Instant calculateStartOfSlot(DayOfWeek day, int slotIndex, Duration duration) {
        // We pick an initial point in time, in this case we picked the 8th of April 2019
        // because it's a monday. If we need the start time of a Tuesday, then we just add 1 day to it.
        // Same logic for the hour & minute timestamp.
        Instant tStart = Instant
                .parse("2019-04-08T08:00:00.00Z")
                .plus(day.getValue() - 1, ChronoUnit.DAYS)
                .plus(duration.multipliedBy((long) slotIndex));

        // We need to "skip" the time occupied by the lunch break.
        // Add the lunchbreak time to all tStart's after the lunchbreak.
        if (tStart.atZone(ZoneOffset.UTC).getHour() >= 12) {
            tStart = tStart.plus(1, ChronoUnit.HOURS);
        }

        return tStart;
    }
}
