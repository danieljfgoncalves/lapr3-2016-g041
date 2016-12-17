/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lapr.project.utils.matrix_graph.MatrixGraph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a airnetwork. (contains a graph of coordinates and segments)
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
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
    private MatrixGraph<Coordinate, Segment> testMatrix;

    @Before
    public void setUp() {
        instance = new AirNetwork();

        testMatrix = new MatrixGraph(true);
        testMatrix.insertVertex(new Coordinate(1.0, 1.0));
        testMatrix.insertVertex(new Coordinate(2.0, 2.0));
        testMatrix.insertVertex(new Coordinate(3.0, 3.0));

        testMatrix.insertEdge(new Coordinate(1.0, 1.0), new Coordinate(2.0, 2.0), new Segment(new ArrayList<>(), 1.0, 1.0));
        testMatrix.insertEdge(new Coordinate(2.0, 2.0), new Coordinate(3.0, 3.0), new Segment(new ArrayList<>(), 2.0, 2.0));
    }

    /**
     * Test of setNetwork & getNetwork method, of class AirNetwork.
     */
    @Test
    public void testGetSetNetwork() {
        System.out.println("getSetNetwork");

        // Test get/set pair
        MatrixGraph<Coordinate, Segment> expResult = testMatrix;
        instance.setNetwork(testMatrix);
        MatrixGraph<Coordinate, Segment> result = instance.getNetwork();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumSegments method, of class AirNetwork.
     */
    @Test
    public void testGetNumSegments() {
        System.out.println("getNumSegments");
        int expResult = 2;
        AirNetwork instance2 = new AirNetwork(testMatrix);
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
        AirNetwork instance2 = new AirNetwork(testMatrix);
        int result = instance2.getNumJunctions();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSegments method, of class AirNetwork.
     */
    @Test
    public void testGetSegments() {
        System.out.println("getSegments");
        AirNetwork instance2 = new AirNetwork(testMatrix);

        List<Segment> segments = new LinkedList<>();
        segments.add(new Segment(new ArrayList<>(), 1.0, 1.0));
        segments.add(new Segment(new ArrayList<>(), 2.0, 2.0));
        Iterable<Segment> expResult = segments;

        Iterable<Segment> result = instance2.getSegments();
        assertEquals(expResult, result);
    }

    /**
     * Test of getJunctions method, of class AirNetwork.
     */
    @Test
    public void testGetJunctions() {
        System.out.println("getJunctions");
        AirNetwork instance2 = new AirNetwork(testMatrix);

        List<Coordinate> coordinates = new LinkedList<>();
        coordinates.add(new Coordinate(1.0, 1.0));
        coordinates.add(new Coordinate(2.0, 2.0));
        coordinates.add(new Coordinate(3.0, 3.0));
        Iterable<Coordinate> expResult = coordinates;
        Iterable<Coordinate> result = instance2.getJunctions();
        assertEquals(expResult, result);
    }

    /**
     * Test of addSegment method, of class AirNetwork.
     */
    @Test
    public void testAddSegment() {
        System.out.println("addSegment");

        AirNetwork instance2 = new AirNetwork(testMatrix);
        Coordinate coordinateA = new Coordinate(1.0, 1.0);
        Coordinate coordinateB = new Coordinate(3.0, 3.0);
        Segment newSegment = new Segment(new ArrayList<>(), 1.0, 1.0);
        boolean result = instance2.addSegment(coordinateA, coordinateB, newSegment);
        assertTrue(result);
        // Adding the same segment should return false
        boolean result2 = instance2.addSegment(coordinateA, coordinateB, newSegment);
        assertFalse(result2);
        // Verify if network contains segment
        assertEquals(new Segment(new ArrayList<>(), 1.0, 1.0), instance2.getSegments().iterator().next());
    }

    /**
     * Test of removeSegment method, of class AirNetwork.
     */
    @Test
    public void testRemoveSegment() {
        System.out.println("removeSegment");

        AirNetwork instance2 = new AirNetwork(testMatrix);
        Coordinate coordinateA = new Coordinate(2.0, 2.0);
        Coordinate coordinateB = new Coordinate(3.0, 3.0);
        Segment expResult = new Segment(new ArrayList<>(), 2.0, 2.0);
        Segment result = instance2.removeSegment(coordinateA, coordinateB);
        assertEquals(expResult, result);

        // See if number of edges is right
        assertTrue(instance2.getNumSegments() == 1);
    }

    /**
     * Test of addJunction method, of class AirNetwork.
     */
    @Test
    public void testAddJunction() {
        System.out.println("addJunction");
        Coordinate newJunction = new Coordinate(4.0, 4.0);
        boolean result = instance.addJunction(newJunction);
        // Check if adds (true)
        assertTrue(result);
        // Check if doesn't add same coordinate
        boolean result2 = instance.addJunction(newJunction);
        assertFalse(result2);
        // Verify if network contains coordinate
        assertEquals(new Coordinate(4.0, 4.0), instance.getJunctions().iterator().next());
    }

    /**
     * Test of removeJunction method, of class AirNetwork.
     */
    @Test
    public void testRemoveJunction01() {
        System.out.println("removeJunction");
        // Test if method returns true when a valid junction is removed
        Coordinate junction = new Coordinate(1.0, 1.0);
        AirNetwork instance = new AirNetwork(testMatrix);
        boolean result = instance.removeJunction(junction);
        assertTrue(result);
    }

    /**
     * Test of removeJunction method, of class AirNetwork.
     */
    @Test
    public void testRemoveJunction02() {
        System.out.println("removeJunction");
        // Test if method returns false when junction is invalid
        Coordinate junction = new Coordinate(4.0, 4.0);
        AirNetwork instance = new AirNetwork(testMatrix);
        boolean result = instance.removeJunction(junction);
        assertFalse(result);
    }

    /**
     * Test of removeJunction method, of class AirNetwork.
     */
    @Test
    public void testRemoveJunction03() {
        System.out.println("removeJunction");
        // Test if method returns false when junction is invalid
        Coordinate junction = new Coordinate(3.0, 3.0);
        AirNetwork instance = new AirNetwork(testMatrix);
        instance.removeJunction(junction);
        int expResult = 2;
        int result = instance.getNumJunctions();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class AirNetwork.
     */
    @Test
    public void testHashCode01() {
        System.out.println("hashCode");
        // Verify if equal objects have same hash code
        AirNetwork instance2 = new AirNetwork();
        int expResult = instance2.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class AirNetwork.
     */
    @Test
    public void testHashCode02() {
        System.out.println("hashCode");
        // Test if not equal objects have same hash code
        AirNetwork instance2 = new AirNetwork(testMatrix);
        int expResult = instance2.hashCode();
        int result = instance.hashCode();
        assertTrue(expResult != result);
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
        AirNetwork instance2 = new AirNetwork(testMatrix);
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }

    /**
     * Test of toString method, of class AirNetwork.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        // Test if string is equal
        AirNetwork instance = new AirNetwork(testMatrix);
        String expResult = "AirNetwork{\n"
                + "network=\n"
                + "Vertices:\n"
                + "Latitude: 1,000000\n"
                + "Longitude: 1,000000\n"
                + "\n"
                + "Latitude: 2,000000\n"
                + "Longitude: 2,000000\n"
                + "\n"
                + "Latitude: 3,000000\n"
                + "Longitude: 3,000000\n"
                + "\n"
                + "\n"
                + "Matrix:\n"
                + "   |  0  |  1  |  2 \n"
                + " 0 |     |  X  |     \n"
                + " 1 |     |     |  X  \n"
                + " 2 |     |     |     \n"
                + "\n"
                + "Edges:\n"
                + "From 0 to 1-> Segment{allowedAltitudes=\"[]\", windDirection=\"1.0\", windSpeed=\"1.0\"}\n"
                + "From 1 to 2-> Segment{allowedAltitudes=\"[]\", windDirection=\"2.0\", windSpeed=\"2.0\"}\n"
                + "\n"
                + "\n"
                + "}";

        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
