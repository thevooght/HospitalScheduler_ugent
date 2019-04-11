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
 * @author jrvhulle
 */
public class BlockingScheduleStrategy extends BaseScheduleStrategy {
    
    /**
     * A session is split up in blocks of multiple time slots. All patients of
     * whom the time slots lies within the same block get an appointment time
     * equal to the start time of the block. This rule can be altered by 
     * assuming a different number of slots per block (B). You can assume B 
     * is equal to 2.
     *
     * @param s
     * @param arrivals
     */
    public void fill(Schedule s, Arrival[] arrivals) {
        // TODO: alter the code so we can assume a different number of slots 
        // per block.
        for (int i = 1; i < s.timeSlots.length + 1; i++) {
            Arrival arrival = arrivals[i - 1];
            ScheduleTimeSlot slot = s.timeSlots[i - 1];
            
            // Check if i is an even number. If so, we know that we are at 
            // the second slot of a block.
            if ((i % 2) == 0) {
                ScheduleTimeSlot previousSlot = s.timeSlots[i - 2];
                arrival.tAppointment = previousSlot.tStart;
            } else {
                arrival.tAppointment = slot.tStart;
            }
            
            slot.assignedTo = arrival;
        }

    }
}
