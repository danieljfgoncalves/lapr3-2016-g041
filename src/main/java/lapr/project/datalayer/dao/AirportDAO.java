/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.dao;

import java.util.List;
import lapr.project.model.Airport;

/**
 * Interface to manage a airport's data access object.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface AirportDAO {

    /**
     * Obtains an Airport from data source.
     * 
     * @param iata airport iata(id).
     * @throws java.lang.Exception
     */
    Airport getAirport(String iata) throws Exception;
    
    /**
     * Obtains all airports from data source.
     * @throws java.lang.Exception
     */
    List<Airport> getAirports() throws Exception;
    
    /**
     * Adds a new airport to data source.
     * 
     * @param airport the airport to add to data source.
     * @throws java.lang.Exception
     */
    void addAirport(Airport airport) throws Exception;
}
