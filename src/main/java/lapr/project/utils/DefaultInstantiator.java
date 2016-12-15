/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.Project;
import lapr.project.model.Simulator;

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
    public static Simulator createSimulator() {

        Project project1 = new Project("Simullations Europe", "Flight routes of europe.", 915);
        Project project2 = new Project("Africa Flights", "The commerce flights.", 792);
        Project project3 = new Project("Instruments Exportations", "All the instrumentation exportations.", 12345);
        Project project4 = new Project("Cargo", "Transportations to America.", 2323);
        Project project5 = new Project("Ryan Air", "All the available flights.", 8484);
        Project project6 = new Project("Project 20", "My flight consumption simulations.", 20);
        Project project7 = new Project("Suplements", "Simullations for suplements company.", 33);
        Project project8 = new Project("Charity", "Charity flights.", 123321);
        Project project9 = new Project("Bidings", "Flights for bidding items", 9);
        Project project10 = new Project("FIFA", "Flights for FIFA teams.", 4949);
        Project project11 = new Project("Mining Core", "Flights for a mining company.", 2233);
        Project project12 = new Project("UK Core", "Super secret flights.", 111222);
        Project project13 = new Project("EasyJet", "Flights of easyJet company.", 3);
        Project project14 = new Project("Jet2", "Flights of jet2 company.", 4443);
        Project project15 = new Project("Pegasus Airline", "Flights of Pegasus Airline company.", 121212);

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

        return new Simulator(projects);
    }
}
