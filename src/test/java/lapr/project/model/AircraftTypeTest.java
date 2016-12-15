/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for AircraftType enumeration class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AircraftTypeTest {

    /**
     * Test of valueOf method, of class AircraftType.
     */
    @Test
    public void testValueOfPassenger() {
        System.out.println("valueOf");
        String name = "PASSENGER";
        AircraftType expResult = AircraftType.PASSENGER;
        AircraftType result = AircraftType.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class AircraftType.
     */
    @Test
    public void testValueOfCargo() {
        System.out.println("valueOf");
        String name = "CARGO";
        AircraftType expResult = AircraftType.CARGO;
        AircraftType result = AircraftType.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of valueOf method, of class AircraftType.
     */
    @Test
    public void testValueOfMixed() {
        System.out.println("valueOf");
        String name = "MIXED";
        AircraftType expResult = AircraftType.MIXED;
        AircraftType result = AircraftType.valueOf(name);
        assertEquals(expResult, result);
    }

}
