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
 * Tests the aircraft models class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AircraftModelsRegisterTest {

    /**
     * The airacraft model register to be tested.
     */
    private AircraftModelsRegister instance;

    @Before
    public void setUp() {
        List<AircraftModel> aircraftModels = new ArrayList<>();
        aircraftModels.add(new AircraftModel());
        instance = new AircraftModelsRegister(aircraftModels);
    }

    /**
     * Test of getAircraftModels and setAircraftModels method, of class
     * AircraftModelsRegister.
     */
    @Test
    public void testGetAircraftModels() {
        System.out.println("getAircraftModels and setAircraftModels");

        List<AircraftModel> aircraftModels = new ArrayList<>();
        aircraftModels.add(new AircraftModel());

        instance.setAircraftModels(aircraftModels);

        assertEquals(instance.getAircraftModels(), aircraftModels);
    }

    /**
     * Test of equals method, of class AircraftModelsRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        List<AircraftModel> aircraftModels = new ArrayList<>();
        aircraftModels.add(new AircraftModel());
        Object obj = new AircraftModelsRegister(aircraftModels);

        assertTrue(instance.equals(obj));
    }

}
