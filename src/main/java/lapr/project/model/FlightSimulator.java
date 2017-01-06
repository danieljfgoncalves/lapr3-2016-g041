/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lapr.project.datalayer.DbConnection;

/**
 * Represents the simulator to store projects and make the simulations.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightSimulator {

    /**
     * Gets the projects.
     *
     * @return projects available projects
     * @throws java.sql.SQLException error on sql connection
     */
    public List<Project> getProjects() throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();

        String query = "SELECT * FROM PROJECT";

        // try with resorces auto-close the object
        try (Connection connection = DbConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int serieNumber = resultSet.getInt("ID_PROJECT");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");

                projects.add(new Project(serieNumber, name, description));
            }

        }

        return projects;
    }

    /**
     * Creates a project receiving their name and description.
     *
     * @param name project's name
     * @param description project's description
     * @return created project
     */
    public Project createProject(String name, String description) {
        return new Project(name, description);
    }

    /**
     * Validates a given project.
     *
     * @param project project to be validated
     * @return true if it is valid, false otherwise
     */
    public boolean validateProject(Project project) {
        // TODO
        return project.validate();
    }

    /**
     * Checks if a project with a specific name already exists.
     *
     * @param name the name to verify
     * @return true if a project with the given name already exists and false
     * otherwise
     */
    public boolean validateNameExists(String name) {
        // TODO
        return true;
    }

    /**
     * Adds a given project.
     *
     * @param project project to add
     * @return true if it is successfully added, false otherwise
     */
    public boolean addProject(Project project) {
        // TODO
        return true;
    }
}
