/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * The allowed altitude.
     */
    private List<Double> allowedAltitudes;

    /**
     * The angle of the wind direction relative to north.
     */
    private Double windDirection;

    /**
     * The wind speed.
     */
    private Double windSpeed;

    /**
     * The default value for the angle of the wind direction relative to north.
     */
    private final static Double DEFAULT_WIND_DIRECTION = 90d;

    /**
     * The default value for wind speed.
     */
    private final static Double DEFAULT_WIND_SPEED = 20d;

    /**
     * The epsilon of the allowed error.
     */
    private final static Double EPSILON = 0.01d;

    /**
     * Creates an instance of segment with it's default values.
     */
    public Segment() {
        this.allowedAltitudes = new ArrayList<>();
        this.windDirection = DEFAULT_WIND_DIRECTION;
        this.windSpeed = DEFAULT_WIND_SPEED;
    }

    /**
     * Creates an instance of segment receiving their attributes.
     *
     * @param allowedAltitudes allowed altitudes
     * @param windDirection the angle of the wind direction relative to north
     * @param windSpeed the wind speed
     */
    public Segment(List<Double> allowedAltitudes, Double windDirection, Double windSpeed) {
        this.allowedAltitudes = allowedAltitudes;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    /**
     * Creates a segment receiving another segment.
     *
     * @param otherSegment other segment to copy
     */
    public Segment(Segment otherSegment) {
        this.allowedAltitudes = new ArrayList<>(otherSegment.allowedAltitudes);
        this.windDirection = otherSegment.windDirection;
        this.windSpeed = otherSegment.windSpeed;
    }

    /**
     * Gets the allowed altitudes.
     *
     * @return allowed altitudes
     */
    public List<Double> getAllowedAltitudes() {
        return allowedAltitudes;
    }

    /**
     * Sets the allowed altitudes.
     *
     * @param allowedAltitudes allowed altitudes
     */
    public void setAllowedAltitudes(List<Double> allowedAltitudes) {
        this.allowedAltitudes = allowedAltitudes;
    }

    /**
     * Gets the wind direction.
     *
     * @return wind direction
     */
    public Double getWindDirection() {
        return windDirection;
    }

    /**
     * Sets the wind direction.
     *
     * @param windDirection wind direction
     */
    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Gets the wind speed.
     *
     * @return wind speed
     */
    public Double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Sets the wind speed.
     *
     * @param windSpeed wind speed
     */
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.allowedAltitudes);
        hash = 17 * hash + Objects.hashCode(this.windDirection);
        hash = 17 * hash + Objects.hashCode(this.windSpeed);
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
        return this.allowedAltitudes.equals(other.allowedAltitudes)
                && Math.abs(this.windDirection - other.windDirection) < EPSILON
                && Math.abs(this.windSpeed - other.windSpeed) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("Segment{allowedAltitudes=\"%s\", windDirection=\"%s\", windSpeed=\"%s\"}",
                this.allowedAltitudes, this.windDirection, this.windSpeed);
    }

}
