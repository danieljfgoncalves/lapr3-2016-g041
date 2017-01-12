/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.dao;

import java.sql.SQLException;
import java.util.List;
import lapr.project.model.Project;

/**
 * Interface to manage a project's data acess object.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface ProjectDAO {

    /**
     * Gets a project receiving its serie number.
     *
     * @param projectSerieNumber
     * @return the project with a specific serie number
     * @throws Exception exception
     */
    Project getProject(Integer projectSerieNumber) throws Exception;

    /**
     * Gets the list of projects.
     *
     * @return list of projects
     * @throws Exception exception
     */
    List<Project> getProjects() throws Exception;

    /**
     * Adds a project.
     *
     * @param project the project to add
     * @throws Exception exception
     */
    void addProject(Project project) throws Exception;

    /**
     * Updates the name and description of a project.
     *
     * @param project the project to update
     * @throws Exception
     */
    void updateProjectNameAndDescription(Project project) throws Exception;

    /**
     * Deletes a project.
     *
     * @param serieNumber the serie number of the project to delete
     * @throws java.lang.Exception
     */
    void deleteProject(int serieNumber) throws Exception;

    /**
     * Creates a empty project.
     * 
     * @return an empty project
     * @throws Exception 
     */
    Project createEmptyProject() throws Exception;
    
    /**
     * Validates if the name of a project already exists.
     * 
     * @param name the given name
     * @return true if the name does not exist, false otherwise
     * @throws Exception 
     */
    boolean validateProjectName(String name) throws Exception;
    
}
