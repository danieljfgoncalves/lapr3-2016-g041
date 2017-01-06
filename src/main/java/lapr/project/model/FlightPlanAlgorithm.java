/*
 * Package location for Project concept
 */
package lapr.project.model;

import java.util.LinkedList;
import org.jscience.physics.amount.Amount;

/**
 * Interface to represent a Path Algorithm.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface FlightPlanAlgorithm {

    /**
     * Generates the flight plan based on the algorithm's criteria.
     *
     * @param network The air network
     * @param flight The selected flight
     * @param flightplan the flight plan to insert
     * @return the criteria total amount (distance, consumption, etc.)
     */
    Amount<?> generateFlightPlan(AirNetwork network, FlightSimulation flight, LinkedList<Segment> flightplan);
}
