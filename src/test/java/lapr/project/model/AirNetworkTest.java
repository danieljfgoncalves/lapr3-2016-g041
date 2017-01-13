/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import lapr.project.utils.graph.MapEdge;
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
public class AirNetworkTest {

    /**
     * Test instance of a airnetwork
     */
    private AirNetwork instance;

    /**
     * Test adjancency matrix
     */
    private MapGraph<Coordinate, Segment> testMap;

    @Before
    public void setUp() {
        instance = new AirNetwork();

        testMap = new MapGraph(true);
        testMap.insertVertex(new Coordinate("ID01", 1.0, 1.0));
        testMap.insertVertex(new Coordinate("ID02", 2.0, 2.0));
        testMap.insertVertex(new Coordinate("ID03", 3.0, 3.0));

        testMap.insertEdge(new Coordinate("ID01", 1.0, 1.0), new Coordinate("ID02", 2.0, 2.0), new Segment("SG01", Amount.valueOf(1.0, SI.METER), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT)), 0);
        testMap.insertEdge(new Coordinate("ID02", 2.0, 2.0), new Coordinate("ID03", 3.0, 3.0), new Segment("SG02", Amount.valueOf(1.0, SI.METER), Amount.valueOf(2.0, NonSI.DEGREE_ANGLE), Amount.valueOf(2.0, NonSI.KNOT)), 0);
    }

    /**
     * Test of setNetwork & getNetwork method, of class AirNetwork.
     */
    @Test
    public void testGetSetNetwork() {
        System.out.println("getSetNetwork");

        // Test get/set pair
        MapGraph<Coordinate, Segment> expResult = testMap;
        instance.setNetwork(testMap);
        MapGraph<Coordinate, Segment> result = instance.getNetwork();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumSegments method, of class AirNetwork.
     */
    @Test
    public void testGetNumSegments() {
        System.out.println("getNumSegments");
        int expResult = 2;
        AirNetwork instance2 = new AirNetwork(testMap);
        int result = instance2.getNumSegments();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumJunctions method, of class AirNetwork.
     */
    @Test
    public void testGetNumJunctions() {
        System.out.println("getNumJunctions");
        int expResult = 3;
        AirNetwork instance2 = new AirNetwork(testMap);
        int result = instance2.getNumJunctions();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSegments method, of class AirNetwork.
     */
    @Test
    public void testGetSegments() {
        System.out.println("getSegments");
        AirNetwork instance2 = new AirNetwork(testMap);

        List<Segment> segments = new LinkedList<>();
        segments.add(new Segment("SG01", Amount.valueOf(1.0, SI.METER), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT)));
        segments.add(new Segment("SG02", Amount.valueOf(1.0, SI.METER), Amount.valueOf(2.0, NonSI.DEGREE_ANGLE), Amount.valueOf(2.0, NonSI.KNOT)));
        Iterable<Segment> expResult = segments;

        Iterable<MapEdge<Coordinate, Segment>> edges = instance2.getSegments();
        List<Segment> result = new LinkedList<>();
        for (MapEdge<Coordinate, Segment> edge : edges) {
            result.add(edge.getElement());
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of getJunctions method, of class AirNetwork.
     */
    @Test
    public void testGetJunctions() {
        System.out.println("getJunctions");
        AirNetwork instance2 = new AirNetwork(testMap);

        List<Coordinate> coordinates = new LinkedList<>();
        coordinates.add(new Coordinate("ID01", 1.0, 1.0));
        coordinates.add(new Coordinate("ID02", 2.0, 2.0));
        coordinates.add(new Coordinate("ID03", 3.0, 3.0));
        Object[] expResult = coordinates.toArray();
        List junctions = new LinkedList((Set) instance2.getJunctions());
        Object[] result = junctions.toArray();

        assertArrayEquals(expResult, result);
    }

    /**
     * Test of addSegment method, of class AirNetwork.
     */
    @Test
    public void testAddSegment() {
        System.out.println("addSegment");

        AirNetwork instance2 = new AirNetwork(testMap);
        Coordinate coordinateA = new Coordinate("ID01", 1.0, 1.0);
        Coordinate coordinateB = new Coordinate("ID03", 3.0, 3.0);
        Segment newSegment = new Segment("SG01", Amount.valueOf(1.0, SI.METER), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        boolean result = instance2.addSegment(coordinateA, coordinateB, newSegment);
        assertTrue(result);
        // Adding the same segment should return false
        boolean result2 = instance2.addSegment(coordinateA, coordinateB, newSegment);
        assertFalse(result2);
        // Verify if network contains segment
        Segment expResult = new Segment("SG01", Amount.valueOf(1.0, SI.METER), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        Segment result3 = instance2.getSegments().iterator().next().getElement();
        assertEquals(expResult, result3);
    }

    /**
     * Test of addSegment method, of class AirNetwork.
     */
    @Test
    public void testAddSegment_CoordinateIDs() {
        System.out.println("addSegment_with_coordinateIDs");

        AirNetwork instance2 = new AirNetwork(testMap);
        String idA = "ID01";
        String idB = "ID03";
        Segment newSegment = new Segment("SG01", Amount.valueOf(1.0, SI.METER), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        boolean result = instance2.addSegment(idA, idB, newSegment);
        assertTrue(result);
        // Adding the same segment should return false
        boolean result2 = instance2.addSegment(idA, idB, newSegment);
        assertFalse(result2);
        // Verify if network contains segment
        Segment expResult = new Segment("SG01", Amount.valueOf(1.0, SI.METER), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        Segment result3 = instance2.getSegments().iterator().next().getElement();
        assertEquals(expResult, result3);
    }

    /**
     * Test of removeSegment method, of class AirNetwork.
     */
    @Test
    public void testRemoveSegment() {
        System.out.println("removeSegment");

        AirNetwork instance2 = new AirNetwork(testMap);
        Coordinate coordinateA = new Coordinate("ID02", 2.0, 2.0);
        Coordinate coordinateB = new Coordinate("ID03", 3.0, 3.0);
        boolean result = instance2.removeSegment(coordinateA, coordinateB);
        assertTrue(result);

        // See if number of edges is right
        assertTrue(instance2.getNumSegments() == 1);
    }

    /**
     * Test of addJunction method, of class AirNetwork.
     */
    @Test
    public void testAddJunction() {
        System.out.println("addJunction");
        Coordinate newJunction = new Coordinate("ID04", 4.0, 4.0);
        boolean result = instance.addJunction(newJunction);
        // Check if adds (true)
        assertTrue(result);
        // Check if doesn't add same coordinate
        boolean result2 = instance.addJunction(newJunction);
        assertFalse(result2);
        // Verify if network contains coordinate
        assertEquals(new Coordinate("ID04", 4.0, 4.0), instance.getJunctions().iterator().next());
    }

    /**
     * Test of removeJunction method, of class AirNetwork.
     */
    @Test
    public void testRemoveJunction01() {
        System.out.println("removeJunction");
        // Test if method returns true when a valid junction is removed
        Coordinate junction = new Coordinate("ID02", 2.0, 2.0);
        AirNetwork instance1 = new AirNetwork(testMap);
        boolean result = instance1.removeJunction(junction);
        assertTrue(result);
    }

    /**
     * Test of removeJunction method, of class AirNetwork.
     */
    @Test
    public void testRemoveJunction02() {
        System.out.println("removeJunction");
        // Test if method returns false when junction is invalid
        Coordinate junction = new Coordinate("ID04", 4.0, 4.0);
        AirNetwork instance1 = new AirNetwork(testMap);
        boolean result = instance1.removeJunction(junction);
        assertFalse(result);
    }

    /**
     * Test of removeJunction method, of class AirNetwork.
     */
    @Test
    public void testRemoveJunction03() {
        System.out.println("removeJunction");
        // Test if method returns false when junction is invalid
        Coordinate junction = new Coordinate("ID03", 3.0, 3.0);
        AirNetwork instance1 = new AirNetwork(testMap);
        instance1.removeJunction(junction);
        int expResult = 2;
        int result = instance1.getNumJunctions();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AirNetwork.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        // Test if object is equal to AirNetwork
        Object instance2 = new AirNetwork();
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class AirNetwork.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        // Test if object is not equal to AirNetwork
        Object instance2 = new String();
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }

    /**
     * Test of equals method, of class AirNetwork.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals");
        // Test if Airnetwork is equal to AirNetwork
        AirNetwork instance2 = new AirNetwork();
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class AirNetwork.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals");
        // Test if Airnetwork is equal to AirNetwork
        AirNetwork instance2 = new AirNetwork(testMap);
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }
}
