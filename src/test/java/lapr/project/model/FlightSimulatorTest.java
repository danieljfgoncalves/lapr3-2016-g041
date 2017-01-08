/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests simulator class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightSimulatorTest {

    private FlightSimulator instance;

    @Before
    public void setUp() {
        instance = new FlightSimulator();
    }

    /**
     * Test of createProject method, of class FlightSimulator.
     */
    @Test
    public void testCreateProject() {
        System.out.println("createProject");

        String name = "Fly Safe";
        String description = "The safest routes in a nutshell";

        Project result = instance.createProject(name, description);
        result.setSerieNumber(1);
        Project expResult = new Project(name, description);
        expResult.setSerieNumber(1);

        assertEquals(expResult, result);
    }
}
