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
 * Tests for copy project controller.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class CopyProjectControllerTest {

    /**
     * The instance to test.
     */
    private CopyProjectController instance;

    /**
     * The flight simulator used in the test.
     */
    private FlightSimulator flightSimulator;

    @Before
    public void setUp() {
        flightSimulator = new FlightSimulator();
        Project projectToCopy = new Project("Simullations Europe", "Flight routes of europe.");
        instance = new CopyProjectController(flightSimulator, projectToCopy);
    }

    /**
     * Test of setCopyProjectData method, of class CopyProjectController.
     */
    @Test
    public void testSetCopyProjectData02() {
        System.out.println("setCopyProjectData");

        String name = "";
        String description = "Test description";

        boolean expResult = false; //name is not valid (empty)
        boolean result = instance.setCopyProjectData(name, description);
        assertEquals(result, expResult);
    }

    /**
     * Test of setCopyProjectData method, of class CopyProjectController.
     */
    @Test
    public void testSetCopyProjectData03() {
        System.out.println("setCopyProjectData");

        String name = "Simullations Europe";
        String description = "Test description";

        boolean expResult = false; //name already exists
        boolean result = instance.setCopyProjectData(name, description);
        assertEquals(result, expResult);
    }

}
