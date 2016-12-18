/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class FlightTest {

    /**
     * Test instance of a flight
     */
    private Flight instance;

    @Before
    public void setUp() {
        instance = new Flight();
    }

    /**
     * Test of getFlightType method, of class Flight.
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
     * Test of getDesignator method, of class Flight.
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
     * Test of getAirline method, of class Flight.
     */
    @Test
    public void testGetSetAirline() {
        System.out.println("getAirline");
        String expResult = "TAP";
        instance.setAirline(expResult);
        String result = instance.getAirline();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAircraft method, of class Flight.
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
     * Test of getOrigin method, of class Flight.
     */
    @Test
    public void testGetSetOrigin() {
        System.out.println("getOrigin");
        Airport expResult = new Airport();
        expResult.setIATA("OPO"); // Change one attribute
        instance.setOrigin(expResult);
        Airport result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDestination method, of class Flight.
     */
    @Test
    public void testGetSetDestination() {
        System.out.println("getDestination");
        Airport expResult = new Airport();
        expResult.setIATA("LIS"); // Change one attribute
        instance.setDestination(expResult);
        Airport result = instance.getDestination();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDepartureDate method, of class Flight.
     */
    @Test
    public void testGetSetDepartureDate() {
        System.out.println("getDepartureDate");
        Calendar expResult = new GregorianCalendar(2016, 12, 25);
        instance.setDepartureDate(expResult);
        Calendar result = instance.getDepartureDate();
        assertEquals(expResult, result);

    }

    /**
     * Test of getScheduledArrival method, of class Flight.
     */
    @Test
    public void testGetSetScheduledArrival() {
        System.out.println("getScheduledArrival");
        Calendar expResult = new GregorianCalendar(2016, 12, 25, 15 * 60, 00);
        instance.setScheduledArrival(expResult);
        Calendar result = instance.getScheduledArrival();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStops method, of class Flight.
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
     * Test of getFlightPlan method, of class Flight.
     */
    @Test
    public void testGetSetFlightPlan() {
        System.out.println("getFlightPlan");
        List<Segment> expResult = new LinkedList<>();
        expResult.add(new Segment());
        instance.setFlightPlan(expResult);
        List<Segment> result = instance.getFlightPlan();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Flight.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        // Test if object is equal to Flight
        Object obj = new Flight();
        boolean result = instance.equals(obj);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class Flight.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        // Test if object is not equal to Flight
        Object obj = new Segment();
        boolean result = instance.equals(obj);
        assertFalse(result);
    }

    /**
     * Test of equals method, of class Flight.
     */
    @Test
    public void testEquals03() {
        System.out.println("equals");
        // Test if instance2 is equal to Flight
        Flight instance2 = new Flight();
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class Flight.
     */
    @Test
    public void testEquals04() {
        System.out.println("equals");
        // Test if instance2 is not equal to Flight
        Flight instance2 = new Flight();
        instance2.setDesignator("test");
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }
}
