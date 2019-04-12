/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import java.util.Random;
import the.avengers.hospitalscheduler.scans.*;

/**
 * Patient class contains all the medical information.
 *
 * @author Tony Stark
 */
public class Patient {

    /**
     * Urgent patients are non-elective patients that arrive to the outpatient
     * department without having an appointment.
     */
    public boolean urgent = false;
    public BaseScan scan = new NormalScan();

    public Patient(boolean urgent) {
        if (urgent) {
            Random r = new Random();
            Double freq = r.nextDouble();

            if (freq <= 0.7) {
                this.scan = new BrainScan();
            } else if (freq <= 0.8) {
                this.scan = new SpineLumbarVertebraScan();
            } else if (freq <= 0.9) {
                this.scan = new SpineCervicalVertebraScan();
            } else if (freq <= 0.95) {
                this.scan = new AbdomenMrcpScan();
            } else if (freq <= 1) {
                this.scan = new OtherScan();
            }

            this.urgent = true;
        }
    }

}
