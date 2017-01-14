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
 * @author Tiago Correia - 1151031
 */
public class Coordinate implements Junction {

    /**
     * The coordinate's ID.
     */
    private String id;

    /**
     * The coordinate's latitude.
     */
    private Double latitude;

    /**
     * The coordinate's longitude.
     */
    private Double longitude;

    /**
     * The default coordinate's ID.
     */
    private final static String DEFAULT_ID = "ID00";

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
     * @param id the given id
     * @param latitude the given latitude
     * @param longitude the given longitude
     */
    public Coordinate(String id, Double latitude, Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Creates a Coordinate with default values.
     */
    public Coordinate() {
        this.id = DEFAULT_ID;
        this.latitude = DEFAULT_LATITUDE;
        this.longitude = DEFAULT_LONGITUDE;
    }

    /**
     * Creates a copy of a Coordinate.
     *
     * @param other the Coordinate to copy
     */
    public Coordinate(Coordinate other) {
        this.id = other.id;
        this.longitude = other.longitude;
        this.latitude = other.latitude;
    }

    /**
     * Gets the coordinate's id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Modifies the coordinate's id.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Coordinate other = (Coordinate) obj;

        return id.equalsIgnoreCase(other.id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("ID: %s%nLatitude: %.6f%nLongitude: %.6f%n", id, latitude, longitude);
    }

    @Override
    public Coordinate getCoordinate() {
        return this;
    }
}
