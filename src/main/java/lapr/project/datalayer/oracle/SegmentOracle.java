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
import lapr.project.model.Coordinate;
import lapr.project.model.Segment;
import lapr.project.utils.graph.MapEdge;
import lapr.project.utils.graph.MapVertex;
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
    private MapEdge<Coordinate, Segment> mapRow(ResultSet rs) throws Exception {

        Segment segment = new Segment();
        segment.setId(rs.getString(1));
        segment.setWindDirection(Amount.valueOf(rs.getDouble(2), SI.RADIAN));
        segment.setWindIntensity(Amount.valueOf(rs.getDouble(3), SI.METERS_PER_SECOND));
        segment.setAltitude(Amount.valueOf(rs.getDouble(4), SI.METER));

        Coordinate origin = new Coordinate(rs.getString(5), rs.getDouble(6), rs.getDouble(7));
        Coordinate dest = new Coordinate(rs.getString(8), rs.getDouble(9), rs.getDouble(10));

        return new MapEdge<>(segment, 0, new MapVertex<Coordinate, Segment>(0, origin),
                new MapVertex<Coordinate, Segment>(1, dest));
    }

    @Override
    public MapEdge<Coordinate, Segment> getSegment(String id) throws Exception {
        // TODO : Implement stored procedure.
        String query = "{? = call FC_GET_SEGMENT (?, ?)}";

        MapEdge<Coordinate, Segment> segment = null;

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
    public List<MapEdge<Coordinate, Segment>> getSegments() throws Exception {

        List<MapEdge<Coordinate, Segment>> segments = new ArrayList<>();

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
                    MapEdge<Coordinate, Segment> segment = mapRow(resultSet);
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
    public void addSegment(MapEdge<Coordinate, Segment> edge) throws SQLException {

        Segment segment = edge.getElement();
        String originID = edge.getVOrig().getId();
        String destID = edge.getVDest().getId();

        // TODO : Implement stored procedure.
        String query = "{call PC_ADD_SEGMENT (?, ?, ?, ?, ?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement statement = connection.prepareCall(query)) {
            // Procedure params
            statement.setString(1, segment.getId());
            statement.setDouble(2, segment.getWindDirection().doubleValue(SI.RADIAN));
            statement.setDouble(3, segment.getWindIntensity().doubleValue(SI.METERS_PER_SECOND));
            statement.setDouble(4, segment.getAltitude().doubleValue(SI.METER));
            statement.setDouble(5, projectSerieNumber);
            statement.setString(6, originID);
            statement.setString(7, destID);

            // procedure call
            statement.executeUpdate();
        }
    }

}
