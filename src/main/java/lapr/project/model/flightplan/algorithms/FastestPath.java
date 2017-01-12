/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
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
import lapr.project.utils.graph.MapGraphAlgorithms;
import org.jscience.physics.amount.Amount;

/**
 * Represents the algorithm to calculate the fastest time (SI: Min) flight plan.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FastestPath extends ShortestFlightPlan {

    /**
     * Algorithm's description.
     */
    private static final String DESCRIPTION = "Fastest Path";

    @Override
    public Amount<?> generateFlightPlan(AirNetwork network, FlightSimulation flight, LinkedList<Segment> flightplan)
            throws Exception {

        MapGraph<Coordinate, Segment> graph = createFlightTimeGraph(network, flight);

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

    private MapGraph<Coordinate, Segment> createFlightTimeGraph(AirNetwork network, FlightSimulation flight) throws CloneNotSupportedException {

        Coordinate vOrig = flight.getFlightInfo().getOriginAirport().getCoordinates();
        Coordinate vDest = flight.getFlightInfo().getDestinationAirport().getCoordinates();
        Amount<Length> altitude = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseAltitude();
        Amount<Velocity> machNumber = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseSpeed();
        List<Stop> stops = flight.getFlightInfo().getStops();

        MapGraph<Coordinate, Segment> graph = network.getNetwork().clone();
        for (MapEdge<Coordinate, Segment> edge : graph.edges()) {

            double subToDist = 0;
            double addToTime = 0;
            if (isTechnicalStop(edge.getVOrig(), stops) || edge.getVOrig().equals(vOrig)) {

                AlgorithmAnalysis climb = Calculus.calculateClimb(flight, getAirportAltitude(edge.getVOrig(), flight));
                subToDist += climb.getDistance().doubleValue(SI.METER);
                addToTime += climb.getDuration().doubleValue(SI.SECOND);
            } else if (isTechnicalStop(edge.getVDest(), stops) || edge.getVDest().equals(vDest)) {

                AlgorithmAnalysis landing = Calculus.calculateLanding(flight, getAirportAltitude(edge.getVDest(), flight));
                subToDist += landing.getDistance().doubleValue(SI.METER);
                addToTime += landing.getDuration().doubleValue(SI.SECOND);
            }

            Amount<Angle> flightDirection = Calculus.direction(edge.getVOrig(), edge.getVDest());

            double groundSpeed = Calculus.calculateGS(altitude, machNumber,
                    edge.getElement().getWindIntensity(), edge.getElement().getWindDirection(), flightDirection).doubleValue(SI.METERS_PER_SECOND);
            double cruiseDistance = edge.getWeight() - subToDist;

            double cruiseTime = cruiseDistance / groundSpeed;

            edge.setWeight(cruiseTime + addToTime);
        }

        return graph;
    }

    private boolean isTechnicalStop(Coordinate coord, List<Stop> stops) {

        boolean isStop = false;
        Iterator<Stop> it = stops.iterator();

        while (!isStop && it.hasNext()) {

            Stop next = it.next();

            if (coord.equals(next.getCoordinate())) {
                isStop = true;
            }
        }
        return isStop;
    }

    private Amount<Length> getAirportAltitude(Coordinate coordinate, FlightSimulation flight) {

        if (coordinate.equals(flight.getFlightInfo().getOriginAirport().getCoordinates())) {
            return flight.getFlightInfo().getOriginAirport().getAltitude();
        }
        if (coordinate.equals(flight.getFlightInfo().getOriginAirport().getCoordinates())) {
            return flight.getFlightInfo().getDestinationAirport().getAltitude();
        }

        Iterator<Stop> it = flight.getFlightInfo().getStops().iterator();
        while (it.hasNext()) {

            Stop next = it.next();
            if (coordinate.equals(next.getCoordinate())) {
                return next.getAirport().getAltitude();
            }
        }
        return Amount.valueOf(0d, SI.METER);
    }

    @Override
    protected double addStopWeight(Junction junction) {
        // If the junction is a technical stop add minimal stop time.
        return (junction instanceof Stop) ? ((Stop) junction).getMinimumStopMinutes().doubleValue(NonSI.MINUTE) : 0d;
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

}
