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
 * NORMAL PATIENTS
 *
 * No significant differences between the distributions of different scan types.
 * Consequently, only one scan duration distribution was estimated for all
 * elective patients. The elective scan duration follows a normal distribution
 * with a mean of 15 minutes and a standard deviation of 3 minutes .
 *
 * @author Tony Stark
 */
public class NormalScan extends BaseScan {

    public NormalScan() {
        super();

        this.type = ScanEnum.NORMAL;

        Random r = new Random();
        long ScanTime = (long) (r.nextGaussian() * 3 + 15);
        this.tScanTime = Duration.ofMinutes(ScanTime);

    }
}
