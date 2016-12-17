/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the aircraft class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AircraftTest {

    private Aircraft instance;

    @Before
    public void setUp() {
        instance = new Aircraft(99, "Ryan Air", 30, 60);
    }

    /**
     * Test of setRegistration and getRegistration methods, of class Aircraft.
     */
    @Test
    public void testGetSetRegistration() {
        System.out.println("get and setRegistration");

        Integer registration = 35;
        instance.setRegistration(registration);

        assertEquals(instance.getRegistration(), registration);
    }

    /**
     * Test of getCompany and setCompany methods, of class Aircraft.
     */
    @Test
    public void testGetSetCompany() {
        System.out.println("get and setCompany");

        String company = "Wizzayr";
        instance.setCompany(company);

        assertEquals(instance.getCompany(), company);
    }

    /**
     * Test of setCabinConfiguration and getCabinConfiguration methods, of class
     * Aircraft.
     */
    @Test
    public void testGetSetCabinConfiguration() {
        System.out.println("get and setCabinConfiguration");

        Integer cabinConfiguration = 45;
        instance.setCabinConfiguration(cabinConfiguration);

        assertEquals(instance.getCabinConfiguration(), cabinConfiguration);
    }

    /**
     * Test of setNumberFlightCrew and getNumberFlightCrew method, of class
     * Aircraft.
     */
    @Test
    public void testGetSetNumberFlightCrew() {
        System.out.println("get and setNumberFlightCrew");

        Integer numberFlightCrew = null;
        instance.setNumberFlightCrew(numberFlightCrew);

        assertEquals(instance.getNumberFlightCrew(), numberFlightCrew);
    }

    /**
     * Test of equals method, of class Aircraft.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;

        assertFalse(instance.equals(obj));

        obj = new Aircraft(99, "Ryan Air", 30, 60);

        assertTrue(instance.equals(obj));
    }

    /**
     * Test of toString method, of class Aircraft.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "Aircraft{registration=\"99\", company=\"Ryan Air\", cabinConfiguration=\"30\", numberFlightCrew=\"60\"}";
        String result = instance.toString();

        assertEquals(expResult, result);
    }

}
