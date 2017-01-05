/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import lapr.project.model.FlightInfo;
import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;
import lapr.project.ui.components.CustomMenuBar;
import lapr.project.ui.components.TableModelFlight;

/**
 * The main frame for the application.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class MainFrame extends JFrame implements ProjectHandler {

    /**
     * The app simulator.
     */
    private final FlightSimulator simulator;

    /**
     * Active Project.
     */
    private Project activeProject;

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Flight Simulator";

    /**
     * Padding border.
     */
    private static final EmptyBorder PADDING_BORDER = new EmptyBorder(50, 50, 50, 50);

    /**
     * Page padding border.
     */
    private static final EmptyBorder PAGE_PADDING_BORDER = new EmptyBorder(50, 50, 50, 50);

    /**
     * The button prefered size.
     */
    private static final Dimension BUTTON_PREFERED_SIZE = new Dimension(150, 30);

    /**
     * The project title label.
     */
    private JLabel projectTitleLabel;

    /**
     * The project description label.
     */
    private JLabel projectDescriptionLabel;

    /**
     * The simulations table.
     */
    private JTable simulationsTable;

    /**
     * Creates an instance of the main frame.
     *
     * @param simulator the simulator
     */
    public MainFrame(FlightSimulator simulator) {
        super(WINDOW_TITLE);

        // Set simulator
        this.simulator = simulator;
        // Instanciate active project
        this.activeProject = null;

        // Set Custom Menu Bar
        CustomMenuBar customMenuBar = new CustomMenuBar(this, simulator);
        setJMenuBar(customMenuBar);

        createComponents();

        pack();
    }

    public void openProjectSelection() {
        ProjectSelectionDialog projectSelectionDialog = new ProjectSelectionDialog(this, simulator);
        projectSelectionDialog.setVisible(true);
        projectSelectionDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Creates the components for frame.
     */
    private void createComponents() {

        // TODO: Set main panel not visble
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(projectInfoPanel(), BorderLayout.NORTH);
        componentsPanel.add(createTablePanel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        componentsPanel.setBorder(PAGE_PADDING_BORDER);
        add(componentsPanel);
    }

    public Project getActiveProject() {
        return this.activeProject;
    }

    /**
     * Creates the project info panel.
     *
     * @return project info panel
     */
    private JPanel projectInfoPanel() {
        JPanel projectInfoPanel = new JPanel(new BorderLayout());

        projectTitleLabel = new JLabel("Project Name Placeholder", SwingConstants.CENTER);
        projectTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        projectDescriptionLabel = new JLabel("Project Description Placeholder", SwingConstants.CENTER);
        projectDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        projectInfoPanel.add(projectTitleLabel, BorderLayout.NORTH);
        projectInfoPanel.add(projectDescriptionLabel, BorderLayout.SOUTH);

        return projectInfoPanel;
    }

    /**
     * Creates the table panel.
     *
     * @return table panel
     */
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());

        // TODO remove this mock object
        ArrayList<FlightInfo> flights = new ArrayList<>();
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());

        simulationsTable = new JTable(new TableModelFlight(flights));

        JScrollPane scrollPane = new JScrollPane(simulationsTable);
        scrollPane.setBorder(PADDING_BORDER);

        tablePanel.add(scrollPane);

        return tablePanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        JButton openSimulationButton = new JButton("Open Simulation");
        openSimulationButton.setPreferredSize(BUTTON_PREFERED_SIZE);
        openSimulationButton.setEnabled(false);

        JButton createSimulationButton = new JButton("New Simulation");
        createSimulationButton.setPreferredSize(BUTTON_PREFERED_SIZE);

        buttonsPanel.add(openSimulationButton);
        buttonsPanel.add(createSimulationButton);

        return buttonsPanel;
    }

    @Override
    public void activateProject(Project project) {
        this.activeProject = project;
        refreshProject();
    }

    private void refreshProject() {
        Collections.sort(activeProject.getSimulations().getFlights());
        simulationsTable.setModel(new TableModelFlight(activeProject.getSimulations().getFlights()));
        this.projectTitleLabel.setText(activeProject.getName());
        this.projectDescriptionLabel.setText(activeProject.getDescription());
    }
}
