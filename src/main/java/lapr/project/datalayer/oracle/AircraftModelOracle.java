/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.oracle;

import com.github.lgooddatepicker.zinternaltools.Pair;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Velocity;
import javax.measure.quantity.Volume;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import lapr.project.datalayer.DbConnection;
import lapr.project.datalayer.dao.AircraftModelDAO;
import lapr.project.model.AircraftModel;
import lapr.project.model.AircraftType;
import lapr.project.model.MotorType;
import lapr.project.model.Motorization;
import lapr.project.model.ThrustFunction;
import lapr.project.utils.CustomUnits;
import oracle.jdbc.OracleTypes;
import org.jscience.physics.amount.Amount;

/**
 * Class responsible to manage an aircraft model in a oracle database.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AircraftModelOracle implements AircraftModelDAO {

    /**
     * Project's id.
     */
    private final int projectSerieNumber;

    /**
     * Constructor of a aircraft model oracle class.
     *
     * @param projectSerieNumber project serie number
     */
    public AircraftModelOracle(int projectSerieNumber) {
        this.projectSerieNumber = projectSerieNumber;
    }

    /**
     * Gets an aircraft row by a result set.
     *
     * @param resultSet result set
     * @param connection connection
     * @return aicraft model
     * @throws SQLException sql exception
     */
    private AircraftModel mapRow(ResultSet resultSet, Connection connection) throws SQLException {

        int idAircraftModel = resultSet.getInt(1);
        String modelId = resultSet.getString(2);
        String description = resultSet.getString(3);
        String maker = resultSet.getString(4);
        String aircraftTypeString = resultSet.getString(5);
        AircraftType aircraftType = aircraftTypeString.equalsIgnoreCase("Passenger")
                ? AircraftType.PASSENGER : aircraftTypeString.equalsIgnoreCase("Cargo")
                ? AircraftType.CARGO : AircraftType.MIXED;
        String motor = resultSet.getString(6);
        int numberMotors = resultSet.getInt(7);
        String motorTypeString = resultSet.getString(8);
        MotorType motorType = motorTypeString.equalsIgnoreCase("Turbojet")
                ? MotorType.TURBOJET : motorTypeString.equalsIgnoreCase("Turbofan")
                ? MotorType.TURBOFAN : MotorType.TURBOPROP;
        Amount<Length> cruiseAltitude = Amount.valueOf(resultSet.getDouble(9), SI.METER);
        Amount<Velocity> cruiseSpeed = Amount.valueOf(resultSet.getDouble(10), SI.METERS_PER_SECOND);
        Amount tsfc = Amount.valueOf(resultSet.getDouble(11), CustomUnits.TSFC_SI);
        Amount<Dimensionless> lapseRateFactor = Amount.valueOf(resultSet.getDouble(12), Unit.ONE);
        Amount<Force> thrust0 = Amount.valueOf(resultSet.getDouble(13), SI.NEWTON);
        Amount<Force> thrustMaxSpeed = Amount.valueOf(resultSet.getDouble(14), SI.NEWTON);
        Amount<Velocity> maxSpeed = Amount.valueOf(resultSet.getDouble(15), SI.METERS_PER_SECOND);
        ThrustFunction thrustFunction = new ThrustFunction(thrust0, thrustMaxSpeed, maxSpeed);
        Motorization motorization = new Motorization(numberMotors, motor, motorType, cruiseAltitude, cruiseSpeed, tsfc, lapseRateFactor, thrustFunction);
        Amount<Mass> emptyWeight = Amount.valueOf(resultSet.getDouble(16), SI.KILOGRAM);
        Amount<Mass> mtow = Amount.valueOf(resultSet.getDouble(17), SI.KILOGRAM);
        Amount<Mass> maxPayload = Amount.valueOf(resultSet.getDouble(18), SI.KILOGRAM);
        Amount<Volume> maxFuelCapacity = Amount.valueOf(resultSet.getDouble(19), SI.CUBIC_METRE);
        Amount<Velocity> vmo = Amount.valueOf(resultSet.getDouble(20), SI.METERS_PER_SECOND);
        Amount<Velocity> mmo = Amount.valueOf(resultSet.getDouble(21), SI.METERS_PER_SECOND);
        Amount<Area> wingArea = Amount.valueOf(resultSet.getDouble(22), SI.SQUARE_METRE);
        Amount<Length> wingSpan = Amount.valueOf(resultSet.getDouble(23), SI.METER);
        Amount<Dimensionless> aspectRatio = Amount.valueOf(resultSet.getDouble(24), Unit.ONE);
        Amount<Dimensionless> e = Amount.valueOf(resultSet.getDouble(25), Unit.ONE);

        ArrayList<Pair<Double, Double>> cdragFunctionList = new ArrayList<>();
        String queryCdragFunction = "{?= call FC_GET_CDRAG_FUNCTION (?)}";
        try (CallableStatement callableStatement = connection.prepareCall(queryCdragFunction)) {
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setDouble(2, idAircraftModel);
            callableStatement.executeUpdate();
            try (ResultSet resultSetCdrag = (ResultSet) callableStatement.getObject(1)) {
                while (resultSetCdrag.next()) {
                    cdragFunctionList.add(new Pair<>(resultSetCdrag.getDouble(1), resultSetCdrag.getDouble(2)));
                }
            }
        }

        double cdrag[][] = new double[cdragFunctionList.size()][2];
        for (int i = 0; i < cdragFunctionList.size(); i++) {
            Pair<Double, Double> pair = cdragFunctionList.get(i);
            cdrag[i][0] = pair.first;
            cdrag[i][1] = pair.second;
        }

        return new AircraftModel(modelId, description, maker, aircraftType,
                motorization, emptyWeight, mtow, maxPayload,
                maxFuelCapacity, vmo, mmo, wingArea,
                wingSpan, aspectRatio, e, cdrag);
    }

    @Override
    public AircraftModel getAircraftModel(String modelID) throws SQLException {
        AircraftModel aircraftModel = null;

        String queryAircraftModels = "{?= call FC_GET_AIRCRAFT_MODEL (?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(queryAircraftModels)) {
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setString(2, modelID);
            callableStatement.setDouble(3, projectSerieNumber);
            callableStatement.executeUpdate();
            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                while (resultSet.next()) {
                    aircraftModel = mapRow(resultSet, connection);
                }
            }
        }

        return aircraftModel;
    }

    @Override
    public List<AircraftModel> getAircraftModels() throws SQLException {
        List<AircraftModel> aircraftModels = new ArrayList<>();

        String queryAircraftModels = "{?= call FC_GET_AIRCRAFT_MODELS (?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(queryAircraftModels)) {
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setDouble(2, projectSerieNumber);
            callableStatement.executeUpdate();
            try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                while (resultSet.next()) {
                    aircraftModels.add(mapRow(resultSet, connection));
                }
            }
        }

        return aircraftModels;
    }

    @Override
    public void addAircraftModel(AircraftModel aircraftModel) throws SQLException {
        String query = "{call PC_CREATE_AIRCRAFT_MODEL "
                + "(?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setDouble(1, projectSerieNumber);
            callableStatement.setString(2, aircraftModel.getModelID());
            callableStatement.setString(3, aircraftModel.getDescription());
            callableStatement.setString(4, aircraftModel.getMaker());
            callableStatement.setString(5, aircraftModel.getType().equals(AircraftType.PASSENGER)
                    ? "Passenger" : aircraftModel.getType().equals(AircraftType.CARGO) ? "Cargo" : "Mixed");
            callableStatement.setString(6, aircraftModel.getMotorization().getMotor());
            callableStatement.setDouble(7, aircraftModel.getMotorization().getNumberOfMotors());
            callableStatement.setString(8, aircraftModel.getMotorization().getMotorType().equals(MotorType.TURBOFAN)
                    ? "Turbofan" : aircraftModel.getMotorization().getMotorType().equals(MotorType.TURBOJET)
                    ? "Turbojet" : "Turboprop");
            callableStatement.setDouble(9, aircraftModel.getMotorization().getCruiseAltitude().doubleValue(SI.METER));
            callableStatement.setDouble(10, aircraftModel.getMotorization().getCruiseSpeed().doubleValue(SI.METERS_PER_SECOND));
            callableStatement.setDouble(11, aircraftModel.getMotorization().getTsfc().doubleValue(CustomUnits.TSFC_SI));
            callableStatement.setDouble(12, aircraftModel.getMotorization().getLapseRateFactor().doubleValue(Unit.ONE));
            callableStatement.setDouble(13, aircraftModel.getMotorization().getThrustFunction().getThrust0().doubleValue(SI.NEWTON));
            callableStatement.setDouble(14, aircraftModel.getMotorization().getThrustFunction().getThrustMaxSpeed().doubleValue(SI.NEWTON));
            callableStatement.setDouble(15, aircraftModel.getMotorization().getThrustFunction().getMaxSpeed().doubleValue(SI.METERS_PER_SECOND));
            callableStatement.setDouble(16, aircraftModel.getEmptyWeight().doubleValue(SI.KILOGRAM));
            callableStatement.setDouble(17, aircraftModel.getMtow().doubleValue(SI.KILOGRAM));
            callableStatement.setDouble(18, aircraftModel.getMaxPayload().doubleValue(SI.KILOGRAM));
            callableStatement.setDouble(19, aircraftModel.getMaxFuelCapacity().doubleValue(SI.CUBIC_METRE));
            callableStatement.setDouble(20, aircraftModel.getVmo().doubleValue(SI.METERS_PER_SECOND));
            callableStatement.setDouble(21, aircraftModel.getMmo().doubleValue(SI.METERS_PER_SECOND));
            callableStatement.setDouble(22, aircraftModel.getWingArea().doubleValue(SI.SQUARE_METRE));
            callableStatement.setDouble(23, aircraftModel.getWingSpan().doubleValue(SI.METER));
            callableStatement.setDouble(24, aircraftModel.getAspectRatio().doubleValue(Unit.ONE));
            callableStatement.setDouble(25, aircraftModel.getE().doubleValue(Unit.ONE));
            callableStatement.registerOutParameter(26, OracleTypes.INTEGER);
            callableStatement.executeUpdate();

            int idAircraftModel = callableStatement.getInt(26);

            for (double[] cdragFunction : aircraftModel.getCdragFunction()) {
                String queryCdragFunction = "{call PC_CREATE_CDRAG_FUNCTION (?, ?, ?)}";
                try (CallableStatement cdragCallableStatement = connection.prepareCall(queryCdragFunction)) {
                    cdragCallableStatement.setDouble(1, cdragFunction[0]);
                    cdragCallableStatement.setDouble(2, cdragFunction[1]);
                    cdragCallableStatement.setDouble(3, idAircraftModel);
                    cdragCallableStatement.executeUpdate();
                }
            }
        }
    }

}
