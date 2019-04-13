/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.Assert;
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

    /**
     * Test whether comparator by phonecall instant works properly.
     */
    @Test
    public void testSortArrivalsByPhoneCallInstant() {
        Arrival last = new Arrival(true);
        Arrival second = new Arrival(true);
        Arrival first = new Arrival(true);

        last.tPhoneCall = Instant.now();
        second.tPhoneCall = last.tPhoneCall.minus(1, ChronoUnit.HOURS);
        first.tPhoneCall = second.tPhoneCall.minus(1, ChronoUnit.HOURS);

        Arrival[] unsorted = {
            last,
            second,
            first
        };

        Arrival[] expected = {
            first,
            second,
            last
        };

        Arrays.sort(unsorted, Arrival.compareByPhoneCall());

        Assert.assertArrayEquals(unsorted, expected);
    }

}
