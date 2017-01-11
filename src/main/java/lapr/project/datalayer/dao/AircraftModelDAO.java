/**
 * Package location for data layer concepts.
 */
package lapr.project.datalayer.dao;

import java.util.List;
import lapr.project.model.AircraftModel;

/**
 * Interface to manage a aircraft model's data acess object.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface AircraftModelDAO {

    /**
     * Gets an aircraft model by his modelID.
     *
     * @param modelID aircraft modelID
     * @return aircraft model
     * @throws Exception exception
     */
    AircraftModel getAircraftModel(String modelID) throws Exception;

    /**
     * Gets the aircraft models.
     *
     * @return aircraft models
     * @throws Exception exception
     */
    List<AircraftModel> getAircraftModels() throws Exception;

    /**
     * Adds a given aircraft model.
     *
     * @param aircraftModel aircraft model
     * @throws Exception exception
     */
    void addAircraftModel(AircraftModel aircraftModel) throws Exception;
}
