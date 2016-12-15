/*
 * Package location for Project concept
 */
package lapr.project.model;

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
     * Default value for Description
     */
    private final static String DEFAULT_DESCRIPTION= "Lista de Voos";
     /**
      * Default value for Name
      */
    private final static String DEFAULT_NAME = "Voos para localização";
    
    /**
     * Default value for Serie Number
     */
    private final static int DEFAULT_SERIE_NUMBER = 000000;
     
    /**
     * Creates an instance of project with it's default values
     */
    public Project() {
        this.description = DEFAULT_DESCRIPTION;
        this.name = DEFAULT_NAME;
        this.serieNumber = DEFAULT_SERIE_NUMBER;
    }
    
    /**
     * Creates an instance of project receiving it's attributes
     * 
     * @param description the description of the project
     * @param name the name of the project
     * @param serieNumber the serie number of the project
     */
    public Project(String description, String name, int serieNumber){
        this.description = description;
        this.name = name;
        this.serieNumber = serieNumber;
    }
    
    /**
     * Creates a project receiving another project
     * 
     * @param otherProject  other project to copy
     */
    public Project(Project otherProject){
        this.description = otherProject.description;
        this.name = otherProject.name;
        this.serieNumber = otherProject.serieNumber;
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
     * @param description description
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
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the serie number
     * 
     * @return  serie number
     */
    public int getSerieNumber() {
        return serieNumber;
    }

    /**
     * Sets the serie number
     * 
     * @param serieNumber serie number
     */
    public void setSerieNumber(int serieNumber) {
        this.serieNumber = serieNumber;
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
        return "Project{" + "description=" + description + ", name=" + name + ", serieNumber=" + serieNumber + '}';
    }
    
    
}
