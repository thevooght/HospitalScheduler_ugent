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
 * durations between different scan types due to setup times. The spine cervical
 * vertebra scan duration follows a normal distribution with a mean of 22.5
 * minutes and a standard deviation of 2.5 minutes.
 *
 * @author Tony Stark
 */
public class SpineCervicalVertebraScan extends BaseScan {

    public SpineCervicalVertebraScan() {
        super();

        this.type = ScanEnum.SPINE_CERVICAL_VERTEBRA;

        Random r = new Random();
        long ScanTime = (long) (r.nextGaussian() * 2.5 + 22.5);
        this.tScanTime = Duration.ofMinutes(ScanTime);
    }

}
