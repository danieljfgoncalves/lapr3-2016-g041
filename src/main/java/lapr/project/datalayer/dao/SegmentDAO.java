/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.dao;

import java.util.List;
import lapr.project.model.Segment;

/**
 * Interface to manage a segments's data acess object.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface SegmentDAO {

    /**
     * Obtains a segment from data source.
     *
     * @param id segment id.
     * @throws java.lang.Exception
     */
    Segment getSegment(String id) throws Exception;

    /**
     * Obtains all segments from data source.
     *
     * @throws java.lang.Exception
     */
    List<Segment> getSegments() throws Exception;

    /**
     * Adds a new segment to data source.
     *
     * @param segment the segment to add to data source.
     * @throws java.lang.Exception
     */
    void addSegment(Segment segment) throws Exception;
}
