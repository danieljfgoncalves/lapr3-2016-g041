/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.datalayer.DbConnection;
import lapr.project.datalayer.dao.ProjectDAO;
import lapr.project.model.Project;
import oracle.jdbc.OracleTypes;

/**
 * Class responsible to manage a project in a oracle database.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ProjectOracle implements ProjectDAO {

    @Override
    public Project getProject(Integer projectSerieNumber) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Project> getProjects() throws Exception {
        ArrayList<Project> projects = new ArrayList<>();

        String query = "{?= call FC_GET_PROJECTS}";

        try (Connection connection = DbConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeUpdate();

            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                while (resultSet.next()) {
                    int serieNumber = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);

                    projects.add(new Project(serieNumber, name, description));
                }
            }
        }
        return projects;
    }

    @Override
    public void addProject(Project project) throws Exception {
        String query = "INSERT INTO PROJECT (NAME, DESCRIPTION) VALUES (?, ?)";

        try (Connection connection = DbConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateProjectNameAndDescription(Project project) throws Exception {
        String query = "{call PC_EDIT_PROJECT_N_D (?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setDouble(1, project.getSerieNumber());
            callableStatement.setString(2, project.getName());
            callableStatement.setString(3, project.getDescription());

            callableStatement.executeUpdate();
        }
    }

    @Override
    public void deleteProject(int serieNumber) throws Exception {
        String query = "{call PC_DELETE_PROJECT (?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setDouble(1, serieNumber);
            callableStatement.executeUpdate();
        }
    }

    @Override
    public Project createEmptyProject() throws Exception {
        String query = "{call PC_CREATE_EMPTY_PROJECT (?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(3, OracleTypes.VARCHAR);

            callableStatement.executeUpdate();

            int serieNumber = callableStatement.getInt(1);
            String name = callableStatement.getString(2);
            String description = callableStatement.getString(3);

            return new Project(serieNumber, name, description);
        }
    }

    @Override
    public boolean validateProjectName(String name) throws Exception {
        List<Project> projects = getProjects();

        for (Project project : projects) {
            if (project.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }
}
