/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.factories;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.Random;
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

    public static Arrival[] generate(DayOfWeek day, boolean urgent) {
        // Amount of arrivals
        // TODO: how many arrivals for urgent patients? 28.345 is for elective.
        int n = urgent ? getPoissonRandom(28.345) : getPoissonRandom(28.345);

        // TODO: what about days where they are half open but desk is always open (monday to friday)?
        // Divide n by two if only half a day? Or use f
        Arrival[] arrivals = new Arrival[n];
        for (int i = 0; i < n; i++) {
            // Create new arrival and add it to the list
            Arrival arrival = new Arrival(urgent);
            arrivals[i] = arrival;

        }
        // TODO: chance of 2% that they never show up/arrive
        // TODO: add tardiness on arrival
        // arrival.tArrival.plus(getTardinessRandom());

        return arrivals;
    }

    /**
     * When fitting the empirical data, we find that the number of elective
     * arrivals per day follows a Poisson distribution with mean = 28.345 .
     */
    private static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    /**
     * Tardiness distribution function follows a normal distribution with a mean
     * of 0 and a standard deviation of 2.5 minutes.
     *
     * Did a patient arrive early or late?
     *
     * @return randomly generated duration of tardiness.
     */
    private static Duration getTardinessRandom() {
        Random r = new Random();
        long tardiness = (long) ((r.nextGaussian() * 2.5) * 60);
        return Duration.ofSeconds(tardiness);
    }
}
