/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.appointmentschedulers;

import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Day;

/**
 * The abstract base class for the appointment time schedulers for elective
 * patients is to be extended for the 4 different strategies.
 *
 * - FCFS. - Bailey-Welch rule. - Blocking rule. - Benchmarking rule
 *
 * @author Tony Stark
 */
public abstract class BaseScheduleStrategy {

    abstract Arrival[] fill(Day s, Arrival[] arrivals);
}
