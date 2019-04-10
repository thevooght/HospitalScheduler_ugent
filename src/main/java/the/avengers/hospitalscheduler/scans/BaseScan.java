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
     * significant differences bet ween the distributions of the scan durations
     * between different scan types due to setup times.
     *
     */
    public ScanEnum type;
    private Duration tScanTime;

    abstract Duration getScanTime();
}
