/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import lapr.project.model.FlightSimulator;
import lapr.project.model.Project;
import lapr.project.utils.DefaultInstantiator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for open project controller.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class OpenProjectControllerTest {

    /**
     * The instance to be tested.
     */
    private OpenProjectController instance;

    /**
     * The flight simulator to be used on tests.
     */
    private FlightSimulator flightSimulator;

    @Before
    public void setUp() {
        flightSimulator = DefaultInstantiator.createSimulator();
        instance = new OpenProjectController(flightSimulator);
    }

    /**
     * Test of activeProjects method, of class OpenProjectController.
     */
    @Test
    public void testActiveProjects() {
        System.out.println("activeProjects");

        Project project = new Project("Testing Project", "Testing project for simulator");
        flightSimulator.addProject(project);

        instance.activeProjects(project);

        assertEquals(flightSimulator.getActivatedProject(), project);
    }

}
