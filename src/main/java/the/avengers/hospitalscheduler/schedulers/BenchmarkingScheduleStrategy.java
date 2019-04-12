/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.schedulers;

import java.time.Duration;
import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Schedule;
import the.avengers.hospitalscheduler.primitives.ScheduleTimeSlot;

/**
 *
 * @author jrvhulle
 */
public class BenchmarkingScheduleStrategy extends BaseScheduleStrategy {
    
    /**
     * All patients are assigned to an earlier appointment time than the start 
     * time of their slot, i.e. appointment time = start time of appointed time 
     * slot minus k x sigma (with sigma the standard deviation of the scan 
     * duration of elective patients and k designated how much the possible 
     * deviations in the scan duration are taken into account). You can assume 
     * k is equal to 0.5.	
     *
     * @param s
     * @param arrivals
     */
    public void fill(Schedule s, Arrival[] arrivals) {
        double k = 0.5;
        double sigma = 3;
        long kxsigma = Math.round(k * sigma);
        Duration timeEarlier = Duration.ofMinutes(kxsigma);
        for (int i = 0; i < s.timeSlots.length; i++) { 
            ScheduleTimeSlot slot = s.timeSlots[i];
            Arrival arrival = arrivals[i];

            arrival.tAppointment = slot.tStart.minus(timeEarlier);
            slot.assignedTo = arrival;
        }
    }
}
