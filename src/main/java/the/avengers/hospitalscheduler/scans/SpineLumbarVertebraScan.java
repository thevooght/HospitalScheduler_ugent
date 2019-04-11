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
 * durations between different scan types due to setup times.	
 * The spine lumbar vertebra scan duration follows a normal distribution 
 * with a mean of 17.5 minutes and a standard deviation of 1 minutes.	
 *
 * @author Tony Stark
 */

public class SpineLumbarVertebraScan extends BaseScan {
    
    public ScanEnum type = ScanEnum.SPINE_LUMBAR_VERTEBRA;
    private Duration tScanTime;

    public Duration getScanTime() {
        if (tScanTime != null) {
            return tScanTime;
        }
        // Generate scan time based on mean 17.5 min and deviation 1 min
        else {
            Random r = new Random();
            long ScanTime = (long) (r.nextGaussian()*1 + 17.5);
            tScanTime = Duration.ofMinutes(ScanTime);
            return tScanTime;  
        }
        
    }
}
