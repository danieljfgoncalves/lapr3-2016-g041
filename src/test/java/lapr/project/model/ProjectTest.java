/*
 * Package location for Project concept tests
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Project class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class ProjectTest {

    /**
     * The instance to test.
     */
    private Project instance;

    @Before
    public void setUp() {
        instance = new Project();
    }

    /**
     * Test of getDescription and setDescription methods, of class Project.
     */
    @Test
    public void testGetSetDescription() {
        System.out.println("getDescription");

        String expResult = "description";
        instance.setDescription(expResult);
        assertEquals(expResult, instance.getDescription());
    }

    /**
     * Test of getName and setName methods, of class Project.
     */
    @Test
    public void testGetSetName() {
        System.out.println("getName");

        String expResult = "name";
        instance.setName(expResult);
        assertEquals(expResult, instance.getName());
    }

    /**
     * Test of getSerieNumber and setSerieNumber method, of class Project.
     */
    @Test
    public void testGetSetSerieNumber() {
        System.out.println("getSerieNumber");

        int expResult = 10;
        instance.setSerieNumber(expResult);
        assertEquals(expResult, instance.getSerieNumber());
    }

    /**
     * Test of getAirNetwork and setAirNetwork methods, of class Project.
     */
    @Test
    public void testGetSetAirNetwork() {
        System.out.println("getAirNetwork");

        AirNetwork airNetwork = new AirNetwork();
        instance.setAirNetwork(airNetwork);
        assertEquals(airNetwork, instance.getAirNetwork());
    }

    /**
     * Test of getAirports and setAirports methods, of class Project.
     */
    @Test
    public void testGetSetAirports() {
        System.out.println("getAirports");

        List<Airport> airports = new ArrayList();
        instance.setAirports(airports);
        assertEquals(airports, instance.getAirports());
    }

    /**
     * Test of getAircraftModels and setAircraftModels method, of class Project.
     */
    @Test
    public void testGetSetAircraftModels() {
        System.out.println("getAircraftModels");

        List<AircraftModel> aircraftModels = new ArrayList();
        instance.setAircraftModels(aircraftModels);
        assertEquals(aircraftModels, instance.getAircraftModels());
    }

    /**
     * Test of getSimulations and setSimulations methods, of class Project.
     */
    @Test
    public void testGetSetSimulations() {
        System.out.println("getSimulations");
        
        List<Simulation> simulations = new ArrayList();
        assertEquals(simulations, instance.getSimulations());
    }

    /**
     * Test of validate method, of class Project.
     */
    @Test
    public void testValidate01() {
        System.out.println("validate");
        String name = "";
        boolean expResult = false;
        boolean result = instance.validate(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class Project.
     */
    @Test
    public void testValidate02() {
        System.out.println("validate");
        String name = "a";
        boolean expResult = true;
        boolean result = instance.validate(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Project.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;
        assertFalse(instance.equals(obj));
        obj = new Project();
        ((Project)obj).setSerieNumber(1);
        instance.setSerieNumber(1);
        assertTrue(instance.equals(obj));
    }
}
