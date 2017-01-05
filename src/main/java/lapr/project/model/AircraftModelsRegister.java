/*
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an register for aircraft models.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AircraftModelsRegister {

    /**
     * The list of aircraft models.
     */
    private List<AircraftModel> aircraftModels;

    /**
     * Creates an instance of aircraft models register with their default
     * values.
     */
    public AircraftModelsRegister() {
        this.aircraftModels = new ArrayList<>();
    }

    /**
     * Creates an instance of aircraft models register receiving the aircraft
     * models.
     *
     * @param aircraftModels aircraft models
     */
    public AircraftModelsRegister(List<AircraftModel> aircraftModels) {
        this.aircraftModels = aircraftModels;
    }

    /**
     * Creates an instance of aircraft model register copying another aircraft
     * register.
     *
     * @param otherAircraftModelsRegister another aircraft register
     */
    public AircraftModelsRegister(AircraftModelsRegister otherAircraftModelsRegister) {
        this.aircraftModels = new ArrayList<>(otherAircraftModelsRegister.aircraftModels);
    }

    /**
     * Gets the aircraft models.
     *
     * @return aircraft models
     */
    public List<AircraftModel> getAircraftModels() {
        return aircraftModels;
    }

    /**
     * Sets the aircraft models.
     *
     * @param aircraftModels aircraft models
     */
    public void setAircraftModels(List<AircraftModel> aircraftModels) {
        this.aircraftModels = aircraftModels;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.aircraftModels);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final AircraftModelsRegister other = (AircraftModelsRegister) obj;
        return this.aircraftModels.equals(other.aircraftModels);
    }

    @Override
    public String toString() {
        return String.format("AircraftModelsRegister{aircraftModels=%s}", this.aircraftModels);
    }
}
