/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Airport class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AirportTest {

    /**
     * The instance to be tested.
     */
    private Airport instance;

    public AirportTest() {
    }

    @Before
    public void setUp() {
        instance = new Airport();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName and setName methods, of class Airport.
     */
    @Test
    public void testGetSetName() {
        System.out.println("getName and setName");

        String name = "Aeroporto Francisco Sá Carneiro";
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getTown and setTown methods, of class Airport.
     */
    @Test
    public void testGetSetTown() {
        System.out.println("getTown and setTown");

        String town = "Porto";
        instance.setTown(town);
        assertEquals(town, instance.getTown());
    }

    /**
     * Test of getCountry and setCountry methods, of class Airport.
     */
    @Test
    public void testGetSetCountry() {
        System.out.println("getCountry");

        String country = "Portugal";
        instance.setCountry(country);
        assertEquals(country, instance.getCountry());
    }

    /**
     * Test of getIATA and setIATA methods, of class Airport.
     */
    @Test
    public void testGetSetIATA() {
        System.out.println("getIATA");

        String IATA = "OPO";
        instance.setIATA(IATA);
        assertEquals(IATA, instance.getIATA());
    }

    /**
     * Test of getCoordinates and setCoordinates methods, of class Airport.
     */
    @Test
    public void testGetSetCoordinates() {
        System.out.println("getCoordinates");

        Coordinate coordinate = new Coordinate("testLocation", 41.2481003, -8.6813898);
        instance.setCoordinates(coordinate);
        assertEquals(coordinate, instance.getCoordinates());
    }

    /**
     * Test of getAltitude and setAltitude methods, of class Airport.
     */
    @Test
    public void testGetSetAltitude() {
        System.out.println("getAltitude");

        Amount<Length> altitude = Amount.valueOf(50d, SI.METER);
        instance.setAltitude(altitude);
        assertEquals(altitude, instance.getAltitude());
    }

    /**
     * Test of equals method, of class Airport.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;
        assertFalse(instance.equals(obj));
        obj = new Airport();
        assertTrue(instance.equals(obj));
    }
}
