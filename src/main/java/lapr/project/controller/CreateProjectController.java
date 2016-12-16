/**
 * Package location for Apllication Controllers concepts.
 */
package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.Simulator;

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
    private final Simulator simulator;

    /**
     * Creates an instance of the controller.
     *
     * @param simulator the simulator
     */
    public CreateProjectController(Simulator simulator) {
        this.simulator = simulator;
    }

    /**
     * Creates a new project
     *
     * @param name project's name
     * @param description project's description
     * @return true if the project is successfully added, false otherwise
     */
    public boolean newProject(String name, String description) {
        Project project = simulator.createProject(name, description);

        return simulator.validateProject(project) ? simulator.addProject(project) : false;
    }
}