/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Stop class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class StopTest {

    /**
     * the instance to be tested
     */
    private Stop instance;

    @Before
    public void setUp() {
        instance = new Stop(new Airport(), 1, new GregorianCalendar(2016, 12, 25, 10, 10, 10), new GregorianCalendar(2016, 12, 25, 10, 10, 10));
    }

    /**
     * Test of getSetAirport method, of class Stop.
     */
    @Test
    public void testGetSetAirport() {
        System.out.println("getSetAirport");
        Coordinate coordinates = new Coordinate();
        Airport expResult = new Airport("Sá carneiro", "Porto", "Portugal", "OPO", coordinates, 10.0);
        instance.setAirport(expResult);
        Airport result = instance.getAirport();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetMinimumStopMinutes method, of class Stop.
     */
    @Test
    public void testGetSetMinimumStopMinutes() {
        System.out.println("getSetMinimumStopMinutes");
        Integer expResult = 10;
        instance.setMinimumStopMinutes(10);
        Integer result = instance.getMinimumStopMinutes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetScheduleArrival method, of class Stop.
     */
    @Test
    public void testGetSetScheduleArrival() {
        System.out.println("getSetScheduleArrival");
        Calendar expResult = new GregorianCalendar(2016, 12, 25);
        instance.setScheduleArrival(expResult);
        Calendar result = instance.getScheduleArrival();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetDepartureTime method, of class Stop.
     */
    @Test
    public void testGetSetDepartureTime() {
        System.out.println("getSetDepartureTime");   
        Calendar expResult = new GregorianCalendar(2016, 12, 27);
        instance.setDepartureTime(expResult);
        Calendar result = instance.getDepartureTime();    
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Stop, expecting result true for comparing
     * different objects.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object obj = new Stop(new Airport(), 10, new GregorianCalendar(2016, 12, 25, 10, 10, 10), new GregorianCalendar(2016, 12, 25, 10, 10, 10));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Stop, expecting result true for comparing
     * same objects.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        Object obj = new Stop(new Airport(), 1, new GregorianCalendar(2016, 12, 25, 10, 10, 10), new GregorianCalendar(2016, 12, 25, 10, 10, 10));
        assertTrue(instance.equals(obj));
    }

}
