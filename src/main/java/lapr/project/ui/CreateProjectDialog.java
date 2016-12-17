/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * The frame to create project.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class CreateProjectDialog extends JDialog {

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Create Project";

    /**
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The button prefered size.
     */
    private final static Dimension BUTTON_PREFERED_SIZE = new Dimension(150, 30);

    /**
     * The label prefered size.
     */
    private final static Dimension LABEL_PREFERED_SIZE = new Dimension(100, 30);

    /**
     * The label prefered size.
     */
    private final static Dimension TEXT_FIELD_PREFERED_SIZE = new Dimension(150, 30);

    /**
     * Creates an instance of create project dialog.
     *
     * @param parentFrame the parent frame
     */
    public CreateProjectDialog(JFrame parentFrame) {
        super(parentFrame, WINDOW_TITLE);

        createComponents();

        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocationRelativeTo(parentFrame);
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

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setPreferredSize(TEXT_FIELD_PREFERED_SIZE);

        namePanel.add(nameLabel);
        namePanel.add(nameField);

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

        JTextField descriptionField = new JTextField();
        descriptionField.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionField.setPreferredSize(TEXT_FIELD_PREFERED_SIZE);

        descriptionPanel.add(descriptionLabel);
        descriptionPanel.add(descriptionField);

        return descriptionPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel.
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));

        buttonsPanel.add(createImportsPanel(), BorderLayout.NORTH);
        buttonsPanel.add(createCreateProjectLabel(), BorderLayout.CENTER);

        return buttonsPanel;
    }

    /**
     * Creates the imports panel.
     *
     * @return imports panel
     */
    private JPanel createImportsPanel() {
        JPanel importsPanel = new JPanel();

        JButton importAirNetworkButton = new JButton("Import Air Network");
        importAirNetworkButton.setPreferredSize(BUTTON_PREFERED_SIZE);

        JButton importAirportsButton = new JButton("Import Airports");
        importAirportsButton.setPreferredSize(BUTTON_PREFERED_SIZE);

        JButton importAircraftsButton = new JButton("Import Aircrafts");
        importAircraftsButton.setPreferredSize(BUTTON_PREFERED_SIZE);

        importsPanel.add(importAirNetworkButton);
        importsPanel.add(importAirportsButton);
        importsPanel.add(importAircraftsButton);

        return importsPanel;
    }

    /**
     * Creates the create project label.
     *
     * @return create project label
     */
    private JPanel createCreateProjectLabel() {
        JPanel createProjectLabel = new JPanel();

        JButton createProjectButton = new JButton("Create Project");
        createProjectButton.setPreferredSize(BUTTON_PREFERED_SIZE);

        createProjectLabel.add(createProjectButton);

        return createProjectLabel;
    }
}
