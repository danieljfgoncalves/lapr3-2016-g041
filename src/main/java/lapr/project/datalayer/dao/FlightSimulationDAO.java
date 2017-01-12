/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.dao;

import java.util.List;
import lapr.project.model.FlightSimulation;

/**
 * Interface to manage a flight simulation's data access object.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface FlightSimulationDAO {

    /**
     * Gets a flight simulation.
     *
     * @param flightSimulationID flight simulation ID
     * @return flight simulation
     * @throws Exception exception
     */
    FlightSimulation getFlightSimulation(int flightSimulationID) throws Exception;

    /**
     * Gets the flight simulations.
     *
     * @return flight simulations
     * @throws Exception exception
     */
    List<FlightSimulation> getFlightSimulations() throws Exception;

    /**
     * Adds a flight simulation.
     *
     * @param flightSimulation flight simulation
     * @throws Exception exception
     */
    void addFlightSimulation(FlightSimulation flightSimulation) throws Exception;
}
