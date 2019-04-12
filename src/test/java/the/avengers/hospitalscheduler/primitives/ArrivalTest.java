/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import java.time.Duration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import the.avengers.hospitalscheduler.scans.ScanEnum;

/**
 *
 * @author user
 */
public class ArrivalTest {

    public ArrivalTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test whether the scan of an urgent Arrival is not of type normal.
     */
    @Test
    public void testScanNormalArrival() {
        Arrival instance = new Arrival(false);
        assertFalse(instance.urgent);
        assertTrue(instance.scan.type == ScanEnum.NORMAL);
    }

    /**
     * Test whether the scan of an urgent Arrival is not of type normal.
     */
    @Test
    public void testScanUrgentArrival() {
        Arrival instance = new Arrival(true);
        assertTrue(instance.urgent);
        assertFalse(instance.scan.type == ScanEnum.NORMAL);
    }

}
