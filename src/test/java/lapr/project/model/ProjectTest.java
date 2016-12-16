/*
 * Package location for Project concept tests
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for segment class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class ProjectTest {

    private Project instance;

    @Before
    public void setUp() {
        instance = new Project();
    }

    /**
     * Test of getDescription method, of class Project.
     */
    @Test
    public void testGetSetDescription() {
        System.out.println("getDescription");
        Project instance = new Project();
        String expResult = "description";
        instance.setDescription(expResult);
        assertEquals(expResult, instance.getDescription());
    }

    /**
     * Test of getName method, of class Project.
     */
    @Test
    public void testGetSetName() {
        System.out.println("getName");
        Project instance = new Project();
        String expResult = "name";
        instance.setName(expResult);
        assertEquals(expResult, instance.getName());
    }

    /**
     * Test of getSerieNumber method, of class Project.
     */
    @Test
    public void testGetSetSerieNumber() {
        System.out.println("getSerieNumber");
        Project instance = new Project();
        int expResult = 10;
        instance.setSerieNumber(expResult);
        assertEquals(expResult, instance.getSerieNumber());
    }

    /**
     * Test of validate method, of class Project.
     */
    @Test
    public void testValidate01() {
        System.out.println("validate");
        String name = "";
        String description = "";
        boolean expResult = false;
        boolean result = instance.validate(name, description);
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Project.
     */
    @Test
    public void testValidate02() {
        System.out.println("validate");
        String name = "a";
        String description = "b";
        boolean expResult = true;
        boolean result = instance.validate(name, description);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Project.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Project instance = new Project();
        int expResult = -2050343103;
        int result = instance.hashCode();
        assertEquals(expResult, instance.hashCode());
    }

    /**
     * Test of equals method, of class Project.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Project instance = new Project();
        Object obj = null;
        assertFalse(instance.equals(obj));
        obj = new Project();
        assertTrue(instance.equals(obj));

    }

    /**
     * Test of toString method, of class Project.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Project instance = new Project();
        instance.setDescription("name");
        instance.setName("name");
        instance.setSerieNumber(1);
        String expResult = "Project{" + "description=" + "name" + ", name=" + "name" + ", serieNumber=" + "1" + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
