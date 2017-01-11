/**
 * Package location for Model concepts.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import lapr.project.model.AirNetwork;
import lapr.project.model.Airport;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightInfo;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Junction;
import lapr.project.model.Segment;
import lapr.project.model.Stop;
import lapr.project.utils.graph.MapGraph;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a airnetwork. (contains a graph of coordinates and segments)
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ShortestDistanceTest {

    /**
     * test airnetwork
     */
    private AirNetwork network;

    /**
     * test flight
     */
    private FlightSimulation flight;

    @Before
    public void setUp() {

        /*      0 1 2 3 4
                A B C D E
            0 A|0|2|3|6|0|
            1 B|2|0|1|2|0|
            2 C|0|1|0|3|1|
            3 D|0|0|3|0|0|
            4 E|0|0|0|1|0|
         */
        MapGraph<Coordinate, Segment> graph = new MapGraph(true);
        Coordinate a = new Coordinate("A", 1d, 1d);
        Coordinate b = new Coordinate("B", -1d, 1d);
        Coordinate c = new Coordinate("C", 1d, -1d);
        Coordinate d = new Coordinate("D", 10d, 10d);
        Coordinate e = new Coordinate("E", 1d, 10d);
        graph.insertVertex(a);
        graph.insertVertex(b);
        graph.insertVertex(c);
        graph.insertVertex(d);
        graph.insertVertex(e);

        graph.insertEdge(a, b, new Segment(), 2);
        graph.insertEdge(a, c, new Segment(), 3);
        graph.insertEdge(a, d, new Segment(), 6);
        graph.insertEdge(b, a, new Segment(), 2);
        graph.insertEdge(b, c, new Segment(), 1);
        graph.insertEdge(b, d, new Segment(), 2);
        graph.insertEdge(c, b, new Segment(), 1);
        graph.insertEdge(c, d, new Segment(), 3);
        graph.insertEdge(c, e, new Segment(), 1);
        graph.insertEdge(d, c, new Segment(), 3);
        graph.insertEdge(e, d, new Segment(), 1);

        network = new AirNetwork(graph);

        Airport origin = new Airport();
        origin.setCoordinates(a);
        Airport dest = new Airport();
        dest.setCoordinates(d);
        Airport aStop = new Airport();
        aStop.setCoordinates(c);

        List<Stop> stops = new ArrayList();
        Stop stop = new Stop();
        stop.setAirport(aStop);
        stops.add(stop);

        List<Coordinate> waypoints = new ArrayList<>();
        waypoints.add(b);

        FlightInfo info = new FlightInfo();
        info.setOriginAirport(origin);
        info.setDestinationAirport(dest);
        info.setStops(stops);
        info.setWaypoints(waypoints);

        flight = new FlightSimulation();
        flight.setFlightInfo(info);

    }

    /**
     * Test of generateFlightPlan method, of class ShortestDistance.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGenerateFlightPlan() throws Exception {
        System.out.println("generateFlightPlan");
        LinkedList<Segment> flightplan = new LinkedList();
        ShortestDistance instance = new ShortestDistance();
        double expResult = 5d;
        double result = ((Amount<Length>) instance.generateFlightPlan(network, flight, flightplan)).doubleValue(SI.METER);
        assertEquals(expResult, result, 0.1d);
    }

    /**
     * Test of generateFlightPlan method, of class ShortestDistance.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGenerateFlightPlan02() throws Exception {
        System.out.println("generateFlightPlan");
        LinkedList<Segment> flightplan = new LinkedList();
        ShortestDistance instance = new ShortestDistance();
        int expResult = 4;
        Amount dist = (Amount<Length>) instance.generateFlightPlan(network, flight, flightplan);
        int result = flightplan.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of addStop method, of class ShortestDistance.
     */
    @Test
    public void testAddStop() {
        System.out.println("addStop");
        Junction junction = null;
        ShortestDistance instance = new ShortestDistance();
        double expResult = 0.0;
        double result = instance.addStopWeight(junction);
        assertEquals(expResult, result, 0.0);
    }
}
