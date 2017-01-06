/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a flight simulation class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightSimulationTest {

    /**
     * Test instance
     */
    private FlightSimulation instance;

    @Before
    public void setUp() {

        instance = new FlightSimulation(1, new FlightInfo(), new GregorianCalendar(2016, 12, 25),
                new GregorianCalendar(2016, 12, 25), 6, Amount.valueOf(2d, SI.KILO(SI.KILOGRAM)),
                Amount.valueOf(100d, SI.KILO(SI.KILOGRAM)), new LinkedList<>());
    }

    /**
     * Test of getId method, of class FlightSimulation.
     */
    @Test
    public void testGetSetId() {
        System.out.println("getSetId");
        instance.setId(0);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFlightInfo method, of class FlightSimulation.
     */
    @Test
    public void testGetSetFlightInfo() {
        System.out.println("getSetFlightInfo");
        FlightInfo info = new FlightInfo();
        info.setDesignator("test");

        instance.setFlightInfo(info);
        FlightInfo expResult = info;
        FlightInfo result = instance.getFlightInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getScheduledArrival method, of class FlightSimulation.
     */
    @Test
    public void testGetSetScheduledArrival() {
        System.out.println("getSetScheduledArrival");
        instance.setScheduledArrival(new GregorianCalendar(2017, 1, 1));
        Calendar expResult = new GregorianCalendar(2017, 1, 1);
        Calendar result = instance.getScheduledArrival();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDepartureDate method, of class FlightSimulation.
     */
    @Test
    public void testGetSetDepartureDate() {
        System.out.println("getSetDepartureDate");
        instance.setDepartureDate(new GregorianCalendar(2017, 1, 1));
        Calendar expResult = new GregorianCalendar(2017, 1, 1);
        Calendar result = instance.getDepartureDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEffectiveCrew method, of class FlightSimulation.
     */
    @Test
    public void testGetSetEffectiveCrew() {
        System.out.println("getSetEffectiveCrew");
        instance.setEffectiveCrew(10);
        int expResult = 10;
        int result = instance.getEffectiveCrew();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEffectiveCargo method, of class FlightSimulation.
     */
    @Test
    public void testGetSetEffectiveCargo() {
        System.out.println("getSetEffectiveCargo");
        instance.setEffectiveCargo(Amount.valueOf(10, SI.KILO(SI.KILOGRAM)));
        Amount<Mass> expResult = Amount.valueOf(10, SI.KILO(SI.KILOGRAM));
        Amount<Mass> result = instance.getEffectiveCargo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEffectiveFuel method, of class FlightSimulation.
     */
    @Test
    public void testGetSetEffectiveFuel() {
        System.out.println("getSetEffectiveFuel");
        instance.setEffectiveFuel(Amount.valueOf(10, SI.KILO(SI.KILOGRAM)));
        Amount<Mass> expResult = Amount.valueOf(10, SI.KILO(SI.KILOGRAM));
        Amount<Mass> result = instance.getEffectiveFuel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFlightplan method, of class FlightSimulation.
     */
    @Test
    public void testGetSetFlightplan() {
        System.out.println("getFlightplan");
        LinkedList<Segment> flightplan = new LinkedList();
        flightplan.add(new Segment());
        instance.setFlightplan(flightplan);
        LinkedList<Segment> expResult = flightplan;
        LinkedList<Segment> result = instance.getFlightplan();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class FlightSimulation.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        FlightSimulation test = new FlightSimulation();
        test.setId(1);

        int expResult = test.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class FlightSimulation.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new FlightSimulation();
        FlightSimulation instance2 = new FlightSimulation();
        boolean result = instance2.equals(obj);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class FlightSimulation.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        FlightSimulation instance2 = new FlightSimulation();
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }

    /**
     * Test of compareTo method, of class FlightSimulation.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        FlightSimulation o = new FlightSimulation();
        FlightSimulation instance2 = new FlightSimulation();
        int expResult = 0;
        int result = instance2.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class FlightSimulation.
     */
    @Test
    public void testCompareTo02() {
        System.out.println("compareTo");
        FlightSimulation instance2 = new FlightSimulation();
        int expResult = 1;
        int result = instance.compareTo(instance2);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of compareTo method, of class FlightSimulation.
     */
    @Test
    public void testCompareTo03() {
        System.out.println("compareTo");
        FlightSimulation instance2 = new FlightSimulation();
        int expResult = -1;
        int result = instance2.compareTo(instance);
        assertEquals(expResult, result);
    }
}
