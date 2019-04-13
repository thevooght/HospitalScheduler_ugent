/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package the.avengers.hospitalscheduler.factories;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
        boolean halfDay = (day == day.THURSDAY || day == day.SATURDAY);
        double lambda = urgent ? (halfDay ? 1.25 : 2.5) : 28.345;
        int n = getPoissonRandom(lambda);

        // TODO: what about days where they are half open but desk is always open (monday to friday)?
        // Divide n by two if only half a day? Or use f
        Arrival[] arrivals = new Arrival[n];
        for (int i = 0; i < n; i++) {
            // Create new arrival and add it to the list
            Arrival arrival = new Arrival(urgent);
            arrivals[i] = arrival;

            if (arrival.urgent) {
                arrival.tArrival = getTimeRandom(day, true, lambda);
            } else {
                arrival.tPhoneCall = getTimeRandom(day, false, lambda);
                // TODO: tAppointment is not set yet, so the TODOs below need to be moved.
                //       tAppointment is only available after a Day has going through an
                //       appointment scheduler.
                // TODO: chance of 2% that they never show up/arrive
                // TODO: add tardiness on arrival
                // arrival.tArrival = arrival.tAppointment.plus(getTardinessRandom());
            }

            // TODO: draw a number from 0 to 
        }
        return arrivals;
    }

    /**
     * When fitting the empirical data, we find that the number of elective
     * arrivals per day follows a Poisson distribution with mean = 28.345.
     *
     * How many arrivals/phone calls do we receive on a day?
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
     * Given that we have n phone calls/arrivals on a day, how should we spread
     * them over the day? It follows a negative exponential distribution, which
     * means more patients will call or arrive in the morning than at the end of
     * the day.
     *
     * @param day for which day should we generate a random time
     * @param urgent if true generates tArrival instead of tPhoneCall timestamp
     * @return
     */
    private static Instant getTimeRandom(DayOfWeek day, boolean urgent, double lambda) {
        Instant tStart = Instant
                .parse("2019-04-08T08:00:00.00Z")
                .plus(day.getValue() - 1, ChronoUnit.DAYS);

        // Phone calls can be made between 8AM until 5PM (9 hours of time), no lunchbreaks!.
        // However urgent patients have to respect the opening hours of the outpatient department.
        Instant tEnd = tStart.plus(urgent ? 4 : 9, ChronoUnit.HOURS);

        // Get the amount of available minutes between tStart and tEnd.
        long totalMinutes = Duration.between(tStart, tEnd).toMinutes();

        // Generate a random amount of minutes to add to tStart creating
        // the timestamp following the negative exponential distribution
        Random r = new Random();
        // q = -rate * ln (1 - uniformRandom)
        // with rate = 1 / lambda
        double q = Math.log(1 - r.nextDouble()) / (-lambda);

        // q is a random value between [0, 1], multiply the total it.
        // which results in an amount of minutes to add to the start.
        long minutesToAdd = (long) (totalMinutes * q);
        return tStart.plus(minutesToAdd, ChronoUnit.MINUTES);
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
