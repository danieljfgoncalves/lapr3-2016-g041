/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.measure.unit.NonSI;
import lapr.project.utils.matrix.graph.MatrixGraph;
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
        testMatrix.insertVertex(new Coordinate("ID01", 1.0, 1.0));
        testMatrix.insertVertex(new Coordinate("ID02", 2.0, 2.0));
        testMatrix.insertVertex(new Coordinate("ID03", 3.0, 3.0));

        testMatrix.insertEdge(new Coordinate("ID01", 1.0, 1.0), new Coordinate("ID02", 2.0, 2.0), new Segment("SG01", new ArrayList<>(), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT)));
        testMatrix.insertEdge(new Coordinate("ID02", 2.0, 2.0), new Coordinate("ID03", 3.0, 3.0), new Segment("SG02", new ArrayList<>(), Amount.valueOf(2.0, NonSI.DEGREE_ANGLE), Amount.valueOf(2.0, NonSI.KNOT)));
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
        segments.add(new Segment("SG01", new ArrayList<>(), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT)));
        segments.add(new Segment("SG02", new ArrayList<>(), Amount.valueOf(2.0, NonSI.DEGREE_ANGLE), Amount.valueOf(2.0, NonSI.KNOT)));
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
        coordinates.add(new Coordinate("ID01", 1.0, 1.0));
        coordinates.add(new Coordinate("ID02", 2.0, 2.0));
        coordinates.add(new Coordinate("ID03", 3.0, 3.0));
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
        String idA = "ID01";
        String idB = "ID03";
        Segment newSegment = new Segment("SG01", new ArrayList<>(), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        boolean result = instance2.addSegment(idA, idB, newSegment);
        assertTrue(result);
        // Adding the same segment should return false
        boolean result2 = instance2.addSegment(idA, idB, newSegment);
        assertFalse(result2);
        // Verify if network contains segment
        Segment resultSegment = new Segment("SG01", new ArrayList<>(), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        assertEquals(resultSegment, instance2.getSegments().iterator().next());
    }

    /**
     * Test of addSegment method, of class AirNetwork.
     */
    @Test
    public void testAddSegment_CoordinateIDs() {
        System.out.println("addSegment_with_coordinateIDs");

        AirNetwork instance2 = new AirNetwork(testMatrix);
        Coordinate coordinateA = new Coordinate("ID01", 1.0, 1.0);
        Coordinate coordinateB = new Coordinate("ID03", 3.0, 3.0);
        Segment newSegment = new Segment("SG01", new ArrayList<>(), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        boolean result = instance2.addSegment(coordinateA, coordinateB, newSegment);
        assertTrue(result);
        // Adding the same segment should return false
        boolean result2 = instance2.addSegment(coordinateA, coordinateB, newSegment);
        assertFalse(result2);
        // Verify if network contains segment
        Segment resultSegment = new Segment("SG01", new ArrayList<>(), Amount.valueOf(1.0, NonSI.DEGREE_ANGLE), Amount.valueOf(1.0, NonSI.KNOT));
        assertEquals(resultSegment, instance2.getSegments().iterator().next());
    }

    /**
     * Test of removeSegment method, of class AirNetwork.
     */
    @Test
    public void testRemoveSegment() {
        System.out.println("removeSegment");

        AirNetwork instance2 = new AirNetwork(testMatrix);
        Coordinate coordinateA = new Coordinate("ID02", 2.0, 2.0);
        Coordinate coordinateB = new Coordinate("ID03", 3.0, 3.0);
        Segment expResult = new Segment("SG02", new ArrayList<>(), Amount.valueOf(2.0, NonSI.DEGREE_ANGLE), Amount.valueOf(2.0, NonSI.KNOT));
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
        Coordinate junction = new Coordinate("ID01", 1.0, 1.0);
        AirNetwork instance1 = new AirNetwork(testMatrix);
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
        AirNetwork instance1 = new AirNetwork(testMatrix);
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
        AirNetwork instance1 = new AirNetwork(testMatrix);
        instance1.removeJunction(junction);
        int expResult = 2;
        int result = instance1.getNumJunctions();
        assertEquals(expResult, result);
    }

    /**
     * Test of importXml method, of class AirNetwork.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testImportXml01() throws Exception {
        System.out.println("importXml");

        File fileToImport = new File("xml_files" + File.separator + "TestSet01a_Network.xml");
        instance = new AirNetwork();

        assertTrue(instance.importXml(fileToImport));
    }

    /**
     * Test of importXml method, of class AirNetwork.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testImportXml02() throws Exception {
        System.out.println("importXml");

        File fileToImport = new File("xml_files" + File.separator + "Wrong");
        instance = new AirNetwork();

        assertFalse(instance.importXml(fileToImport));
    }

    /**
     * Test of importXml method, of class AirNetwork.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testImportXml03() throws Exception {
        System.out.println("importXml");

        File fileToImport = new File("xml_files" + File.separator + "TestSet01a_Network.xml");
        AirNetwork expResult = new AirNetwork();

        expResult.addJunction(new Coordinate("PT01", 41.2481003, -8.6813898));
        expResult.addJunction(new Coordinate("PT02", 40.7812996, -8.6813898));
        expResult.addJunction(new Coordinate("PT03", 38.7812996, -9.1359196));
        expResult.addJunction(new Coordinate("PT04", 40.7812996, -6.6813898));
        expResult.addJunction(new Coordinate("ES01", 40.4936, -3.56676));
        expResult.addJunction(new Coordinate("ES02", 39.5517006, 2.7388101));

        expResult.addSegment("PT01", "PT02", new Segment("PT01", new ArrayList(), Amount.valueOf(0.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));
        expResult.addSegment("PT02", "PT01", new Segment("PT01", new ArrayList(), Amount.valueOf(0.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));

        expResult.addSegment("PT02", "PT03", new Segment("PT02", new ArrayList(), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));
        expResult.addSegment("PT03", "PT02", new Segment("PT02", new ArrayList(), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));

        expResult.addSegment("PT02", "PT04", new Segment("PT03", new ArrayList(), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));
        expResult.addSegment("PT04", "PT02", new Segment("PT03", new ArrayList(), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));

        expResult.addSegment("PT04", "ES01", new Segment("PT04", new ArrayList(), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));
        expResult.addSegment("ES01", "PT04", new Segment("PT04", new ArrayList(), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));

        expResult.addSegment("ES01", "ES02", new Segment("ES01", new ArrayList(), Amount.valueOf(45.0, NonSI.DEGREE_ANGLE), Amount.valueOf(85.0, NonSI.KNOT)));
        expResult.addSegment("ES02", "ES01", new Segment("ES01", new ArrayList(), Amount.valueOf(45.0, NonSI.DEGREE_ANGLE), Amount.valueOf(85.0, NonSI.KNOT)));

        AirNetwork result = new AirNetwork();
        result.importXml(fileToImport);

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
        AirNetwork instance2 = new AirNetwork(testMatrix);
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }
}
