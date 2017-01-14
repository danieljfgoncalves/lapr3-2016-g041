/**
 * Package location for Application Controllers concepts.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.datalayer.oracle.AircraftModelOracle;
import lapr.project.model.AircraftModel;

/**
 * Controller to export by aircrafts.
 *
 * @author Eric Amaral 1141570
 * @author Daniel Gonçalves 1151452
 * @author Ivo Ferro 1151159
 * @author Tiago Correia 1151031
 */
public class ExportByAircraftsController {

    /**
     * The project serie number.
     */
    private final int projectSerieNumber;

    /**
     * Creates an instance of export by aircraft controller.
     *
     * @param projectSerieNumber the project serie number.
     */
    public ExportByAircraftsController(int projectSerieNumber) {
        this.projectSerieNumber = projectSerieNumber;
    }

    /**
     * Gets the aircraft models.
     *
     * @return aircraft models
     * @throws java.sql.SQLException sql exception
     */
    public List<AircraftModel> getAircraftModels() throws SQLException {
        AircraftModelOracle aircraftModelDAO = new AircraftModelOracle(projectSerieNumber);
        return aircraftModelDAO.getAircraftModels();
    }
}
