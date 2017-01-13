/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;

/**
 * Represents an Algorithm analysis.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AlgorithmAnalysis {

    /**
     * distance.
     */
    private Amount<Length> distance;

    /**
     * duration.
     */
    private Amount<Duration> duration;

    /**
     * fuel consumption.
     */
    private Amount<Mass> consumption;

    /**
     * The default distance (SI: m).
     */
    private final static Amount<Length> DEFAULT_DISTANCE = Amount.valueOf(0d, SI.METER);

    /**
     * The default duration (SI: s).
     */
    private final static Amount<Duration> DEFAULT_DURATION = Amount.valueOf(0d, SI.SECOND);

    /**
     * The default fuel consumption (SI: kg).
     */
    private final static Amount<Mass> DEFAULT_CONSUMPTION = Amount.valueOf(0d, SI.KILOGRAM);

    /**
     * Creates an Analysis.
     *
     * @param distance distance
     * @param duration duration
     * @param consumption duration
     */
    public AlgorithmAnalysis(Amount<Length> distance, Amount<Duration> duration, Amount<Mass> consumption) {
        this.distance = distance;
        this.duration = duration;
        this.consumption = consumption;
    }

    /**
     * Creates an Analysis with default values.
     */
    public AlgorithmAnalysis() {
        this.distance = DEFAULT_DISTANCE;
        this.duration = DEFAULT_DURATION;
        this.consumption = DEFAULT_CONSUMPTION;
    }

    /**
     * Creates a copy of other Analysis.
     *
     * @param other the Analysis to copy
     */
    public AlgorithmAnalysis(AlgorithmAnalysis other) {
        this.distance = other.distance;
        this.duration = other.duration;
        this.consumption = other.consumption;
    }

    /**
     * Obtains the distance.
     *
     * @return the distance
     */
    public Amount<Length> getDistance() {
        return distance;
    }

    /**
     * Sets the distance.
     *
     * @param distance the distance to set
     */
    public void setDistance(Amount<Length> distance) {
        this.distance = distance;
    }

    /**
     * Obtains the duration.
     *
     * @return the duration
     */
    public Amount<Duration> getDuration() {
        return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the duration to set
     */
    public void setDuration(Amount<Duration> duration) {
        this.duration = duration;
    }

    /**
     * Obtains the fuel consumption.
     *
     * @return the consumption
     */
    public Amount<Mass> getConsumption() {
        return consumption;
    }

    /**
     * Sets the fuel consumption.
     *
     * @param consumption the consumption to set
     */
    public void setConsumption(Amount<Mass> consumption) {
        this.consumption = consumption;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.distance);
        hash = 23 * hash + Objects.hashCode(this.duration);
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

        final AlgorithmAnalysis other = (AlgorithmAnalysis) obj;

        return Objects.equals(this.consumption, other.consumption)
                && Objects.equals(this.duration, other.duration)
                && Objects.equals(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return "AlgorithmAnalysis{" + "distance=" + distance + ", duration=" + duration + ", consumption=" + consumption + '}';
    }
}
