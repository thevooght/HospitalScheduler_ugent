/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.urgencyschedulers;

import the.avengers.hospitalscheduler.primitives.Week;

/**
 * Block Strategy
 *
 * Starting from the beginning of a session, a time slot for urgent patients is
 * planned each time after a block of six time slots for elective patients. This
 * strategy is illustrated in Appendix (Figure 5).
 *
 * @author user
 */
public class BlockUrgencyScheduler extends BaseUrgencyScheduler {

    public void fill(Week w, int nUrgencySlots) {
    }
}
