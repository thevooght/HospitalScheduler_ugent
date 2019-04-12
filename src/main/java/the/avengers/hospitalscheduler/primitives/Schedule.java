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
 * Schedule is created for every day and contains the available time slots.
 *
 * @author Tony Stark
 */
public class Schedule {

    public ScheduleTimeSlot[] timeSlots;

}
