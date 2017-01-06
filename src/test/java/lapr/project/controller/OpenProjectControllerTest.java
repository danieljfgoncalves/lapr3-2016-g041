/**
 * Package location for Application Controllers tests.
 */
package lapr.project.controller;

import lapr.project.model.FlightSimulator;
import org.junit.Before;

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
     * The instance to be tested.
     */
    private OpenProjectController instance;

    /**
     * The flight simulator to be used on tests.
     */
    private FlightSimulator flightSimulator;

    @Before
    public void setUp() {
        flightSimulator = new FlightSimulator();
        instance = new OpenProjectController(flightSimulator);
    }

}
