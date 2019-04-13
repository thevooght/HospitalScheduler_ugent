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
 * A time slot has a starting timestamp, a duration and can either be reserved
 * for urgencies, or assigned to an elective patient/arrival.
 *
 * @author TonyStark
 */
public class TimeSlot {

    public boolean reservedForUrgent = false;

    // tStart contains day & time!
    public Instant tStart;
    public Duration duration = Duration.ofMinutes(15);

    public Instant tEnd() {
        return tStart.plus(this.duration);
    }

    // The AppointmentScheduleStrategy class will assign an Arrival/Patient to the timeslot.
    public Arrival assignedTo;
}
