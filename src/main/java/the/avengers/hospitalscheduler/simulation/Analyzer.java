/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.simulation;

import java.time.Duration;
import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Schedule;
import the.avengers.hospitalscheduler.primitives.ScheduleTimeSlot;

/**
 * The Analyzer takes a filled-in schedule as input (including overtime). It
 * outputs the several performance metrics required by the assignment.
 *
 * @author Tony Stark
 */
public class Analyzer {

    /**
     * The sum of all the patients their appointment waiting times.
     *
     * performance metric #1
     */
    public Duration tSumOfAppointmentWaitingTime;

    /**
     * The sum of all the elective patients their scan waiting times.
     *
     * performance metric #2
     */
    public Duration tSumOfElectiveScanWaitingTime;

    /**
     * The sum of all the urgency patients their scan waiting times.
     *
     * performance metric #3
     */
    public Duration tSumOfUrgencyScanWaitingTime;

    /**
     * The overtime required in the department to examine all patients.
     *
     * performance metric #4
     */
    public Duration tSumOfOverTime;

    /**
     * Given a filled-in schedule, calculate the several performance metrics.
     *
     * @param schedule a filled-in schedule.
     */
    // TODO: we may have to pass an array of arrivals instead.
    // What if nobody was assigned to a slot, and it's just empty?
    public Analyzer(Schedule schedule) throws Exception {
        // Go over the slots and get the patient from assignedTo.

        for (ScheduleTimeSlot slot : schedule.timeSlots) {
            Arrival patient = slot.assignedTo;

            if (patient == null) {
                throw new Exception("The schedule was not fully filled in!");
            }
        }
    }
}
