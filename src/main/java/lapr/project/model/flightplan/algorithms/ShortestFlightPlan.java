/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import javax.measure.unit.SI;
import lapr.project.model.AirNetwork;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightPlanAlgorithm;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Segment;
import lapr.project.utils.graph.map.MapGraph;
import lapr.project.utils.graph.map.MapGraphAlgorithms;
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

    @Override
    public Amount<?> generateFlightPlan(AirNetwork network, FlightSimulation flight, LinkedList<Segment> flightplan) {

        MapGraph<Coordinate, Segment> graph = network.getNetwork();

        // TODO: Update onces airport class is updated too.
        Coordinate origin = new Coordinate();
        Coordinate dest = new Coordinate();

        // New ordered list of coordinates.
        LinkedList<Coordinate> coordinates = new LinkedList<>();

        // TODO: Include waypoints and stops.
        
        double distance = MapGraphAlgorithms.shortestPath(graph, origin, dest, coordinates);

        if (distance > 0) {
            // Get segments from ordered coordinates.
            Iterator<Coordinate> it = coordinates.iterator();
            Coordinate first = it.next();
            while (it.hasNext()) {

                Coordinate second = it.next();

                flightplan.add(graph.getEdge(first, second).getElement());

                first = second;
            }
        }

        return Amount.valueOf(distance, SI.METER);
    }
}
