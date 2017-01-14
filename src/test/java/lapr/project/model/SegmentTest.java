/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for segment class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class SegmentTest {

    /**
     * The instance to be tested.
     */
    private Segment instance;

    @Before
    public void setUp() {
        instance = new Segment();
    }

    /**
     * Test of getId and setId method, of class Segment.
     */
    @Test
    public void testGetSetIdentification() {
        System.out.println("get and setIdentification");

        String identification = "";
        instance.setId(identification);

        assertEquals(instance.getId(), identification);
    }

    /**
     * Test of getAltitude method, of class Segment.
     */
    @Test
    public void testGetSetAltitude() {
        System.out.println("setAllowedAltitudes");
        instance.setAltitude(Amount.valueOf(100d, SI.METER));

        Amount<Length> expResult = Amount.valueOf(100d, SI.METER);
        Amount<Length> result = instance.getAltitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWindDirection method, of class Segment.
     */
    @Test
    public void testGetSetWindDirection() {
        System.out.println("get and setWindDirection");

        Amount<Angle> windDirection = Amount.valueOf(75.5d, NonSI.DEGREE_ANGLE);

        instance.setWindDirection(windDirection);

        assertTrue(instance.getWindDirection().approximates(windDirection));
    }

    /**
     * Test of getWindSpeed method, of class Segment.
     */
    @Test
    public void testGetSetWindSpeed() {
        System.out.println("get and setWindSpeed");

        Amount<Velocity> windSpeed = Amount.valueOf(113.13d, NonSI.KNOT);

        instance.setWindIntensity(windSpeed);

        assertTrue(instance.getWindIntensity().approximates(windSpeed));
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;

        assertFalse(instance.equals(obj));

        obj = new Segment();

        assertTrue(instance.equals(obj));
    }
}
