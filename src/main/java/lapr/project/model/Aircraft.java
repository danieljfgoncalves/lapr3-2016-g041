/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an aircraft.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Aircraft {

    /**
     * The aircraft id code.
     */
    private Integer id;

    /**
     * The aircraft model.
     */
    private AircraftModel aircraftModel;
    
    /**
     * The company name.
     */
    private String company;

    /**
     * The number of elements in the flight crew.
     */
    private Integer maxCrew;
    
    /**
     * The number of seats in each class.
     */
    private List<Integer> maxPassengerPerClass;

    /**
     * Default value for company name.
     */
    private static final String DEFAULT_COMPANY = "unknown company";

    /**
     * Default value for the number of elements in the flight crew.
     */
    private static final Integer DEFAULT_NUMBER_FLIGHT_MAX_CREW = 120;

    /**
     * Creates an empty instance of aircraft.
     */
    public Aircraft() {
        int lower = 1, upper = 1000;
        this.id = (int) (Math.random() * (upper - lower)) + lower;
        this.company = DEFAULT_COMPANY;
        this.aircraftModel = new AircraftModel();
        this.maxPassengerPerClass = new ArrayList<>();
        this.maxCrew = DEFAULT_NUMBER_FLIGHT_MAX_CREW;
    }

    /**
     * Creates an aircraft receiving their attributes.
     *
     * @param registration
     * @param aircraftModel
     * @param company
     * @param maxPassengerPerClass
     * @param maxCrew
     */
    public Aircraft(Integer registration, AircraftModel aircraftModel, String company, List maxPassengerPerClass, Integer maxCrew) {
        this.id = registration;
        this.aircraftModel = aircraftModel;
        this.company = company;
        this.maxPassengerPerClass = new ArrayList<>(maxPassengerPerClass);
        this.maxCrew = maxCrew;
    }

    /**
     * Creates an instance of aircraft copying another aircraft.
     *
     * @param otherAircraft aircraft to be copied.
     */
    public Aircraft(Aircraft otherAircraft) {
        this.id = otherAircraft.id;
        this.aircraftModel = otherAircraft.aircraftModel;
        this.company = otherAircraft.company;
        this.maxPassengerPerClass = new ArrayList<>(otherAircraft.getMaxPassengerPerClass());
        this.maxCrew = otherAircraft.maxCrew;
    }

    /**
     * Gets the id.
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Obtains the aircraft model.
     * @return aircraft model
     */
    public AircraftModel getAircraftModel(){
        return this.aircraftModel;
    }
    
    /**
     * Modifies the aircraft model.
     * @param aircraftModel the new aircraft model
     */
    public void setAircraftModel(AircraftModel aircraftModel){
        this.aircraftModel = aircraftModel;
    }

    /**
     * gets the company.
     *
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company.
     *
     * @param company company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Gets the cabin configuration.
     *
     * @return cabin configuration
     */
    public List<Integer> getMaxPassengerPerClass() {
        return this.maxPassengerPerClass;
    }

    /**
     * Sets the cabin configuration.
     *
     * @param maxPassengerPerClass cabin configuration
     */
    public void setMaxPassengerPerClass(List<Integer> maxPassengerPerClass) {
        this.maxPassengerPerClass = maxPassengerPerClass;
    }

    /**
     * Gets the number of flight crew.
     *
     * @return number of flight crew
     */
    public Integer getMaxCrew() {
        return maxCrew;
    }

    /**
     * Sets the number of flight crew.
     *
     * @param maxCrew number of flight crew
     */
    public void setMaxCrew(Integer maxCrew) {
        this.maxCrew = maxCrew;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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

        final Aircraft other = (Aircraft) obj;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return String.format("Aircraft{id=\"%d\", aircraftModel=\"%s\", company=\"%s\", maxPassengerPerClass=\"%s\", maxCrew=\"%d\"}",
                this.id, this.aircraftModel, this.company, this.maxPassengerPerClass, this.maxCrew);
    }
}
