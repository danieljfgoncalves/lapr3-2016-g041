/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import lapr.project.model.FlightSimulation;
import lapr.project.ui.components.TableModelFlightSimulation;

/**
 * The frame to filter simulations dialog.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FilteredSimulationsUI extends JDialog {

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Filter Airports";

    /**
     * The button prefered size.
     */
    private static final Dimension BUTTON_PREFERED_SIZE = new Dimension(150, 30);

    /**
     * The flight simulations
     */
    private List<FlightSimulation> flightSimulations;
    
    /**
     * The simulations table.
     */
    private JTable simulationsTable;
    
    /**
     * 
     * @param parentWindow
     * @param flightSimulations 
     */
    public FilteredSimulationsUI(Window parentWindow, List<FlightSimulation> flightSimulations) {
        super(parentWindow, WINDOW_TITLE);
        setModal(true);
        this.setResizable(false);

        createComponents();

        pack();
        setLocationRelativeTo(parentWindow);
    }

    /**
     * Creates the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));
        
        componentsPanel.add(createTable());
        componentsPanel.add(createExportButton());
    }

    private JPanel createTable() {
        JPanel panel = new JPanel();
        
        simulationsTable = new JTable(new TableModelFlightSimulation(flightSimulations));
        
        return panel;
    }

    private JPanel createExportButton() {
        JPanel panel = new JPanel();
        
        JButton exportSimulations = new JButton("Export by ori/dest airport");
        exportSimulations.setPreferredSize(BUTTON_PREFERED_SIZE);
        exportSimulations.addActionListener((ActionEvent ae) -> {
            // TODO
        });
        
        return panel;
    }
}
