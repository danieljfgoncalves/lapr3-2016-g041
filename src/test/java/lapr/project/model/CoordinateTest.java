/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Coordinate class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class CoordinateTest {

    private Coordinate instance;

    @Before
    public void setUp() {
        instance = new Coordinate();
    }

    /**
     * Test of getLongitude and setLongitude methods, of class Coordinate.
     */
    @Test
    public void testGetSetLongitude() {
        System.out.println("getLongitude");

        Double longitude = 41.254514;
        instance.setLongitude(longitude);
        assertEquals(longitude, instance.getLongitude());
    }

    /**
     * Test of getLatitude and setLatitude methods, of class Coordinate.
     */
    @Test
    public void testGetSetLatitude() {
        System.out.println("getLatitude");

        Double latitude = -8.844170;
        instance.setLatitude(latitude);
        assertEquals(latitude, instance.getLatitude());
    }

    /**
     * Test of hashCode method, of class Coordinate.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        int expResult = 9583;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Coordinate.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;
        assertFalse(instance.equals(obj));
        obj = new Coordinate();
        assertTrue(instance.equals(obj));
    }

    /**
     * Test of toString method, of class Coordinate.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = String.format("Latitude: %.6f\nLongitude: %.6f\n", 0.0d, 0.0d);
        String result = instance.toString();
        assertEquals(expResult, result);

    }

}