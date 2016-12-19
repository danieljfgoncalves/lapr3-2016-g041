/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the simulator to store projects and make the simulations.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class FlightSimulator {

    /**
     * The projects.
     */
    private List<Project> projects;

    /**
     * Status of the project
     */
    private Project activatedProject;

    /**
     * Creates an instance of simulator with its default values.
     */
    public FlightSimulator() {
        this.projects = new ArrayList<>();
    }

    /**
     * Creates an instance of Simulator receiving their projects.
     *
     * @param projects the projects
     */
    public FlightSimulator(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Creates an instance of simulator copying another simulator.
     *
     * @param otherSimulator other simulator
     */
    public FlightSimulator(FlightSimulator otherSimulator) {
        this.projects = new ArrayList<>(otherSimulator.projects);
    }

    /**
     * Gets the projects.
     *
     * @return projects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Sets the projects.
     *
     * @param projects projects
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Gets the activated project
     *
     * @return activated project
     */
    public Project getActivatedProject() {
        return activatedProject;
    }

    /**
     * Sets a project as activated
     *
     * @param activatedProject activated project
     */
    public void setActivatedProject(Project activatedProject) {
        this.activatedProject = activatedProject;
    }

    /**
     * Creates a project receiving their name and description.
     *
     * @param name project's name
     * @param description project's description
     * @return created project
     */
    public Project createProject(String name, String description) {
        return new Project(name, description);
    }

    /**
     * Validates if a given project.
     *
     * @param project project to be validated
     * @return true if it is valid, false otherwise
     */
    public boolean validateProject(Project project) {
        return !this.projects.contains(project);
    }
    
    /**
     * Checks if a project with a specific name already exists.
     * 
     * @param name the name to verify
     * @return true if a project with the given name already exists and false otherwise
     */
    public boolean validateNameExists(String name){
        for(Project project : projects){
            if(project.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a given project.
     *
     * @param project project to add
     * @return true if it is successfully added, false otherwise
     */
    public boolean addProject(Project project) {
        return this.projects.add(project);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.projects);
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

        final FlightSimulator other = (FlightSimulator) obj;
        return this.projects.equals(other.projects);
    }

    @Override
    public String toString() {
        return String.format("Simulator{projects=%s}", this.projects);
    }

}
