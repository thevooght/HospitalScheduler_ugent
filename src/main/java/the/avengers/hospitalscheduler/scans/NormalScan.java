/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.scans;

import java.time.Duration;
import java.time.Instant;

/**
 * NORMAL PATIENTS
 *
 * No significant differences between the distributions of different scan types.
 * Consequently, only one scan duration distribution w as estimated for all
 * elective patients. The elective scan duration follows a normal distribution
 * with a mean of 15 minutes and a standard deviation of 3 minutes .
 *
 * @author user
 */
public class NormalScan extends BaseScan {

    public ScanEnum type = ScanEnum.NORMAL;
    private Duration tScanTime;

    public Duration getScanTime() {
        // If we had already randomly picked a scan time before,
        // then return it again to maintain consistency.
        // Every patient will still have a randomized scan time, but once
        // determined it will not change anymore.
        if (tScanTime != null) {
            return tScanTime;
        }

        // TODO: generate scan time based on mean 15 min and deviation 3 min
        // Tip: Duration.ofMinutes(16)
        // https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html#ofMinutes-long-
        return tScanTime;
    }
}
