/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import lapr.project.model.FlightSimulation;
import lapr.project.ui.components.TableModelFlightSimulation;
import lapr.project.utils.exportable.FlightSimulationHTML;

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
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The bold label font.
     */
    private final static Font BOLD_LABEL_FONT = new Font("Helvetica", Font.BOLD, 15);

    /**
     * The flight simulations
     */
    private final List<FlightSimulation> flightSimulations;

    /**
     * The simulations table.
     */
    private JTable simulationsTable;

    /**
     * The export simulations button.
     */
    private JButton exportSimulationsButton;

    /**
     * The parent window.
     */
    private final Window parentWindow;

    /**
     *
     * @param parentWindow
     * @param flightSimulations
     */
    public FilteredSimulationsUI(Window parentWindow, List<FlightSimulation> flightSimulations) {
        super(parentWindow, WINDOW_TITLE);
        setModal(true);
        setResizable(false);

        this.flightSimulations = flightSimulations;
        this.parentWindow = parentWindow;

        createComponents();

        pack();
        setLocationRelativeTo(parentWindow);
    }

    /**
     * Creates the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(createTable(), BorderLayout.NORTH);
        componentsPanel.add(createExportButton(), BorderLayout.SOUTH);

        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    private JPanel createTable() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel tableLabel = new JLabel("Select the simulations to export:", SwingConstants.CENTER);
        tableLabel.setFont(BOLD_LABEL_FONT);
        simulationsTable = new JTable(new TableModelFlightSimulation(flightSimulations));
        ListSelectionModel listSelectionModel = simulationsTable.getSelectionModel();
        listSelectionModel.addListSelectionListener((ListSelectionEvent lse) -> {
            exportSimulationsButton.setEnabled(!listSelectionModel.isSelectionEmpty());
        });

        panel.add(tableLabel, BorderLayout.NORTH);
        panel.add(simulationsTable, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createExportButton() {
        JPanel panel = new JPanel();

        exportSimulationsButton = new JButton("Export");
        exportSimulationsButton.setEnabled(false);
        exportSimulationsButton.setPreferredSize(BUTTON_PREFERED_SIZE);
        exportSimulationsButton.addActionListener((ActionEvent ae) -> {
            List<FlightSimulation> selectedSimulations = new ArrayList<>();
            int selectedRow[] = simulationsTable.getSelectedRows();
            for (int i : selectedRow) {
                selectedSimulations.add(flightSimulations.get(i));
            }
            JFileChooser fileChooser = new JFileChooser();
            int resposta = fileChooser.showSaveDialog(this);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                FlightSimulationHTML flightSimulationHTML = new FlightSimulationHTML(selectedSimulations);
                try {
                    flightSimulationHTML.export(file);
                    dispose();
                    parentWindow.dispose();
                    JOptionPane.showMessageDialog(this,
                            "The file was successfully exported!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid file.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(FilteredSimulationsUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        panel.add(exportSimulationsButton);

        return panel;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setVisible(true);
        FilteredSimulationsUI filteredSimulationsUI = new FilteredSimulationsUI(f, new ArrayList<>());
        filteredSimulationsUI.setVisible(true);
    }
}
