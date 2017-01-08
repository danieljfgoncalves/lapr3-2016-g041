/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.Project;
import lapr.project.utils.Import;
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
     */
    public boolean importFile(File file) throws SAXException, IOException, ParserConfigurationException {
        return Import.importAircraftModelsFromXml(file, project.getSerieNumber());
    }

}
