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
 * Tests the simulation class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class SimulationTest {

    /**
     * The simulation instance to be tested.
     */
    private Simulation instance;

    @Before
    public void setUp() {
        instance = new Simulation();
    }

    /**
     * Test of getFlights and setFlights method, of class Simulation.
     */
    @Test
    public void testGetSetFlights() {
        System.out.println("getFlights and setFlights");

        List<FlightInfo> flights = new ArrayList<>();
        flights.add(new FlightInfo());

        instance.setFlights(flights);

        assertEquals(instance.getFlights(), flights);
    }

    /**
     * Test 1 of equals method, of class Simulation.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals 1");

        Object obj = null;

        assertFalse(instance.equals(obj));
    }

    /**
     * Test 2 of equals method, of class Simulation.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals 2");

        Object obj = new Simulation();

        assertTrue(instance.equals(obj));
    }

}
