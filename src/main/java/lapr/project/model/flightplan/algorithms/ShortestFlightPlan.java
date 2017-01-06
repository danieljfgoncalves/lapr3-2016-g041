/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.LinkedList;
import lapr.project.model.AirNetwork;
import lapr.project.model.FlightPlanAlgorithm;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Segment;

/**
 *
 * @author Daniel Goncalves <1151452@isep.ipp.pt>
 */
public class ShortestFlightPlan implements FlightPlanAlgorithm {

    /**
     * Algorithm's description.
     */
    public static final String DESCRIPTION = "Shortest Path";

    @Override
    public LinkedList<Segment> generateFlightPlan(AirNetwork network, FlightSimulation flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
