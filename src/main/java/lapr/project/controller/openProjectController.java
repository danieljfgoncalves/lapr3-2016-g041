/*
 * Package location for controllers
 */
package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.model.Simulator;

/**
 * Controller responsible for opening projects
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class openProjectController {
    
    Simulator simulator;
    
    public openProjectController(Simulator simulator){
      this.simulator = simulator;        
    }
    
    public void activeProjects(Project project){
        simulator.setActivatedProject(project); 
    }
}

