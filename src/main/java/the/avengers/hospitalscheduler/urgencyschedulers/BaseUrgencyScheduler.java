/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.urgencyschedulers;

import java.util.Arrays;
import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Day;
import the.avengers.hospitalscheduler.primitives.TimeSlot;
import the.avengers.hospitalscheduler.primitives.Week;

/**
 * The abstract base class for the urgency schedulers which reserves/mark
 * specific time slots for urgent patients.
 *
 * This class is to be extended for the 3 different strategies:
 *
 * 1. Evenly distributed over morning & afternoon
 *
 * 2. Evenly distributed over opening hours
 *
 * 3. After a block of six normal slots
 *
 * @author Tony Stark
 */
public abstract class BaseUrgencyScheduler {

    public abstract void fill(Week w, int nUrgencySlots);

    /**
     * Note that urgent patients arriving outside the opening hours of the
     * outpatient department (e.g. on Thursday afternoon or at night) should not
     * be considered as they are examined in the emergency department of the
     * hospital.
     */
    public void assign(Day d, Arrival[] arrivals) {
        // Make sure that the urgent arrivals are sorted correctly.
        Arrays.sort(arrivals, Arrival.compareByArrival());

        Arrival[] overtimers = {};
        for (Arrival patient : arrivals) {
            boolean found = false;
            // Try and find an available time slots, after the arrival time of
            // the patient.
            for (TimeSlot slot : d.timeSlots) {
                if (slot.assignedTo == null
                        && slot.tStart.isAfter(patient.tArrival())) {
                    slot.assignedTo = patient;
                    found = true;
                    break;
                }
            }

            // No available timeslot was found for the urgent patients,
            // we need to serve them in overtime :/
            if (!found) {
                overtimers = Arrays.copyOf(overtimers, overtimers.length + 1);
                overtimers[overtimers.length - 1] = patient;
            }
        }

        d.addOverTimeSlotsFor(overtimers);
    }
}
