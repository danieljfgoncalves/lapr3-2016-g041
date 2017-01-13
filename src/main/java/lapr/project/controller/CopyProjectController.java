/**
 * Package location for controller concepts.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.List;
import lapr.project.datalayer.dao.AircraftModelDAO;
import lapr.project.datalayer.dao.AirportDAO;
import lapr.project.datalayer.dao.CoordinateDAO;
import lapr.project.datalayer.dao.ProjectDAO;
import lapr.project.datalayer.dao.SegmentDAO;
import lapr.project.datalayer.oracle.AircraftModelOracle;
import lapr.project.datalayer.oracle.AirportOracle;
import lapr.project.datalayer.oracle.CoordinateOracle;
import lapr.project.datalayer.oracle.ProjectOracle;
import lapr.project.datalayer.oracle.SegmentOracle;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.Coordinate;
import lapr.project.model.Project;
import lapr.project.model.Segment;
import lapr.project.utils.graph.MapEdge;

/**
 * Represents a CopyProjectController.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class CopyProjectController {

    /**
     * The copy of a project.
     */
    private Project projectCopy;

    /**
     * The project to copy.
     */
    private final Project originalProject;

    private ProjectDAO projectDAO;

    /**
     * Creates an instance of the controller.
     *
     * @param originalProject the project to copy
     */
    public CopyProjectController(Project originalProject) {
        this.originalProject = originalProject;
        projectCopy = new Project();
        projectDAO = new ProjectOracle();
    }

    /**
     * Sets the project's copy name and description.
     *
     * @param name the name of the project copy
     * @param description the description of the project copy
     * @return true if the name is not empty and if a project with the same name
     * does not exist, false otherwise
     * @throws java.sql.SQLException
     */
    public boolean createProjectCopy(String name, String description) throws SQLException, Exception {

        if (projectCopy.validateName() && projectDAO.validateProjectName(name)) {
            projectCopy = projectDAO.createEmptyProject();
            projectCopy.setName(name);
            projectCopy.setDescription(description);
            projectDAO.updateProjectNameAndDescription(projectCopy);
            copyProject();
            return true;
        }
        return false;
    }

    /**
     * Gets the created project.
     *
     * @return created project
     * @throws java.lang.Exception
     */
    public Project getCopiedProject() throws Exception {
        return projectDAO.getProject(projectCopy.getSerieNumber());
    }

    public void deleteProject() throws Exception {
        projectDAO.deleteProject(projectCopy.getSerieNumber());
    }

    public void copyProject() throws Exception {
        AirportDAO originalAirportDAO = new AirportOracle(originalProject.getSerieNumber());
        SegmentDAO originalSegmentDAO = new SegmentOracle(originalProject.getSerieNumber());
        CoordinateDAO originalCoordinateDAO = new CoordinateOracle(originalProject.getSerieNumber());
        AircraftModelDAO originalAircraftModelDAO = new AircraftModelOracle(originalProject.getSerieNumber());

        AirportDAO copyAirportDAO = new AirportOracle(projectCopy.getSerieNumber());
        SegmentDAO copySegmentDAO = new SegmentOracle(projectCopy.getSerieNumber());
        CoordinateDAO copyCoordinateDAO = new CoordinateOracle(projectCopy.getSerieNumber());
        AircraftModelDAO copyAircraftModelDAO = new AircraftModelOracle(projectCopy.getSerieNumber());

        //adds coordinates to copy project
        List<Coordinate> coordinates = originalCoordinateDAO.getCoordinates();
        if (!coordinates.isEmpty()) {
            for (Coordinate coordinate : coordinates) {
                copyCoordinateDAO.addCoordinate(coordinate);
            }
        }
        
        //adds aircraft model
        List<AircraftModel> aircraftModels = originalAircraftModelDAO.getAircraftModels();
        if (!aircraftModels.isEmpty()) {
            for (AircraftModel aircraftModel : aircraftModels) {
                copyAircraftModelDAO.addAircraftModel(aircraftModel);
            }
        }

        //adds airports to copy project
        List<Airport> airports = originalAirportDAO.getAirports();
        if (!airports.isEmpty()) {
            for (Airport airport : airports) {
                copyAirportDAO.addAirport(airport);
            }
        }

        //adds segments to copy project
        List<MapEdge<Coordinate, Segment>> segments = originalSegmentDAO.getSegments();
        if (!segments.isEmpty()) {
            for (MapEdge<Coordinate, Segment> segment : segments) {
                copySegmentDAO.addSegment(segment);
            }
        }
    }
}
