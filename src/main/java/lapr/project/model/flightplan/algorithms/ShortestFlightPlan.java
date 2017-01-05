/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.LinkedList;
import javax.measure.quantity.Length;
import lapr.project.model.AirNetwork;
import lapr.project.model.Calculus;
import lapr.project.model.Coordinate;
import lapr.project.model.Flight;
import lapr.project.model.FlightPlanAlgorithm;
import lapr.project.model.Segment;
import lapr.project.utils.graph.matrix.MatrixGraph;
import org.jscience.physics.amount.Amount;

/**
 *
 * @author Daniel Goncalves <1151452@isep.ipp.pt>
 */
public class ShortestFlightPlan implements FlightPlanAlgorithm {

    /**
     * Algorithm's description.
     */
    public static final String DESCRIPTION = "Shortest Path";

    /**
     * Create an auxilary matrix graph with distance (length amount) between
     * points as edges.
     *
     * @param network The airnetwork.
     * @return an auxilary matrix graph with distance between points as edges
     */
    private MatrixGraph calculateFlightPlan(AirNetwork network) {

        // Create matrix graph
        MatrixGraph<Coordinate, Amount<Length>> aux = new MatrixGraph(true);

        // Add all coordinates to aux.
        for (Coordinate vertex : network.getJunctions()) {

            aux.insertVertex(vertex);
        }

        // Add all edges to aux.
        for (Segment segment : network.getSegments()) {

            Amount<Length> distance = Calculus.distance(segment., second)
        }
    }

    @Override
    public LinkedList<Segment> generateFlightPlan(AirNetwork network, Flight flight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
