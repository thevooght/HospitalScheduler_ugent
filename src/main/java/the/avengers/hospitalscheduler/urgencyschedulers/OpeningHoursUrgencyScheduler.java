/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.urgencyschedulers;

import java.time.DayOfWeek;
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

        // There are minimum 10 urgent slots
        if (nUrgencySlots == 10) {
         for (int i = 0; i < w.days.length; i++) {
            if (w.days[i].day == DayOfWeek.THURSDAY || w.days[i].day == DayOfWeek.SATURDAY) {
                w.days[i].timeSlots[15].reservedForUrgent = true;
            } else {
                w.days[i].timeSlots[10].reservedForUrgent = true;
                w.days[i].timeSlots[21].reservedForUrgent = true;
            }
        }
        }
        
        //for 11 till 15, evenly distribute 3 slots for every whole day
        
        if (nUrgencySlots > 10 && nUrgencySlots < 15) {
            int slots = 10;
            for (int i = 0; i < w.days.length; i++) {
                if (w.days[i].day == DayOfWeek.THURSDAY || w.days[i].day == DayOfWeek.SATURDAY) {
                w.days[i].timeSlots[15].reservedForUrgent = true;
                } else if (nUrgencySlots - slots > 0){
                w.days[i].timeSlots[7].reservedForUrgent = true;
                w.days[i].timeSlots[15].reservedForUrgent = true;
                w.days[i].timeSlots[23].reservedForUrgent = true;
                } else {
                w.days[i].timeSlots[10].reservedForUrgent = true;
                w.days[i].timeSlots[21].reservedForUrgent = true;    
                }
                slots ++;
                //all slots are used before sunday
            }
        }
        
        //from slot 15 till slot 16 (2nd slot on the half days)
        if (nUrgencySlots > 14 && nUrgencySlots < 17) {
            for (int i = 0; i<w.days.length; i++ ){
                if (w.days[i].day == DayOfWeek.THURSDAY || w.days[i].day == DayOfWeek.SATURDAY) {
                w.days[i].timeSlots[7].reservedForUrgent = true;
                    if (nUrgencySlots == 16){
                        w.days[i].timeSlots[15].reservedForUrgent = true;}
                } else {
                    w.days[i].timeSlots[7].reservedForUrgent = true;
                    w.days[i].timeSlots[15].reservedForUrgent = true;
                    w.days[i].timeSlots[23].reservedForUrgent = true; 
                }
        }
        }
        
        //from slot 17 till slot 20
        if (nUrgencySlots > 16 && nUrgencySlots <= 20) {
            int slots = 16;
            for (int i = 0; i<w.days.length; i++ ){
                if (w.days[i].day == DayOfWeek.THURSDAY || w.days[i].day == DayOfWeek.SATURDAY) {
                    w.days[i].timeSlots[7].reservedForUrgent = true;
                    w.days[i].timeSlots[15].reservedForUrgent = true;
                } else if (nUrgencySlots - slots > 0){
                    w.days[i].timeSlots[5].reservedForUrgent = true;
                    w.days[i].timeSlots[12].reservedForUrgent = true;
                    w.days[i].timeSlots[19].reservedForUrgent = true;
                    w.days[i].timeSlots[25].reservedForUrgent = true;
                } else {
                    w.days[i].timeSlots[7].reservedForUrgent = true;
                    w.days[i].timeSlots[15].reservedForUrgent = true;
                    w.days[i].timeSlots[23].reservedForUrgent = true;   
                }
                slots ++; 
                }
            }
        }
}
