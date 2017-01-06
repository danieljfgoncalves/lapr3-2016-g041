/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import lapr.project.model.FlightSimulator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for create project controller.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class CreateProjectControllerTest {

    /**
     * Instance to be tested.
     */
    private CreateProjectController instance;

    @Before
    public void setUp() {
        instance = new CreateProjectController(new FlightSimulator());
    }

    /**
     * Test of newProject method, of class CreateProjectController.
     */
    @Test
    public void testNewProject() {
        System.out.println("newProject");

        String name = "Flying";
        String description = "All my flights.";

        assertTrue(instance.newProject(name, description));
    }

}
