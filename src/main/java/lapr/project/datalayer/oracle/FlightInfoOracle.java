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
import java.util.List;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import lapr.project.datalayer.DbConnection;
import lapr.project.datalayer.dao.FlightInfoDao;
import lapr.project.model.Aircraft;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.Coordinate;
import lapr.project.model.FlightInfo;
import lapr.project.model.FlightPattern;
import lapr.project.model.FlightType;
import lapr.project.model.Stop;
import oracle.jdbc.OracleTypes;
import org.jscience.physics.amount.Amount;

/**
 * Class responsible to manage an flight info in a oracle database.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightInfoOracle implements FlightInfoDao {

    /**
     * Project's id.
     */
    private final int projectSerieNumber;

    /**
     * Constructs a flight info of oracle database system.
     *
     * @param projectSerieNumber project serie number
     */
    public FlightInfoOracle(int projectSerieNumber) {
        this.projectSerieNumber = projectSerieNumber;
    }

    /**
     * Given a result set, reads the data and map to a flight info object.
     *
     * @param resultSetFlightInfo result set
     * @param connection connection
     * @return flight info
     * @throws SQLException sql exception
     */
    private FlightInfo mapRow(ResultSet resultSetFlightInfo, Connection connection) throws SQLException {
        int idFlightInfo = resultSetFlightInfo.getInt(1);
        String flightInfoDesignator = resultSetFlightInfo.getString(2);
        String flightTypeStr = resultSetFlightInfo.getString(3);
        FlightType flightType = flightTypeStr.equalsIgnoreCase("Regular")
                ? FlightType.REGULAR : FlightType.CHARTER;

        // origin airport data
        String originAirportIata = resultSetFlightInfo.getString(4);
        String originAirportName = resultSetFlightInfo.getString(5);
        String originAirportTown = resultSetFlightInfo.getString(6);
        String originAirportCountry = resultSetFlightInfo.getString(7);
        Amount<Length> originAirportAltitude = Amount.valueOf(resultSetFlightInfo.getDouble(8), SI.METER);

        // origin airport coordinate data
        String originCoordinateCode = resultSetFlightInfo.getString(9);
        Double originCoordinatelatitude = Double.parseDouble(resultSetFlightInfo.getString(10));
        Double originCoordinatelongitude = Double.parseDouble(resultSetFlightInfo.getString(11));

        // origin airport object
        Coordinate originCoordinate = new Coordinate(originCoordinateCode, originCoordinatelatitude, originCoordinatelongitude);
        Airport originAirport = new Airport(originAirportName, originAirportTown,
                originAirportCountry, originAirportIata,
                originCoordinate, originAirportAltitude);

        // destination airport data
        String destAirportIata = resultSetFlightInfo.getString(12);
        String destAirportName = resultSetFlightInfo.getString(13);
        String destAirportTown = resultSetFlightInfo.getString(14);
        String destAirportCountry = resultSetFlightInfo.getString(15);
        Amount<Length> destAirportAltitude = Amount.valueOf(resultSetFlightInfo.getDouble(16), SI.METER);

        // destination airport coordinate data
        String destCoordinateCode = resultSetFlightInfo.getString(17);
        Double destCoordinatelatitude = Double.parseDouble(resultSetFlightInfo.getString(18));
        Double destCoordinatelongitude = Double.parseDouble(resultSetFlightInfo.getString(19));

        // destination airport object
        Coordinate destCoordinate = new Coordinate(destCoordinateCode, destCoordinatelatitude, destCoordinatelongitude);
        Airport destAirport = new Airport(destAirportName, destAirportTown,
                destAirportCountry, destAirportIata,
                destCoordinate, destAirportAltitude);

        int idAircraft = resultSetFlightInfo.getInt(20);
        String modelID = resultSetFlightInfo.getString(21);
        String aircraftCompany = resultSetFlightInfo.getString(22);
        Amount<Mass> aircraftMaxCargo = Amount.valueOf(resultSetFlightInfo.getDouble(23), SI.KILOGRAM);
        int maxCrew = resultSetFlightInfo.getInt(24);

        // flight info stops
        List<Stop> stops = new ArrayList<>();
        String queryStops = "{?= call FC_GET_FLIGHT_STOPS (?)}";
        try (CallableStatement callableStatementStops = connection.prepareCall(queryStops)) {
            callableStatementStops.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementStops.setDouble(2, idFlightInfo);
            callableStatementStops.executeUpdate();
            try (ResultSet resultSetStops = (ResultSet) callableStatementStops.getObject(1)) {
                while (resultSetStops.next()) {
                    String stopAirportIata = resultSetStops.getString(1);
                    String stopAirportName = resultSetStops.getString(2);
                    String stopAirportTown = resultSetStops.getString(3);
                    String stopAirportCountry = resultSetStops.getString(4);
                    Amount<Length> stopAirportAltitude = Amount.valueOf(resultSetStops.getDouble(5), SI.METER);
                    String stopCoordinateCode = resultSetStops.getString(6);
                    Double stopCoordinateLatitude = Double.parseDouble(resultSetStops.getString(7));
                    Double stopCoordinateLongitude = Double.parseDouble(resultSetStops.getString(8));
                    Amount<Duration> minimumStopMinutes = Amount.valueOf(resultSetStops.getInt(9), SI.SECOND);
                    Calendar departureTime = new GregorianCalendar();
                    departureTime.setTime(resultSetStops.getDate(10));
                    Calendar scheduledArrival = new GregorianCalendar();
                    scheduledArrival.setTime(resultSetStops.getDate(11));

                    Coordinate stopCoordinate = new Coordinate(stopCoordinateCode, stopCoordinateLatitude, stopCoordinateLongitude);
                    Airport stopAirport = new Airport(stopAirportName, stopAirportTown, stopAirportCountry, stopAirportIata, stopCoordinate, stopAirportAltitude);
                    stops.add(new Stop(stopAirport, minimumStopMinutes, scheduledArrival, departureTime));
                }
            }
        }

        // flight info waypoints
        List<Coordinate> waypoints = new ArrayList<>();
        String queryWaypoints = "{?= call FC_GET_WAYPOINTS (?)}";
        try (CallableStatement callableStatementWaypoints = connection.prepareCall(queryWaypoints)) {
            callableStatementWaypoints.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementWaypoints.setDouble(2, idFlightInfo);
            callableStatementWaypoints.executeUpdate();
            try (ResultSet resultSetWaypoints = (ResultSet) callableStatementWaypoints.getObject(1)) {
                while (resultSetWaypoints.next()) {
                    String waypointCoordinateCode = resultSetWaypoints.getString(1);
                    Double waypointCoordinateLatitude = Double.parseDouble(resultSetWaypoints.getString(2));
                    Double waypointCoordinateLongitude = Double.parseDouble(resultSetWaypoints.getString(3));
                    waypoints.add(new Coordinate(waypointCoordinateCode, waypointCoordinateLatitude, waypointCoordinateLongitude));
                }
            }
        }

        // aircraft classes
        List<Integer> maxPassengersPerClass = new ArrayList<>();
        String queryClasses = "{?= call FC_GET_CLASSES_MAX (?)}";
        try (CallableStatement callableStatementClasses = connection.prepareCall(queryClasses)) {
            callableStatementClasses.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementClasses.setDouble(2, idFlightInfo);
            callableStatementClasses.executeUpdate();
            try (ResultSet resultSetClasses = (ResultSet) callableStatementClasses.getObject(1)) {
                while (resultSetClasses.next()) {
                    maxPassengersPerClass.add(resultSetClasses.getInt(1));
                }
            }
        }

        // aircraft flight pattern
        FlightPattern flightPattern = new FlightPattern();
        String queryFlightPattern = "{?= call FC_GET_FLIGHT_PATTERNS (?)}";
        try (CallableStatement callableStatementFlightPattern = connection.prepareCall(queryFlightPattern)) {
            callableStatementFlightPattern.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementFlightPattern.setDouble(2, idFlightInfo);
            callableStatementFlightPattern.executeUpdate();
            try (ResultSet resultSetFlightPattern = (ResultSet) callableStatementFlightPattern.getObject(1)) {
                while (resultSetFlightPattern.next()) {
                    Amount<Length> altitude = Amount.valueOf(resultSetFlightPattern.getDouble(1), SI.METER);
                    Amount<Velocity> vClimb = Amount.valueOf(resultSetFlightPattern.getDouble(2), SI.METERS_PER_SECOND);
                    Amount<Velocity> vDesc = Amount.valueOf(resultSetFlightPattern.getDouble(3), SI.METERS_PER_SECOND);
                    Amount rDesc = Amount.valueOf(resultSetFlightPattern.getDouble(4), SI.METERS_PER_SECOND);
                    flightPattern.insertLine(altitude, vClimb, vDesc, rDesc);
                }
            }
        }

        // aircraft model
        AircraftModelOracle aircraftModelDAO = new AircraftModelOracle(projectSerieNumber);
        AircraftModel aircraftModel = aircraftModelDAO.getAircraftModel(modelID);

        Aircraft aircraft = new Aircraft(idAircraft, aircraftModel,
                aircraftCompany, aircraftMaxCargo,
                maxCrew, maxPassengersPerClass,
                flightPattern);

        return new FlightInfo(flightType, flightInfoDesignator, originAirport, destAirport, aircraft, waypoints, stops);
    }

    @Override
    public FlightInfo getFlightInfo(String designator) throws SQLException {
        FlightInfo flightInfo;

        String queryFlightInfo = "{?= call FC_GET_FLIGHT_INFO (?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatementFlightInfo = connection.prepareCall(queryFlightInfo)) {
            callableStatementFlightInfo.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementFlightInfo.setString(2, designator);
            callableStatementFlightInfo.setDouble(3, projectSerieNumber);
            callableStatementFlightInfo.executeUpdate();

            try (ResultSet resultSetFlightInfo = (ResultSet) callableStatementFlightInfo.getObject(1)) {
                resultSetFlightInfo.next();
                flightInfo = mapRow(resultSetFlightInfo, connection);
            }
        }

        return flightInfo;
    }

    @Override
    public List<FlightInfo> getFlightsInfo() throws SQLException {
        List<FlightInfo> flightsInfo = new ArrayList<>();

        String queryFlightInfo = "{?= call FC_GET_FLIGHTS_INFO (?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatementFlightInfo = connection.prepareCall(queryFlightInfo)) {
            callableStatementFlightInfo.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatementFlightInfo.setDouble(2, projectSerieNumber);
            callableStatementFlightInfo.executeUpdate();

            try (ResultSet resultSetFlightInfo = (ResultSet) callableStatementFlightInfo.getObject(1)) {
                while (resultSetFlightInfo.next()) {
                    flightsInfo.add(mapRow(resultSetFlightInfo, connection));
                }
            }
        }

        return flightsInfo;
    }

    @Override
    public void addFlightInfo(FlightInfo flightInfo) throws SQLException {
        String queryFlightInfo = "{call PC_ADD_FLIGHT_INFO "
                + "(?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatementFlightInfo = connection.prepareCall(queryFlightInfo)) {
            callableStatementFlightInfo.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatementFlightInfo.registerOutParameter(2, OracleTypes.INTEGER);
            callableStatementFlightInfo.setDouble(3, projectSerieNumber);
            callableStatementFlightInfo.setString(4, flightInfo.getDesignator());
            callableStatementFlightInfo.setString(5, flightInfo.getOriginAirport().getIATA());
            callableStatementFlightInfo.setString(6, flightInfo.getDestinationAirport().getIATA());
            callableStatementFlightInfo.setString(7,
                    flightInfo.getFlightType().equals(FlightType.REGULAR)
                    ? "Regular" : "Charter");
            callableStatementFlightInfo.setString(8, flightInfo.getAircraft()
                    .getAircraftModel().getModelID());
            callableStatementFlightInfo.setString(9, flightInfo.getAircraft().getCompany());
            callableStatementFlightInfo.setDouble(10, flightInfo.getAircraft()
                    .getMaxCargo().doubleValue(SI.KILOGRAM));
            callableStatementFlightInfo.setDouble(11, flightInfo.getAircraft().getMaxCrew());
            callableStatementFlightInfo.executeUpdate();

            int idFlightInfo = callableStatementFlightInfo.getInt(1);
            int idAircraft = callableStatementFlightInfo.getInt(2);

            String queryFlightPattern = "{call PC_ADD_FLIGHT_PATTERN (?, ?, ?, ?, ?)}";
            Amount[][] matrixFlightPattern = flightInfo.getAircraft().getFlightPattern().getFlightProfile();
            for (Amount[] matrixFlightPatternRow : matrixFlightPattern) {
                if (matrixFlightPatternRow[0] != null
                        && matrixFlightPatternRow[1] != null
                        && matrixFlightPatternRow[2] != null
                        && matrixFlightPatternRow[2] != null) {

                    try (CallableStatement callableStatementFlightPattern = connection.prepareCall(queryFlightPattern)) {
                        callableStatementFlightPattern.setDouble(1, idAircraft);
                        callableStatementFlightPattern.setDouble(2, matrixFlightPatternRow[0].doubleValue(SI.METER));
                        callableStatementFlightPattern.setDouble(3, matrixFlightPatternRow[1].doubleValue(SI.METRES_PER_SECOND));
                        callableStatementFlightPattern.setDouble(4, matrixFlightPatternRow[2].doubleValue(SI.METRES_PER_SECOND));
                        callableStatementFlightPattern.setDouble(5, matrixFlightPatternRow[3].doubleValue(SI.METERS_PER_SECOND));
                        callableStatementFlightPattern.executeUpdate();
                    }
                }
            }

            String queryClass = "{call PC_ADD_CLASS (?, ?)}";
            for (Integer maxPassengers : flightInfo.getAircraft().getMaxPassengerPerClass()) {
                try (CallableStatement callableStatementClass = connection.prepareCall(queryClass)) {
                    callableStatementClass.setDouble(1, idAircraft);
                    callableStatementClass.setDouble(2, maxPassengers);
                    callableStatementClass.executeUpdate();
                }
            }

            String queryWaypoints = "{call PC_ADD_WAYPOINTS (?, ?, ?)}";
            for (Coordinate waypoint : flightInfo.getWaypoints()) {
                try (CallableStatement callableStatementWaypoints = connection.prepareCall(queryWaypoints)) {
                    callableStatementWaypoints.setDouble(1, projectSerieNumber);
                    callableStatementWaypoints.setString(2, waypoint.getId());
                    callableStatementWaypoints.setDouble(3, idFlightInfo);
                    callableStatementWaypoints.executeUpdate();
                }
            }

            String queryFlightStop = "{call PC_ADD_FLIGHT_STOP (?, ?, ?, ?, ?, ?)}";
            for (Stop stop : flightInfo.getStops()) {
                try (CallableStatement callableStatementStop = connection.prepareCall(queryFlightStop)) {
                    callableStatementStop.setDouble(1, projectSerieNumber);
                    callableStatementStop.setDouble(2, idFlightInfo);
                    callableStatementStop.setString(3, stop.getAirport().getIATA());
                    callableStatementStop.setDouble(4, stop.getMinimumStopMinutes().doubleValue(NonSI.MINUTE));
                    callableStatementStop.setDate(5, new java.sql.Date(stop.getDepartureTime().getTimeInMillis()));
                    callableStatementStop.setDate(6, new java.sql.Date(stop.getScheduleArrival().getTimeInMillis()));
                    callableStatementStop.executeUpdate();
                }
            }
        }
    }

}
