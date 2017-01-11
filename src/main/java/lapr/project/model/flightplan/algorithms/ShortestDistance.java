/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import javax.measure.unit.SI;
import lapr.project.model.AirNetwork;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Junction;
import lapr.project.model.Segment;
import lapr.project.model.flightplan.ShortestFlightPlan;
import lapr.project.utils.exceptions.FailedAnalysisException;
import lapr.project.utils.graph.MapGraph;
import org.jscience.physics.amount.Amount;
import lapr.project.model.flightplan.FlightPlan;

/**
 * Represents the algorithm to calculate the shortest distance (SI: Meters)
 * flight plan.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ShortestDistance extends ShortestFlightPlan implements FlightPlan {

    /**
     * Algorithm's description.
     */
    public static final String DESCRIPTION = "Shortest Path";

    @Override
    public Amount<?> generateFlightPlan(AirNetwork network, FlightSimulation flight, LinkedList<Segment> flightplan)
            throws FailedAnalysisException {

        MapGraph<Coordinate, Segment> graph = network.getNetwork();

        // New ordered list of coordinates.
        LinkedList<Coordinate> coordinates = new LinkedList<>();

        // Find best shortest path between orgin & dest, passing through waypoints/stops
        double distance = shortestPath(graph, flight, coordinates);

        if (distance < 1 || coordinates.isEmpty()) {
            throw new FailedAnalysisException();
        }
        // Get segments from ordered coordinates.
        Iterator<Coordinate> it = coordinates.iterator();
        Coordinate first = it.next();
        while (it.hasNext()) {

            Coordinate second = it.next();

            flightplan.add(graph.getEdge(first, second).getElement());

            first = second;
        }

        return Amount.valueOf(distance, SI.METER);
    }

    @Override
    protected double addStopWeight(Junction junction) {
        // This algorithm doesn't imply any stop/waypoint passing alteration.
        return 0d;
    }

}
