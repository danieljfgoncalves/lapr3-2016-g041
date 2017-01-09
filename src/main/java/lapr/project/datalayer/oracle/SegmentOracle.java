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
import lapr.project.datalayer.dao.SegmentDAO;
import lapr.project.model.Segment;
import oracle.jdbc.OracleTypes;
import org.jscience.physics.amount.Amount;

/**
 * Class responsible to manage a segment in a oracle database.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class SegmentOracle implements SegmentDAO {

    /**
     * Project's id.
     */
    private int projectSerieNumber;

    /**
     * Constructor of a segment oracle class.
     *
     * @param projectSerieNumber project serie number
     */
    public SegmentOracle(int projectSerieNumber) {

        this.projectSerieNumber = projectSerieNumber;
    }

    /**
     * Map Segment table to a Segment Java Object.
     *
     * @param rs the DB result set
     * @return a segment object
     * @throws Exception
     */
    private Segment mapRow(ResultSet rs) throws Exception {

        Segment segment = new Segment();
        segment.setId(rs.getString(1));
        segment.setWindDirection(Amount.valueOf(rs.getDouble(2), SI.RADIAN));
        segment.setWindIntensity(Amount.valueOf(rs.getDouble(3), SI.METERS_PER_SECOND));
        segment.setAltitude(Amount.valueOf(rs.getDouble(4), SI.METER));

        return segment;
    }

    @Override
    public Segment getSegment(String id) throws Exception {
        // TODO : Implement stored procedure.
        String query = "{? = call FC_GET_SEGMENT (?, ?)}";

        Segment segment = null;

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
                    segment = mapRow(resultSet);
                }
            }
        }

        return segment;
    }

    @Override
    public List<Segment> getSegments() throws Exception {

        List<Segment> segments = new ArrayList<>();

        // TODO : Implement stored procedure.
        String query = "{? = call FC_GET_SEGMENTS (?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Function return
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            // Function params
            statement.setDouble(2, projectSerieNumber);
            // Function call
            statement.executeUpdate();

            try (ResultSet resultSet = (ResultSet) statement.getObject(1)) {
                while (resultSet.next()) {
                    Segment segment = mapRow(resultSet);
                    if (segment != null) {
                        segments.add(segment);
                    } else {
                        throw new IllegalArgumentException("Invalid segment with id #" + resultSet.getString(1));
                    }
                }
            }
        }

        return segments;
    }

    @Override
    public void addSegment(Segment segment) throws SQLException {
        // TODO : Implement stored procedure.
        String query = "{call PC_ADD_SEGMENT (?, ?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Procedure params
            statement.setString(1, segment.getId());
            statement.setDouble(2, segment.getWindDirection().doubleValue(SI.RADIAN));
            statement.setDouble(3, segment.getWindIntensity().doubleValue(SI.METERS_PER_SECOND));
            statement.setDouble(4, segment.getAltitude().doubleValue(SI.METER));
            statement.setDouble(5, projectSerieNumber);
            // procedure call
            statement.executeUpdate();
        }
    }

}
