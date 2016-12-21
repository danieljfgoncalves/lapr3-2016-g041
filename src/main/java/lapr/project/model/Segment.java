/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
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
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Segment {

    /**
     * The segment identification.
     */
    private String identification;

    /**
     * The allowed altitude (SI: m).
     */
    private List<Amount<Length>> allowedAltitudes;

    /**
     * The angle of the wind direction relative to north-south line (SI: RAD).
     */
    private Amount<Angle> windDirection;

    /**
     * The wind intensity (SI: m/s).
     */
    private Amount<Velocity> windIntensity;

    /**
     * The default identification.
     */
    private static final String DEFAULT_IDENTIFICATION = "XX01";

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
        this.identification = DEFAULT_IDENTIFICATION;
        this.allowedAltitudes = new ArrayList<>();
        this.windDirection = DEFAULT_WIND_DIRECTION;
        this.windIntensity = DEFAULT_WIND_SPEED;
    }

    /**
     * Creates an instance of segment receiving their attributes.
     *
     * @param identification segment's identification
     * @param allowedAltitudes allowed altitudes
     * @param windDirection the angle of the wind direction relative to north
     * @param windSpeed the wind speed
     */
    public Segment(String identification, List<Amount<Length>> allowedAltitudes, Amount<Angle> windDirection,
            Amount<Velocity> windSpeed) {
        this.identification = identification;
        this.allowedAltitudes = allowedAltitudes;
        this.windDirection = windDirection;
        this.windIntensity = windSpeed;
    }

    /**
     * Creates a segment receiving another segment.
     *
     * @param otherSegment other segment to copy
     */
    public Segment(Segment otherSegment) {
        this.identification = otherSegment.identification;
        this.allowedAltitudes = new ArrayList<>(otherSegment.allowedAltitudes);
        this.windDirection = otherSegment.windDirection;
        this.windIntensity = otherSegment.windIntensity;
    }

    /**
     * Gets the segment's identification.
     *
     * @return segment's identification
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Sets the segment's identification.
     *
     * @param identification segment's identification
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Gets the allowed altitudes (SI: m).
     *
     * @return allowed altitudes
     */
    public List<Amount<Length>> getAllowedAltitudes() {
        return allowedAltitudes;
    }

    /**
     * Sets the allowed altitudes (SI: m).
     *
     * @param allowedAltitudes allowed altitudes
     */
    public void setAllowedAltitudes(List<Amount<Length>> allowedAltitudes) {
        this.allowedAltitudes = allowedAltitudes;
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
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.identification);
        hash = 17 * hash + Objects.hashCode(this.allowedAltitudes);
        hash = 17 * hash + Objects.hashCode(this.windDirection);
        hash = 17 * hash + Objects.hashCode(this.windIntensity);
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

        final Segment other = (Segment) obj;
        return this.identification.equals(other.identification)
                && this.allowedAltitudes.equals(other.allowedAltitudes)
                && this.windDirection.approximates(other.windDirection)
                && this.windIntensity.approximates(other.windIntensity);
    }

    @Override
    public String toString() {
        return String.format("Segment{identification=%s, allowedAltitudes=%s, windDirection=%s, windSpeed=%s}",
                this.identification, this.allowedAltitudes, this.windDirection, this.windIntensity);
    }

}
