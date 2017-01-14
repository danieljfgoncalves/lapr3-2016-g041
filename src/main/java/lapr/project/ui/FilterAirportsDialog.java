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
import lapr.project.model.Airport;
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
public class FilterAirportsDialog extends JDialog {

    /**
     * The parent frame.
     */
    private final Window parentFrame;

    /**
     * The airports list.
     */
    private final List<Airport> airports;

    /**
     * the selected origin airport.
     */
    private Airport originAirport;

    /**
     * the selected detination airport.
     */
    private Airport destAirport;

    /**
     * List of flight simulations
     */
    private final List<FlightSimulation> flightSimulations;

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
    private static final String WINDOW_TITLE = "Filter Airports";

    /**
     * Creates an instance of select filter airports dialog.
     *
     * @param parentFrame the parent frame
     * @param airports list of airports
     * @param flightSimulations
     */
    public FilterAirportsDialog(Window parentFrame, List<Airport> airports, List<FlightSimulation> flightSimulations) {
        super(parentFrame, WINDOW_TITLE);
        setModal(true);
        this.setResizable(false);

        this.airports = airports;
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

        JLabel selectOriginLabel = new JLabel("Select origin airport:");
        JLabel selectDestLabel = new JLabel("Select destination airport:");

        JComboBox<Airport> originAirportComboBox = new JComboBox<>();
        originAirportComboBox.setModel(new DefaultComboBoxModel(airports.toArray()));
        originAirportComboBox.setPreferredSize(new Dimension(420, 25));
        originAirportComboBox.setRenderer(new ListCellRendererAirport());
        originAirport = (Airport) originAirportComboBox.getSelectedItem();
        originAirportComboBox.addActionListener((ActionEvent ae) -> {
            originAirport = (Airport) originAirportComboBox.getSelectedItem();
        });

        JComboBox<Airport> destinationAirportComboBox = new JComboBox<>();
        destinationAirportComboBox.setModel(new DefaultComboBoxModel(airports.toArray()));
        destinationAirportComboBox.setPreferredSize(new Dimension(420, 25));
        destinationAirportComboBox.setRenderer(new ListCellRendererAirport());
        destAirport = (Airport) destinationAirportComboBox.getSelectedItem();
        destinationAirportComboBox.addActionListener((ActionEvent ae) -> {
            destAirport = (Airport) destinationAirportComboBox.getSelectedItem();
        });

        //align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(selectOriginLabel)
                        .addComponent(selectDestLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(originAirportComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(destinationAirportComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                )
        );

        //align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectOriginLabel)
                        .addComponent(originAirportComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectDestLabel)
                        .addComponent(destinationAirportComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
                if (simulation.getFlightInfo().getOriginAirport().equals(originAirport)
                        && simulation.getFlightInfo().getDestinationAirport().equals(destAirport)) {
                    filteredSimulations.add(simulation);
                }
            }
            if (!filteredSimulations.isEmpty()) {
                FilteredSimulationsUI filteredSimulationsUI = new FilteredSimulationsUI(this, flightSimulations);
                filteredSimulationsUI.setVisible(true);
            } else {
                int selection = JOptionPane.showConfirmDialog(null, "There is no simulations with selected airports!", "Simulation Filter", JOptionPane.DEFAULT_OPTION);
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
            int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the operation? ", "Filter Airports", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        return button;
    }
}
