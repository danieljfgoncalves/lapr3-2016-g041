/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;

/**
 * Represents a seguement. (connection between two coordinates will be
 * represented on the graph)
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Segment {

    /**
     * The segment id.
     */
    private String id;

    /**
     * The allowed altitude (SI: m).
     */
    private Amount<Length> altitude;

    /**
     * The angle of the wind direction relative to north-south line (SI: RAD).
     */
    private Amount<Angle> windDirection;

    /**
     * The wind intensity (SI: m/s).
     */
    private Amount<Velocity> windIntensity;

    /**
     * The default id.
     */
    private static final String DEFAULT_IDENTIFICATION = "XX01";

    /**
     * The default allowed altitude.
     */
    private final static Amount<Length> DEFAULT_ALTITUDE = Amount.valueOf(0d, SI.METER);

    /**
     * The default value for the angle of the wind direction relative to north.
     */
    private final static Amount<Angle> DEFAULT_WIND_DIRECTION = Amount.valueOf(0d, SI.RADIAN);

    /**
     * The default value for wind speed.
     */
    private final static Amount<Velocity> DEFAULT_WIND_SPEED = Amount.valueOf(0d, SI.METERS_PER_SECOND);

    /**
     * Creates an instance of segment with it's default values.
     */
    public Segment() {
        this.id = DEFAULT_IDENTIFICATION;
        this.altitude = DEFAULT_ALTITUDE;
        this.windDirection = DEFAULT_WIND_DIRECTION;
        this.windIntensity = DEFAULT_WIND_SPEED;
    }

    /**
     * Creates an instance of segment receiving their attributes.
     *
     * @param identification segment's id
     * @param altitude allowed altitudes
     * @param windDirection the angle of the wind direction relative to north
     * @param windSpeed the wind speed
     */
    public Segment(String identification, Amount<Length> altitude, Amount<Angle> windDirection,
            Amount<Velocity> windSpeed) {
        this.id = identification;
        this.altitude = altitude;
        this.windDirection = windDirection;
        this.windIntensity = windSpeed;
    }

    /**
     * Creates a segment receiving another segment.
     *
     * @param otherSegment other segment to copy
     */
    public Segment(Segment otherSegment) {
        this.id = otherSegment.id;
        this.altitude = otherSegment.altitude;
        this.windDirection = otherSegment.windDirection;
        this.windIntensity = otherSegment.windIntensity;
    }

    /**
     * Gets the segment's id.
     *
     * @return segment's id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the segment's id.
     *
     * @param identification segment's id
     */
    public void setId(String identification) {
        this.id = identification;
    }

    /**
     * Gets the allowed altitude (SI: m).
     *
     * @return allowed altitude
     */
    public Amount<Length> getAltitude() {
        return altitude;
    }

    /**
     * Sets the allowed altitudes (SI: m).
     *
     * @param altitude allowed altitudes
     */
    public void setAltitude(Amount<Length> altitude) {
        this.altitude = altitude;
    }

    /**
     * Gets the wind direction (SI: RAD).
     *
     * @return wind direction
     */
    public Amount<Angle> getWindDirection() {
        return windDirection;
    }

    /**
     * Sets the wind direction (SI: RAD).
     *
     * @param windDirection wind direction
     */
    public void setWindDirection(Amount<Angle> windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Gets the wind speed (SI: m/s).
     *
     * @return wind speed
     */
    public Amount<Velocity> getWindIntensity() {
        return windIntensity;
    }

    /**
     * Sets the wind speed (SI: m/s).
     *
     * @param windIntensity wind speed
     */
    public void setWindIntensity(Amount<Velocity> windIntensity) {
        this.windIntensity = windIntensity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Segment other = (Segment) obj;
        return this.id.equals(other.id)
                && this.altitude.approximates(other.altitude)
                && this.windDirection.approximates(other.windDirection)
                && this.windIntensity.approximates(other.windIntensity);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Segment{identification=%s, allowedAltitudes=%s, windDirection=%s, windSpeed=%s}",
                this.id, this.altitude, this.windDirection, this.windIntensity);
    }

}
