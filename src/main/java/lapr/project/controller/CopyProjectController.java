/**
 * Package location for controller concepts.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;

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
    private final Project projectCopy;

    /**
     * The simulator.
     */
    private final FlightSimulator flightSimulator;

    /**
     * Creates an instance of the controller.
     *
     * @param flightSimulator the simulator
     * @param project the selected project
     */
    public CopyProjectController(FlightSimulator flightSimulator, Project project) {
        this.flightSimulator = flightSimulator;
        projectCopy = new Project();
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
    public boolean setCopyProjectData(String name, String description) throws SQLException {
        if (projectCopy.validateName() && flightSimulator.validateProjectName(name)) {
            projectCopy.setName(name);
            projectCopy.setDescription(description);
            return true;
        }
        return false;
    }

    /**
     * Validates if the project does not exist in the flight simulator.
     *
     * @return true if the project does not exist and is added to the project
     * list, false otherwise
     * @throws java.sql.SQLException
     */
    public boolean addProjectCopy() throws SQLException {
        return flightSimulator.validateProjectName(projectCopy.getName()) ? flightSimulator.addProject(projectCopy) : false;
    }

    /**
     * Get the copied project.
     *
     * @return copied project
     */
    public Project getCopiedProject() {
        return this.projectCopy;
    }
}
