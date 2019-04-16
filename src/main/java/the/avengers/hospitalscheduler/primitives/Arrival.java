/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;

/**
 * An Arrival is any type of patient that has visited the hospital.
 *
 * Arrival class contains all the information of their visit (timestamps).
 *
 * Tip: Use the Arrival class instead of the Patient class everywhere in the
 * code because they it contains all the juicy data required for the simulation
 * and performance evaluation.
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
    public Instant _tArrival;                       // Only set if urgent patient
    public Duration dTardiness = Duration.ZERO;     // Only set if electrince patient

    public Instant tArrival() {
        if (this.urgent) {
            return this._tArrival;
        } else {
            // Elective patients are expected at their appointment hour
            // With some tardiness.
            return this.tAppointment.plus(this.dTardiness);
        }
    }

    public Instant tScan;

    public Instant tScanEnd() {
        return this.tScan.plus(this.scan.tScanTime);
    }

    /**
     * The appointment waiting time of an elective patient is the time between
     * their request for an appointment (phone call) and their actual
     * appointment time.
     *
     * @return the duration time
     */
    public Duration tAppointmentWaitingTime() {
        return Duration.between(this.tPhoneCall, this.tAppointment);
    }

    /**
     * The scan waiting time of an elective patient time is the time between the
     * physical arrival of the patient in the department and the start time of
     * the treatment.
     *
     * @return the duration time
     */
    public Duration tScanWaitingTime() {
        return Duration.between(this.tArrival(), this.tScan);
    }

    /**
     * We need to sort the randomly generated Arrivals by their phone call to
     * make the appointment schedulers algorithms easier.
     *
     * @return comparator used in the scheduling rules.
     */
    public static Comparator<Arrival> compareByPhoneCall() {
        return new Comparator<Arrival>() {
            @Override
            public int compare(Arrival o1, Arrival o2) {
                return o1.tPhoneCall.compareTo(o2.tPhoneCall);
            }
        };
    }

    /**
     * We need to sort the randomly generated Arrivals by their arrival time to
     * make the simulation & overtime calculations easier.
     *
     * @return comparator used in the scheduling rules.
     */
    public static Comparator<Arrival> compareByArrival() {
        return new Comparator<Arrival>() {
            @Override
            public int compare(Arrival o1, Arrival o2) {
                return o1.tArrival().compareTo(o2.tArrival());
            }
        };
    }

    /**
     * We need to sort the randomly generated Arrivals by their completion time
     * to make the simulation & overtime calculations easier.
     *
     * @return comparator used in the scheduling rules.
     */
    public static Comparator<Arrival> compareByScanEnd() {
        return new Comparator<Arrival>() {
            @Override
            public int compare(Arrival o1, Arrival o2) {
                return o1.tScanEnd().compareTo(o2.tScanEnd());
            }
        };
    }

    /**
     * Returns whether all required fields for the analysis were filled in.
     */
    public boolean isCompleted() {
        if ((!this.urgent && this.tPhoneCall == null)
                || (!this.urgent && this.tAppointment == null)
                || (!this.urgent && this.tTimeSlot == null)
                || (this.urgent && this._tArrival == null)
                || (this.tScan == null)) {
            return false;
        }
        return true;
    }

    public String toString() {
        String s = "[ " + name + ": urgent=" + this.urgent;
        if (!this.urgent) {
            s += " called=" + this.tPhoneCall;
            s += " appointment=" + this.tAppointment;
            s += " timeslot=" + this.tTimeSlot;
        } else {
            s += " tArrival=" + this._tArrival;
        }
        s += " scan=" + this.scan.type;
        s += " ]";
        return s;
    }

    public Arrival(boolean urgent) {
        super(urgent);
    }
}
