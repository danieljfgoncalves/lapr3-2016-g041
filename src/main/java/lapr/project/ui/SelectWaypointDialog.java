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
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import lapr.project.model.Coordinate;
import lapr.project.model.Project;

/**
 * The frame to create select waypoit dialog.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 *
 */
public class SelectWaypointDialog extends JDialog {

    /**
     * The parent frame.
     */
    private final JDialog parentFrame;

    /**
     * The combo box with the coordinates for mandatory waypoints.
     */
    private JComboBox<Coordinate> waypointsComboBox;

    /**
     * The open project.
     */
    private final Project project;

    /**
     * the selected waypoint.
     */
    private Coordinate waypoint;

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
     * Creates an instance of select mandatory waypoint dialog.
     *
     * @param parentFrame the parent frame
     * @param project the open project
     */
    public SelectWaypointDialog(JDialog parentFrame, Project project) {
        super(parentFrame, WINDOW_TITLE);
        setModal(true);
        this.parentFrame = parentFrame;
        this.project = project;
        this.setResizable(false);

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

        JLabel selectWaypointLabel = new JLabel("Select the desired mandatory waypoint:");

        JComboBox<Coordinate> waypointsComboBox = new JComboBox<>();
        waypointsComboBox.setPreferredSize(new Dimension(350, 25));
        //populate origin airport combobox
        for (Coordinate coordinate : project.getAirNetwork().getNetwork().vertices()) {
            waypointsComboBox.addItem(coordinate);
        }
//        waypointsComboBox.setRenderer(//TODO);
        waypointsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                waypoint = (Coordinate) waypointsComboBox.getSelectedItem();
            }
        });

        //align horizontally
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(selectWaypointLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(waypointsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                )
        );

        //align vertically
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(selectWaypointLabel)
                        .addComponent(waypointsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
            //TODO
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
                    + "No mandatory waypoint will be added!", "Mandatory Waypoint", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        return button;
    }

}
