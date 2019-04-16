/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.simulation;

import java.time.DayOfWeek;
import java.time.Instant;
import java.util.Arrays;
import the.avengers.hospitalscheduler.appointmentschedulers.BaseScheduleStrategy;
import the.avengers.hospitalscheduler.factories.ArrivalFactory;
import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Day;
import the.avengers.hospitalscheduler.primitives.TimeSlot;
import the.avengers.hospitalscheduler.primitives.Week;
import the.avengers.hospitalscheduler.urgencyschedulers.BaseUrgencyScheduler;

/**
 * The simulator has a filled-in Week as input, it will calculate the actual
 * scan start times as urgent patients have priority over elective patients.
 *
 * We merge the Arrival[] arrays of elective and urgent patients into a new
 * array and sort it based on the arrival time. We then loop over all the
 * patients and calculate the actual tScan (start) times.
 *
 * @author Tony Stark
 */
public class Simulator {

    public static Analyzer run(int nUrgencySlots, BaseUrgencyScheduler urgencyScheduler, BaseScheduleStrategy appointmentScheduler) {
        // We create a new week
        Week week = new Week();

        // We apply the urgency scheduler to reserve time slots for elective patients.
        urgencyScheduler.fill(week, nUrgencySlots);

        // Create random phone calls / urgent arrivals for every day of the week.
        // Some elective patients can be served the same day, we call these
        // "carry over" electives because we plan them in the next day, with dips
        // on the first timeslots. Early birds get the best worms, or something...
        Arrival[] carryOverElectives = {};
        for (Day d : week.days) {
            // Generate a random electives that called in.
            Arrival[] elective = ArrivalFactory.generate(d.day, false);

            // The appointment scheduler for the previous day was not able to schedule everyone in.
            // We need to include these today.
            if (carryOverElectives.length != 0) {
                // Merge the carry overs & the newly generated electives into one array.
                Arrival[] merged = Arrays.copyOf(carryOverElectives, carryOverElectives.length + elective.length);
                System.arraycopy(elective, 0, merged, carryOverElectives.length, elective.length);

                elective = merged;
                Arrays.sort(elective, Arrival.compareByPhoneCall());
            }

            // Attempt to fill the appointment timeslots, this may create
            // new carry overs.
            carryOverElectives = appointmentScheduler.fill(d, elective);
            // We remove the carryOverElectives from the elective array
            // as they are being carried over to the next day.
            // 1st Assumption: the carry-over electives are always the people who
            // called in last. This is currently true for all the strategies
            // but if we ever change the strategies then this may not hold anymore.
            // e.g strategy which priorities based on the scan type.
            elective = Arrays.copyOf(elective, elective.length - carryOverElectives.length);

            // Generate urgent arrivals, and assign them to the first unassigned timeslots
            // it can find, or generate overtime timeslots.
            Arrival[] urgent = ArrivalFactory.generate(d.day, true);
            urgencyScheduler.assign(d, urgent);

            // Merge the electives and urgent into a single arrivals array.
            Arrival[] merged = Arrays.copyOf(elective, elective.length + urgent.length);
            System.arraycopy(urgent, 0, merged, elective.length, urgent.length);

            d.arrivals = merged;

            boolean complete = simulateDay(d);
            if (!complete) {
                System.out.println("A particular day was not deemed completed.");
            }
        }

        return new Analyzer(week);
    }

    /**
     * Runs the simulation for a day, calculates the tScan (start) times for the
     * day.
     */
    public static boolean simulateDay(Day day) {
        Instant tScanEndLastPatient = null;
        for (TimeSlot slot : day.timeSlots) {
            if (slot.assignedTo != null) {
                // We have a patient planned in our schedule, serve them.
                Arrival patient = slot.assignedTo;

                // If the scanner was free, we get served immediately.
                if (tScanEndLastPatient == null
                        || tScanEndLastPatient.isBefore(patient.tArrival())) {
                    tScanEndLastPatient = patient.tArrival();
                }

                // Set the start of the scan, equal to the end of the last patient.
                patient.tScan = tScanEndLastPatient;
                tScanEndLastPatient = patient.tScanEnd();
            }
        }

        for (TimeSlot overtimeSlot : day.overtimeSlots) {
            if (overtimeSlot.assignedTo != null) {
                // We have a patient planned in our schedule, serve him.
                Arrival urgency = overtimeSlot.assignedTo;

                // There is always a patient before the overtime patients
                // else the urgent patients would've been booked into the free
                // elective slot. However, if they are running early on the schedule
                // then a patient scheduled into overtime might get a scan that 
                // starts before overtime begins.
                if (tScanEndLastPatient.isBefore(urgency.tArrival())) {
                    tScanEndLastPatient = urgency.tArrival();
                }
                urgency.tScan = tScanEndLastPatient;
                tScanEndLastPatient = urgency.tScanEnd();
            }
        }

        return day.isCompleted();
    }
}
