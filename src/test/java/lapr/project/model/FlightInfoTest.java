/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a flight class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class FlightInfoTest {

    /**
     * Test instance of a flight
     */
    private FlightInfo instance;

    @Before
    public void setUp() {
        instance = new FlightInfo();
    }

    /**
     * Test of getFlightType method, of class FlightInfo.
     */
    @Test
    public void testGetSetFlightType() {
        System.out.println("getFlightType");
        FlightType expResult = FlightType.REGULAR;
        instance.setFlightType(expResult);
        FlightType result = instance.getFlightType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDesignator method, of class FlightInfo.
     */
    @Test
    public void testGetSetDesignator() {
        System.out.println("getDesignator");
        String expResult = "TP0001a";
        instance.setDesignator(expResult);
        String result = instance.getDesignator();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraft method, of class FlightInfo.
     */
    @Test
    public void testGetSetAircraft() {
        System.out.println("getAircraft");
        Aircraft expResult = new Aircraft();
        expResult.setCompany("TAP"); // Change one attribute
        instance.setAircraft(expResult);
        Aircraft result = instance.getAircraft();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrigin method, of class FlightInfo.
     */
    @Test
    public void testGetSetOrigin() {
        System.out.println("getOrigin");
        Airport expResult = new Airport();
        expResult.setIATA("OPO"); // Change one attribute
        instance.setOriginAirport(expResult);
        Airport result = instance.getOriginAirport();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDestination method, of class FlightInfo.
     */
    @Test
    public void testGetSetDestination() {
        System.out.println("getDestination");
        Airport expResult = new Airport();
        expResult.setIATA("LIS"); // Change one attribute
        instance.setDestinationAirport(expResult);
        Airport result = instance.getDestinationAirport();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStops method, of class FlightInfo.
     */
    @Test
    public void testGetSetStops() {
        System.out.println("getStops");
        List<Stop> expResult = new LinkedList<>();
        expResult.add(new Stop());
        instance.setStops(expResult);
        List<Stop> result = instance.getStops();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWaypoints and setWaypoints methods, of class FlightInfo.
     */
    @Test
    public void testGetSetWaypoints() {
        System.out.println("getFlightPlan");
        List<Coordinate> expResult = new LinkedList<>();
        expResult.add(new Coordinate());
        instance.setWaypoints(expResult);
        List<Coordinate> result = instance.getWaypoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class FlightInfo.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        // Test if object is equal to FlightInfo
        Object obj = new FlightInfo();
        boolean result = instance.equals(obj);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class FlightInfo.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        // Test if object is not equal to FlightInfo
        Object obj = new Segment();
        boolean result = instance.equals(obj);
        assertFalse(result);
    }

    /**
     * Test of equals method, of class FlightInfo.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals");
        // Test if instance2 is equal to FlightInfo
        FlightInfo instance2 = new FlightInfo();
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class FlightInfo.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals");
        // Test if instance2 is not equal to FlightInfo
        FlightInfo instance2 = new FlightInfo();
        instance2.setDesignator("test");
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }
}
