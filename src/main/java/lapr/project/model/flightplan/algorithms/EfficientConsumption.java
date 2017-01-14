/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import lapr.project.model.AirNetwork;
import lapr.project.model.AlgorithmAnalysis;
import lapr.project.model.Calculus;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Junction;
import lapr.project.model.Segment;
import lapr.project.model.Stop;
import lapr.project.model.flightplan.ShortestFlightPlan;
import lapr.project.utils.exceptions.FailedAnalysisException;
import lapr.project.utils.exceptions.InsufficientFuelException;
import lapr.project.utils.graph.MapEdge;
import lapr.project.utils.graph.MapGraph;
import static lapr.project.utils.graph.MapGraphAlgorithms.getPath;
import static lapr.project.utils.graph.MapGraphAlgorithms.revPath;
import org.jscience.physics.amount.Amount;

/**
 * Represents the algorithm to calculate the most efficient consumption flight
 * plan.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class EfficientConsumption extends ShortestFlightPlan {

    /**
     * Algorithm's description.
     */
    private static final String DESCRIPTION = "Fuel efficient Path";

    private Amount<Mass> initialFuel;

    @Override
    public Amount<?> generateFlightPlan(AirNetwork network, FlightSimulation flight, LinkedList<Segment> flightplan)
            throws Exception {

        MapGraph<Coordinate, Segment> graph = network.getNetwork();

        // Store initial fuel
        initialFuel = flight.getEffectiveFuel();

        // New ordered list of coordinates.
        LinkedList<Coordinate> coordinates = new LinkedList<>();

        // Find best shortest path between orgin & dest, passing through waypoints/stops
        double consumption = shortestFlightPlan(graph, flight, coordinates);

        if (consumption < 1 || coordinates.isEmpty()) {
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

        return Amount.valueOf(consumption, SI.KILOGRAM);
    }

    private double efficientPath(MapGraph<Coordinate, Segment> network, Coordinate vOrig, Coordinate vDest,
            LinkedList<Coordinate> efficientPath, FlightSimulation flight, List<Junction> junctions)
            throws InsufficientFuelException {

        if (!network.validVertex(vOrig) || !network.validVertex(vDest)) {
            return -1d;
        }

        int numVert = network.numVertices();

        Object[] objs = ((Set) network.vertices()).toArray();
        Coordinate[] vertices = new Coordinate[numVert];
        for (int i = 0; i < objs.length; i++) {
            vertices[i] = (Coordinate) objs[i];
        }

        boolean visited[] = new boolean[numVert];
        int[] pathKeys = new int[numVert];
        double[] dist = new double[numVert];

        efficientPathLength(network, vOrig, vertices, visited, pathKeys, dist, flight, junctions);

        efficientPath.clear();
        if (!visited[network.getKey(vDest)]) {
            return -1d;
        }
        getPath(network, vOrig, vDest, vertices, pathKeys, efficientPath);

        LinkedList<Coordinate> pathInOrder = revPath(efficientPath);
        efficientPath.clear();
        while (!pathInOrder.isEmpty()) {
            efficientPath.add(pathInOrder.removeFirst());
        }

        int vDestId = network.getKey(vDest);
        if (!visited[vDestId]) {
            return -1d;
        }

        // Deduct wasted fuel
        double newFuel = flight.getEffectiveFuel().doubleValue(SI.KILOGRAM) - dist[vDestId];
        flight.setEffectiveFuel(Amount.valueOf(newFuel, SI.KILOGRAM));

        return dist[vDestId];
    }

    /**
     * Computes efficient path from a source vertex to all reachable vertices of
     * a graph g with nonnegative edge weights This implementation uses
     * Dijkstra's algorithm
     *
     * @param g MapGraph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathkeys minimum path vertices keys
     * @param dist minimum consumptions
     */
    private void efficientPathLength(MapGraph<Coordinate, Segment> g, Coordinate vOrig, Coordinate[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist, FlightSimulation flight, List<Junction> junctions) throws InsufficientFuelException {

        for (Coordinate v : vertices) {
            dist[g.getKey(v)] = Double.POSITIVE_INFINITY;
            pathKeys[g.getKey(v)] = -1;
            visited[g.getKey(v)] = false;
        }

        dist[g.getKey(vOrig)] = 0;

        // Orgin flight departure (calculate climb)
        AlgorithmAnalysis analysisClimb = Calculus.calculateClimb(flight, flight.getFlightInfo().getOriginAirport().getAltitude());
        double climbConsumption = analysisClimb.getConsumption().doubleValue(SI.KILOGRAM);
        double climbDistance = analysisClimb.getDistance().doubleValue(SI.METER);
        // Initialize Landing values
        double descConsumption = 0; // Start at zero
        double descDistance = 0; // Start at zero

        while (vOrig != null) {
            int vOrigValue = g.getKey(vOrig);
            visited[vOrigValue] = true;

            Amount<Mass> prevFuel = flight.getEffectiveFuel();
            for (MapEdge<Coordinate, Segment> edge : g.outgoingEdges(vOrig)) {
                Coordinate vAdj = g.opposite(vOrig, edge);

                if (isTechnicalStop(vAdj, junctions)) {
                    AlgorithmAnalysis analysisDesc = Calculus.calculateLanding(flight, flight.getFlightInfo().getOriginAirport().getAltitude());
                    descConsumption = analysisDesc.getConsumption().doubleValue(SI.KILOGRAM);
                    descDistance = analysisDesc.getDistance().doubleValue(SI.METER);
                }
                // Subtract climbing & descending (distance) from distance
                double cruiseDistance = edge.getWeight() - (climbDistance + descDistance);
                Amount<Length> virtualDist = Calculus.virtualDistance(cruiseDistance, flight, edge.getElement(),
                        edge.getVOrig(), edge.getVDest());

                // Add climbing consumption
                double consumption = climbConsumption;
                // TODO: reorganize params & units
                Amount<Mass> currentFuel = prevFuel.minus(Amount.valueOf(climbConsumption, SI.KILOGRAM));
                flight.setEffectiveFuel(currentFuel);
                consumption += Calculus.calculateCruise(flight, virtualDist).getConsumption().doubleValue(SI.KILOGRAM);
                consumption += descConsumption;

                if (!visited[g.getKey(vAdj)] && dist[g.getKey(vAdj)] > dist[vOrigValue] + consumption) {
                    dist[g.getKey(vAdj)] = dist[vOrigValue] + consumption;
                    pathKeys[g.getKey(vAdj)] = vOrigValue;
                }
            }

            vOrig = null;
            double minimunDistance = Double.POSITIVE_INFINITY;

            Amount<Mass> nextFuel = Amount.valueOf(0d, SI.KILOGRAM);
            for (Coordinate ver : vertices) {
                int vId = g.getKey(ver);
                if (visited[vId] == false && dist[vId] < minimunDistance) {
                    vOrig = ver;
                    minimunDistance = dist[vId];
                    nextFuel = initialFuel.minus(Amount.valueOf(dist[vId], SI.KILOGRAM));
                }
            }

            flight.setEffectiveFuel(nextFuel);
            if (isTechnicalStop(vOrig, junctions)) {
                // calculate climb for techinical stop
                analysisClimb = Calculus.calculateClimb(flight, flight.getFlightInfo().getOriginAirport().getAltitude());
                climbConsumption = analysisClimb.getConsumption().doubleValue(SI.KILOGRAM);
                climbDistance = analysisClimb.getDistance().doubleValue(SI.METER);
            }
        }

        // Reset Fuel
        flight.setEffectiveFuel(initialFuel);
    }

    private boolean isTechnicalStop(Coordinate coord, List<Junction> junctions) {

        if (coord == null) {
            return false;
        }

        boolean isStop = false;
        Iterator<Junction> it = junctions.iterator();

        while (!isStop && it.hasNext()) {

            Junction next = it.next();

            if (next instanceof Stop && coord.equals(next.getCoordinate())) {
                isStop = true;
            }
        }
        return isStop;
    }

    @Override
    protected double pathAlgorithm(MapGraph<Coordinate, Segment> network, Coordinate vOrig,
            Coordinate vDest, LinkedList<Coordinate> efficientPath, FlightSimulation flight,
            List<Junction> junctions) throws InsufficientFuelException {

        return efficientPath(network, vOrig, vDest, efficientPath, flight, junctions);
    }

    @Override
    protected double addStopWeight(Junction junction) {
        return 0d;
    }

    @Override
    protected void actionAtStop(Junction junction, FlightSimulation flight) {
        if (junction instanceof Stop) {
            flight.setEffectiveFuel(initialFuel);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}
