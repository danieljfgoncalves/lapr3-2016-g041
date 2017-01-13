/**
 * Package location for controller concepts.
 */
package lapr.project.controller;

import java.io.File;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.datalayer.dao.SegmentDAO;
import lapr.project.datalayer.oracle.FlightInfoOracle;
import lapr.project.datalayer.oracle.FlightSimulationOracle;
import lapr.project.datalayer.oracle.SegmentOracle;
import lapr.project.model.AirNetwork;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightInfo;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Segment;
import lapr.project.model.flightplan.FlightPlan;
import lapr.project.utils.graph.MapEdge;

/**
 * Represents the controller to simulate flights.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class SimulateFlightController {

    /**
     * The selected project serie number.
     */
    private final int projectSertieNumber;

    /**
     * The simulate flight controller.
     *
     * @param projectSerieNumber project serie number
     */
    public SimulateFlightController(int projectSerieNumber) {
        this.projectSertieNumber = projectSerieNumber;
    }

    /**
     * Gets the flights info.
     *
     * @return flights info
     * @throws SQLException sql exception
     */
    public List<FlightInfo> getFlightsInfo() throws SQLException {
        FlightInfoOracle flightInfoDAO = new FlightInfoOracle(projectSertieNumber);
        return flightInfoDAO.getFlightsInfo();
    }

    /**
     * Gets the flight plans.
     *
     * @return flight plans
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public List<FlightPlan> getFlightPlans() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<FlightPlan> flightPlans = new ArrayList<>();

        String DIR_FLIGHT_PLANS = "src/main/java/lapr/project/model/flightplan/algorithms";
        File folder = new File(DIR_FLIGHT_PLANS);

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                Path filePath = file.toPath();

                String extension = filePath.toString().substring(filePath.toString().indexOf('.'));
                if (extension.equals(".java")) {
                    String caminho = filePath.toString().replaceAll("\\/|\\\\", ".");
                    caminho = caminho.replace("src.main.java.", "");
                    caminho = caminho.replace(".java", "");

                    Class cls = Class.forName(caminho.trim());
                    Object object = (Object) cls.newInstance();
                    flightPlans.add((FlightPlan) object);
                }

            }
        }

        return flightPlans;
    }

    /**
     * Gets the air network.
     *
     * @return the air network
     * @throws Exception exception
     */
    public AirNetwork getAirNetwork() throws Exception {
        SegmentDAO segmentDAO = new SegmentOracle(projectSertieNumber);
        List<MapEdge<Coordinate, Segment>> edges = segmentDAO.getSegments();
        AirNetwork airNetwork = new AirNetwork();
        for (MapEdge<Coordinate, Segment> mapEdge : edges) {
            airNetwork.addJunction(mapEdge.getVDest());
            airNetwork.addJunction(mapEdge.getVOrig());
            airNetwork.addSegment(mapEdge.getVOrig(), mapEdge.getVDest(), mapEdge.getElement());
        }
        return airNetwork;
    }

    /**
     * Saves the flight simulation.
     *
     * @param flightSimulation flight simulation
     * @throws SQLException sql eception
     */
    public void createFlightSimulation(FlightSimulation flightSimulation) throws SQLException {
        FlightSimulationOracle flightSimulationDAO = new FlightSimulationOracle(projectSertieNumber);
        flightSimulationDAO.addFlightSimulation(flightSimulation);
    }
}
