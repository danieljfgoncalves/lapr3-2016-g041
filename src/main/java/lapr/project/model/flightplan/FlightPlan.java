/*
 * Package location for Project concept
 */
package lapr.project.model.flightplan;

import java.util.LinkedList;
import lapr.project.model.AirNetwork;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Segment;
import lapr.project.utils.exceptions.FailedAnalysisException;
import org.jscience.physics.amount.Amount;

/**
 * Interface to represent a Path Algorithm.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface FlightPlan {

    /**
     * Generates the flight plan based on the algorithm's criteria.
     *
     * @param network The air network
     * @param flight The selected flight
     * @param flightplan the flight plan to insert
     * @return the criteria total amount (distance, consumption, etc.)
     * @throws java.lang.Exception
     */
    Amount<?> generateFlightPlan(AirNetwork network, FlightSimulation flight, LinkedList<Segment> flightplan) 
            throws Exception;
    
    /**
     * Obtains the algorithm description. 
     * 
     * @return 
     */
    String getDescription();
}
