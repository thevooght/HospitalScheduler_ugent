/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.appointmentschedulers;

import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Day;
import the.avengers.hospitalscheduler.primitives.TimeSlot;

/**
 *
 * @author user
 */
public class FcfsScheduleStrategy extends BaseScheduleStrategy {

    /**
     * The appointment time is equal to the start time of the assigned time
     * slot.
     *
     * @param s schedule to fill in.
     * @param arrivals elective arrivals only! (Do not include urgency patients)
     */
    public void fill(Day s, Arrival[] arrivals) {
        for (int i = 0; i < s.timeSlots.length; i++) {
            // Not enough arrivals to fill all the timeslots, stop. 
            if (arrivals.length <= i) {
                break;
            }

            TimeSlot slot = s.timeSlots[i];
            Arrival arrival = arrivals[i];

            // Set the arrivals appointment time to the start time of the slot
            // and set the assignedTo field in the slot!
            arrival.tAppointment = slot.tStart;
            slot.assignedTo = arrival;
        }
    }
}
