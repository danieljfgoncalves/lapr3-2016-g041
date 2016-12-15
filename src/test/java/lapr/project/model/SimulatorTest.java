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
public class SimulatorTest {

    private Simulator instance;

    @Before
    public void setUp() {
        instance = new Simulator();
    }

    /**
     * Test of setProjects and getProjects methods, of class Simulator.
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
     * Test of setProjects and getProjects methods, of class Simulator.
     */
    @Test
    public void testGetSetActivatedProject() {
        System.out.println("get and setActivatedProject");

        Project project = new Project();

        instance.setActivatedProject(project);

        assertEquals(instance.getActivatedProject(), project);
    }

    /**
     * Test of hashCode method, of class Simulator.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");

        int expResult = 236;
        int result = instance.hashCode();

        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Simulator.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;

        assertFalse(instance.equals(obj));

        obj = new Simulator();

        assertTrue(instance.equals(obj));
    }

    /**
     * Test of toString method, of class Simulator.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "Simulator{projects=[]}";
        String result = instance.toString();

        assertEquals(expResult, result);
    }

    /**
     * Test of createProject method, of class Simulator.
     */
    @Test
    public void testCreateProject() {
        System.out.println("createProject");

        Project expResult = new Project();
        Project result = instance.createProject();
        assertEquals(expResult, result);
    }

}
