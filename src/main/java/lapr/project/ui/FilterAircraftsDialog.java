/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import lapr.project.model.AircraftModel;
import lapr.project.model.FlightSimulation;
import lapr.project.ui.components.ListCellRendererAirport;

/**
 * The frame to filter simulations dialog.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FilterAircraftsDialog extends JDialog {

    /**
     * The parent frame.
     */
    private final Window parentFrame;

    /**
     * The aircrafts list.
     */
    private final List<AircraftModel> aircraftModelsList;

    /**
     * the selected aircraft model.
     */
    private AircraftModel aircraftModel;

    /**
     * List of flight simulations
     */
    private List<FlightSimulation> flightSimulations;

    /**
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The color for the dialog panels.
     */
    private final static Color DEFAULT_COLOR = new Color(220, 220, 220);

    /**
     * The default grey line border for the panels.
     */
    private final static Border DEFAULT_GREY_LINE_BORDER = BorderFactory.createLineBorder(Color.DARK_GRAY);

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Filter Aircraft Models";

    /**
     * Creates an instance of filter aircraft models dialog.
     *
     * @param parentFrame the parent frame
     * @param aircraftModelsList the list of aircraft models
     * @param flightSimulations
     */
    public FilterAircraftsDialog(Window parentFrame, List<AircraftModel> aircraftModelsList, List<FlightSimulation> flightSimulations) {
        super(parentFrame, WINDOW_TITLE);
        setModal(true);
        this.setResizable(false);

        this.aircraftModelsList = aircraftModelsList;
        this.parentFrame = parentFrame;
        this.flightSimulations = flightSimulations;

        createComponents();

        pack();
        setLocationRelativeTo(parentFrame);
    }

    /**
     * Creates the visual components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(createMainPanel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the main panel.
     *
     * @return main panel
     */
    private Component createMainPanel() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        panel.setBackground(DEFAULT_COLOR);
        panel.setBorder(DEFAULT_GREY_LINE_BORDER);

        JLabel selectAircraftLabel = new JLabel("Select the aircraft model:");

        JComboBox<AircraftModel> aircraftModelsComboBox = new JComboBox<>();
        aircraftModelsComboBox.setModel(new DefaultComboBoxModel(aircraftModelsList.toArray()));
        aircraftModelsComboBox.setPreferredSize(new Dimension(420, 25));
        aircraftModelsComboBox.setRenderer(new ListCellRendererAirport());
        aircraftModel = (AircraftModel) aircraftModelsComboBox.getSelectedItem();
        aircraftModelsComboBox.addActionListener((ActionEvent ae) -> {
            aircraftModel = (AircraftModel) aircraftModelsComboBox.getSelectedItem();
        });

        //align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(selectAircraftLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(aircraftModelsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                )
        );

        //align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectAircraftLabel)
                        .addComponent(aircraftModelsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
        );
        return panel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return the buttons panel
     */
    private Component createButtonsPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton okButton = createOkButton();
        JButton cancelButton = createCancelButton();

        panel.add(okButton);
        panel.add(cancelButton);

        return panel;
    }

    /**
     * Creates the OK button.
     *
     * @return OK button
     */
    private JButton createOkButton() {
        JButton button = new JButton("OK");
        button.addActionListener((ActionEvent ae) -> {
            List<FlightSimulation> filteredSimulations = new ArrayList();
            for (FlightSimulation simulation : flightSimulations) {
                if (simulation.getFlightInfo().getAircraft().getAircraftModel().equals(aircraftModel)) {
                    filteredSimulations.add(simulation);
                }
            }
            if (!filteredSimulations.isEmpty()) {
                int selection = JOptionPane.showConfirmDialog(null, "Simulations added successfully!", "Simulation Filter", JOptionPane.DEFAULT_OPTION);
                if (selection == JOptionPane.OK_OPTION) {

                    dispose();
                }
            } else {
                int selection = JOptionPane.showConfirmDialog(null, "There is no simulations with selected aircraft!", "Simulation Filter", JOptionPane.DEFAULT_OPTION);
                if (selection == JOptionPane.OK_OPTION) {
                    dispose();
                }
            }
        });
        return button;
    }

    /**
     * Creates the cancel button.
     *
     * @return the cancel button
     */
    private JButton createCancelButton() {
        JButton button = new JButton("Cancel");
        button.addActionListener((ActionEvent ae) -> {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the operation? ", "Filter Aircraft Models", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        return button;
    }
}
