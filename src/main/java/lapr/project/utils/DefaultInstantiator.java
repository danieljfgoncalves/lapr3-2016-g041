/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;

/**
 * Creates instances with mockup data.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class DefaultInstantiator {

    /**
     * Creates an instance of simulator with mockup data.
     *
     * @return the simulator
     */
    public static FlightSimulator createSimulator() {

        Project project1 = new Project("Simullations Europe", "Flight routes of europe.");
        Project project2 = new Project("Africa Flights", "The commerce flights.");
        Project project3 = new Project("Instruments Exportations", "All the instrumentation exportations.");
        Project project4 = new Project("Cargo", "Transportations to America.");
        Project project5 = new Project("Ryan Air", "All the available flights.");
        Project project6 = new Project("Project 20", "My flight consumption simulations.");
        Project project7 = new Project("Suplements", "Simullations for suplements company.");
        Project project8 = new Project("Charity", "Charity flights.");
        Project project9 = new Project("Bidings", "Flights for bidding items");
        Project project10 = new Project("FIFA", "Flights for FIFA teams.");
        Project project11 = new Project("Mining Core", "Flights for a mining company.");
        Project project12 = new Project("UK Core", "Super secret flights.");
        Project project13 = new Project("EasyJet", "Flights of easyJet company.");
        Project project14 = new Project("Jet2", "Flights of jet2 company.");
        Project project15 = new Project("Pegasus Airline", "Flights of Pegasus Airline company.");

        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        projects.add(project4);
        projects.add(project5);
        projects.add(project6);
        projects.add(project7);
        projects.add(project8);
        projects.add(project9);
        projects.add(project10);
        projects.add(project11);
        projects.add(project12);
        projects.add(project13);
        projects.add(project14);
        projects.add(project15);

        return new FlightSimulator(projects);
    }
}
