/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.appointmentschedulers;

import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Schedule;
import the.avengers.hospitalscheduler.primitives.ScheduleTimeSlot;

/**
 *
 * @author user
 */
public class BaileyWelchScheduleStrategy extends BaseScheduleStrategy {

    /**
     * The appointment time of the first K patients of a session is equal to the
     * start time of the first time slot of the particular day . All subsequent
     * patients receive an appointment time equal to the slot start time of the
     * previous patient minus the expected scan duration, which is equal to the
     * length of one time slot. You can assume K is equal to 2.
     *
     * @param s schedule to fill in.
     * @param arrivals elective arrivals only! (Do not include urgency patients)
     */
    public void fill(Schedule s, Arrival[] arrivals) {
        ScheduleTimeSlot firstSlot = s.timeSlots[0];
        int k = 2;

        for (int i = 0; i < s.timeSlots.length; i++) {
            // Not enough arrivals to fill all the timeslots, stop. 
            if (arrivals.length <= i) {
                break;
            }

            Arrival arrival = arrivals[i];

            if (i < k) {
                // Appointment time of the first two arrivals is equal to tStart
                // of the first time slot.
                ScheduleTimeSlot slot = s.timeSlots[i];
                arrival.tAppointment = firstSlot.tStart;

                // TODO: is this a bit problematic, can we really assign patient 
                // 2 to the second timeslot?
                slot.assignedTo = arrival;
            } else {
                // Previous slot contains the previous patient as well, so we use that.
                ScheduleTimeSlot previousSlot = s.timeSlots[i - 1];
                ScheduleTimeSlot slot = s.timeSlots[i];

                /**
                 * FLORIAN TODO: I don't quite understand the language here
                 * "receive an appointment time equal to the slot start time of
                 * the previous patient minus the expected scan duration, which
                 * is equal to the length of one time slot
                 *
                 * Should we use the expected scan duration or the length of one
                 * time slot? The only explanation I have is that a "normal"
                 * patient has a scan time of 15 minutes, which coincides with
                 * the length of a time slot (which is a given = 15 minutes)
                 *
                 * "arrival.tAppointment =
                 * previousSlot.tStart.minus(previousSlot.duration);" This will
                 * result in 3 people arriving at t = 0; patient 0 appointed to
                 * t = 0 (from slot 0) (occupies slot 0) patient 1 appointed to
                 * t = 0 (from slot 0) (occupies slot 1) patient 2 appointed to
                 * t = 0 (slot 1 - dur)(occupies slot 2) patient 3 appointed to
                 * t = 1 (slot 2 - dur)(occupies slot 3)
                 *
                 * I don't know if this is the correct logic!
                 */
                /**
                 * JEF This will result in following arriving pattern; patient 0
                 * appointed to t = 0 (from slot 0) (occupies slot 0) patient 1
                 * appointed to t = 0 (from slot 0) (occupies slot 1) patient 2
                 * appointed to t = 2 (slot 1 + dur)(occupies slot 2) patient 3
                 * appointed to t = 3 (slot 2 + dur)(occupies slot 3)
                 */
                arrival.tAppointment = previousSlot.tStart.plus(previousSlot.duration);
                slot.assignedTo = arrival;

                // ASK ON FEEDBACK SESSION
            }
        }
    }
}
