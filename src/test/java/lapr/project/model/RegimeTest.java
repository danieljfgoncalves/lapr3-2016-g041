/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import lapr.project.utils.CustomUnits;
import org.jscience.physics.amount.Amount;
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

        Amount tsfc = Amount.valueOf(0.6, CustomUnits.TSFC_US);
        instance.setTsfc(tsfc);
        assertEquals(tsfc, instance.getTsfc());
    }

    /**
     * Test of getSpeed and setSpeed methods, of class Regime.
     */
    @Test
    public void testGetSetSpeed() {
        System.out.println("getSpeed");

        Amount<Velocity> speed = Amount.valueOf(0.9, NonSI.MACH);
        instance.setSpeed(speed);
        assertEquals(speed, instance.getSpeed());
    }

    /**
     * Test of getThrust and setThrust methods, of class Regime.
     */
    @Test
    public void testGetSetThrust() {
        System.out.println("getThrust");

        Amount<Force> thrust = Amount.valueOf(10250d, NonSI.POUND_FORCE);
        instance.setThrust(thrust);
        assertEquals(thrust, instance.getThrust());
    }

    /**
     * Test of getAltitude and setAltitude methods, of class Regime.
     */
    @Test
    public void testGetSetAltitude() {
        System.out.println("getAltitude");

        Amount<Length> altitude = Amount.valueOf(30000d, NonSI.FOOT);
        instance.setAltitude(altitude);
        assertEquals(altitude, instance.getAltitude());
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
}
