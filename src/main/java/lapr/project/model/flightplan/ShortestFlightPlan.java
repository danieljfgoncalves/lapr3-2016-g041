/*
 * Package location for Project concept
 */
package lapr.project.model.flightplan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Junction;
import lapr.project.model.Segment;
import lapr.project.utils.graph.MapGraph;
import lapr.project.utils.graph.MapGraphAlgorithms;

/**
 * Abstract class to represent shortest flight plan (example distance, time, etc.).
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public abstract class ShortestFlightPlan {

    /**
     * Adds weight to path if any alteration is need when passing a waypoint or a stop.
     * 
     * @param junction the junction to analyse
     * @return 
     */
    protected abstract double addStopWeight(Junction junction);

    /**
     * Calculates the shortest path passing through all waypoints & stops.
     * 
     * @param graph the airnetwork
     * @param flight the flight to simulate
     * @param shortestPath list of coordinates that form the flight plan
     * @return the distance (meters, time, etc.)
     */
    protected double shortestPath(MapGraph<Coordinate, Segment> graph, FlightSimulation flight,
            LinkedList<Coordinate> shortestPath) {

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
            for (Junction junction : junctions) {

                LinkedList<Coordinate> temp = new LinkedList<>();
                double dist = MapGraphAlgorithms.shortestPath(graph, currentVert, junction.getCoordinate(), temp);
                if (dist > 0 && dist < minDist) {
                    minDist = dist;
                    pathToNextVert = temp;
                    nextVert = junction;
                }
            }
            if (minDist == Double.POSITIVE_INFINITY) {
                return -1d;
            }
            // Add values for nearest junction.
            pathToNextVert.pop(); // First Vert is already in path.
            currentVert = nextVert.getCoordinate();
            shortestPath.addAll(pathToNextVert);
            totalDist += minDist;
            // add stopage time/distance, if any.
            totalDist += addStopWeight(nextVert);
            // remove visited vert
            junctions.remove(nextVert);
        }

        // find shortest path from last wayoint/stop to destination
        LinkedList<Coordinate> pathToDest = new LinkedList<>();
        double distToDest = MapGraphAlgorithms.shortestPath(graph, currentVert, vDest, pathToDest);
        if (distToDest == -1) {
            return -1d;
        }
        totalDist += distToDest;
        pathToDest.pop();
        shortestPath.addAll(pathToDest);

        return totalDist;
    }
}
