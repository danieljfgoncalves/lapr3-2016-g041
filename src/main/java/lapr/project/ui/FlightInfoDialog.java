/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import lapr.project.model.AircraftModel;
import lapr.project.model.Airport;
import lapr.project.model.FlightSimulator;
import lapr.project.model.Project;

/**
 * The frame to create project.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 *
 * @param <T> window that extend ProjectHandler
 *
 */
public class FlightInfoDialog<T extends Window & ProjectHandler> extends JDialog {

    /**
     * The parent window.
     */
    private final T parentWindow;

    /**
     * The active project.
     */
    private Project project;

    /**
     * The layout for the forms.
     */
    private CardLayout cl;

    /**
     * The current active form.
     */
    private int currentForm = 1;

    private AircraftModel aircraftModel;

    /**
     * The forms panel.
     */
    private JPanel formsPanel;

    /**
     * The selectAircraft label.
     */
    private JLabel selectAircraftLabel;

    /**
     * The class label.
     */
    private JLabel classLabel;

    /**
     * The addFlightInfo label.
     */
    private JLabel addFlightInfoLabel;

    /**
     * The name label.
     */
    private JLabel nameLabel;

    /**
     * The next button.
     */
    private JButton nextButton;

    /**
     * The previous button.
     */
    private JButton previousButton;

    /**
     * The number of classes text field.
     */
    private JTextField txtNumClasses;

    /**
     * The climb rate text field.
     */
    private JTextField txtClimbRate;

    /**
     * The flight info name text field.
     */
    private JTextField txtFlightInfoName;

    /**
     * The selected origin airport.
     */
    private Airport originAirport;

    /**
     * The selected destination airport.
     */
    private Airport destinationAirport;

    /**
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The button preferred size.
     */
    private final static Dimension BUTTON_PREFERRED_SIZE = new Dimension(100, 30);

    /**
     * The preferred size for bigger buttons.
     */
    private final static Dimension BUTTON_PREFERRED_BIG_SIZE = new Dimension(200, 30);

    /**
     * The label preferred size.
     */
    private final static Dimension LABEL_PREFERRED_SIZE = new Dimension(100, 35);

    /**
     * The orientation panel size.
     */
    private final static Dimension ORIENTATION_PANEL_SIZE = new Dimension(250, 600);

    /**
     * The main panel size.
     */
    private final static Dimension MAIN_PANEL_SIZE = new Dimension(750, 600);

    /**
     * The buttons panel size.
     */
    private final static Dimension BUTTONS_PANEL_SIZE = new Dimension(750, 75);

    /**
     * The plain label font.
     */
    private final static Font PLAIN_LABEL_FONT = new Font("Helvetica", Font.PLAIN, 17);

    /**
     * The bold label font.
     */
    private final static Font BOLD_LABEL_FONT = new Font("Helvetica", Font.BOLD, 17);

    /**
     * The color for the dialog panels.
     */
    private final static Color DEFAULT_COLOR = new Color(220, 220, 220);

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Create Flight Info";

    /**
     * Creates an instance of the flight info dialog.
     *
     * @param parentWindow the parent window
     * @param simulator the simulator
     */
    public FlightInfoDialog(T parentWindow, FlightSimulator simulator, Project project) {
        super(parentWindow, WINDOW_TITLE);
        setModal(true);
        this.parentWindow = parentWindow;
        this.project = project;

        createComponents();

        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocationRelativeTo(parentWindow);
    }

    /**
     * Creates the visual components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(createOrientationPanel(), BorderLayout.WEST);
        componentsPanel.add(createMainPanel(), BorderLayout.EAST);

        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates the orientation panel.
     *
     * @return orientation panel
     */
    private Component createOrientationPanel() {
        JPanel orientationPanel = new JPanel(new BorderLayout());
        orientationPanel.setPreferredSize(ORIENTATION_PANEL_SIZE);
        orientationPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        orientationPanel.setBackground(DEFAULT_COLOR);
        orientationPanel.add(createLabelsPanel(), BorderLayout.NORTH);

        return orientationPanel;
    }

    /**
     * Creates the labels panel
     *
     * @return the labels panel
     */
    private Component createLabelsPanel() {
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setBackground(DEFAULT_COLOR);
        labelsPanel.setBorder(PADDING_BORDER);

        labelsPanel.add(createSelectAircraftLabel());
        labelsPanel.add(createClassLabel());
        labelsPanel.add(createAddFlightInfoLabel());
        labelsPanel.add(createNameLabel());

        return labelsPanel;
    }

    /**
     * Creates the select aircraft label.
     *
     * @return the select aircraft label
     */
    private Component createSelectAircraftLabel() {
        selectAircraftLabel = new JLabel("Select Aircraft");
        selectAircraftLabel.setPreferredSize(LABEL_PREFERRED_SIZE);
        selectAircraftLabel.setFont(BOLD_LABEL_FONT);
        selectAircraftLabel.setBorder(PADDING_BORDER);
        return selectAircraftLabel;
    }

    /**
     * Creates the class label.
     *
     * @return class label
     */
    private Component createClassLabel() {
        classLabel = new JLabel("Set Aircraft Classes");
        classLabel.setPreferredSize(LABEL_PREFERRED_SIZE);
        classLabel.setFont(PLAIN_LABEL_FONT);
        classLabel.setBorder(PADDING_BORDER);
        return classLabel;
    }

    /**
     * Creates the add flight info label.
     *
     * @return the add flight info label
     */
    private Component createAddFlightInfoLabel() {
        addFlightInfoLabel = new JLabel("Add Flight Info");
        addFlightInfoLabel.setPreferredSize(LABEL_PREFERRED_SIZE);
        addFlightInfoLabel.setFont(PLAIN_LABEL_FONT);
        addFlightInfoLabel.setBorder(PADDING_BORDER);
        return addFlightInfoLabel;
    }

    /**
     * Creates the name label.
     *
     * @return the name label
     */
    private Component createNameLabel() {
        nameLabel = new JLabel("Set Name");
        nameLabel.setPreferredSize(LABEL_PREFERRED_SIZE);
        nameLabel.setFont(PLAIN_LABEL_FONT);
        nameLabel.setBorder(PADDING_BORDER);
        return nameLabel;
    }

    /**
     * Creates the main panel.
     *
     * @return the main panel
     */
    private Component createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setPreferredSize(MAIN_PANEL_SIZE);
        mainPanel.add(createFormsPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        return mainPanel;
    }

    /**
     * Creates the forms panel.
     *
     * @return the forms panel
     */
    private Component createFormsPanel() {
        cl = new CardLayout();
        formsPanel = new JPanel();
        formsPanel.setLayout(cl);

        formsPanel.add(createSelectAircraftForm(), "1");
        formsPanel.add(createClassForm(), "2");
        formsPanel.add(createAddFlightInfoForm(), "3");
        formsPanel.add(createNameForm(), "4");

        return formsPanel;
    }

    private Component createSelectAircraftForm() {
        JPanel selectAircraftForm = new JPanel();
        selectAircraftForm.setLayout(new GridLayout(10, 1, 0, 0));
        selectAircraftForm.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        selectAircraftForm.setBackground(DEFAULT_COLOR);

        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        panel1.setBackground(DEFAULT_COLOR);
        panel2.setBackground(DEFAULT_COLOR);

        JLabel selectAircraftModelLabel = new JLabel("Select the Aircraft Model:");
        JComboBox<AircraftModel> aircraftModelComboBox = new JComboBox<>();
        aircraftModelComboBox.setPreferredSize(new Dimension(150, 25));
        for (AircraftModel am : project.getAircraftModelsRegister().getAircraftModels()) {
            aircraftModelComboBox.addItem(am);
        }

        aircraftModelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aircraftModel = (AircraftModel) aircraftModelComboBox.getSelectedItem();
            }
        });

        JLabel numClassesLabel = new JLabel("Number of classes in the aircraft:");
        txtNumClasses = new JTextField();
        txtNumClasses.setPreferredSize(new Dimension(25, 25));

        panel1.add(selectAircraftModelLabel);
        panel1.add(aircraftModelComboBox);

        panel2.add(numClassesLabel);
        panel2.add(txtNumClasses);

        selectAircraftForm.add(panel1);
        selectAircraftForm.add(panel2);

        return selectAircraftForm;
    }

    private Component createClassForm() {
        JPanel classForm = new JPanel();
        classForm.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        classForm.setBackground(DEFAULT_COLOR);
        return classForm;
    }

    private Component createAddFlightInfoForm() {
        JPanel addFlightInfo = new JPanel();
        addFlightInfo.setLayout(new GridLayout(5, 1, 0, 0));
        addFlightInfo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        addFlightInfo.setBackground(DEFAULT_COLOR);

        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        panel1.setBackground(DEFAULT_COLOR);
        panel2.setBackground(DEFAULT_COLOR);
        panel3.setBackground(DEFAULT_COLOR);
        panel4.setBackground(DEFAULT_COLOR);
        panel5.setBackground(DEFAULT_COLOR);

        JLabel climbRateLabel = new JLabel("Climb Rate:");
        txtClimbRate = new JTextField();
        txtClimbRate.setPreferredSize(new Dimension(25, 25));
        panel1.add(climbRateLabel);
        panel1.add(txtClimbRate);

        JLabel originAirportLabel = new JLabel("Origin Airport:");
        JComboBox<Airport> originAirportComboBox = new JComboBox<>();
        originAirportComboBox.setPreferredSize(new Dimension(150, 25));

        for (Airport airport : project.getAirportsRegister().getAirports()) {
            originAirportComboBox.addItem(airport);
        }
        originAirportComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                originAirport = (Airport) originAirportComboBox.getSelectedItem();
            }
        });
        panel2.add(originAirportLabel);
        panel2.add(originAirportComboBox);

        JLabel destinationAirportLabel = new JLabel("Destination Airport:");
        JComboBox<Airport> destinationAirportComboBox = new JComboBox<>();
        destinationAirportComboBox.setPreferredSize(new Dimension(150, 25));

        for (Airport airport : project.getAirportsRegister().getAirports()) {
            destinationAirportComboBox.addItem(airport);
        }
        destinationAirportComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                destinationAirport = (Airport) destinationAirportComboBox.getSelectedItem();
            }
        });
        panel3.add(destinationAirportLabel);
        panel3.add(destinationAirportComboBox);

        panel4.add(createAddTechStopButton());
        panel5.add(createAddWaypointButton());

        addFlightInfo.add(panel1);
        addFlightInfo.add(panel2);
        addFlightInfo.add(panel3);
        addFlightInfo.add(panel4);
        addFlightInfo.add(panel5);

        return addFlightInfo;
    }

    private JButton createAddTechStopButton() {
        JButton addTechStopButton = new JButton("Add Technical Stop");
        addTechStopButton.setPreferredSize(BUTTON_PREFERRED_BIG_SIZE);

        addTechStopButton.addActionListener((ActionEvent ae) -> {
            //TODO
        });
        return addTechStopButton;
    }

    private JButton createAddWaypointButton() {
        JButton addWaypointButton = new JButton("Add Waypoint");
        addWaypointButton.setPreferredSize(BUTTON_PREFERRED_BIG_SIZE);

        addWaypointButton.addActionListener((ActionEvent ae) -> {
            //TODO
        });
        return addWaypointButton;
    }

    private Component createNameForm() {
        JPanel nameForm = new JPanel();
        nameForm.setLayout(new GridLayout(1, 1));
        nameForm.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        nameForm.setBackground(DEFAULT_COLOR);

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setBackground(DEFAULT_COLOR);

        JLabel insertNameLabel = new JLabel("Flight Info Name:");
        txtFlightInfoName = new JTextField();
        txtFlightInfoName.setPreferredSize(new Dimension(300, 25));
        panel1.add(insertNameLabel);
        panel1.add(txtFlightInfoName);

        nameForm.add(panel1);

        return nameForm;
    }

    private Component createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.setPreferredSize(BUTTONS_PANEL_SIZE);
        buttonsPanel.setBackground(DEFAULT_COLOR);
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        buttonsPanel.add(createPreviousButton());
        buttonsPanel.add(createNextButton());
        buttonsPanel.add(createFinishButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    private Component createPreviousButton() {
        previousButton = new JButton("Previous");
        previousButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        if (currentForm == 1) {
            previousButton.setEnabled(false);
        }
        previousButton.addActionListener((ActionEvent ae) -> {
            cl.previous(formsPanel);
            currentForm--;
            switch (currentForm) {
                case 1:
                    previousButton.setEnabled(false);
                    classLabel.setFont(PLAIN_LABEL_FONT);
                    selectAircraftLabel.setFont(BOLD_LABEL_FONT);
                    break;
                case 2:
                    classLabel.setFont(BOLD_LABEL_FONT);
                    addFlightInfoLabel.setFont(PLAIN_LABEL_FONT);
                    break;
                case 3:
                    nextButton.setEnabled(true);
                    addFlightInfoLabel.setFont(BOLD_LABEL_FONT);
                    nameLabel.setFont(PLAIN_LABEL_FONT);
                    break;
            }
        });
        return previousButton;
    }

    private Component createNextButton() {
        nextButton = new JButton("Next");
        nextButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        nextButton.addActionListener((ActionEvent ae) -> {
            cl.next(formsPanel);
            currentForm++;
            switch (currentForm) {
                case 2:
                    previousButton.setEnabled(true);
                    selectAircraftLabel.setFont(PLAIN_LABEL_FONT);
                    classLabel.setFont(BOLD_LABEL_FONT);
                    break;
                case 3:
                    classLabel.setFont(PLAIN_LABEL_FONT);
                    addFlightInfoLabel.setFont(BOLD_LABEL_FONT);
                    break;
                case 4:
                    nextButton.setEnabled(false);
                    addFlightInfoLabel.setFont(PLAIN_LABEL_FONT);
                    nameLabel.setFont(BOLD_LABEL_FONT);
                    break;
            }
        });
        return nextButton;
    }

    private Component createFinishButton() {
        JButton finishButton = new JButton("Finish");
        finishButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        finishButton.addActionListener((ActionEvent ae) -> {
            // TODO
        });
        return finishButton;
    }

    private Component createCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        cancelButton.addActionListener((ActionEvent ae) -> {
            //TODO
        });
        return cancelButton;
    }

}
