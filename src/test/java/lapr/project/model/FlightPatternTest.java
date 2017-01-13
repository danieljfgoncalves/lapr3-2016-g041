/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the FlightPattern class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightPatternTest {

    FlightPattern instance;

    @Before
    public void setUp() {
        instance = new FlightPattern();

        instance.insertLine(Amount.valueOf(0, SI.METER), Amount.valueOf(210, NonSI.KNOT), Amount.valueOf(180, NonSI.KNOT), Amount.valueOf(-5, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(1000, SI.METER), Amount.valueOf(210, NonSI.KNOT), Amount.valueOf(200, NonSI.KNOT), Amount.valueOf(-7, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(2000, SI.METER), Amount.valueOf(220, NonSI.KNOT), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(-7, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(3000, SI.METER), Amount.valueOf(230, NonSI.KNOT), Amount.valueOf(270, NonSI.KNOT), Amount.valueOf(-8, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(4000, SI.METER), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-8, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(5000, SI.METER), Amount.valueOf(260, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(6000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(7000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(8000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(9000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(10000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(11000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(12000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(13000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        instance.insertLine(Amount.valueOf(14000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));

    }

    /**
     * Test of getFlightProfile method, of class FlightPattern.
     */
    @Test
    public void testGetFlightProfile() {
        System.out.println("getFlightProfile");
        FlightPattern inst = new FlightPattern();
        Amount[][] expResult = new Amount[15][4];
        Amount[][] result = inst.getFlightProfile();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setFlightProfile method, of class FlightPattern.
     */
    @Test
    public void testSetFlightProfile() {
        System.out.println("setFlightProfile");
        Amount[][] matrix = {{Amount.valueOf(0, SI.METER), Amount.valueOf(210, NonSI.KNOT), Amount.valueOf(180, NonSI.KNOT), Amount.valueOf(-5, SI.METERS_PER_SECOND)}};
        instance.setFlightProfile(matrix);
        Assert.assertArrayEquals(matrix, instance.getFlightProfile());
    }

    /**
     * Test of numLines method, of class FlightPattern.
     */
    @Test
    public void testNumLines() {
        System.out.println("numLines");
        int expResult = 15;
        int result = instance.numLines();
        assertEquals(expResult, result);
    }

    /**
     * Test 1 of getVclimb method, of class FlightPattern.
     */
    @Test
    public void testGetVclimb01() {
        System.out.println("getVclimb");
        Amount<Length> altitude = (Amount.valueOf(9372.36, SI.METER));
        Amount<Velocity> result = instance.getVclimb(altitude);
        Amount<Velocity> expResult = (Amount.valueOf(290, NonSI.KNOT));
        assertEquals(expResult, result);
    }

    /**
     * Test 2 of getVclimb method, of class FlightPattern.
     */
    @Test
    public void testGetVclimb02() {
        System.out.println("getVclimb");
        Amount<Length> altitude = (Amount.valueOf(3884.71, SI.METER));
        Amount<Velocity> result = instance.getVclimb(altitude);
        Amount<Velocity> expResult = (Amount.valueOf(230, NonSI.KNOT));
        assertEquals(expResult, result);
    }

    /**
     * Test 3 of getVclimb method, of class FlightPattern.
     */
    @Test
    public void testGetVclimb03() {
        System.out.println("getVclimb");
        Amount<Length> altitude = (Amount.valueOf(10, SI.METER));
        Amount<Velocity> result = instance.getVclimb(altitude);
        Amount<Velocity> expResult = (Amount.valueOf(210, NonSI.KNOT));
        assertEquals(expResult, result);
    }

    /**
     * Test 1 of getVdesc method, of class FlightPattern.
     */
    @Test
    public void testGetVdesc01() {
        System.out.println("getVdesc");
        Amount<Length> altitude = (Amount.valueOf(7145.33, SI.METER));
        Amount<Velocity> result = instance.getVdesc(altitude);
        Amount<Velocity> expResult = (Amount.valueOf(300, NonSI.KNOT));
        assertEquals(expResult, result);
    }

    /**
     * Test 2 of getVdesc method, of class FlightPattern.
     */
    @Test
    public void testGetVdesc02() {
        System.out.println("getVdesc");
        Amount<Length> altitude = (Amount.valueOf(1633.92, SI.METER));
        Amount<Velocity> result = instance.getVdesc(altitude);
        Amount<Velocity> expResult = (Amount.valueOf(200, NonSI.KNOT));
        assertEquals(expResult, result);
    }

    /**
     * Test 3 of getVdesc method, of class FlightPattern.
     */
    @Test
    public void testGetVdesc03() {
        System.out.println("getVdesc");
        Amount<Length> altitude = (Amount.valueOf(257.88, SI.METER));
        Amount<Velocity> result = instance.getVdesc(altitude);
        Amount<Velocity> expResult = (Amount.valueOf(180, NonSI.KNOT));
        assertEquals(expResult, result);
    }

    /**
     * Test of getRdesc method, of class FlightPattern.
     */
    @Test
    public void testGetRdesc() {
        System.out.println("getRdesc");
        Amount<Length> altitude = (Amount.valueOf(5500.88, SI.METER));
        Amount<Velocity> result = instance.getRdesc(altitude);
        Amount<Velocity> expResult = (Amount.valueOf(-10, SI.METERS_PER_SECOND));
        assertEquals(expResult, result);
    }

    /**
     * Test 1 of getAltitudeIndex method, of class FlightPattern.
     */
    @Test
    public void testGetAltitudeIndex01() {
        System.out.println("getAltitudeIndex");
        Amount<Length> altitude = (Amount.valueOf(5305.87, SI.METER));
        int result = instance.getAltitudeIndex(altitude);
        int expResult = 5;
        assertEquals(expResult, result);
    }

    /**
     * Test 2 of getAltitudeIndex method, of class FlightPattern.
     */
    @Test
    public void testGetAltitudeIndex02() {
        System.out.println("getAltitudeIndex");
        Amount<Length> altitude = (Amount.valueOf(9797.22, SI.METER));
        int result = instance.getAltitudeIndex(altitude);
        int expResult = 9;
        assertEquals(expResult, result);
    }

    /**
     * Test 3 of getAltitudeIndex method, of class FlightPattern.
     */
    @Test
    public void testGetAltitudeIndex03() {
        System.out.println("getAltitudeIndex");
        Amount<Length> altitude = (Amount.valueOf(5000, SI.METER));
        int result = instance.getAltitudeIndex(altitude);
        int expResult = 5;
        assertEquals(expResult, result);
    }

}
