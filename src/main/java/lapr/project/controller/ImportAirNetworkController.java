/**
 * Package location for Apllication Controllers concepts.
 */
package lapr.project.controller;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.AirNetwork;
import lapr.project.model.Project;
import org.xml.sax.SAXException;

/**
 * Controller to import a airnetwork.
 *
 * @author Daniel Gonçalves 1151452
 * @author Eric Amaral 1141570
 * @author Ivo Ferro 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia 1151031
 */
public class ImportAirNetworkController {

    /**
     * The selected project.
     */
    private Project selectedProject;

    /**
     * Creates an instance of the controller.
     *
     * @param Project the selected project
     */
    public ImportAirNetworkController(Project project) {
        this.selectedProject = project;
    }

    /**
     * Imports an air network.
     * 
     * @param fileToImport file containing xml to import
     * @return true if imported was successful
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException 
     */
    public boolean importAirNetwork(File fileToImport) throws SAXException, IOException, ParserConfigurationException {

        AirNetwork airNetwork = selectedProject.getAirNetwork();

        return airNetwork.importXml(fileToImport);
    }

}
