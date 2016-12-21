/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.measure.quantity.Duration;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
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
        instance = new Stop(new Airport(), Amount.valueOf(1d, NonSI.MINUTE), new GregorianCalendar(2016, 12, 25, 10, 10, 10), new GregorianCalendar(2016, 12, 25, 10, 10, 10));
    }

    /**
     * Test of getSetAirport method, of class Stop.
     */
    @Test
    public void testGetSetAirport() {
        System.out.println("getSetAirport");
        Airport expResult = new Airport("Sá carneiro", "Porto", "Portugal", "OPO", 1.0d, -1.0d, Amount.valueOf(10d, SI.METER));
        instance.setAirport(expResult);
        Airport result = instance.getAirport();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetMinimumStopMinutes method, of class Stop.
     */
    @Test
    public void testGetSetMinimumStop() {
        System.out.println("getSetMinimumStopMinutes");
        Amount<Duration> expResult = Amount.valueOf(10d, NonSI.MINUTE);
        instance.setMinimumStop(expResult);
        Amount<Duration> result = instance.getMinimumStop();
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
        Object obj = new Stop(new Airport(), Amount.valueOf(10d, NonSI.MINUTE), new GregorianCalendar(2016, 12, 25, 10, 10, 10), new GregorianCalendar(2016, 12, 25, 10, 10, 10));
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
        Object obj = new Stop(new Airport(), Amount.valueOf(1d, NonSI.MINUTE), new GregorianCalendar(2016, 12, 25, 10, 10, 10), new GregorianCalendar(2016, 12, 25, 10, 10, 10));
        assertTrue(instance.equals(obj));
    }

}
