/*
 * Package location for controllers
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.model.FlightSimulator;
import lapr.project.model.Project;

/**
 * Controller responsible for editing project properties
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class EditProjectPropertiesController {

    /**
     * The project to edit it's properties
     */
    private final Project project;

    /**
     * The simulator.
     */
    private final FlightSimulator flightSimulator;

    public EditProjectPropertiesController(FlightSimulator flightSimulator, Project projectSelected) {
        this.flightSimulator = flightSimulator;
        this.project = projectSelected;
    }

    /**
     * Sets the project properties.
     *
     * @param name project's name
     * @param description project's description
     * @return true if the project is successfully updated, false otherwise
     * @throws SQLException error in database
     */
    public boolean setProjectProperties(String name, String description) throws SQLException {
        String tempName = this.project.getName();
        if (project.validateName()) {
            if ((tempName.equalsIgnoreCase(name) && !flightSimulator.validateProjectName(name))
                    || flightSimulator.validateProjectName(name)) {

                this.project.setName(name);
                this.project.setDescription(description);

                // updates the project on database
                flightSimulator.updateProjectNameAndDescription(project);
                
                return true;
            }
        }
        return false;
    }
}
