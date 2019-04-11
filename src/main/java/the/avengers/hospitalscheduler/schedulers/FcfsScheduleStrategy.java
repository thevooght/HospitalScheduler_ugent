/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.schedulers;

import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Schedule;
import the.avengers.hospitalscheduler.primitives.ScheduleTimeSlot;

/**
 *
 * @author user
 */
public class FcfsScheduleStrategy extends BaseScheduleStrategy {

    /**
     * The appointment time is equal to the start time of the assigned time
     * slot.
     *
     * @param s
     * @param arrivals
     */
    public void fill(Schedule s, Arrival[] arrivals) {
        for (int i = 0; i < s.timeSlots.length; i++) { 
            ScheduleTimeSlot slot = s.timeSlots[i];
            Arrival arrival = arrivals[i];

            // Set the arrivals appointment time to the start time of the slot
            // and set the assignedTo field in the slot!
            arrival.tAppointment = slot.tStart;
            slot.assignedTo = arrival;
        }
    }
}
