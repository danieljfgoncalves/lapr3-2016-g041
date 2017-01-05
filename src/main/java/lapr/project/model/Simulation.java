/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a simulation.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Simulation {

    /**
     * The list of flights.
     */
    private List<FlightInfo> flights;

    /**
     * Creates an instance of simulation with their default values.
     */
    public Simulation() {
        this.flights = new ArrayList<>();
    }

    /**
     * Creates an simulation receiving their flights.
     *
     * @param flights flights
     */
    public Simulation(List<FlightInfo> flights) {
        this.flights = flights;
    }

    /**
     * Gets the flights.
     *
     * @return flights
     */
    public List<FlightInfo> getFlights() {
        return flights;
    }

    /**
     * Sets the flights.
     *
     * @param flights flights
     */
    public void setFlights(List<FlightInfo> flights) {
        this.flights = flights;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.flights);
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

        final Simulation other = (Simulation) obj;
        return this.flights.equals(other.flights);
    }

    @Override
    public String toString() {
        return String.format("Simulation{flights=%s}", this.flights);
    }
}
