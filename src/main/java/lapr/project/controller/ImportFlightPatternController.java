/**
 * Package location for Apllication Controllers concepts.
 */
package lapr.project.controller;

import java.io.File;
import lapr.project.model.FlightPattern;
import lapr.project.utils.importable.FlightPatternCSV;

/**
 * Controller to import a a flight pattern.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author Tiago Correia 1151031
 */
public class ImportFlightPatternController {

    /**
     * Creates an instance of the controller.
     */
    public ImportFlightPatternController() {
    }

    /**
     * Imports a flight pattern.
     *
     * @param fileToImport file containing csv to import
     * @return true if imported was successful
     * @throws Exception
     */
    public FlightPattern importFlightPattern(File fileToImport) throws Exception {

        FlightPatternCSV importer = new FlightPatternCSV(fileToImport);

        Object flightPatternObj = importer.importFile();

        if (flightPatternObj != null && flightPatternObj instanceof FlightPattern) {
            FlightPattern flightPattern = (FlightPattern) flightPatternObj;
            return flightPattern;
        }

        return null;
    }
}
