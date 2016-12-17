/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the class motorization.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class MotorizationTest {

    private Motorization instance;

    @Before
    public void setUp() {
        ArrayList<Regime> regimes = new ArrayList<>();
        regimes.add(new Regime("Cruise", 0.564d, 0.85d, 12820d, 45e3));
        instance = new Motorization(4, "GE CF6-80C2B1F", MotorType.TURBOFAN, regimes);
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
     * Test of setRegimes and getRegimes method, of class Motorization.
     */
    @Test
    public void testGetSetRegimes() {
        System.out.println("get and setRegimes");

        List<Regime> regimes = new ArrayList<>();
        regimes.add(new Regime());
        instance.setRegimes(regimes);

        assertEquals(instance.getRegimes(), regimes);
    }

    /**
     * Test of equals method, of class Motorization.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;
        assertFalse(instance.equals(obj));

        ArrayList<Regime> regimes = new ArrayList<>();
        regimes.add(new Regime("Cruise", 0.564d, 0.85d, 12820d, 45e3));
        obj = new Motorization(4, "GE CF6-80C2B1F", MotorType.TURBOFAN, regimes);

        assertTrue(instance.equals(obj));
    }

}
