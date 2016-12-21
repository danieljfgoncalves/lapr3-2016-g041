/*
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.measure.quantity.Duration;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;

/**
 * Represents a stop of a flight
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Stop {

    /**
     * Stop's airport
     */
    private Airport airport;

    /**
     * Stop's minimum stop (SI: s)
     */
    private Amount<Duration> minimumStop;

    /**
     * Stop's schedule arrival
     */
    private Calendar scheduleArrival;

    /**
     * Stop's departure time
     */
    private Calendar departureTime;

    /**
     * Stop's minimum stop minutes by default
     */
    private static final Amount<Duration> DEFAULT_MINIMUM_STOP = Amount.valueOf(0d, SI.SECOND);

    /**
     * Constructs an instance of Stop
     *
     * @param airport the stop's airport
     * @param minimumStop the stop's minimum stop minutes
     * @param scheduleArrival the stop's schedule arrival
     * @param departureTime the stop's departure time
     */
    public Stop(Airport airport, Amount<Duration> minimumStop, Calendar scheduleArrival, Calendar departureTime) {
        this.airport = airport;
        this.minimumStop = minimumStop;
        this.scheduleArrival = scheduleArrival;
        this.departureTime = departureTime;
    }

    /**
     * Constructs an instance of Stop using it's parameters by default.
     */
    public Stop() {
        this.airport = new Airport();
        this.minimumStop = DEFAULT_MINIMUM_STOP;
        this.scheduleArrival = new GregorianCalendar();
        this.departureTime = new GregorianCalendar();
    }

    /**
     * Constructs a copy of an instance of Stop
     *
     * @param otherStop the instance of Stop to copy
     */
    public Stop(Stop otherStop) {
        this.airport = otherStop.airport;
        this.minimumStop = otherStop.minimumStop;
        this.scheduleArrival = otherStop.scheduleArrival;
        this.departureTime = otherStop.departureTime;
    }

    /**
     * Obtains the Stop's airport
     *
     * @return the airport
     */
    public Airport getAirport() {
        return this.airport;
    }

    /**
     * Modifies the Stop's airport
     *
     * @param airport the Stop's airport to set
     */
    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    /**
     * Obtains the Stop's minimum stop minutes
     *
     * @return the minimum stop minutes
     */
    public Amount<Duration> getMinimumStop() {
        return minimumStop;
    }

    /**
     * Modifies the Stop's minimum stop minutes
     *
     * @param minimumStop the Stop's minimum stop minutes to set
     */
    public void setMinimumStop(Amount<Duration> minimumStop) {
        this.minimumStop = minimumStop;
    }

    /**
     * Obtains the Stop's schedule arrival
     *
     * @return the schedule arrival
     */
    public Calendar getScheduleArrival() {
        return scheduleArrival;
    }

    /**
     * Modifies the Stop's schedule arrival
     *
     * @param scheduleArrival the Stop's schedule arrival to set
     */
    public void setScheduleArrival(Calendar scheduleArrival) {
        this.scheduleArrival = scheduleArrival;
    }

    /**
     * Obtains the Stop's departure time
     *
     * @return the departure time
     */
    public Calendar getDepartureTime() {
        return departureTime;
    }

    /**
     * Modifies the Stop's departure time
     *
     * @param departureTime the Stop's departure time to set
     */
    public void setDepartureTime(Calendar departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.airport);
        hash = 97 * hash + Objects.hashCode(this.minimumStop);
        hash = 97 * hash + Objects.hashCode(this.scheduleArrival);
        hash = 97 * hash + Objects.hashCode(this.departureTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Stop other = (Stop) obj;

        return this.airport.equals(other.airport)
                && this.departureTime.equals(other.departureTime)
                && this.minimumStop.equals(other.minimumStop)
                && this.scheduleArrival.equals(other.scheduleArrival);
    }

    @Override
    public String toString() {
        return String.format("Stop{airport=%s, minumum stop minutes=%d, schedule arrival=%s, departure time=%s",
                this.airport, this.minimumStop.longValue(NonSI.MINUTE), this.scheduleArrival, this.departureTime);
    }
}
