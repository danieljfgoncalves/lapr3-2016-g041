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
import javax.measure.unit.SI;
import lapr.project.datalayer.DbConnection;
import lapr.project.datalayer.dao.AirportDAO;
import lapr.project.model.Airport;
import lapr.project.model.Coordinate;
import oracle.jdbc.OracleTypes;
import org.jscience.physics.amount.Amount;

/**
 * Class responsible to manage an airport in a oracle database.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AirportOracle implements AirportDAO {

    /**
     * Project's id.
     */
    private int projectSerieNumber;

    /**
     * Constructor of an airport oracle class.
     *
     * @param projectSerieNumber project serie number
     */
    public AirportOracle(int projectSerieNumber) {
        this.projectSerieNumber = projectSerieNumber;
    }

    /**
     * Map Airport table to a Coordinate Java Object.
     *
     * @param rs the DB result set
     * @return an airport object
     * @throws Exception
     */
    private Airport mapRow(ResultSet rs) throws Exception {

        Airport airport = new Airport();
        airport.setIATA(rs.getString(1));
        airport.setName(rs.getString(2));
        airport.setTown(rs.getString(3));
        airport.setCountry(rs.getString(4));
        airport.setAltitude(Amount.valueOf(rs.getDouble(5), SI.METER));
        Coordinate coordinateAirport = new Coordinate(rs.getString(6), rs.getDouble(7), rs.getDouble(8));
        airport.setCoordinates(coordinateAirport);

        return airport;
    }

    @Override
    public Airport getAirport(String iata) throws Exception {

        String query = "{? = call FC_GET_AIRPORT (?, ?)}";

        Airport airport = null;

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Function return
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            // Function params
            statement.setString(2, iata);
            statement.setInt(3, projectSerieNumber);
            // Function call
            statement.executeUpdate();

            try (ResultSet resultSet = (ResultSet) statement.getObject(1)) {
                while (resultSet.next()) {
                    airport = mapRow(resultSet);
                }
            }
        }

        return airport;
    }

    @Override
    public List<Airport> getAirports() throws Exception {

        List<Airport> airports = new ArrayList<>();

        String query = "{? = call FC_GET_AIRPORTS (?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Function return
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            // Function params
            statement.setDouble(2, projectSerieNumber);
            // Function call
            statement.executeUpdate();

            try (ResultSet resultSet = (ResultSet) statement.getObject(1)) {
                while (resultSet.next()) {
                    Airport airport = mapRow(resultSet);
                    if (airport != null) {
                        airports.add(airport);
                    } else {
                        throw new IllegalArgumentException("Invalid airport with id #" + resultSet.getString(1));
                    }
                }
            }
        }

        return airports;
    }

    @Override
    public void addAirport(Airport airport) throws Exception {

        String query = "{call PC_CREATE_AIRPORT (?, ?, ?, ?, ?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Procedure params
            statement.setString(1, airport.getIATA());
            statement.setString(2, airport.getName());
            statement.setString(3, airport.getTown());
            statement.setString(4, airport.getCountry());
            statement.setDouble(5, airport.getAltitude().doubleValue(SI.METER));
            statement.setString(6, airport.getCoordinates().getId());
            statement.setDouble(7, projectSerieNumber);
            // procedure call
            statement.executeUpdate();
        }
    }

    @Override
    public String getCoordinateIdFromDB(Double latitude, Double longitude, int numSerie) throws SQLException {
        String query = "{?= call FC_GET_ID_COORDINATE(?, ?, ?)}";
        
        String idCoordinate = "";

        try (Connection connection = DbConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.setString(2, String.valueOf(latitude));
            callableStatement.setString(3, String.valueOf(longitude));
            callableStatement.setDouble(4, numSerie);

            callableStatement.executeUpdate();

            idCoordinate = callableStatement.getString(1);
        }
        return idCoordinate;
    }

}
