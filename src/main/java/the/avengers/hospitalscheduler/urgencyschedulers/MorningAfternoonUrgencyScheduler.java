/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.urgencyschedulers;

import java.time.DayOfWeek;
import the.avengers.hospitalscheduler.primitives.Week;

/**
 * Morning & Afternoon Strategy
 *
 * Currently, the hospital has installed the time slots for urgent patients at
 * the end of each morning or afternoon block as indicated in Figure 1.
 * Increasing the number of slots for urgent patients will increase or decrease
 * this number at the end of each morning or afternoon block. Time slots for
 * urgent patients are distributed evenly over the morning and afternoon block
 * and over the days.
 *
 * @author user
 */
public class MorningAfternoonUrgencyScheduler extends BaseUrgencyScheduler {

    public void fill(Week w, int nUrgencySlots) {

        // There are minimum 10 urgent slots
        for (int i = 0; i < w.days.length; i++) {
            if (w.days[i].day == DayOfWeek.THURSDAY || w.days[i].day == DayOfWeek.SATURDAY) {
                w.days[i].timeSlots[15].reservedForUrgent = true;
            } else {
                w.days[i].timeSlots[15].reservedForUrgent = true;
                w.days[i].timeSlots[31].reservedForUrgent = true;
            }
        }

        // Increasing the number of slots for ugent patients will increase this
        // number at the end of each morning or afternoon block.
        // If there are more than 10 urgency slots needed, these extra slots are
        // firstly assigned to the morning of the whole days.
        if (nUrgencySlots > 10 && nUrgencySlots < 15) {
            int dayCounter = 0;
            for (int i = 10; i < nUrgencySlots; i++) {
                if (w.days[dayCounter].day == DayOfWeek.THURSDAY) {
                    dayCounter++; // Skip this day for now
                }
                w.days[dayCounter].timeSlots[14].reservedForUrgent = true;
                dayCounter++;
            }
        }

        // If there are more than 14 urgency slots needed, these extra slots are
        // then assigned to the morning of the half days.
        if (nUrgencySlots > 14 && nUrgencySlots < 17) {
            int dayCounter = 3;
            for (int i = 14; i < nUrgencySlots; i++) {
                w.days[dayCounter].timeSlots[14].reservedForUrgent = true;
                dayCounter += 2;
            }
        }
        // Finally, if there are more than 16 slots needed, these last extra
        // slots are assigned to the afternoons of the whole days.
        if (nUrgencySlots > 16) {
            int dayCounter = 0;
            for (int i = 16; i < nUrgencySlots; i++) {
                if (w.days[dayCounter].day == DayOfWeek.THURSDAY) {
                    dayCounter++; // Skip this day
                }
                w.days[dayCounter].timeSlots[30].reservedForUrgent = true;
                dayCounter++;
            }
        }

    }
}
