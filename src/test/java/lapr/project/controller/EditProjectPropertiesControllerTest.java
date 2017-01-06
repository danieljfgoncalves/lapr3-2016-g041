/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import lapr.project.model.FlightSimulator;
import lapr.project.model.Project;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for editProjectPropertiesController class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class EditProjectPropertiesControllerTest {

    private EditProjectPropertiesController instance;
    private FlightSimulator flightSimulator;
    private Project project;

    @Before
    public void setUp() {
        flightSimulator = new FlightSimulator();
        project = new Project();
        instance = new EditProjectPropertiesController(flightSimulator, project);
    }

    /**
     * Test of setProjectProperties method, of class
     * EditProjectPropertiesController.
     */
    @Test
    public void testSetProjectProperties02() {
        System.out.println("setProjectProperties");
        String name = "";
        String description = "";
        boolean expResult = false;
        boolean result = instance.setProjectProperties(name, description);
        assertEquals(expResult, result);
    }

}
