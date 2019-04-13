/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.urgencyschedulers;

import the.avengers.hospitalscheduler.primitives.Week;

/**
 * Morning & Afternoon Strategy
 *
 * Increasing the number of slots for urgent patients will increase or decrease
 * this number at the end of each morning or afternoon block. Time slots for
 * urgent patients are distributed evenly over the morning and afternoon block
 * and over the days. This strategy is illustrated in Appendix (Figure 1)
 *
 * @author user
 */
public class MorningAfternoonUrgencyScheduler extends BaseUrgencyScheduler {

    public void fill(Week w, int nUrgencySlots) {
    }
}
