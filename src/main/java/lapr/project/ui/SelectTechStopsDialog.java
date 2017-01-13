/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;
import java.util.List;
import javax.measure.quantity.Duration;
import javax.measure.unit.NonSI;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import lapr.project.model.Airport;
import lapr.project.model.Stop;
import lapr.project.ui.components.ListCellRendererAirport;
import org.jscience.physics.amount.Amount;

/**
 * The frame to create select technical stop dialog.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 *
 */
public class SelectTechStopsDialog extends JDialog {

    /**
     * The parent frame.
     */
    private final FlightInfoDialog parentFrame;

    /**
     * The airports list.
     */
    private final List<Airport> airports;

    /**
     * The minimum stop time text field.
     */
    private JTextField txtMinStopTime;

    /**
     * the selected technical stop airport.
     */
    private Airport stopAirport;

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
    private static final String WINDOW_TITLE = "Add Technical Stop";

    /**
     * Creates an instance of select technical stop dialog.
     *
     * @param parentFrame the parent frame
     * @param airports
     */
    public SelectTechStopsDialog(FlightInfoDialog parentFrame, List<Airport> airports) {
        super(parentFrame, WINDOW_TITLE);
        setModal(true);
        this.setResizable(false);

        this.airports = airports;
        this.parentFrame = parentFrame;

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

        JLabel selectTechStopLabel = new JLabel("Select Airport for technical stop:");
        JLabel minimumStopTimeLabel = new JLabel("Minimum stop time (minutes):");

        JComboBox<Airport> techStopComboBox = new JComboBox<>();
        techStopComboBox.setModel(new DefaultComboBoxModel(airports.toArray()));
        techStopComboBox.setPreferredSize(new Dimension(420, 25));
        techStopComboBox.setRenderer(new ListCellRendererAirport());
        stopAirport = (Airport) techStopComboBox.getSelectedItem();
        techStopComboBox.addActionListener((ActionEvent ae) -> {
            stopAirport = (Airport) techStopComboBox.getSelectedItem();
        });

        txtMinStopTime = new JTextField();
        txtMinStopTime.setPreferredSize(new Dimension(50, 25));

        //align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(selectTechStopLabel)
                        .addComponent(minimumStopTimeLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(techStopComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinStopTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                )
        );

        //align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectTechStopLabel)
                        .addComponent(techStopComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(minimumStopTimeLabel)
                        .addComponent(txtMinStopTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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

        JButton addButton = createAddButton();
        JButton cancelButton = createCancelButton();

        panel.add(addButton);
        panel.add(cancelButton);

        return panel;
    }

    /**
     * Creates the add button.
     *
     * @return add button
     */
    private JButton createAddButton() {
        JButton button = new JButton("Add");
        button.addActionListener((ActionEvent ae) -> {
            Amount<Duration> minStopMinutes = Amount
                    .valueOf(Double.parseDouble(txtMinStopTime.getText()), NonSI.MINUTE);
            parentFrame.addStop(new Stop(stopAirport, minStopMinutes,
                    new GregorianCalendar(), new GregorianCalendar()));
            dispose();
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
            int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the operation? "
                    + "No technical stop will be added!", "Technical Stop", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        return button;
    }

}
