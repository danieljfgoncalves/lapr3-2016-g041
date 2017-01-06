/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import java.io.File;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import lapr.project.model.AirNetwork;
import lapr.project.model.Coordinate;
import lapr.project.model.Project;
import lapr.project.model.Segment;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for import air network controller.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class ImportAirNetworkControllerTest {

    /**
     * Controller test instance
     */
    private ImportAirNetworkController instance;

    /**
     * Test porject.
     */
    private Project testProject;

    @Before
    public void setUp() {

        testProject = new Project();
        instance = new ImportAirNetworkController(testProject);
    }

    /**
     * Test of importAirNetwork method, of class ImportAirNetworkController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testImportAirNetwork01() throws Exception {
        System.out.println("importAirNetwork");
        File fileToImport = new File("xml_files" + File.separator + "TestSet01a_Network.xml");
        boolean result = instance.importAirNetwork(fileToImport);
        assertTrue(result);
    }

    /**
     * Test of importAirNetwork method, of class ImportAirNetworkController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testImportAirNetwork02() throws Exception {
        System.out.println("importAirNetwork");
        File fileToImport = new File("xml_files" + File.separator + "wrong");
        boolean result = instance.importAirNetwork(fileToImport);
        assertFalse(result);
    }

    /**
     * Test of importAirNetwork method, of class ImportAirNetworkController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testImportAirNetwork03() throws Exception {
        System.out.println("importAirNetwork");

        AirNetwork expResult = new AirNetwork();

        expResult.addJunction(new Coordinate("PT01", 41.2481003, -8.6813898));
        expResult.addJunction(new Coordinate("PT02", 40.7812996, -8.6813898));
        expResult.addJunction(new Coordinate("PT03", 38.7812996, -9.1359196));
        expResult.addJunction(new Coordinate("PT04", 40.7812996, -6.6813898));
        expResult.addJunction(new Coordinate("ES01", 40.4936, -3.56676));
        expResult.addJunction(new Coordinate("ES02", 39.5517006, 2.7388101));

        expResult.addSegment("PT01", "PT02", new Segment("PT01", Amount.valueOf(0.0, SI.METER), Amount.valueOf(0.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));
        expResult.addSegment("PT02", "PT01", new Segment("PT01", Amount.valueOf(0.0, SI.METER), Amount.valueOf(0.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));

        expResult.addSegment("PT02", "PT03", new Segment("PT02", Amount.valueOf(0.0, SI.METER), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));
        expResult.addSegment("PT03", "PT02", new Segment("PT02", Amount.valueOf(0.0, SI.METER), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(80.0, NonSI.KNOT)));

        expResult.addSegment("PT02", "PT04", new Segment("PT03", Amount.valueOf(0.0, SI.METER), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));
        expResult.addSegment("PT04", "PT02", new Segment("PT03", Amount.valueOf(0.0, SI.METER), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));

        expResult.addSegment("PT04", "ES01", new Segment("PT04", Amount.valueOf(0.0, SI.METER), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));
        expResult.addSegment("ES01", "PT04", new Segment("PT04", Amount.valueOf(0.0, SI.METER), Amount.valueOf(15.0, NonSI.DEGREE_ANGLE), Amount.valueOf(100.0, NonSI.KNOT)));

        expResult.addSegment("ES01", "ES02", new Segment("ES01", Amount.valueOf(0.0, SI.METER), Amount.valueOf(45.0, NonSI.DEGREE_ANGLE), Amount.valueOf(85.0, NonSI.KNOT)));
        expResult.addSegment("ES02", "ES01", new Segment("ES01", Amount.valueOf(0.0, SI.METER), Amount.valueOf(45.0, NonSI.DEGREE_ANGLE), Amount.valueOf(85.0, NonSI.KNOT)));

        File fileToImport = new File("xml_files" + File.separator + "TestSet01a_Network.xml");
        instance.importAirNetwork(fileToImport);

        AirNetwork result = testProject.getAirNetwork();

        assertEquals(expResult, result);
    }
}
