/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the AirportsRegister class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AirportsRegisterTest {

    /**
     * The instance to test.
     */
    private AirportsRegister instance;

    @Before
    public void setUp() {
        List<Airport> airports = new ArrayList<>();
        airports.add(new Airport());
        instance = new AirportsRegister(airports);
    }

    /**
     * Test of getAirports and setAirports methods, of class AirportsRegister.
     */
    @Test
    public void testGetSetAirports() {
        System.out.println("getAirports");

        List<Airport> airports = new ArrayList<>();
        airports.add(new Airport("Francisco Sá Carneiro", "Porto", "Portugal", "OPO", 1.0d, 1.1d, Amount.valueOf(100.0d, SI.METER)));
        instance.setAirports(airports);
        assertEquals(airports, instance.getAirports());
    }

    /**
     * Test of equals method, of class AirportsRegister.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");

        List<Airport> airports = new ArrayList<>();
        airports.add(new Airport());
        Object obj = new AirportsRegister(airports);
        assertTrue(instance.equals(obj));
    }

    @Test
    public void testImportXml() throws Exception {
        System.out.println("importXml");

        File fileToImport = new File("xml_files" + File.separator + "TestSet01a_Airports.xml");
        fileToImport.getAbsolutePath();
        instance = new AirportsRegister();

        assertTrue(instance.importXml(fileToImport));
    }

}
