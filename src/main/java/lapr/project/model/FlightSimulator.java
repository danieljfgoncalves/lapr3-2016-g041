/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
     * Checks if the projects exists.
     *
     * @param name project's name to validate
     * @return true if the name is valid, false otherwise
     * @throws SQLException error in database
     */
    public boolean validateProjectName(String name) throws SQLException {
        List<Project> projects = getProjects();

        for (Project project : projects) {
            if (project.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds a given project.
     *
     * @param project project to add
     * @return true if it is successfully added, false otherwise
     * @throws java.sql.SQLException database error
     */
    public boolean addProject(Project project) throws SQLException {
        String query = "INSERT INTO PROJECT (ID_PROJECT, NAME, DESCRIPTION) VALUES (?, ?, ?)";

        try (Connection connection = DbConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, project.getSerieNumber());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.executeUpdate();

        }

        return true;
    }

    /**
     * Updates a given project.
     *
     * @param project project to update
     * @throws SQLException database error
     */
    public void updateProjectNameAndDescription(Project project) throws SQLException {
        String query = String.format("UPDATE PROJECT SET NAME = '%s', DESCRIPTION = '%s' WHERE ID_PROJECT = %d",
                project.getName(), project.getDescription(), project.getSerieNumber());

        try (Connection connection = DbConnection.getConnection();
                Statement statement = connection.createStatement();) {
            statement.executeUpdate(query);
        }
    }
}
