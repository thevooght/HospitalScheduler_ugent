/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.factories;

import the.avengers.hospitalscheduler.primitives.Arrival;

/**
 * The ArrivalFactory generates a "stream" / array of random arrivals according
 * to the specifications in the assignment.
 *
 * Basically, it has to simulate the real world randomness.
 *
 * @author user
 */
public class ArrivalFactory {

    public Arrival[] generate() {
        // TODO: figure out how many arrivals a day to simulate, replace 9000.
        Arrival[] arrivals = new Arrival[9000];
        /**
         * TODO: generate random patients (probably need to build a
         * PatientFactory)
         */

        /**
         * TODO: for loop that generates random arrivals, also randomize the
         * timestamp fields etc.
         */
        return arrivals;
    }
}
