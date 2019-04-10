/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.schedulers;

import the.avengers.hospitalscheduler.primitives.Arrival;
import the.avengers.hospitalscheduler.primitives.Schedule;

/**
 * The ABSTRACT base class for the schedule strategy is to be extended for the 4
 * different stategies.
 *
 * - FCFS. - Bailey-Welch rule. - Blocking rule. - Benchmarking rule
 *
 * @author Tony Stark
 */
public abstract class BaseScheduleStrategy {

    abstract void fill(Schedule s, Arrival[] arrivals);
}
