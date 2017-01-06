/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import lapr.project.model.Project;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for open project controller.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class OpenProjectControllerTest {

    /**
     * The instance to test.
     */
    private OpenProjectController instance;

    @Before
    public void setUp() {
        instance = new OpenProjectController();
    }

    /**
     * Test 1 of validateProject method, of class OpenProjectController.
     */
    @Test
    public void testValidateProject1() {
        System.out.println("validateProject 1");

        Project project = new Project(91919191, "Professional Flights", "Simulations of professional flights consuptions.");

        assertTrue(instance.validateProject(project));
    }

    /**
     * Test 2 of validateProject method, of class OpenProjectController.
     */
    @Test
    public void testValidateProject2() {
        System.out.println("validateProject 2");

        Project project = new Project(91919191, "", "Simulations of professional flights consuptions.");

        assertFalse(instance.validateProject(project));
    }

    /**
     * Test 3 of validateProject method, of class OpenProjectController.
     */
    @Test
    public void testValidateProject3() {
        System.out.println("validateProject 3");

        Project project = new Project(91919191, "                      ", "Simulations of professional flights consuptions.");

        assertFalse(instance.validateProject(project));
    }

}
