/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.scans;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * URGENT PATIENTS
 *
 * There are significant differences between the distributions of the scan
 * durations between different scan types due to setup times. The scan duration
 * of other scans follows a normal distribution with a mean of 30 minutes and a
 * standard deviation of 4.5 minutes.
 *
 * @author Tony Stark
 */
public class OtherScan extends BaseScan {

    public OtherScan() {
        super();

        this.type = ScanEnum.OTHERS;

        // Generate scan time based on mean 30 min and deviation 4.5 min
        Random r = new Random();
        long ScanTime = Math.round(r.nextGaussian() * 4.5 + 30);
        this.tScanTime = Duration.ofMinutes(ScanTime);
    }

}
