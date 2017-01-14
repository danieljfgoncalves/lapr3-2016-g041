/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.measure.unit.SI;
import lapr.project.model.AirNetwork;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Junction;
import lapr.project.model.Segment;
import lapr.project.model.flightplan.ShortestFlightPlan;
import lapr.project.utils.exceptions.FailedAnalysisException;
import lapr.project.utils.exceptions.InsufficientFuelException;
import lapr.project.utils.graph.MapGraph;
import lapr.project.utils.graph.MapGraphAlgorithms;
import org.jscience.physics.amount.Amount;

/**
 * Represents the algorithm to calculate the shortest distance (SI: Meters)
 * flight plan.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ShortestDistance extends ShortestFlightPlan {

    /**
     * Algorithm's description.
     */
    private static final String DESCRIPTION = "Shortest Path";

    @Override
    public Amount<?> generateFlightPlan(AirNetwork network, FlightSimulation flight, LinkedList<Segment> flightplan)
            throws Exception {

        MapGraph<Coordinate, Segment> graph = network.getNetwork();

        // New ordered list of coordinates.
        LinkedList<Coordinate> coordinates = new LinkedList<>();

        // Find best shortest path between orgin & dest, passing through waypoints/stops
        double distance = shortestFlightPlan(graph, flight, coordinates);

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

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    protected double pathAlgorithm(MapGraph<Coordinate, Segment> network, Coordinate vOrig,
            Coordinate vDest, LinkedList<Coordinate> efficientPath, FlightSimulation flight,
            List<Junction> junctions) throws InsufficientFuelException {

        return MapGraphAlgorithms.shortestPath(network, vOrig, vDest, efficientPath);
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    protected void actionAtStop(Junction junction, FlightSimulation flight) {
        // does nothing
    }

}
