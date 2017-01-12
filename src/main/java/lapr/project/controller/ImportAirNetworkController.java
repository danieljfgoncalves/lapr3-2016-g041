/**
 * Package location for Apllication Controllers concepts.
 */
package lapr.project.controller;

import java.io.File;
import java.sql.SQLException;
import lapr.project.datalayer.dao.CoordinateDAO;
import lapr.project.datalayer.dao.SegmentDAO;
import lapr.project.datalayer.oracle.CoordinateOracle;
import lapr.project.datalayer.oracle.SegmentOracle;
import lapr.project.model.AirNetwork;
import lapr.project.model.Coordinate;
import lapr.project.model.Project;
import lapr.project.model.Segment;
import lapr.project.utils.graph.MapEdge;
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
    
    public void saveToDatabase() throws SQLException, Exception{
        CoordinateDAO coordinateDAO = new CoordinateOracle(selectedProject.getSerieNumber());
        SegmentDAO segmentDAO = new SegmentOracle(selectedProject.getSerieNumber());
        
        for(Coordinate coordinate : selectedProject.getAirNetwork().getJunctions()){
            coordinateDAO.addCoordinate(coordinate);
        }
        for (MapEdge<Coordinate, Segment> edge : selectedProject.getAirNetwork().getSegments()) {
            segmentDAO.addSegment(edge);
        }
        
    }

}
