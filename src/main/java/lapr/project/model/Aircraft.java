/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;

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
     * The max cargo.
     */
    private Amount<Mass> maxCargo;

    /**
     * The number of elements in the flight crew.
     */
    private Integer maxCrew;

    /**
     * The number of seats in each class.
     */
    private List<Integer> maxPassengerPerClass;

    /**
     * The aircraft flight pattern.
     */
    private FlightPattern flightPattern;

    /**
     * Default value for the number of elements in the flight crew.
     */
    private static final Integer DEFAULT_ID = -1;

    /**
     * Default value for company name.
     */
    private static final String DEFAULT_COMPANY = "unknown company";

    private static final Amount<Mass> DEFAULT_MAX_CARGO = Amount.valueOf(50000, SI.KILOGRAM);

    /**
     * Default value for the number of elements in the flight crew.
     */
    private static final Integer DEFAULT_MAX_CREW = 120;

    /**
     * Creates an aircraft with their default values.
     */
    public Aircraft() {
        this.id = DEFAULT_ID;
        this.aircraftModel = new AircraftModel();
        this.company = DEFAULT_COMPANY;
        this.maxCargo = DEFAULT_MAX_CARGO;
        this.maxCrew = DEFAULT_MAX_CREW;
        this.maxPassengerPerClass = new ArrayList<>();
        this.flightPattern = new FlightPattern();
    }

    /**
     * Creates an aircraft receiving their parameters.
     *
     * @param id aircraft ID
     * @param aircraftModel aircraft model
     * @param company company
     * @param maxCargo max cargo
     * @param maxCrew max crew
     * @param maxPassengerPerClass max passengers per class
     * @param flightPattern flight pattern
     */
    public Aircraft(Integer id, AircraftModel aircraftModel, String company, Amount<Mass> maxCargo, Integer maxCrew, List<Integer> maxPassengerPerClass, FlightPattern flightPattern) {
        this.id = id;
        this.aircraftModel = aircraftModel;
        this.company = company;
        this.maxCargo = maxCargo;
        this.maxCrew = maxCrew;
        this.maxPassengerPerClass = maxPassengerPerClass;
        this.flightPattern = flightPattern;
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
        this.flightPattern = otherAircraft.flightPattern;
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
     *
     * @return aircraft model
     */
    public AircraftModel getAircraftModel() {
        return this.aircraftModel;
    }

    /**
     * Modifies the aircraft model.
     *
     * @param aircraftModel the new aircraft model
     */
    public void setAircraftModel(AircraftModel aircraftModel) {
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
     * Gets the max cargo.
     *
     * @return max cago
     */
    public Amount<Mass> getMaxCargo() {
        return maxCargo;
    }

    /**
     * Sets the max cargo.
     *
     * @param maxCargo max cargo
     */
    public void setMaxCargo(Amount<Mass> maxCargo) {
        this.maxCargo = maxCargo;
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

    /**
     * Gets the flight pattern.
     *
     * @return flight pattern
     */
    public FlightPattern getFlightPattern() {
        return flightPattern;
    }

    /**
     * Sets the flight pattern.
     *
     * @param flightPattern flight pattern
     */
    public void setFlightPattern(FlightPattern flightPattern) {
        this.flightPattern = flightPattern;
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
        return String.format("Aircraft{id=%d, aircraftModel=aircraftModel, company=%s, maxCargo=%s, maxCrew=%d, maxPassengerPerClass=%s}",
                id, company, maxCargo, maxCrew, maxPassengerPerClass);
    }
}
