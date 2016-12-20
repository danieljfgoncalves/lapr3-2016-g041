/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;
import lapr.project.ui.components.CustomMenuBar;

/**
 * The main frame for the application.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class MainFrame extends JFrame implements ProjectHandler {

    /**
     * The app simulator.
     */
    private final FlightSimulator simulator;

    /**
     * Active Project.
     */
    private Project activeProject;

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Flight Simulator";

    /**
     * Creates an instance of the main frame.
     *
     * @param simulator the simulator
     */
    public MainFrame(FlightSimulator simulator) {
        super(WINDOW_TITLE);

        // Set simulator
        this.simulator = simulator;
        // Instanciate active project
        this.activeProject = null;

        // Set Custom Menu Bar
        CustomMenuBar customMenuBar = new CustomMenuBar(this, simulator);
        setJMenuBar(customMenuBar);

        // TODO: Implement components
        createComponents();

        pack();
    }

    public void openProjectSelection() {
        ProjectSelectionDialog projectSelectionDialog = new ProjectSelectionDialog(this, simulator);
        projectSelectionDialog.setVisible(true);
        projectSelectionDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Creates the components for frame.
     */
    public void createComponents() {

        // TODO: Set main panel not visble
    }

    @Override
    public void activateProject(Project project) {
        this.activeProject = project;
        //TODO activate project
        JOptionPane.showMessageDialog(this,
                "The project was activated!");
    }
    
    public Project getActiveProject(){
        return this.activeProject;
    }
}
