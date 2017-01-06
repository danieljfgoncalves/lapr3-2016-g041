package lapr.project.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import lapr.project.model.FlightSimulator;

/**
 * The class to start the application.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
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

        // Mock Object
        FlightSimulator flightSimulator = new FlightSimulator();

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Open Main Frame
        MainFrame mainFrame = new MainFrame(flightSimulator);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setSize(screenSize);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Open Project Selection
        mainFrame.openProjectSelection();
    }
}
