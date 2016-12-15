package lapr.project.ui;

import javax.swing.JFrame;
import lapr.project.model.Simulator;
import lapr.project.utils.DefaultInstantiator;

/**
 * The class to start the application.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
class Main {

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {
    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Simulator simulator = DefaultInstantiator.createSimulator();

        ProjectSelectionFrame projectSelectionFrame = new ProjectSelectionFrame(simulator);
        projectSelectionFrame.setVisible(true);
        projectSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
