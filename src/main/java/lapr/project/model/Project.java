/*
 * Package location for Project concept
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Project.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Project {

    /**
     * Description of the Project
     */
    private String description;

    /**
     * Name of the Project
     */
    private String name;

    /**
     * Serie Number of the Project
     */
    private int serieNumber;

    /**
     * The airNetwork of the project.
     */
    private AirNetwork airNetwork;

    /**
     * The project's list of airports.
     */
    private List<Airport> airports;

    /**
     * The project's aircraft models.
     */
    private List<AircraftModel> aircraftModels;

    /**
     * The project's simulations.
     */
    private List<Simulation> simulations;

    /**
     * Default value for Description
     */
    private final static String DEFAULT_DESCRIPTION = "No description";
    /**
     * Default value for Name
     */
    private final static String DEFAULT_NAME = "Untitled";

    /**
     * Default value for Serie Number
     */
    private final static int DEFAULT_SERIE_NUMBER = 0;

    /**
     * Creates an instance of project with its default values
     */
    public Project() {
        this.serieNumber = DEFAULT_SERIE_NUMBER;
        this.name = DEFAULT_NAME;
        this.description = DEFAULT_DESCRIPTION;
        this.airNetwork = new AirNetwork();
        this.airports = new ArrayList();
        this.aircraftModels = new ArrayList();
        this.simulations = new ArrayList();
    }

    /**
     * Creates a project receiving its name and description.
     *
     * @param name project the given project's name
     * @param description the given project's description
     * @param airNetwork the given air network
     * @param airports the given airport list
     * @param aircraftModels the given aircraft models list
     * @param simulations the given simulations
     */
    public Project(String name, String description, AirNetwork airNetwork, List<Airport> airports, List<AircraftModel> aircraftModels, List<Simulation> simulations) {
        final int lower = 1, upper = Integer.MAX_VALUE;
        this.serieNumber = (int) (Math.random() * (upper - lower)) + lower;
        this.name = name;
        this.description = description;
        this.airNetwork = airNetwork;
        this.airports = airports;
        this.aircraftModels = aircraftModels;
        this.simulations = simulations;
    }

    /**
     * Creates an instance of a Project, receiving only the name and description.
     * 
     * @param name the given name
     * @param description the given description
     */
    public Project(String name, String description) {
        final int lower = 1, upper = Integer.MAX_VALUE;
        this.serieNumber = (int) (Math.random() * (upper - lower)) + lower;
        this.name = name;
        this.description = description;
        this.airNetwork = new AirNetwork();
        this.airports = new ArrayList();
        this.aircraftModels = new ArrayList();
        this.simulations = new ArrayList();
    }

    /**
     * Creates an instance of project receiving it's attributes
     *
     * @param name the name of the project
     * @param description the description of the project
     * @param serieNumber the serie number of the project
     * @param airNetwork the air network of the project
     * @param airports the airports of the project
     * @param aircraftModels the aircraft models of the project
     * @param simulations the simulations of the project
     */
    public Project(String name, String description, int serieNumber, AirNetwork airNetwork, List<Airport> airports, List<AircraftModel> aircraftModels, List<Simulation> simulations) {
        this.serieNumber = serieNumber;
        this.name = name;
        this.description = description;
        this.airNetwork = airNetwork;
        this.airports = airports;
        this.aircraftModels = aircraftModels;
        this.simulations = simulations;
    }

    /**
     * Creates a project with a given name, description and serieNumber.
     *
     * @param name the given name
     * @param description the given description
     * @param serieNumber the given serieNumber
     */
    public Project(String name, String description, int serieNumber) {
        this.name = name;
        this.description = description;
        this.serieNumber = serieNumber;
    }

    /**
     * Creates a project receiving another project
     *
     * @param otherProject other project to copy
     */
    public Project(Project otherProject) {
        final int lower = 1, upper = Integer.MAX_VALUE;
        this.serieNumber = (int) (Math.random() * (upper - lower)) + lower;
        this.airNetwork = otherProject.airNetwork;
        this.airports = otherProject.airports;
        this.aircraftModels = otherProject.aircraftModels;
        //simulations are not supposed to be copied
    }

    /**
     * Gets the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     *
     * @param description description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the serie number
     *
     * @return serie number
     */
    public int getSerieNumber() {
        return serieNumber;
    }

    /**
     * Sets the serie number
     *
     * @param serieNumber serie number to set
     */
    public void setSerieNumber(int serieNumber) {
        this.serieNumber = serieNumber;
    }

    /**
     * Gets the project's air network.
     *
     * @return the airNetwork
     */
    public AirNetwork getAirNetwork() {
        return airNetwork;
    }

    /**
     * Modifies the project's air network
     *
     * @param airNetwork the airNetwork to set
     */
    public void setAirNetwork(AirNetwork airNetwork) {
        this.airNetwork = airNetwork;
    }

    /**
     * Gets the project's airports.
     *
     * @return the airports
     */
    public List<Airport> getAirports() {
        return airports;
    }

    /**
     * Modifies the project's airports
     *
     * @param airports the airports to set
     */
    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    /**
     * Gets the project's aircraft models.
     *
     * @return the aircraftModels
     */
    public List<AircraftModel> getAircraftModels() {
        return aircraftModels;
    }

    /**
     * Modifies the project's aircraft models.
     *
     * @param aircraftModels the aircraftModels to set
     */
    public void setAircraftModels(List<AircraftModel> aircraftModels) {
        this.aircraftModels = aircraftModels;
    }

    /**
     * Gets the project's simulations.
     *
     * @return the simulations
     */
    public List<Simulation> getSimulations() {
        return simulations;
    }

    /**
     * Modifies the project's simulations.
     *
     * @param simulations the simulations to set
     */
    public void setSimulations(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    /**
     * Obtains the validation of name and description(true if name and
     * description not empty), false otherwise
     *
     * @param name
     * @param description
     * @return true if validated, false otherwise
     */
    public boolean validate(String name, String description) {
        return !name.equals("") && !description.equals("");
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.serieNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        return this.serieNumber == (other.serieNumber);
    }

    @Override
    public String toString() {
        return String.format("Serie Number: %d\n"
                + "Name: %s\n"
                + "Description: %s\n"
                + "Air Network: %s\n"
                + "Airports: %s\n"
                + "Aircraft Models: %s\n"
                + "Simulations: %s", serieNumber, name, description, airNetwork, airports, aircraftModels, simulations);
    }

}
