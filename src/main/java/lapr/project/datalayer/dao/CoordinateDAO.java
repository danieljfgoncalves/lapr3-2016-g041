/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.dao;

import java.util.List;
import lapr.project.model.Coordinate;

/**
 * Interface to manage a coordinate's data acess object.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface CoordinateDAO {

    /**
     * Obtains a coordinate from data source.
     * 
     * @param id coordinate id.
     * @throws java.lang.Exception
     */
    Coordinate getCoordinate(String id) throws Exception;
    
    /**
     * Obtains all coordinates from data source.
     * @throws java.lang.Exception
     */
    List<Coordinate> getCoordinates() throws Exception;
    
    /**
     * Adds a new coordinate to data source.
     * 
     * @param coordinate the coordinate to add to data source.
     * @throws java.lang.Exception
     */
    void addCoordinate(Coordinate coordinate) throws Exception;
}
