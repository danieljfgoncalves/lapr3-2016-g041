/*
 * Package location for flight plan algorithms.
 */
package lapr.project.model.flightplan.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.measure.quantity.Mass;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import lapr.project.model.AirNetwork;
import lapr.project.model.Airport;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightPattern;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Junction;
import lapr.project.model.Segment;
import lapr.project.model.Stop;
import lapr.project.utils.CustomUnits;
import lapr.project.utils.graph.MapGraph;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Represents the algorithm to calculate the fastest time (SI: Min) flight plan.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class EfficientConsumptionTest {

    private AirNetwork airNetwork;

    private FlightSimulation flight;

    private FlightPattern flightPattern = new FlightPattern();

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

        graph.insertEdge(a, b, new Segment(), 2000000);
        graph.insertEdge(a, c, new Segment(), 3000000);
        graph.insertEdge(a, d, new Segment(), 6000000);
        graph.insertEdge(b, a, new Segment(), 2000000);
        graph.insertEdge(b, c, new Segment(), 1000000);
        graph.insertEdge(b, d, new Segment(), 2000000);
        graph.insertEdge(c, b, new Segment(), 1000000);
        graph.insertEdge(c, d, new Segment(), 3000000);
        graph.insertEdge(c, e, new Segment(), 1000000);
        graph.insertEdge(d, c, new Segment(), 3000000);
        graph.insertEdge(e, d, new Segment(), 1000000);

        airNetwork = new AirNetwork(graph);

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

        flight = new FlightSimulation();
        flightPattern.insertLine(Amount.valueOf(0, SI.METER), Amount.valueOf(210, NonSI.KNOT), Amount.valueOf(180, NonSI.KNOT), Amount.valueOf(-5, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(1000, SI.METER), Amount.valueOf(210, NonSI.KNOT), Amount.valueOf(200, NonSI.KNOT), Amount.valueOf(-7, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(2000, SI.METER), Amount.valueOf(220, NonSI.KNOT), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(-7, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(3000, SI.METER), Amount.valueOf(230, NonSI.KNOT), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(-8, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(4000, SI.METER), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(270, NonSI.KNOT), Amount.valueOf(-8, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(5000, SI.METER), Amount.valueOf(260, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(6000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(7000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(8000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(9000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(10000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(11000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(12000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(13000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(14000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flight.getFlightInfo().getAircraft().setFlightPattern(flightPattern);
        flight.getFlightInfo().setOriginAirport(origin);
        flight.getFlightInfo().setDestinationAirport(dest);
        flight.getFlightInfo().setStops(stops);
        flight.getFlightInfo().setWaypoints(waypoints);
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setLapseRateFactor(Amount.valueOf(0.96, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setWingArea(Amount.valueOf(858, SI.SQUARE_METRE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setAspectRatio(Amount.valueOf(9, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setCruiseAltitude(Amount.valueOf(10061, SI.METER));
        flight.getFlightInfo().getAircraft().getAircraftModel().setE(Amount.valueOf(0.84, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrust0(Amount.valueOf(3.38E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrustMaxSpeed(Amount.valueOf(1.80E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setMaxSpeed(Amount.valueOf(0.9, NonSI.MACH));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setNumberOfMotors(4);
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setTsfc(Amount.valueOf(1.60E-04, CustomUnits.TSFC_NNS));
        flight.setEffectiveCargo(Amount.valueOf(70000, SI.KILOGRAM));
        flight.setEffectiveFuel(Amount.valueOf(144720, SI.KILOGRAM));
        flight.getFlightInfo().getAircraft().getAircraftModel().setEmptyWeight(Amount.valueOf(3.00E+05, SI.KILOGRAM));
    }

    /**
     * Test of generateFlightPlan method, of class EfficientConsumption.
     */
    @Test
    public void testGenerateFlightPlan() throws Exception {
        System.out.println("generateFlightPlan");
        LinkedList<Segment> flightplan = new LinkedList<>();
        EfficientConsumption instance = new EfficientConsumption();
        double expResult = 125767d;
        double result = ((Amount<Mass>) instance.generateFlightPlan(airNetwork, flight, flightplan)).doubleValue(SI.KILOGRAM);
        assertEquals(expResult, result, 10d);
    }

    /**
     * Test of pathAlgorithm method, of class EfficientConsumption.
     */
//    @Test
    public void testPathAlgorithm() throws Exception {
        System.out.println("pathAlgorithm");
        MapGraph<Coordinate, Segment> network = null;
        Coordinate vOrig = null;
        Coordinate vDest = null;
        LinkedList<Coordinate> efficientPath = null;
        FlightSimulation flight = null;
        List<Junction> junctions = null;
        EfficientConsumption instance = new EfficientConsumption();
        double expResult = 0.0;
        double result = instance.pathAlgorithm(network, vOrig, vDest, efficientPath, flight, junctions);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStopWeight method, of class EfficientConsumption.
     */
    @Test
    public void testAddStopWeight() {
        System.out.println("addStopWeight");
        Junction junction = new Stop();
        EfficientConsumption instance = new EfficientConsumption();
        double expResult = 0.0;
        double result = instance.addStopWeight(junction);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDescription method, of class EfficientConsumption.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        EfficientConsumption instance = new EfficientConsumption();
        String expResult = "Fuel efficient Path";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

}
