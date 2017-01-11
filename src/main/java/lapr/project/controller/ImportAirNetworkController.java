/**
 * Package location for Apllication Controllers concepts.
 */
package lapr.project.controller;

import java.io.File;
import lapr.project.model.AirNetwork;
import lapr.project.model.Project;
import lapr.project.utils.importable.AirNetworkXML;

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
    private final Project selectedProject;

    /**
     * Creates an instance of the controller.
     *
     * @param project the selected project
     */
    public ImportAirNetworkController(Project project) {
        this.selectedProject = project;
    }

    /**
     * Imports an air network.
     *
     * @param fileToImport file containing xml to import
     * @return true if imported was successful
     * @throws Exception
     */
    public boolean importAirNetwork(File fileToImport) throws Exception {

        AirNetworkXML importer = new AirNetworkXML(fileToImport);

        Object airNetwork = importer.importFile();

        if (airNetwork != null && airNetwork instanceof AirNetwork) {

            this.selectedProject.setAirNetwork((AirNetwork) airNetwork);

            return true;
        }

        return false;
    }

}
