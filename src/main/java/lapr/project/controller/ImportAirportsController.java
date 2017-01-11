/**
 * Package location for Apllication Controllers concepts.
 */
package lapr.project.controller;

import java.io.File;
import java.util.List;
import lapr.project.model.Airport;
import lapr.project.model.Project;
import lapr.project.utils.importable.AirportXML;

/**
 * Controller to import airports.
 *
 * @author Eric Amaral 1141570
 * @author Daniel Gonçalves 1151452
 * @author Ivo Ferro 1151159
 * @author Tiago Correia 1151031
 */
public class ImportAirportsController {

    /**
     * The project.
     */
    private final Project selectedProject;

    /**
     * Creates an instance of ImportAirportsController.
     *
     * @param project project to be imported
     */
    public ImportAirportsController(Project project) {
        this.selectedProject = project;
    }

    /**
     * Imports a list of airports from a xml file.
     *
     * @param fileToImport file containing xml to import
     * @return the imported list of airports or null if null
     * @throws Exception
     */
    public List<Airport> importAirports(File fileToImport) throws Exception {
        AirportXML importer = new AirportXML(fileToImport);
        List<Airport> airports = (List<Airport>) importer.importFile();

        if (airports == null) {
            return null;
        }

        return airports;
    }

    public void saveToDatabase(List<Airport> airports) {
        for (Airport airport : airports) {
            //TODO
        }
    }
}
