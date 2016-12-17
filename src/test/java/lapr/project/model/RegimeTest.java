/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Regime class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class RegimeTest {

    /**
     * The instance to be tested.
     */
    private Regime instance;

    @Before
    public void setUp() {
        instance = new Regime();
    }

    /**
     * Test of getRegimeID and setRegimeID methods, of class Regime.
     */
    @Test
    public void testGetSetRegimeID() {
        System.out.println("getRegimeID");

        String regime = "test regime";
        instance.setRegimeID(regime);
        assertEquals(regime, instance.getRegimeID());
    }

    /**
     * Test of getTsfc and setTsfc methods, of class Regime.
     */
    @Test
    public void testGetSetTsfc() {
        System.out.println("getTsfc");

        Double tsfc = 0.600d;
        instance.setTsfc(tsfc);
        assertEquals(tsfc, instance.getTsfc());
    }

    /**
     * Test of getSpeed and setSpeed methods, of class Regime.
     */
    @Test
    public void testGetSetSpeed() {
        System.out.println("getSpeed");

        Double speed = 0.90d;
        instance.setSpeed(speed);
        assertEquals(speed, instance.getSpeed());
    }

    /**
     * Test of getThrust and setThrust methods, of class Regime.
     */
    @Test
    public void testGetSetThrust() {
        System.out.println("getThrust");

        Double thrust = 10250.0d;
        instance.setThrust(thrust);
        assertEquals(thrust, instance.getThrust());
    }

    /**
     * Test of getAltitude and setAltitude methods, of class Regime.
     */
    @Test
    public void testGetSetAltitude() {
        System.out.println("getAltitude");

        Double altitude = 30000.0d;
        instance.setAltitude(altitude);
        assertEquals(altitude, instance.getAltitude());
    }

    /**
     * Test of hashCode method, of class Regime.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        int expResult = 1011290466;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Regime.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;
        assertFalse(instance.equals(obj));
        obj = new Regime();
        assertTrue(instance.equals(obj));
    }

    /**
     * Test of toString method, of class Regime.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = String.format("Regime ID: %s\n"
                + "TSFC: %.3f\n"
                + "Speed: %.2f\n"
                + "Thrust: %.1f\n"
                + "Altitude: %.1f", "Cruise", 0.1d, 0.1d, 1.0d, 100.0d);
        assertEquals(expResult, instance.toString());
    }

}
