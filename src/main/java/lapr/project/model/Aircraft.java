/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;

/**
 * Represents an aircraft.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Aircraft {

    /**
     * The aircraft registration code.
     */
    private Integer registration;

    /**
     * The company name.
     */
    private String company;

    /**
     * The number of seats in each class.
     */
    private Integer cabinConfiguration;

    /**
     * The number of elements in the flight crew.
     */
    private Integer numberFlightCrew;

    /**
     * Default value for company name.
     */
    private static final String DEFAULT_COMPANY = "unknown company";

    /**
     * Default value for the number of seats in each class.
     */
    private static final Integer DEFAULT_CABIN_CONFIGURATION = 60;

    /**
     * Default value for the number of elements in the flight crew.
     */
    private static final Integer DEFAULT_NUMBER_FLIGHT_CREW = 120;

    /**
     * Creates an empty instance of aircraft.
     */
    public Aircraft() {
        int lower = 1, upper = 1000;
        this.registration = (int) (Math.random() * (upper - lower)) + lower;
        this.company = DEFAULT_COMPANY;
        this.cabinConfiguration = DEFAULT_CABIN_CONFIGURATION;
        this.numberFlightCrew = DEFAULT_NUMBER_FLIGHT_CREW;
    }

    /**
     * Creates an aircraft receiving their attributes.
     *
     * @param registration
     * @param company
     * @param cabinConfiguration
     * @param numberFlightCrew
     */
    public Aircraft(Integer registration, String company, Integer cabinConfiguration, Integer numberFlightCrew) {
        this.registration = registration;
        this.company = company;
        this.cabinConfiguration = cabinConfiguration;
        this.numberFlightCrew = numberFlightCrew;
    }

    /**
     * Creates an instance of aircraft copying another aircraft.
     *
     * @param otherAircraft aircraft to be copied.
     */
    public Aircraft(Aircraft otherAircraft) {
        this.registration = otherAircraft.registration;
        this.company = otherAircraft.company;
        this.cabinConfiguration = otherAircraft.cabinConfiguration;
        this.numberFlightCrew = otherAircraft.numberFlightCrew;
    }

    /**
     * Gets the registration.
     *
     * @return registration
     */
    public Integer getRegistration() {
        return registration;
    }

    /**
     * Sets the registration.
     *
     * @param registration registration
     */
    public void setRegistration(Integer registration) {
        this.registration = registration;
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
    public Integer getCabinConfiguration() {
        return cabinConfiguration;
    }

    /**
     * Sets the cabin configuration.
     *
     * @param cabinConfiguration cabin configuration
     */
    public void setCabinConfiguration(Integer cabinConfiguration) {
        this.cabinConfiguration = cabinConfiguration;
    }

    /**
     * Gets the number of flight crew.
     *
     * @return number of flight crew
     */
    public Integer getNumberFlightCrew() {
        return numberFlightCrew;
    }

    /**
     * Sets the number of flight crew.
     *
     * @param numberFlightCrew number of flight crew
     */
    public void setNumberFlightCrew(Integer numberFlightCrew) {
        this.numberFlightCrew = numberFlightCrew;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.registration);
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
        return this.registration.equals(other.registration);
    }

    @Override
    public String toString() {
        return String.format("Aircraft{registration=\"%d\", company=\"%s\", cabinConfiguration=\"%d\", numberFlightCrew=\"%d\"}",
                this.registration, this.company, this.cabinConfiguration, this.numberFlightCrew);
    }
}
