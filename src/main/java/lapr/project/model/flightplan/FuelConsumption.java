/*
 * Package location for path algorithms
 */
package lapr.project.model.flightplan;

import java.util.LinkedList;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Segment;
import lapr.project.utils.exceptions.InsufficientFuelException;

/**
 * Interface to verify if fuel is sufficient.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface FuelConsumption {

    /**
     * Verifies if sufficiente fuel.
     *
     * @param flight The selected flight
     * @param flightplan the flight plan to verify
     * @return true if fuel is sufficient, false otherwise.
     * @throws lapr.project.utils.exceptions.InsufficientFuelException
     */
    boolean hasSufficientFuel(FlightSimulation flight, LinkedList<Segment> flightplan) throws InsufficientFuelException;
}
