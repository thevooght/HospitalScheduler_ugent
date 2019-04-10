/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import java.time.Duration;
import java.time.Instant;
import the.avengers.hospitalscheduler.primitives.Arrival;

/**
 *
 * @author TonyStark
 */
public class ScheduleTimeSlot {

    public boolean reservedForUrgent = false;

    // When does the time slot start and how long should it be.
    public Instant tStart;
    public Duration duration;

    // The ScheduleStrategy class will assign an Arrival/Patient to the timeslot.
    public Arrival assignedTo;
}
