/*
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an register for aircraft models.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AirportsRegister {

    /**
     * The list of airports.
     */
    private List<Airport> airports;

    /**
     * Creates an instance of AirportsRegister, receiving a list of aiports.
     *
     * @param airports the given list of airports
     */
    public AirportsRegister(List<Airport> airports) {
        this.airports = airports;
    }

    /**
     * Creates an instance of AirportsRegister with an empty list.
     */
    public AirportsRegister() {
        this.airports = new ArrayList();
    }

    /**
     * Creates a copy of a AirportsRegister.
     *
     * @param airportsRegister the given AirportsRegister
     */
    public AirportsRegister(AirportsRegister airportsRegister) {
        this.airports = new ArrayList<>(airportsRegister.getAirports());
    }

    /**
     * Gets the airports list.
     *
     * @return the airports
     */
    public List<Airport> getAirports() {
        return airports;
    }

    /**
     * Modifies the airports list.
     *
     * @param airports the airports to set
     */
    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.airports);
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
        final AirportsRegister other = (AirportsRegister) obj;
        return this.airports.equals(other.airports);
    }

    @Override
    public String toString() {
        return String.format("Airports: %s", airports);
    }

}
