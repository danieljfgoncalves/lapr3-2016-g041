/**
 * Package location for controller concepts.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.datalayer.oracle.AircraftModelOracle;
import lapr.project.datalayer.oracle.AirportOracle;
import lapr.project.datalayer.oracle.CoordinateOracle;
import lapr.project.datalayer.oracle.FlightInfoOracle;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightInfo;

/**
 * Represents the controller to create flights info.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class CreateFlightInfoController {

    /**
     * The selected project serie number.
     */
    private final int projectSertieNumber;

    /**
     * The simulate flight controller.
     *
     * @param projectSerieNumber project serie number
     */
    public CreateFlightInfoController(int projectSerieNumber) {
        this.projectSertieNumber = projectSerieNumber;
    }

    /**
     * Gets the aircraft models.
     *
     * @return aircraft models
     * @throws java.sql.SQLException sql exception
     */
    public List<AircraftModel> getAircraftModels() throws SQLException {
        AircraftModelOracle aircraftModelDAO = new AircraftModelOracle(projectSertieNumber);
        return aircraftModelDAO.getAircraftModels();
    }

    /**
     * Gets the airports.
     *
     * @return airports
     * @throws SQLException sql exception
     */
    public List<Airport> getAirports() throws SQLException {
        AirportOracle airportDAO = new AirportOracle(projectSertieNumber);
        return airportDAO.getAirports();
    }

    /**
     * Gets the coordinates.
     *
     * @return coordinates
     * @throws SQLException sql exception
     */
    public List<Coordinate> getCoordinates() throws SQLException {
        CoordinateOracle coordinateDAO = new CoordinateOracle(projectSertieNumber);
        return coordinateDAO.getCoordinates();
    }

    /**
     * Creates a flight info.
     *
     * @param flightInfo flight info
     * @throws SQLException sql exception
     */
    public void createFlightInfo(FlightInfo flightInfo) throws SQLException {
        FlightInfoOracle flightInfoDAO = new FlightInfoOracle(projectSertieNumber);
        flightInfoDAO.addFlightInfo(flightInfo);
    }
}
