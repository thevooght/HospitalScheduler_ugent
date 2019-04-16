/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.simulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import the.avengers.hospitalscheduler.appointmentschedulers.BaseScheduleStrategy;
import the.avengers.hospitalscheduler.appointmentschedulers.FcfsScheduleStrategy;
import the.avengers.hospitalscheduler.urgencyschedulers.BaseUrgencyScheduler;
import the.avengers.hospitalscheduler.urgencyschedulers.MorningAfternoonUrgencyScheduler;

/**
 *
 * @author user
 */
public class MonteCarloTest {

    public MonteCarloTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of run method, of class MonteCarlo.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        int n = 1;
        int nUrgencySlots = 10;
        BaseUrgencyScheduler urgencyScheduler = new MorningAfternoonUrgencyScheduler();
        BaseScheduleStrategy appointmentScheduler = new FcfsScheduleStrategy();
        MonteCarlo run = new MonteCarlo(n, nUrgencySlots, urgencyScheduler, appointmentScheduler);
    }

}
