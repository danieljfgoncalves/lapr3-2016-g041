/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.AbstractListModel;
import lapr.project.model.Project;

/**
 * List model for project.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ListModelProject extends AbstractListModel<Project> {

    /**
     * The list of projects.
     */
    private final List<Project> projects;

    /**
     * Creates an instance of list model projects.
     *
     * @param projects
     */
    public ListModelProject(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * gets the number of projects.
     *
     * @return gets the number of projects
     */
    @Override
    public int getSize() {
        return this.projects.size();
    }

    /**
     * Gets the element on the position i.
     *
     * @param i index of the project
     * @return elements on the position i
     */
    @Override
    public Project getElementAt(int i) {
        return this.projects.get(i);
    }

}
