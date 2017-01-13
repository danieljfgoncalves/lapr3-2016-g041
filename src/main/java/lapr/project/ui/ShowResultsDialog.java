/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import lapr.project.model.FlightSimulation;

/**
 * The frame to create the results dialog.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 *
 */
public class ShowResultsDialog extends JDialog {

    /**
     * The parent frame.
     */
    private final SimulateFlightDialog parentFrame;

    /**
     * The flight simulation with the results.
     */
    private FlightSimulation flightSimulation;

    /**
     * The algorithm used in the simulation.
     */
    private String algorithm;

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

    /**
     * Creates an instance of the show results dialog.
     *
     * @param parentFrame the parent frame
     * @param algorithm the algorithm used
     * @param flightSimulation the flight simulator with the results
     */
    public ShowResultsDialog(SimulateFlightDialog parentFrame, FlightSimulation flightSimulation, String algorithm) {
        super(parentFrame, WINDOW_TITLE);
        setModal(true);
        this.parentFrame = parentFrame;
        this.flightSimulation = flightSimulation;
        this.algorithm = algorithm;
        this.setResizable(true);

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

        JLabel usedAlgorithmLabel = new JLabel("Used Algorithm:");
        usedAlgorithmLabel.setFont(BOLD_LABEL_FONT);
        JLabel usedAlgorithmResultLabel = new JLabel(algorithm);
        usedAlgorithmResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel fuelConsumptionLabel = new JLabel("Fuel Consumption:");
        fuelConsumptionLabel.setFont(BOLD_LABEL_FONT);
        JLabel fuelConsumptionResultLabel = new JLabel("85000 L"); //TODO
        fuelConsumptionResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(BOLD_LABEL_FONT);
        JLabel timeResultLabel = new JLabel("175 minutes"); //TODO
        timeResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel distanceLabel = new JLabel("Distance: ");
        distanceLabel.setFont(BOLD_LABEL_FONT);
        JLabel distanceResultLabel = new JLabel("2420 km"); //TODO
        distanceResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel originLabel = new JLabel("Origin Airport:");
        originLabel.setFont(BOLD_LABEL_FONT);
        JLabel originResultLabel = new JLabel("OPO"); //TODO
        originResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel destLabel = new JLabel("Destination Airport:");
        destLabel.setFont(BOLD_LABEL_FONT);
        JLabel destResultLabel = new JLabel("LHR"); //TODO
        destResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel techStopsLabel = new JLabel("Technical Stops:");
        techStopsLabel.setFont(BOLD_LABEL_FONT);
        JLabel techStopsResultLabel = new JLabel("MAD"); //TODO
        techStopsResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel waypointsLabel = new JLabel("Waypoints:");
        waypointsLabel.setFont(BOLD_LABEL_FONT);
        JLabel waypointsResultLabel = new JLabel("PT02, PT04, ES03"); //TODO
        waypointsResultLabel.setFont(PLAIN_LABEL_FONT);

        JLabel pathLabel = new JLabel("Result Path:");
        pathLabel.setFont(BOLD_LABEL_FONT);
        JLabel pathResultLabel = new JLabel("PT01 -> PT05 -> ES05 -> ES06 -> FR06 -> UK02 -> UK08"); //TODO
        pathResultLabel.setFont(PLAIN_LABEL_FONT);

        //align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(usedAlgorithmLabel)
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
                        .addComponent(usedAlgorithmResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(usedAlgorithmLabel)
                        .addComponent(usedAlgorithmResultLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
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

        JButton saveButton = createSaveButton();
        JButton discardButton = createDiscardButton();

        panel.add(saveButton);
        panel.add(discardButton);

        return panel;
    }

    /**
     * Creates the save button.
     *
     * @return save button
     */
    private JButton createSaveButton() {
        JButton button = new JButton("Save");
        button.addActionListener((ActionEvent ae) -> {
            parentFrame.saveOnDatabase(flightSimulation);
        });
        return button;
    }

    /**
     * Creates the discard button.
     *
     * @return the discard button
     */
    private JButton createDiscardButton() {
        JButton button = new JButton("Discard");
        button.addActionListener((ActionEvent ae) -> {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to discard the results?", "Results", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        return button;
    }
}
