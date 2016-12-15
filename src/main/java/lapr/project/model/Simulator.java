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
public class Simulator {

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
    public Simulator() {
        this.projects = new ArrayList<>();
    }

    /**
     * Creates an instance of Simulator receiving their projects.
     *
     * @param projects the projects
     */
    public Simulator(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Creates an instance of simulator copying another simulator.
     *
     * @param otherSimulator other simulator
     */
    public Simulator(Simulator otherSimulator) {
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
     * Creates a new project.
     *
     * @return the new project
     */
    public Project createProject() {
        Project newProject = new Project();
        projects.add(newProject);
        return newProject;
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

        final Simulator other = (Simulator) obj;
        return this.projects.equals(other.projects);
    }

    @Override
    public String toString() {
        return String.format("Simulator{projects=%s}", this.projects);
    }

}
