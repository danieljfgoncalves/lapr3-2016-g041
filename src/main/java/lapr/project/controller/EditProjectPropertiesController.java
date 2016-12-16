/*
 * Package location for controllers
 */
package lapr.project.controller;

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

    Project project;

    public EditProjectPropertiesController(Project projectSelected) {
        this.project = projectSelected;
    }

    public boolean setProjectProperties(String name, String description) {

        if (project.validate(name, description)) {
            this.project.setName(name);
            this.project.setDescription(description);
            return true;
        }
        return false;
    }
}
