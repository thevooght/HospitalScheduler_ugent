/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.simulation;

import java.time.Duration;
import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Day;
import the.avengers.hospitalscheduler.primitives.TimeSlot;
import the.avengers.hospitalscheduler.primitives.Week;

/**
 * The Analyzer takes a filled-in Week as input (including overtime). It outputs
 * the several performance metrics required by the assignment.
 *
 * @author Tony Stark
 */
public class Analyzer {

    /**
     * The sum of all the patients their appointment waiting times.
     *
     * performance metric #1
     */
    public Duration tSumOfAppointmentWaitingTime = Duration.ZERO;

    /**
     * The sum of all the elective patients their scan waiting times.
     *
     * performance metric #2
     */
    public Duration tSumOfElectiveScanWaitingTime = Duration.ZERO;

    /**
     * The sum of all the urgency patients their scan waiting times.
     *
     * performance metric #3
     */
    public Duration tSumOfUrgencyScanWaitingTime = Duration.ZERO;

    /**
     * The overtime required in the department to examine all patients.
     *
     * performance metric #4
     */
    public Duration tSumOfOverTime = Duration.ZERO;

    /**
     * Given a filled-in week, calculate the several performance metrics.
     *
     * @param week a filled-in week.
     */
    public Analyzer(Week week) {

        for (Day day : week.days) {
            this.tSumOfOverTime = this.tSumOfOverTime.plus(day.tOverTime());

            for (Arrival arrival : day.arrivals) {
                if (arrival.urgent) {
                    this.tSumOfUrgencyScanWaitingTime = this.tSumOfUrgencyScanWaitingTime.plus(arrival.tScanWaitingTime());
                } else {
                    this.tSumOfAppointmentWaitingTime = this.tSumOfAppointmentWaitingTime.plus(arrival.tAppointmentWaitingTime());
                    this.tSumOfElectiveScanWaitingTime = this.tSumOfElectiveScanWaitingTime.plus(arrival.tScanWaitingTime());
                }
            }
        }
    }
}
