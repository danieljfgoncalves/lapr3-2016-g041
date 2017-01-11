/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import lapr.project.utils.CustomUnits;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the class motorization.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class MotorizationTest {

    /**
     * The instance to be tested.
     */
    private Motorization instance;

    @Before
    public void setUp() {
        instance = new Motorization(4, "RR Trent 970", MotorType.TURBOFAN,
                Amount.valueOf(43E+03, NonSI.FOOT),
                Amount.valueOf(0.85, NonSI.MACH),
                Amount.valueOf(1.6E-4, CustomUnits.TSFC_SI),
                Amount.valueOf(0.96, Unit.ONE),
                new ThrustFunction(Amount.valueOf(348.31E+03, SI.NEWTON),
                        Amount.valueOf(1.8E+05, SI.NEWTON),
                        Amount.valueOf(0.9, NonSI.MACH)));
    }

    /**
     * Test of setNumberOfMotors and getNumberOfMotors methods, of class
     * Motorization.
     */
    @Test
    public void testGetSetNumberOfMotors() {
        System.out.println("get and setNumberOfMotors");

        Integer numberOfMotors = 6;
        instance.setNumberOfMotors(numberOfMotors);

        assertEquals(instance.getNumberOfMotors(), numberOfMotors);
    }

    /**
     * Test of setMotor and getMotor method, of class Motorization.
     */
    @Test
    public void testGetSetMotor() {
        System.out.println("get and setMotor");

        String motor = "GE CF6-90";
        instance.setMotor(motor);

        assertEquals(instance.getMotor(), motor);
    }

    /**
     * Test of setMotorType and getMotorType method, of class Motorization.
     */
    @Test
    public void testGetSetMotorType() {
        System.out.println("get and setMotorType");

        MotorType motorType = MotorType.TURBOPROP;
        instance.setMotorType(motorType);

        assertEquals(instance.getMotorType(), motorType);
    }

    /**
     * Test of setCruiseAltitude and getCruiseAltitude method, of class
     * Motorization.
     */
    @Test
    public void testGetSetCruiseAltitude() {
        System.out.println("getCruiseAltitude and setCruiseAltitude");

        Amount<Length> cruiseAltitude = Amount.valueOf(33e3, NonSI.FOOT);
        instance.setCruiseAltitude(cruiseAltitude);

        assertEquals(instance.getCruiseAltitude(), cruiseAltitude);
    }

    /**
     * Test of getCruiseSpeed and setCruiseSpeed method, of class Motorization.
     */
    @Test
    public void testGetSetCruiseSpeed() {
        System.out.println("getCruiseSpeed and setCruiseSpeed");

        Amount<Velocity> cruiseSpeed = Amount.valueOf(0.47, NonSI.MACH);
        instance.setCruiseSpeed(cruiseSpeed);

        assertEquals(instance.getCruiseSpeed(), cruiseSpeed);
    }

    /**
     * Test of getTsfc and setTsfc method, of class Motorization.
     */
    @Test
    public void testGetSetTsfc() {
        System.out.println("getTsfc and setTsfc");

        Amount tsfc = Amount.valueOf(1.12E-31, CustomUnits.TSFC_SI);
        instance.setTsfc(tsfc);

        assertEquals(instance.getTsfc(), tsfc);
    }

    /**
     * Test of getLapseRateFactor and setLapseRateFactor methods, of class
     * Motorization.
     */
    @Test
    public void testGetSetLapseRateFactor() {
        System.out.println("getLapseRateFactor and setLapseRateFactor");

        Amount lapseRateFactor = Amount.valueOf(0.3, Unit.ONE);
        instance.setLapseRateFactor(lapseRateFactor);

        assertEquals(instance.getLapseRateFactor(), lapseRateFactor);
    }

    /**
     * Test of getThrustFunction and setThrustFunction methods, of class
     * Motorization.
     */
    @Test
    public void testGetSetThrustFunction() {
        System.out.println("getThrustFunction and setThrustFunction");

        ThrustFunction thrustFunction = new ThrustFunction();
        instance.setThrustFunction(thrustFunction);

        assertEquals(instance.getThrustFunction(), thrustFunction);
    }

    /**
     * Test of equals method, of class Motorization.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;
        assertFalse(instance.equals(obj));

        obj = new Motorization(4, "RR Trent 970", MotorType.TURBOFAN,
                Amount.valueOf(43E+03, NonSI.FOOT),
                Amount.valueOf(0.85, NonSI.MACH),
                Amount.valueOf(1.6E-4, CustomUnits.TSFC_SI),
                Amount.valueOf(0.96, Unit.ONE),
                new ThrustFunction(Amount.valueOf(348.31E+03, SI.NEWTON),
                        Amount.valueOf(1.8E+05, SI.NEWTON),
                        Amount.valueOf(0.9, NonSI.MACH)));

        assertTrue(instance.equals(obj));
    }

}
