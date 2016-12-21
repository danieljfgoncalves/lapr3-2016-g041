/*
 * Package location for controllers
 */
package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;

/**
 * Controller responsible for opening projects
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class OpenProjectController {
    
    /**
     * The flight simulator.
     */
    FlightSimulator simulator;
    
    /**
     * Creates an instance of open project controller.
     * 
     * @param simulator the flight simulator
     */
    public OpenProjectController(FlightSimulator simulator){
      this.simulator = simulator;        
    }
    
    /**
     * Activates a given project.
     * 
     * @param project project to activate
     */
    public void activeProjects(Project project){
        simulator.setActivatedProject(project); 
    }
}

