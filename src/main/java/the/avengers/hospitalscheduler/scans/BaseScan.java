/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.scans;

import java.time.Duration;
import java.time.Instant;

/**
 * The ABSTRACT base class for the scan is to be extended for urgent patients.
 * The details are on page 6 of the assignment. The scan time for urgent
 * patients deviates quite a bit.
 *
 * @author Tony Stark
 */
public abstract class BaseScan {

    /**
     * The department data logs showed that for urgent patients there are
     * significant differences between the distributions of the scan durations
     * between different scan types due to setup times.
     *
     */
    public ScanEnum type;

    /**
     * If we had already randomly picked a scan time before, then return it
     * again to maintain consistency. Every patient will still have a randomized
     * scan time, but once determined it will not change anymore.
     */
    public Duration tScanTime;
}
