/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.urgencyschedulers;

import the.avengers.hospitalscheduler.primitives.Week;

/**
 * Opening hours Strategy
 *
 * The time slots dedicated to urgent patients are evenly distributed over the
 * day taking the opening hours of the department into account. When 16 or 32
 * slots are available on a particular day. This strategy is illustrated in
 * Appendix (Figure 4) .
 *
 * @author user
 */
public class OpeningHoursUrgencyScheduler extends BaseUrgencyScheduler {

    public void fill(Week w, int nUrgencySlots) {
    }
}
