/*
 * Package location for controllers
 */
package lapr.project.controller;

import lapr.project.model.FlightSimulator;
import lapr.project.model.Project;

/**
 * Controller responsible for editing project properties
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
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
     * Changes name and description of the project selected and validate if the
     * project does not have the same name in the flight simulator.
     *
     * @return true if the project's properties are edited successfully, false
     * otherwise
     */
    public boolean setProjectProperties(String name, String description) {
        String tempName = this.project.getName();
        if (project.validate(name)) {
            if (tempName.equalsIgnoreCase(name) && flightSimulator.validateNameExists(name)) {
                this.project.setName(name);
                this.project.setDescription(description);
                return true;
            }
            if (!flightSimulator.validateNameExists(name)) {
                this.project.setName(name);
                this.project.setDescription(description);
                return true;
            }
        }
        return false;

    }
}
