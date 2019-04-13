/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.urgencyschedulers;

import the.avengers.hospitalscheduler.primitives.Week;

/**
 * The abstract base class for the urgency schedulers which reserves/mark
 * specific time slots for urgent patients.
 *
 * This class is to be extended for the 3 different strategies:
 *
 * 1. Evenly distributed over morning & afternoon
 *
 * 2. Evenly distributed over opening hours
 *
 * 3. After a block of six normal slots
 *
 * @author Tony Stark
 */
public abstract class BaseUrgencyScheduler {

    abstract void fill(Week w, int nUrgencySlots);
}
