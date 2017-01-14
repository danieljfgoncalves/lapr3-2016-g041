/*
 * Package location for Project concept
 */
package lapr.project.model.flightplan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Junction;
import lapr.project.model.Segment;
import lapr.project.utils.exceptions.InsufficientFuelException;
import lapr.project.utils.graph.MapGraph;
import org.jscience.physics.amount.Amount;

/**
 * Abstract class to represent shortest flight plan (example distance, time,
 * etc.).
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public abstract class ShortestFlightPlan implements FlightPlan {

    /**
     * Adds weight to path if any alteration is need when passing a waypoint or
     * a stop.
     *
     * @param junction the junction to analyse
     * @return the extra weight from a stop/waypoint.
     */
    protected abstract double addStopWeight(Junction junction);

    /**
     * Preformes an action to the flight at a stop.
     *
     * @param junction the junction to analyse
     * @param flight the flight to preforme the action
     */
    protected abstract void actionAtStop(Junction junction, FlightSimulation flight);

    protected abstract double pathAlgorithm(MapGraph<Coordinate, Segment> network, Coordinate vOrig, Coordinate vDest,
            LinkedList<Coordinate> efficientPath, FlightSimulation flight, List<Junction> junctions)
            throws InsufficientFuelException;

    /**
     * Calculates the shortest path passing through all waypoints & stops.
     *
     * @param graph the airnetwork
     * @param flight the flight to simulate
     * @param shortestPath list of coordinates that form the flight plan
     * @return the distance (meters, time, etc.)
     * @throws lapr.project.utils.exceptions.InsufficientFuelException
     */
    protected double shortestFlightPlan(MapGraph<Coordinate, Segment> graph, FlightSimulation flight,
            LinkedList<Coordinate> shortestPath) throws InsufficientFuelException {

        Coordinate vOrig = flight.getFlightInfo().getOriginAirport().getCoordinates();
        Coordinate vDest = flight.getFlightInfo().getDestinationAirport().getCoordinates();

        if (!graph.validVertex(vOrig) || !graph.validVertex(vDest)) {
            return -1d;
        }

        // List all obrigatory coordinates
        List<Junction> junctions = new ArrayList();
        junctions.addAll(flight.getFlightInfo().getWaypoints());
        junctions.addAll(flight.getFlightInfo().getStops());

        // Verify if all coords are in graph
        for (Junction junction : junctions) {
            if (!graph.validVertex(junction.getCoordinate())) {
                return -1d;
            }
        }

        double totalDist = 0;
        shortestPath.clear();
        shortestPath.add(vOrig); // add first vert (origin)
        Coordinate currentVert = vOrig; // start from origin

        while (!junctions.isEmpty()) {

            LinkedList<Coordinate> pathToNextVert = new LinkedList<>();
            double minDist = Double.POSITIVE_INFINITY;
            Junction nextVert = new Coordinate(); // Placeholder (to avoid null dereferencing)
            Amount<Mass> nextFuel = Amount.valueOf(0d, SI.KILOGRAM);
            Amount<Mass> currentFuel = flight.getEffectiveFuel();
            for (Junction junction : junctions) {

                flight.setEffectiveFuel(currentFuel);

                LinkedList<Coordinate> temp = new LinkedList<>();
                double dist = pathAlgorithm(graph, currentVert, junction.getCoordinate(), temp, flight, junctions);
                if (dist > 0 && dist < minDist) {
                    nextFuel = flight.getEffectiveFuel();
                    minDist = dist;
                    pathToNextVert = temp;
                    nextVert = junction;
                }
            }
            if (minDist == Double.POSITIVE_INFINITY) {
                return -1d;
            }
            // Fuel at next vert
            flight.setEffectiveFuel(nextFuel);
            // Add values for nearest junction.
            pathToNextVert.pop(); // First Vert is already in path.
            currentVert = nextVert.getCoordinate();
            shortestPath.addAll(pathToNextVert);
            totalDist += minDist;
            // add stopage time/distance, if any & preformes an action if any.
            actionAtStop(nextVert, flight);
            totalDist += addStopWeight(nextVert);
            // remove visited vert
            junctions.remove(nextVert);
        }

        // find shortest path from last waypoint/stop to destination
        LinkedList<Coordinate> pathToDest = new LinkedList<>();
        double distToDest = pathAlgorithm(graph, currentVert, vDest, pathToDest, flight, junctions);
        if (distToDest == -1) {
            return -1d;
        }
        totalDist += distToDest;
        pathToDest.pop();
        shortestPath.addAll(pathToDest);

        return totalDist;
    }
}
