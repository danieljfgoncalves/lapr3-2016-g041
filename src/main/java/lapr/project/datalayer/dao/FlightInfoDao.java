/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.dao;

import java.util.List;
import lapr.project.model.FlightInfo;

/**
 * Interface to manage a flight info's data acess object.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface FlightInfoDao {

    /**
     * Gets a flight info receiving their designator.
     *
     * @param designator flight info's designator
     * @return flight info
     * @throws Exception exception
     */
    FlightInfo getFlightInfo(String designator) throws Exception;

    /**
     * Gets the flights info.
     *
     * @return flights info
     * @throws Exception exception
     */
    List<FlightInfo> getFlightsInfo() throws Exception;

    /**
     * Adds a flight info.
     *
     * @param flightInfo flight info
     * @throws Exception exception
     */
    void addFlightInfo(FlightInfo flightInfo) throws Exception;
}
