/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import lapr.project.controller.OpenProjectController;
import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;
import lapr.project.ui.components.ListCellRendererProject;
import lapr.project.ui.components.ListModelProject;

/**
 * The start frame for application.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ProjectSelectionDialog extends JDialog implements ProjectHandler {

    /**
     * Main Frame.
     */
    private final MainFrame mainFrame;

    /**
     * The simulator.
     */
    private final FlightSimulator simulator;

    /**
     * List of the selected projects.
     */
    private List<Project> projects;

    /**
     * The selected Project.
     */
    private Project project;

    /**
     * The JList for the list of projects.
     */
    private JList projectsList;

    /**
     * The button to open project.
     */
    private JButton openProjectButton;

    /**
     * The button to copy project.
     */
    private JButton copyProjectButton;

    /**
     * The button to edit project.
     */
    private JButton editProjectButton;

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Select or create a Project";

    /**
     * Window's dimension.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(600, 400);

    /**
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * Creates an instance of project selection dialog.
     *
     * @param mainFrame the main frame
     * @param simulator the simulator
     */
    public ProjectSelectionDialog(MainFrame mainFrame, FlightSimulator simulator) {

        super(mainFrame, WINDOW_TITLE, true); // Modal

        // Set Main Frame
        this.mainFrame = mainFrame;
        this.simulator = simulator;
        this.projects = null;

        try {
            this.projects = simulator.getProjects();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "There was an error trying to read data from database.",
                    "Data Error",
                    JOptionPane.WARNING_MESSAGE);
        }

        createComponents();

        pack();
        setSize(WINDOW_DIMENSION);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocationRelativeTo(mainFrame);
    }

    /**
     * Creates the components for frame.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(20, 20));

        componentsPanel.add(createListPanel(), BorderLayout.CENTER);
        componentsPanel.add(createButtonsPanel(), BorderLayout.SOUTH);

        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the list panel.
     *
     * @return list panel
     */
    private JPanel createListPanel() {
        JPanel listPanel = new JPanel();

        Collections.sort(projects);
        projectsList = new JList(new ListModelProject(projects));
        projectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectsList.setCellRenderer(new ListCellRendererProject());

        projectsList.addListSelectionListener((ListSelectionEvent lse) -> {
            this.openProjectButton.setEnabled(!projectsList.isSelectionEmpty());
            this.copyProjectButton.setEnabled(!projectsList.isSelectionEmpty());
            this.editProjectButton.setEnabled(!projectsList.isSelectionEmpty());
        });

        JScrollPane scrollPane = new JScrollPane(projectsList);
        listPanel.add(scrollPane);

        return listPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(createNewProjectButton());
        buttonsPanel.add(createOpenProjectButton());
        buttonsPanel.add(createCopyProjectButton());
        buttonsPanel.add(createEditProjectButton());

        return buttonsPanel;
    }

    /**
     * Creates the new project button.
     *
     * @return new project button
     */
    private JButton createNewProjectButton() {
        JButton newProjectButton = new JButton("New Project");

        newProjectButton.addActionListener((ActionEvent ae) -> {
            this.setVisible(false);
            CreateProjectDialog createProjectDialog = new CreateProjectDialog(this, simulator);
            createProjectDialog.setVisible(true);
        });

        return newProjectButton;
    }

    /**
     * Creates the open project button.
     *
     * @return open project button
     */
    private JButton createOpenProjectButton() {
        openProjectButton = new JButton("Open Project");

        openProjectButton.addActionListener((ActionEvent ae) -> {
            OpenProjectController openProjectController = new OpenProjectController(simulator);
            //openProjectController.activeProjects((Project) projectsList.getSelectedValue());
            dispose();
            mainFrame.activateProject((Project) projectsList.getSelectedValue());
        });

        openProjectButton.setEnabled(false);

        return openProjectButton;
    }

    /**
     * Creates the copy project button.
     *
     * @return copy project button
     */
    private JButton createCopyProjectButton() {
        copyProjectButton = new JButton("Copy Project");

        copyProjectButton.addActionListener((ActionEvent ae) -> {
            project = (Project) projectsList.getSelectedValue();
            CopyProjectDialog copyProjectDialog = new CopyProjectDialog(this, simulator, project);
            copyProjectDialog.setVisible(true);
        });

        copyProjectButton.setEnabled(false);

        return copyProjectButton;
    }

    /**
     * Creates the edit project button.
     *
     * @return edit project button
     */
    private JButton createEditProjectButton() {
        editProjectButton = new JButton("Edit Project");

        editProjectButton.addActionListener((ActionEvent ae) -> {
            project = (Project) projectsList.getSelectedValue();
            EditProjectPropertiesDialog editProjectPropertiesDialog = new EditProjectPropertiesDialog(this, simulator, project);
            editProjectPropertiesDialog.setVisible(true);
        });

        editProjectButton.setEnabled(false);

        return editProjectButton;
    }

    @Override
    public void activateProject(Project project) {
        dispose();
        mainFrame.activateProject(project);
    }

    /**
     * Refresh a given projec list.
     *
     * @param projects the projects to update
     */
    public void refreshProjectsList(List<Project> projects) {
        Collections.sort(projects);
        projectsList.setModel(new ListModelProject(projects)); // TODO
    }
}
