/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class WeekTest {

    Week w = new Week();

    public WeekTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void verifyStartTimes() {

        // Should only contain 6 working days
        assertEquals(this.w.days.length, 6);

        // Monday (day = 0)
        assertEquals(this.w.days[0].timeSlots[0].tStart.toString(), "2019-04-08T08:00:00Z");
        assertEquals(this.w.days[0].timeSlots[15].tStart.toString(), "2019-04-08T11:45:00Z");
        // Note: a hop of 1 hour should occur between 15 and 16! (lunch break)
        assertEquals(this.w.days[0].timeSlots[16].tStart.toString(), "2019-04-08T13:00:00Z");
        assertEquals(this.w.days[0].timeSlots[31].tStart.toString(), "2019-04-08T16:45:00Z");

        // Thursday & Saturday (only half a day)
        assertEquals(this.w.days[3].timeSlots.length, 16);
        assertEquals(this.w.days[5].timeSlots.length, 16);
    }

}
