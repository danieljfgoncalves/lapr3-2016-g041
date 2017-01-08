/*
 * Package location for controllers
 */
package lapr.project.controller;

import lapr.project.model.Project;

/**
 * Controller responsible for opening projects
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class OpenProjectController {

    /**
     * Validates if the project to open is valid.
     *
     * @param project project to open and validate
     * @return true if the project is valid, false otherwise
     */
    public boolean validateProject(Project project) {
        return project.validateName();
    }
}
