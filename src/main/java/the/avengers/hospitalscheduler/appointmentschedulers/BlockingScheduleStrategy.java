/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.appointmentschedulers;

import java.util.Arrays;
import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Day;
import the.avengers.hospitalscheduler.primitives.TimeSlot;

/**
 *
 * @author jrvhulle
 */
public class BlockingScheduleStrategy extends BaseScheduleStrategy {

    /**
     * A session is split up in blocks of multiple time slots. All patients of
     * whom the time slots lies within the same block get an appointment time
     * equal to the start time of the block. This rule can be altered by
     * assuming a different number of slots per block (B). You can assume B is
     * equal to 2.
     *
     * @param s schedule to fill in.
     * @param arrivals elective arrivals only! (Do not include urgency patients)
     * @return
     */
    public Arrival[] fill(Day s, Arrival[] arrivals) {
        int B = 2; // Number of slots per block
        int patient = 0;
        Arrival[] unassignedArrivals = {};

        for (int i = 1; i < s.timeSlots.length + 1; i++) {
            TimeSlot slot = s.timeSlots[i - 1];

            if (slot.reservedForUrgent) {
                continue; // Skip this timeslot
            }

            // Not enough arrivals to fill all the normal timeslots, stop. 
            if (arrivals.length <= patient) {
                break;
            }

            Arrival arrival = arrivals[patient];

            // Only assign patients to slots after tPhoneCall.
            if (arrival.tPhoneCall.compareTo(slot.tStart) > 0) {
                continue; // skip this timeslot
            }

            // Check if i is a multiple of B (2). If so, we know that we are at 
            // the second slot of a block.
            if ((i % B) == 0) {
                TimeSlot previousSlot = s.timeSlots[i - B];
                arrival.tAppointment = previousSlot.tStart;
            } else {
                arrival.tAppointment = slot.tStart;
            }

            arrival.tTimeSlot = slot.tStart;
            slot.assignedTo = arrival;
            // Only move to the next patient, if we're sure it was assigned a slot.
            patient++;
        }

        // If a patient can't get treated on the same day as their phone call, 
        // they should be carried over to the next day.
        if (arrivals.length > patient) {
            unassignedArrivals = Arrays.copyOfRange(arrivals, patient, arrivals.length);
        }

        return unassignedArrivals;

    }
}
