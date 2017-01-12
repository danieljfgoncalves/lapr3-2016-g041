/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import lapr.project.datalayer.DbConnection;
import lapr.project.datalayer.dao.FlightSimulationDAO;
import lapr.project.model.FlightInfo;
import lapr.project.model.FlightSimulation;
import lapr.project.model.Segment;
import oracle.jdbc.OracleTypes;
import org.jscience.physics.amount.Amount;

/**
 * Class responsible to manage a flight simulation in a oracle database.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightSimulationOracle implements FlightSimulationDAO {

    /**
     * Project's id.
     */
    private final int projectSerieNumber;

    /**
     * Constructor of a aircraft model oracle class.
     *
     * @param projectSerieNumber project serie number
     */
    public FlightSimulationOracle(int projectSerieNumber) {
        this.projectSerieNumber = projectSerieNumber;
    }

    /**
     * Gets an flight simulation row by a result set.
     *
     * @param resultSet result set
     * @param connection connection
     * @return flight simulation
     * @throws SQLException sql exception
     */
    private FlightSimulation mapRow(ResultSet resultSet, Connection connection) throws SQLException {
        int id = resultSet.getInt(1);
        String flightInfoDesignator = resultSet.getString(2);

        java.sql.Date scheduledArrivalSqlDate = resultSet.getDate(3);
        Calendar scheduledArrival = new GregorianCalendar();
        scheduledArrival.setTime(scheduledArrivalSqlDate);

        java.sql.Date departureSqlDate = resultSet.getDate(4);
        Calendar departureDate = new GregorianCalendar();
        departureDate.setTime(departureSqlDate);

        int effectiveCrew = resultSet.getInt(5);
        Amount<Mass> effectiveCargo = Amount.valueOf(resultSet.getDouble(6), SI.KILOGRAM);
        Amount<Mass> effectiveFuel = Amount.valueOf(resultSet.getDouble(7), SI.KILOGRAM);

        // effective passengers per class
        List<Integer> passengersPerClass = new ArrayList<>();
        String queryClasses = "{?= call FC_GET_CLASSES_MAX (?)}";
        try (CallableStatement callableStatementClasses = connection.prepareCall(queryClasses)) {
            callableStatementClasses.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementClasses.setDouble(2, id);
            callableStatementClasses.executeUpdate();
            try (ResultSet resultSetClasses = (ResultSet) callableStatementClasses.getObject(1)) {
                while (resultSetClasses.next()) {
                    passengersPerClass.add(resultSetClasses.getInt(1));
                }
            }
        }

        // flight plan
        SegmentOracle segmentDAO = new SegmentOracle(projectSerieNumber);
        List<Segment> flightPlan = new LinkedList<>();
        String querySegments = "{?= call FC_GET_FLIGHT_PLAN (?)}";
        try (CallableStatement callableStatementSegments = connection.prepareCall(querySegments)) {
            callableStatementSegments.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementSegments.setDouble(2, id);
            callableStatementSegments.executeUpdate();
            try (ResultSet resultSetClasses = (ResultSet) callableStatementSegments.getObject(1)) {
                while (resultSetClasses.next()) {
                    String segmentID = callableStatementSegments.getString(1);
                    Segment segment = segmentDAO.getSegment(segmentID).getElement();
                    flightPlan.add(segment);
                }
            }
        }

        FlightInfoOracle flightInfoDAO = new FlightInfoOracle(projectSerieNumber);
        FlightInfo flightInfo = flightInfoDAO.getFlightInfo(flightInfoDesignator);

        return new FlightSimulation(id, flightInfo,
                scheduledArrival, departureDate,
                effectiveCrew, effectiveCargo,
                effectiveFuel, flightPlan,
                passengersPerClass);
    }

    @Override
    public FlightSimulation getFlightSimulation(int flightSimulationID) throws SQLException {
        FlightSimulation flightSimulation = null;

        String queryAircraftModels = "{?= call FC_GET_FLIGHT_SIMULATION (?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(queryAircraftModels)) {
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setDouble(2, projectSerieNumber);
            callableStatement.setDouble(3, flightSimulationID);
            callableStatement.executeUpdate();
            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                while (resultSet.next()) {
                    flightSimulation = mapRow(resultSet, connection);
                }
            }
        }

        return flightSimulation;
    }

    @Override
    public List<FlightSimulation> getFlightSimulations() throws SQLException {
        List<FlightSimulation> flightSimulations = new ArrayList<>();

        String queryAircraftModels = "{?= call FC_GET_FLIGHT_SIMULATIONS (?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(queryAircraftModels)) {
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setDouble(2, projectSerieNumber);
            callableStatement.executeUpdate();
            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                while (resultSet.next()) {
                    flightSimulations.add(mapRow(resultSet, connection));
                }
            }
        }

        return flightSimulations;
    }

    @Override
    public void addFlightSimulation(FlightSimulation flightSimulation) throws Exception {
        // TODO implement this
    }

}
