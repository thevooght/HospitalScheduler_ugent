/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.primitives;

import the.avengers.hospitalscheduler.scans.BaseScan;
import the.avengers.hospitalscheduler.scans.NormalScan;

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

}
