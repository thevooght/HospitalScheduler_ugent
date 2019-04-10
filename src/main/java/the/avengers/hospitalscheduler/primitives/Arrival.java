/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author Tony Stark
 */
public class Arrival extends Patient {

    /**
     * In the operational phase, elective patients call the front desk of the
     * outpatient department during the opening hours in order to make an
     * appointment.
     */
    public Instant tPhoneCall;

    /**
     * In addition, the patient is given an appointment time, i.e. the time the
     * patient should show up at the department, which can be different from the
     * start time of the time slot appointed to the patient.
     */
    public Instant tAppointment;
    public Instant tTimeSlot;   // start of timeslot
    /**
     * Technically not in the illustration of the assignment but we have to
     * split up appointment from actual arrival time! Arrivals can be late..
     */
    public Instant tArrival;
    public Instant tScan;

    /**
     * The appointment waiting time of an elective patient is the time between
     * their request for an appointment (phone call) and their actual
     * appointment time.
     *
     * @return the duration time
     */
    public Duration tAppointmentWaitingTime() {
        return Duration.between(tAppointment, tPhoneCall);
    }

    /**
     * The scan waiting time of an elective patient time is the time between the
     * physical arrival of the patient in the department and the start time of
     * the treatment.
     *
     * @return the duration time
     */
    public Duration tScanWaitingTime() {
        return Duration.between(tScan, tArrival);
    }
}
