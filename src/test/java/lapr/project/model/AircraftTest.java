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
 * Tests the aircraft class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AircraftTest {

    private Aircraft instance;

    @Before
    public void setUp() {
        
        List<Integer> maxPassengerPerClass = new ArrayList<>();
        maxPassengerPerClass.add(20);
        maxPassengerPerClass.add(30);
        maxPassengerPerClass.add(40);
        
        instance = new Aircraft(99, new AircraftModel(), "Ryan Air", maxPassengerPerClass, 60);
    }

    /**
     * Test of setId and getId methods, of class Aircraft.
     */
    @Test
    public void testGetSetId() {
        System.out.println("get and setId");

        Integer id = 35;
        instance.setId(id);

        assertEquals(instance.getId(), id);
    }
    
    /**
     * Test of setAircraftModel and getAircraftModel methods, of class Aircraft.
     */
    @Test
    public void testGetSetAircraftModel() {
        System.out.println("getSetAircraftModel");
        
        AircraftModel aircraftModel = new AircraftModel();
        instance.setAircraftModel(aircraftModel);
        
        assertEquals(instance.getAircraftModel(), aircraftModel);
    }

    /**
     * Test of getCompany and setCompany methods, of class Aircraft.
     */
    @Test
    public void testGetSetCompany() {
        System.out.println("get and setCompany");

        String company = "Wizzayr";
        instance.setCompany(company);

        assertEquals(instance.getCompany(), company);
    }

    /**
     * Test of setaxPassengerPerClass and getaxPassengerPerClass methods, of class
     * Aircraft.
     */
    @Test
    public void testGetSetMaxPassengerPerClass() {
        System.out.println("get and set MaxPassengerPerClass");

        List<Integer> maxPassengerPerClass = new ArrayList<>();
        maxPassengerPerClass.add(20);
        maxPassengerPerClass.add(30);
        maxPassengerPerClass.add(40);
        instance.setMaxPassengerPerClass(maxPassengerPerClass);

        assertEquals(instance.getMaxPassengerPerClass(), maxPassengerPerClass);
    }

    /**
     * Test of setMaxCrew and getMaxCrew method, of class
     * Aircraft.
     */
    @Test
    public void testGetSetMaxCrew() {
        System.out.println("get and setMaxCrew");

        Integer maxCrew = null;
        instance.setMaxCrew(maxCrew);

        assertEquals(instance.getMaxCrew(), maxCrew);
    }

    /**
     * Test of equals method, of class Aircraft.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        Object obj = null;

        assertFalse(instance.equals(obj));

        List<Integer> maxPassengerPerClass = new ArrayList<>();
        maxPassengerPerClass.add(20);
        maxPassengerPerClass.add(30);
        maxPassengerPerClass.add(40);
        obj = new Aircraft(99, new AircraftModel(), "Ryan Air", maxPassengerPerClass, 60);

        assertTrue(instance.equals(obj));
    }
}
