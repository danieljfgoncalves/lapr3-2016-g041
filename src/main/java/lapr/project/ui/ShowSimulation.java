/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import lapr.project.model.AlgorithmAnalysis;
import lapr.project.model.FlightSimulation;
import lapr.project.utils.Consts;

/**
 * The frame to create the results dialog.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 *
 */
public class ShowSimulation extends JDialog {

    /**
     * The flight simulation with the results.
     */
    private final FlightSimulation flightSimulation;

    /**
     * The plain label font.
     */
    private final static Font PLAIN_LABEL_FONT = new Font("Helvetica", Font.PLAIN, 17);

    /**
     * The bold label font.
     */
    private final static Font BOLD_LABEL_FONT = new Font("Helvetica", Font.BOLD, 17);

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
    private static final String WINDOW_TITLE = "Results";
    private final int projectID;

    /**
     * Creates an instance of the show results dialog.
     *
     * @param parentWindow the parent frame
     * @param flightSimulation the flight simulator with the results
     * @param projectID
     */
    public ShowSimulation(Window parentWindow, FlightSimulation flightSimulation, int projectID) {
        super(parentWindow, WINDOW_TITLE);
        setModal(true);

        this.flightSimulation = flightSimulation;
        this.projectID = projectID;
        this.setResizable(true);

        createComponents();

        pack();
        setLocationRelativeTo(parentWindow);

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

        AlgorithmAnalysis analysis = new AlgorithmAnalysis();
        try {
            analysis = this.flightSimulation.calculateAnalysis(this.projectID);
        } catch (Exception ex) {
            Logger.getLogger(ShowResultsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        JLabel fuelConsumptionLabel = new JLabel("Fuel Consumption:");
        fuelConsumptionLabel.setFont(BOLD_LABEL_FONT);
        JLabel fuelConsumptionResultLabel = new JLabel(String.format("%.2f L", (analysis.getConsumption().doubleValue(SI.KILOGRAM) / Consts.LITER_CONV)));
        fuelConsumptionResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(BOLD_LABEL_FONT);
        JLabel timeResultLabel = new JLabel(String.format("%d Minutes", analysis.getDuration().longValue(NonSI.MINUTE)));
        timeResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel distanceLabel = new JLabel("Distance: ");
        distanceLabel.setFont(BOLD_LABEL_FONT);
        JLabel distanceResultLabel = new JLabel(String.format("%d km", analysis.getDistance().longValue(SI.KILOMETER)));
        distanceResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel originLabel = new JLabel("Origin Airport:");
        originLabel.setFont(BOLD_LABEL_FONT);
        JLabel originResultLabel = new JLabel(flightSimulation.getFlightInfo().getOriginAirport().getIATA());
        originResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel destLabel = new JLabel("Destination Airport:");
        destLabel.setFont(BOLD_LABEL_FONT);
        JLabel destResultLabel = new JLabel(flightSimulation.getFlightInfo().getDestinationAirport().getIATA());
        destResultLabel.setFont(PLAIN_LABEL_FONT);

        // prints OPO, LIS, ETC
        JLabel techStopsLabel = new JLabel("Technical Stops:");
        techStopsLabel.setFont(BOLD_LABEL_FONT);
        StringBuilder techStops = new StringBuilder();
        for (int i = 0; i < flightSimulation.getFlightInfo().getStops().size(); i++) {
            if (i != flightSimulation.getFlightInfo().getWaypoints().size() - 1) {
                techStops.append(flightSimulation.getFlightInfo().getStops().get(i).getAirport().getIATA()).append(", ");
            } else {
                techStops.append(flightSimulation.getFlightInfo().getStops().get(i).getAirport().getIATA());
            }
        }
        JLabel techStopsResultLabel = new JLabel(techStops.toString());
        techStopsResultLabel.setFont(PLAIN_LABEL_FONT);

        // prints PT01, PT02, ES04, ETC
        JLabel waypointsLabel = new JLabel("Waypoints:");
        waypointsLabel.setFont(BOLD_LABEL_FONT);
        StringBuilder waypoints = new StringBuilder();
        for (int i = 0; i < flightSimulation.getFlightInfo().getWaypoints().size(); i++) {
            if (i != flightSimulation.getFlightInfo().getWaypoints().size() - 1) {
                waypoints.append(flightSimulation.getFlightInfo().getWaypoints().get(i).getId()).append(", ");
            } else {
                waypoints.append(flightSimulation.getFlightInfo().getWaypoints().get(i).getId());
            }
        }
        JLabel waypointsResultLabel = new JLabel(waypoints.toString());
        waypointsResultLabel.setFont(PLAIN_LABEL_FONT);

        // prints PT01 -> ES04 -> ETC
        JLabel pathLabel = new JLabel("Result Path:");
        pathLabel.setFont(BOLD_LABEL_FONT);
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < flightSimulation.getFlightplan().size(); i++) {
            if (i != flightSimulation.getFlightplan().size() - 1) {
                path.append(flightSimulation.getFlightplan().get(i).getId()).append(" -> ");
            } else {
                path.append(flightSimulation.getFlightplan().get(i).getId());
            }
        }
        JLabel pathResultLabel = new JLabel(path.toString());
        pathResultLabel.setFont(PLAIN_LABEL_FONT);

        //align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(fuelConsumptionLabel)
                        .addComponent(timeLabel)
                        .addComponent(distanceLabel)
                        .addComponent(originLabel)
                        .addComponent(destLabel)
                        .addComponent(techStopsLabel)
                        .addComponent(waypointsLabel)
                        .addComponent(pathLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fuelConsumptionResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(timeResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(distanceResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(originResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(destResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(techStopsResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(waypointsResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(pathResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                )
        );

        //align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fuelConsumptionLabel)
                        .addComponent(fuelConsumptionResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(timeLabel)
                        .addComponent(timeResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(distanceLabel)
                        .addComponent(distanceResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(originLabel)
                        .addComponent(originResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(destLabel)
                        .addComponent(destResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(techStopsLabel)
                        .addComponent(techStopsResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(waypointsLabel)
                        .addComponent(waypointsResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pathLabel)
                        .addComponent(pathResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener((ActionEvent ae) -> {
            dispose();
        });

        panel.add(confirmButton);

        return panel;
    }

}
