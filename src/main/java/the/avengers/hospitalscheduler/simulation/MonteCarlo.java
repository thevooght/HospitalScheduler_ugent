/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.simulation;

import the.avengers.hospitalscheduler.primitives.Week;

/**
 *
 * @author user
 */
public class MonteCarlo {

    // TODO: some result variable to store the final performance results
    /**
     *
     * @param n the number of simulations to run
     * @param schedule the schedule for which we want to evaluate the
     * performance.
     */
    public void simulate(int n, Week schedule) {
        for (int i = 0; i < n; i++) {
            // TODO: use the Arrival Factory to generate a stream of arrivals

            /*
                TODO: use the performance evaluation function.
                analyze(Arrivals[], schedule);
                then we need to take the mean the performances
             */
        }
    }
}
