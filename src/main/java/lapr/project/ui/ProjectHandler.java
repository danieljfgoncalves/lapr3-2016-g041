/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import lapr.project.model.Project;

/**
 * Interface to handle project actions on user interface.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface ProjectHandler {

    /**
     * Activates a project.
     *
     * @param project project to be activated
     */
    void activateProject(Project project);
}
