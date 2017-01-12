/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.datalayer.DbConnection;
import lapr.project.datalayer.dao.CoordinateDAO;
import lapr.project.model.Coordinate;
import oracle.jdbc.OracleTypes;

/**
 * Class responsible to manage a coordinate in a oracle database.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class CoordinateOracle implements CoordinateDAO {

    /**
     * Project's id.
     */
    private int projectSerieNumber;

    /**
     * Constructor of a coordinate oracle class.
     *
     * @param projectSerieNumber project serie number
     */
    public CoordinateOracle(int projectSerieNumber) {

        this.projectSerieNumber = projectSerieNumber;
    }

    /**
     * Map Coordinate table to a Coordinate Java Object.
     *
     * @param rs the DB result set
     * @return a coordinate object
     * @throws Exception
     */
    private Coordinate mapRow(ResultSet rs) throws Exception {

        Coordinate coordinate = new Coordinate();
        coordinate.setId(rs.getString(1));
        coordinate.setLatitude(rs.getDouble(2));
        coordinate.setLatitude(rs.getDouble(3));

        return coordinate;
    }

    @Override
    public Coordinate getCoordinate(String id) throws Exception {
        // TODO : Implement stored procedure.
        String query = "{? = call FC_GET_COORDINATE (?, ?)}";

        Coordinate coordinate = null;

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Function return
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            // Function params
            statement.setString(2, id);
            statement.setDouble(3, projectSerieNumber);
            // Function call
            statement.executeUpdate();

            try (ResultSet resultSet = (ResultSet) statement.getObject(1)) {
                while (resultSet.next()) {
                    coordinate = mapRow(resultSet);
                }
            }
        }

        return coordinate;
    }

    @Override
    public List<Coordinate> getCoordinates() throws Exception {

        List<Coordinate> coordinates = new ArrayList<>();

        // TODO : Implement stored procedure.
        String query = "{? = call FC_GET_COORDINATES (?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Function return
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            // Function params
            statement.setDouble(2, projectSerieNumber);
            // Function call
            statement.executeUpdate();

            try (ResultSet resultSet = (ResultSet) statement.getObject(1)) {
                while (resultSet.next()) {
                    Coordinate coordinate = mapRow(resultSet);
                    if (coordinate != null) {
                        coordinates.add(coordinate);
                    } else {
                        throw new IllegalArgumentException("Invalid coordinate with id #" + resultSet.getString(1));
                    }
                }
            }
        }

        return coordinates;
    }

    @Override
    public void addCoordinate(Coordinate coordinate) throws SQLException {
        // TODO : Implement stored procedure.
        String query = "{call PC_ADD_COORDINATE (?, ?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Procedure params
            statement.setString(1, coordinate.getId());
            statement.setString(2, String.valueOf(coordinate.getLatitude()));
            statement.setString(3, String.valueOf(coordinate.getLongitude()));
            statement.setDouble(4, projectSerieNumber);
            // procedure call
            statement.executeUpdate();
        }
    }

}
