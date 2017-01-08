/**
 * Package location for Apllication Controllers concepts.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;

/**
 * Controller to create a project.
 *
 * @author Eric Amaral 1141570
 * @author Daniel Gonçalves 1151452
 * @author Ivo Ferro 1151159
 * @author Tiago Correia 1151031
 */
public class CreateProjectController {

    /**
     * The simulator.
     */
    private final FlightSimulator simulator;

    /**
     * The created project.
     */
    private final Project createdProject;

    /**
     * Creates an instance of the controller.
     *
     * @param simulator the simulator
     */
    public CreateProjectController(FlightSimulator simulator) {
        this.simulator = simulator;
        this.createdProject = new Project();
    }

    /**
     * Creates a new project.
     *
     * @param name project's name
     * @param description project's description
     * @return true if the project is successfully added, false otherwise
     * @throws java.sql.SQLException database error
     */
    public boolean newProject(String name, String description) throws SQLException {

        createdProject.setName(name);
        createdProject.setDescription(description);

        return createdProject.validateName() && simulator.validateProjectName(name) ? simulator.addProject(createdProject) : false;
    }

    /**
     * Gets the created project.
     *
     * @return created project
     */
    public Project getCreatedProject() {
        return this.createdProject;
    }
}
