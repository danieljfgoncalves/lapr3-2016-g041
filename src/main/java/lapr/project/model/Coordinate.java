/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;

/**
 * Represents a Coordinate class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Coordinate {

    /**
     * The coordinate's latitude.
     */
    private Double latitude;

    /**
     * The coordinate's longitude.
     */
    private Double longitude;

    /**
     * The epsilon of the allowed error.
     */
    private final static Double EPSILON = 0.01d;

    /**
     * The default coordinate's longitude.
     */
    private final static Double DEFAULT_LONGITUDE = 0.000000d;

    /**
     * The default coordinate's latitude.
     */
    private final static Double DEFAULT_LATITUDE = 0.000000d;

    /**
     * Creates a Coordinate receiving its attributes.
     *
     * @param latitude the given latitude
     * @param longitude the given longitude
     */
    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Creates a Coordinate with default values.
     */
    public Coordinate() {
        this.latitude = DEFAULT_LATITUDE;
        this.longitude = DEFAULT_LONGITUDE;
    }

    /**
     * Creates a copy of a Coordinate.
     *
     * @param other the Coordinate to copy
     */
    public Coordinate(Coordinate other) {
        this.longitude = other.longitude;
        this.latitude = other.latitude;
    }

    /**
     * Gets the coordinate's latitude.
     *
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Modifies the coordinate's latitude.
     *
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the coordinate's longitude.
     *
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Modifies the coordinate's longitude.
     *
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.longitude);
        hash = 37 * hash + Objects.hashCode(this.latitude);
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

        final Coordinate other = (Coordinate) obj;

        return Math.abs(this.longitude - other.longitude) < EPSILON
                && Math.abs(this.latitude - other.latitude) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("Latitude: %.6f\nLongitude: %.6f\n", latitude, longitude);
    }

}
