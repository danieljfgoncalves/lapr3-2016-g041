/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import lapr.project.controller.CopyProjectController;
import lapr.project.model.Project;
import lapr.project.model.FlightSimulator;

/**
 * The frame to copy a project.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 *
 * @param <T> window that extend ProjectHandler
 */
public class CopyProjectDialog<T extends Window & ProjectHandler> extends JDialog {

    /**
     * The parent window.
     */
    private final T parentWindow;

    /**
     * The controller to copy project.
     */
    private final CopyProjectController controller;

    /**
     * The selected project.
     */
    private final Project project;

    /**
     * The name text field;
     */
    private JTextField nameTextField;

    /**
     * The description text field.
     */
    private JTextField descriptionTextField;

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Copy Project";

    /**
     * Padding border.
     */
    private static final EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The button prefered size.
     */
    private static final Dimension BUTTON_PREFERED_SIZE = new Dimension(150, 30);

    /**
     * The label prefered size.
     */
    private static final Dimension LABEL_PREFERED_SIZE = new Dimension(100, 30);

    /**
     * The label prefered size.
     */
    private static final Dimension TEXT_FIELD_PREFERED_SIZE = new Dimension(150, 30);

    /**
     * Creates an instance of copy project dialog.
     *
     * @param parentWindow the parent window
     * @param simulator the simulator
     * @param project the selected project
     */
    public CopyProjectDialog(T parentWindow, FlightSimulator simulator, Project project) {
        super(parentWindow, WINDOW_TITLE);
        setModal(true);

        this.parentWindow = parentWindow;
        this.controller = new CopyProjectController(simulator, project);
        this.project = project;

        createComponents();
        createWindowClosingListener();

        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocationRelativeTo(parentWindow);
    }

    /**
     * Creates the visual components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(createFieldsPanel(), BorderLayout.NORTH);
        componentsPanel.add(createButtonsPanel(), BorderLayout.CENTER);

        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the fields panel.
     *
     * @return fields panel
     */
    private JPanel createFieldsPanel() {
        JPanel fieldsPanel = new JPanel(new BorderLayout());

        fieldsPanel.add(createNamePanel(), BorderLayout.NORTH);
        fieldsPanel.add(createDescriptionPanel(), BorderLayout.CENTER);

        return fieldsPanel;
    }

    /**
     * Creates the name panel.
     *
     * @return name panel
     */
    private JPanel createNamePanel() {
        JPanel namePanel = new JPanel();

        JLabel nameLabel = new JLabel("Name:", SwingConstants.RIGHT);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setPreferredSize(LABEL_PREFERED_SIZE);

        nameTextField = new JTextField();
        nameTextField.setText(project.getName());
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameTextField.setPreferredSize(TEXT_FIELD_PREFERED_SIZE);

        namePanel.add(nameLabel);
        namePanel.add(nameTextField);

        return namePanel;
    }

    /**
     * Creates the description panel.
     *
     * @return description panel
     */
    private JPanel createDescriptionPanel() {
        JPanel descriptionPanel = new JPanel();

        JLabel descriptionLabel = new JLabel("Description:", SwingConstants.RIGHT);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        descriptionLabel.setPreferredSize(LABEL_PREFERED_SIZE);

        descriptionTextField = new JTextField();
        descriptionTextField.setText(project.getDescription());
        descriptionTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionTextField.setPreferredSize(TEXT_FIELD_PREFERED_SIZE);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionTextField);

        return descriptionPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel.
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));

        buttonsPanel.add(createCopyProjectLabel(), BorderLayout.CENTER);

        return buttonsPanel;
    }

    /**
     * Creates the copy project label.
     *
     * @return copy project label
     */
    private JPanel createCopyProjectLabel() {
        JPanel copyProjectLabel = new JPanel();

        JButton copyProjectButton = new JButton("Copy Project");
        copyProjectButton.setPreferredSize(BUTTON_PREFERED_SIZE);

        copyProjectButton.addActionListener((ActionEvent ae) -> {
            try {
                if (!(controller.setCopyProjectData(nameTextField.getText(), descriptionTextField.getText()))) {
                    throw new IllegalArgumentException("The given name already exists or is invalid. Please try again!");
                } else if (!controller.addProjectCopy()) {
                    JOptionPane.showMessageDialog(rootPane,
                            "A project with the same serie number already exists!",
                            "Copy Project",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(rootPane,
                            "Project successfully copied!",
                            "Copy Project",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        copyProjectLabel.add(copyProjectButton);

        return copyProjectLabel;
    }

    /**
     * Creates the window closing listener.
     */
    private void createWindowClosingListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
                parentWindow.setVisible(true);
            }
        });
    }
}
