/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;

/**
 * Represents an Airport class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Airport {

    /**
     * The name of the airport.
     */
    private String name;

    /**
     * The town where the airport is.
     */
    private String town;

    /**
     * The country of the airport.
     */
    private String country;

    /**
     * The IATA code of the airport.
     */
    private String IATA;

    /**
     * The coordinates of the airport.
     */
    private Coordinate coordinates;

    /**
     * The altitude of the airport.
     */
    private Amount<Length> altitude;

    /**
     * The default name of the airport.
     */
    private final static String DEFAULT_NAME = "Default Name";

    /**
     * The default town where the airport is.
     */
    private final static String DEFAULT_TOWN = "Default Town";

    /**
     * The default country of the airport.
     */
    private final static String DEFAULT_COUNTRY = "Default Country";

    /**
     * The default IATA code of the airport.
     */
    private final static String DEFAULT_IATA = "Default IATA";

    /**
     * The default coordinate of the airport.
     */
    private final static Coordinate DEFAULT_COORDINATE = new Coordinate();

    /**
     * The default altitude of the airport (SI: m).
     */
    private final static Amount<Length> DEFAULT_ALTITUDE = Amount.valueOf(0d, SI.METER);

    /**
     * Creates an Airport receiving its attributes.
     *
     * @param name the airport's name
     * @param town the airport's town
     * @param country the airport's country
     * @param IATA the aiports IATA code
     * @param coordinates the aiport's coordinates
     * @param altitude the airport's altitude
     */
    public Airport(String name, String town, String country, String IATA, Coordinate coordinates, Amount<Length> altitude) {
        this.name = name;
        this.town = town;
        this.country = country;
        this.IATA = IATA;
        this.coordinates = coordinates;
        this.altitude = altitude;
    }

    /**
     * Creates an Airport with default values.
     */
    public Airport() {
        this.name = DEFAULT_NAME;
        this.town = DEFAULT_TOWN;
        this.country = DEFAULT_COUNTRY;
        this.IATA = DEFAULT_IATA;
        this.coordinates = DEFAULT_COORDINATE;
        this.altitude = DEFAULT_ALTITUDE;
    }

    /**
     * Creates a copy of other Airport.
     *
     * @param other the Airport to copy
     */
    public Airport(Airport other) {
        this.name = other.name;
        this.town = other.town;
        this.country = other.country;
        this.IATA = other.IATA;
        this.coordinates = new Coordinate(other.coordinates);
        this.altitude = other.altitude;
    }

    /**
     * Gets the name of the airport.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the name of the airport.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the town of the airport.
     *
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * Modifies the town of the airport.
     *
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Gets the country of the airport.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Modifies the country of the airport.
     *
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the IATA code of the airport.
     *
     * @return the IATA code
     */
    public String getIATA() {
        return IATA;
    }

    /**
     * Modifies the IATA code of the airport.
     *
     * @param IATA the IATA code to set
     */
    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    /**
     * Gets the coordinates of the airport.
     *
     * @return the coordinates
     */
    public Coordinate getCoordinates() {
        return coordinates;
    }

    /**
     * Modifies the coordinates of the airport.
     *
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets the altitude of the airport.
     *
     * @return the altitude
     */
    public Amount<Length> getAltitude() {
        return altitude;
    }

    /**
     * Modifies the altitude of the airport.
     *
     * @param altitude the altitude to set
     */
    public void setAltitude(Amount<Length> altitude) {
        this.altitude = altitude;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.IATA);
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

        final Airport other = (Airport) obj;

        return this.name.equals(other.name) && this.IATA.equals(other.IATA);
    }

    @Override
    public String toString() {
        return String.format("Name: %s\n"
                + "Town: %s\n"
                + "Country: %s\n"
                + "IATA code: %s\n"
                + "Coordinates: %s\n"
                + "Altitude: %.2f\n", name, town, country, IATA, coordinates, altitude);
    }

}
