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
 * Tests simulator class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class FlightSimulatorTest {

    private FlightSimulator instance;

    @Before
    public void setUp() {
        instance = new FlightSimulator();
    }

    /**
     * Test of setProjects and getProjects methods, of class FlightSimulator.
     */
    @Test
    public void testGetSetProjects() {
        System.out.println("get and setProjects");

        List<Project> projects = new ArrayList<>();
        projects.add(new Project());

        instance.setProjects(projects);

        assertEquals(instance.getProjects(), projects);
    }

    /**
     * Test of setProjects and getProjects methods, of class FlightSimulator.
     */
    @Test
    public void testGetSetActivatedProject() {
        System.out.println("get and setActivatedProject");

        Project project = new Project();

        instance.setActivatedProject(project);

        assertEquals(instance.getActivatedProject(), project);
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
        Project expResult = new Project(name, description, result.getSerieNumber());

        assertEquals(expResult, result);
    }

    /**
     * Test of validateProject method, of class FlightSimulator.
     */
    @Test
    public void testValidateProject() {
        System.out.println("validateProject");

        Project project = new Project("Fly Safe", "The safest routes in a nutshell", 101);

        assertTrue(instance.validateProject(project));
    }

    /**
     * Test of addProject method, of class FlightSimulator.
     */
    @Test
    public void testAddProject() {
        System.out.println("addProject");

        Project project = new Project("Fly Safe", "The safest routes in a nutshell", 250);

        assertTrue(instance.addProject(project));
    }

    /**
     * Test of equals method, of class FlightSimulator.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;

        assertFalse(instance.equals(obj));

        obj = new FlightSimulator();

        assertTrue(instance.equals(obj));
    }

    /**
     * Test of toString method, of class FlightSimulator.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "Simulator{projects=[]}";
        String result = instance.toString();

        assertEquals(expResult, result);
    }

}
