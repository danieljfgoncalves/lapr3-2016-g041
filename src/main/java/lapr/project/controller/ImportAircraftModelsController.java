/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.datalayer.oracle.AircraftModelOracle;
import lapr.project.model.AircraftModel;
import lapr.project.model.Project;
import lapr.project.utils.importable.AircraftModelsXML;
import org.xml.sax.SAXException;

/**
 * Controller to create a project.
 *
 * @author Eric Amaral 1141570
 * @author Daniel Gonçalves 1151452
 * @author Ivo Ferro 1151159
 * @author Tiago Correia 1151031
 */
public class ImportAircraftModelsController {

    /**
     * The project.
     */
    private final Project project;

    /**
     * Creates an instance of ImportAircraftModelsController.
     *
     * @param project project to be imported
     */
    public ImportAircraftModelsController(Project project) {
        this.project = project;
    }

    /**
     * Imports a given XML file.
     *
     * @param file file to import
     * @return true if it is successfully imported, false otherwise
     * @throws SAXException parser error
     * @throws IOException invalid file or path
     * @throws ParserConfigurationException configurations errors
     * @throws java.sql.SQLException
     */
    public boolean importAircraftModels(File file) throws SAXException, IOException, ParserConfigurationException, SQLException {
        AircraftModelsXML importer = new AircraftModelsXML(file);

        Object aircraftModelsObj = importer.importFile();

        if (aircraftModelsObj != null && aircraftModelsObj instanceof List) {
            AircraftModelOracle aircraftModelDAO = new AircraftModelOracle(project.getSerieNumber());

            List<AircraftModel> aircraftModels = (List<AircraftModel>) aircraftModelsObj;
            for (AircraftModel aircraftModel : aircraftModels) {
                aircraftModelDAO.addAircraftModel((AircraftModel) aircraftModel);
            }

            return true;
        }
        return false;
    }

}
