/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.simulation;

import java.time.Duration;
import the.avengers.hospitalscheduler.appointmentschedulers.BaseScheduleStrategy;
import the.avengers.hospitalscheduler.appointmentschedulers.FcfsScheduleStrategy;
import the.avengers.hospitalscheduler.primitives.Week;
import the.avengers.hospitalscheduler.urgencyschedulers.BaseUrgencyScheduler;
import the.avengers.hospitalscheduler.urgencyschedulers.MorningAfternoonUrgencyScheduler;

/**
 *
 * @author user
 */
public class MonteCarlo {

    /**
     * The average sum of all the patients their appointment waiting times.
     *
     * performance metric #1
     */
    public Duration tAvgOfAppointmentWaitingTime = Duration.ZERO;

    /**
     * The average sum of all the elective patients their scan waiting times.
     *
     * performance metric #2
     */
    public Duration tAvgOfElectiveScanWaitingTime = Duration.ZERO;

    /**
     * The average sum of all the urgency patients their scan waiting times.
     *
     * performance metric #3
     */
    public Duration tAvgOfUrgencyScanWaitingTime = Duration.ZERO;

    /**
     * The average sum of overtime required in the department to examine all
     * patients.
     *
     * performance metric #4
     */
    public Duration tAvgOfOverTime = Duration.ZERO;

    /**
     *
     * @param n the number of simulations to run performance.
     * @param nUrgencySlots the amount of urgency time slots to schedule.
     * @param urgencyScheduler what type of urgency scheduling strategy to
     * apply.
     * @param appointmentScheduler what type of appointment scheduling strategy
     * to apply.
     */
    public MonteCarlo(int n, int nUrgencySlots, BaseUrgencyScheduler urgencyScheduler, BaseScheduleStrategy appointmentScheduler) {
        for (long i = 0; i < n; i++) {
            Analyzer result = Simulator.run(nUrgencySlots, urgencyScheduler, appointmentScheduler);

            this.tAvgOfOverTime = this.tAvgOfOverTime.plus(result.tSumOfOverTime);
            this.tAvgOfUrgencyScanWaitingTime = this.tAvgOfUrgencyScanWaitingTime.plus(result.tSumOfUrgencyScanWaitingTime);
            this.tAvgOfAppointmentWaitingTime = this.tAvgOfAppointmentWaitingTime.plus(result.tSumOfAppointmentWaitingTime);
            this.tAvgOfElectiveScanWaitingTime = this.tAvgOfElectiveScanWaitingTime.plus(result.tSumOfElectiveScanWaitingTime);
        }
        this.tAvgOfOverTime = this.tAvgOfOverTime.dividedBy(n);
        this.tAvgOfUrgencyScanWaitingTime = this.tAvgOfUrgencyScanWaitingTime.dividedBy(n);
        this.tAvgOfAppointmentWaitingTime = this.tAvgOfAppointmentWaitingTime.dividedBy(n);
        this.tAvgOfElectiveScanWaitingTime = this.tAvgOfElectiveScanWaitingTime.dividedBy(n);

        System.out.println("----------- MONTE CARLO CASINO -----------");
        System.out.println("# runs: " + n);
        System.out.println("appointment sheduling strategy: " + appointmentScheduler.getClass().getSimpleName());
        System.out.println("urgency sheduling strategy: " + urgencyScheduler.getClass().getSimpleName());
        System.out.println("# urgency slots: " + nUrgencySlots);
        System.out.println("------------------------------------------");
        System.out.println("tAvgOfAppointmentWaitingTime=" + this.tAvgOfAppointmentWaitingTime);
        System.out.println("tAvgOfElectiveScanWaitingTime=" + this.tAvgOfElectiveScanWaitingTime);
        System.out.println("tAvgOfOverTime=" + this.tAvgOfOverTime);
        System.out.println("tAvgOfUrgencyScanWaitingTime=" + this.tAvgOfUrgencyScanWaitingTime);
    }
}
