/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import lapr.project.model.FlightInfo;
import lapr.project.model.flightplan.FlightPlan;
import lapr.project.model.flightplan.algorithms.ShortestDistance;
import lapr.project.ui.components.ListModelFlightPlanAlgorithm;
import lapr.project.ui.components.TableModelFlightInfo;

/**
 * Dialog to simulate a flight info
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class SimulateFlightDialog extends JDialog {

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "New Flight Simulation";

    /**
     * The current active form.
     */
    private int currentForm = 1;

    /**
     * The layout for the forms.
     */
    private CardLayout cardLayout;

    /**
     * The forms panel.
     */
    private JPanel formsPanel;

    /**
     * The next button.
     */
    private JButton nextButton;

    /**
     * The previous button.
     */
    private JButton previousButton;

    /**
     * The select flight info label.
     */
    private JLabel selectFLightInfoLabel;

    /**
     * The fields label.
     */
    private JLabel fieldsLabel;

    /**
     * The select algorithm label.
     */
    private JLabel selectAlgorithmLabel;

    /**
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The orientation panel size.
     */
    private final static Dimension ORIENTATION_PANEL_SIZE = new Dimension(225, 380);

    /**
     * The main panel size.
     */
    private final static Dimension MAIN_PANEL_SIZE = new Dimension(600, 380);

    /**
     * The buttons panel size.
     */
    private final static Dimension BUTTONS_PANEL_SIZE = new Dimension(600, 50);

    /**
     * The button preferred size.
     */
    private final static Dimension BUTTON_PREFERRED_SIZE = new Dimension(100, 30);

    /**
     * The label preferred size.
     */
    private final static Dimension LABEL_PREFERRED_SIZE = new Dimension(100, 35);

    /**
     * The color for the dialog panels.
     */
    private final static Color DEFAULT_COLOR = new Color(220, 220, 220);

    /**
     * The default grey line border for the panels.
     */
    private final static Border DEFAULT_GREY_LINE_BORDER = BorderFactory.createLineBorder(Color.DARK_GRAY);

    /**
     * The plain label font.
     */
    private final static Font PLAIN_LABEL_FONT = new Font("Helvetica", Font.PLAIN, 15);

    /**
     * The bold label font.
     */
    private final static Font BOLD_LABEL_FONT = new Font("Helvetica", Font.BOLD, 15);

    /**
     * The plain label font.
     */
    private final static Font FORM_LABEL_FONT = new Font("Arial", Font.BOLD, 13);

    /**
     * Creates an instance of simulate flight dialog.
     *
     * @param parentWindow the parent window
     */
    public SimulateFlightDialog(Window parentWindow) {
        super(parentWindow, WINDOW_TITLE);
        setModal(true);

        createComponents();

        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setLocationRelativeTo(parentWindow);
    }

    /**
     * Creates the UI components.
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
    private JPanel createOrientationPanel() {
        JPanel orientationPanel = new JPanel(new BorderLayout());
        orientationPanel.setPreferredSize(ORIENTATION_PANEL_SIZE);
        orientationPanel.setBorder(DEFAULT_GREY_LINE_BORDER);
        orientationPanel.setBackground(DEFAULT_COLOR);
        orientationPanel.add(createLabelsPanel(), BorderLayout.NORTH);

        return orientationPanel;
    }

    /**
     * Creates the labels panel.
     *
     * @return labels panel
     */
    private JPanel createLabelsPanel() {
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.setBackground(DEFAULT_COLOR);
        labelsPanel.setBorder(PADDING_BORDER);

        labelsPanel.add(createSelectFlightInfoLabel());
        labelsPanel.add(createFieldsLabel());
        labelsPanel.add(createSelectAlgorithmLabel());

        return labelsPanel;
    }

    /**
     * Creates select flight info label.
     *
     * @return flight info label
     */
    private JLabel createSelectFlightInfoLabel() {
        selectFLightInfoLabel = new JLabel("Flight Info");
        selectFLightInfoLabel.setPreferredSize(LABEL_PREFERRED_SIZE);
        selectFLightInfoLabel.setFont(BOLD_LABEL_FONT);
        selectFLightInfoLabel.setBorder(PADDING_BORDER);
        return selectFLightInfoLabel;
    }

    /**
     * Creates fields label.
     *
     * @return fields label
     */
    private JLabel createFieldsLabel() {
        fieldsLabel = new JLabel("Flight Values");
        fieldsLabel.setPreferredSize(LABEL_PREFERRED_SIZE);
        fieldsLabel.setFont(PLAIN_LABEL_FONT);
        fieldsLabel.setBorder(PADDING_BORDER);
        return fieldsLabel;
    }

    /**
     * Creates select algorithm label.
     *
     * @return select algorithm label
     */
    private JLabel createSelectAlgorithmLabel() {
        selectAlgorithmLabel = new JLabel("Flight Algorithm");
        selectAlgorithmLabel.setPreferredSize(LABEL_PREFERRED_SIZE);
        selectAlgorithmLabel.setFont(PLAIN_LABEL_FONT);
        selectAlgorithmLabel.setBorder(PADDING_BORDER);
        return selectAlgorithmLabel;
    }

    /**
     * Creates the main panel.
     *
     * @return main panel
     */
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setPreferredSize(MAIN_PANEL_SIZE);
        mainPanel.add(createFormsPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
        return mainPanel;
    }

    /**
     * Creates the forms panel.
     *
     * @return forms panel
     */
    private JPanel createFormsPanel() {
        cardLayout = new CardLayout();
        formsPanel = new JPanel(cardLayout);

        formsPanel.add(createSelectFlightInfoPanel(), "1");
        formsPanel.add(createFieldsPanel(), "2");
        formsPanel.add(createSelectAlgorithmPanel(), "3");

        return formsPanel;
    }

    /**
     * Creates the buttons panel.
     *
     * @return buttons panel
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.setPreferredSize(BUTTONS_PANEL_SIZE);
        buttonsPanel.setBackground(DEFAULT_COLOR);
        buttonsPanel.setBorder(DEFAULT_GREY_LINE_BORDER);

        buttonsPanel.add(createPreviousButton());
        buttonsPanel.add(createNextButton());
        buttonsPanel.add(createFinishButton());
        buttonsPanel.add(createCancelButton());

        return buttonsPanel;
    }

    /**
     * Creates the flight info panel.
     *
     * @return flight info panel
     */
    private JPanel createSelectFlightInfoPanel() {
        JPanel selectFlightInfoPanel = new JPanel(new BorderLayout(10, 10));
        selectFlightInfoPanel.setBackground(DEFAULT_COLOR);
        selectFlightInfoPanel.setBorder(BorderFactory.createCompoundBorder(DEFAULT_GREY_LINE_BORDER, PADDING_BORDER));

        JLabel selectFlightInfoLabel = new JLabel("Select the flight info:", SwingConstants.CENTER);
        selectFlightInfoLabel.setFont(FORM_LABEL_FONT);

        selectFlightInfoPanel.add(selectFlightInfoLabel, BorderLayout.NORTH);
        selectFlightInfoPanel.add(createFlightInfoTablePanel(), BorderLayout.CENTER);

        return selectFlightInfoPanel;
    }

    /**
     * Creates the flight info table.
     *
     * @return flight info table
     */
    private JPanel createFlightInfoTablePanel() {
        JPanel flightInfoTablePanel = new JPanel(new BorderLayout());
        flightInfoTablePanel.setBackground(DEFAULT_COLOR);

        // TODO remove this mock object
        ArrayList<FlightInfo> flights = new ArrayList<>();
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());
        flights.add(new FlightInfo());

        JTable flightInfoTable = new JTable(new TableModelFlightInfo(flights));
        flightInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(flightInfoTable);
        scrollPane.setBorder(PADDING_BORDER);
        scrollPane.setBackground(DEFAULT_COLOR);

        flightInfoTablePanel.add(scrollPane);

        return flightInfoTablePanel;
    }

    private JPanel createFieldsPanel() {
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setBackground(DEFAULT_COLOR);
        fieldsPanel.setBorder(DEFAULT_GREY_LINE_BORDER);

        JPanel fieldsWrapperLayout = new JPanel(new FlowLayout(FlowLayout.CENTER));
        GroupLayout groupLayout = new GroupLayout(fieldsWrapperLayout);
        fieldsWrapperLayout.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        fieldsWrapperLayout.setBackground(DEFAULT_COLOR);

        // Labels
        JLabel departueDateLabel = new JLabel("Departure Date:");
        departueDateLabel.setFont(FORM_LABEL_FONT);
        JLabel scheduledArrivalLabel = new JLabel("Scheduled Arrival Date:");
        scheduledArrivalLabel.setFont(FORM_LABEL_FONT);
        JLabel crewElementsLabel = new JLabel("Number of crew elements:");
        crewElementsLabel.setFont(FORM_LABEL_FONT);
        JLabel effectiveCargoLabel = new JLabel("Effective cargo weight:");
        effectiveCargoLabel.setFont(FORM_LABEL_FONT);
        JLabel effectiveFuelLoadLabel = new JLabel("Effective fuel load:");
        effectiveFuelLoadLabel.setFont(FORM_LABEL_FONT);
        JLabel class1MembersLabel = new JLabel("Number of elements in class 1:");
        class1MembersLabel.setFont(FORM_LABEL_FONT);
        JLabel class2MembersLabel = new JLabel("Number of elements in class 2:");
        class2MembersLabel.setFont(FORM_LABEL_FONT);
        JLabel class3MembersLabel = new JLabel("Number of elements in class 3:");
        class3MembersLabel.setFont(FORM_LABEL_FONT);
        JLabel class4MembersLabel = new JLabel("Number of elements in class 4:");
        class4MembersLabel.setFont(FORM_LABEL_FONT);
        JLabel class5MembersLabel = new JLabel("Number of elements in class 5:");
        class5MembersLabel.setFont(FORM_LABEL_FONT);

        Locale locale = new Locale("en");
        DatePickerSettings departureDateSettings = new DatePickerSettings(locale);
        TimePickerSettings departureTimeSettings = new TimePickerSettings(locale);
        DatePickerSettings arrivalDateSettings = new DatePickerSettings(locale);
        TimePickerSettings arrivalTimeSettings = new TimePickerSettings(locale);

        final Dimension LOCAL_TEXT_FIELD_DIMENSION = new Dimension(60, 23);

        // Inputs
        DateTimePicker departureDateTimePicker = new DateTimePicker(departureDateSettings, departureTimeSettings);
        DateTimePicker arrivalDateTimePicker = new DateTimePicker(arrivalDateSettings, arrivalTimeSettings);
        JTextField crewElementsTextField = new JTextField(10);
        crewElementsTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);
        JTextField effectiveCargoTextField = new JTextField(10);
        effectiveCargoTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);
        JTextField effectiveFuelLoadTextField = new JTextField(10);
        effectiveFuelLoadTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);
        JTextField class1MembersTextField = new JTextField(10);
        class1MembersTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);
        JTextField class2MembersTextField = new JTextField(10);
        class2MembersTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);
        JTextField class3MembersTextField = new JTextField(10);
        class3MembersTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);
        JTextField class4MembersTextField = new JTextField(10);
        class4MembersTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);
        JTextField class5MembersTextField = new JTextField(10);
        class5MembersTextField.setPreferredSize(LOCAL_TEXT_FIELD_DIMENSION);

        //align horizontally
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(departueDateLabel)
                        .addComponent(scheduledArrivalLabel)
                        .addComponent(crewElementsLabel)
                        .addComponent(effectiveCargoLabel)
                        .addComponent(effectiveFuelLoadLabel)
                        .addComponent(class1MembersLabel)
                        .addComponent(class2MembersLabel)
                        .addComponent(class3MembersLabel)
                        .addComponent(class4MembersLabel)
                        .addComponent(class5MembersLabel)
                )
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(departureDateTimePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(arrivalDateTimePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(crewElementsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(effectiveCargoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(effectiveFuelLoadTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(class1MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(class2MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(class3MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(class4MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(class5MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                )
        );

        //align vertically
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(departueDateLabel)
                        .addComponent(departureDateTimePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(scheduledArrivalLabel)
                        .addComponent(arrivalDateTimePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(crewElementsLabel)
                        .addComponent(crewElementsTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(effectiveCargoLabel)
                        .addComponent(effectiveCargoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(effectiveFuelLoadLabel)
                        .addComponent(effectiveFuelLoadTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(class1MembersLabel)
                        .addComponent(class1MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(class2MembersLabel)
                        .addComponent(class2MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(class3MembersLabel)
                        .addComponent(class3MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(class4MembersLabel)
                        .addComponent(class4MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(class5MembersLabel)
                        .addComponent(class5MembersTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                )
        );

        fieldsPanel.add(fieldsWrapperLayout);

        return fieldsPanel;
    }

    /**
     * Creates the algorigthm selection panel.
     *
     * @return algorigthm selection panel
     */
    private JPanel createSelectAlgorithmPanel() {
        JPanel selectAlgorithmPanel = new JPanel(new BorderLayout(10, 10));
        selectAlgorithmPanel.setBackground(DEFAULT_COLOR);
        selectAlgorithmPanel.setBorder(DEFAULT_GREY_LINE_BORDER);

        JLabel algorithmSelectionLabel = new JLabel("Select the pretended path algorithm:", SwingConstants.CENTER);
        algorithmSelectionLabel.setFont(FORM_LABEL_FONT);

        // TODO remove this mock object
        ArrayList<FlightPlan> flightPlanAlgorithms = new ArrayList<>();
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());
        flightPlanAlgorithms.add(new ShortestDistance());

        JList algorithmList = new JList(new ListModelFlightPlanAlgorithm(flightPlanAlgorithms));
        algorithmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(algorithmList);
        scrollPane.setBorder(PADDING_BORDER);
        scrollPane.setBackground(DEFAULT_COLOR);

        selectAlgorithmPanel.add(algorithmSelectionLabel, BorderLayout.NORTH);
        selectAlgorithmPanel.add(scrollPane, BorderLayout.CENTER);

        return selectAlgorithmPanel;
    }

    /**
     * Creates the previous button.
     *
     * @return the previous button
     */
    private JButton createPreviousButton() {
        previousButton = new JButton("Previous");
        previousButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        if (currentForm == 1) {
            previousButton.setEnabled(false);
        }
        previousButton.addActionListener((ActionEvent ae) -> {
            cardLayout.previous(formsPanel);
            currentForm--;
            switch (currentForm) {
                case 1:
                    previousButton.setEnabled(false);
                    selectFLightInfoLabel.setFont(BOLD_LABEL_FONT);
                    fieldsLabel.setFont(PLAIN_LABEL_FONT);
                    break;
                case 2:
                    nextButton.setEnabled(true);
                    fieldsLabel.setFont(BOLD_LABEL_FONT);
                    selectAlgorithmLabel.setFont(PLAIN_LABEL_FONT);
                    break;
            }
        });
        return previousButton;
    }

    /**
     * Creates the next button.
     *
     * @return the next button
     */
    private JButton createNextButton() {
        nextButton = new JButton("Next");
        nextButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        nextButton.addActionListener((ActionEvent ae) -> {
            cardLayout.next(formsPanel);
            currentForm++;
            switch (currentForm) {
                case 2:
                    previousButton.setEnabled(true);
                    selectFLightInfoLabel.setFont(PLAIN_LABEL_FONT);
                    fieldsLabel.setFont(BOLD_LABEL_FONT);
                    break;
                case 3:
                    nextButton.setEnabled(false);
                    fieldsLabel.setFont(PLAIN_LABEL_FONT);
                    selectAlgorithmLabel.setFont(BOLD_LABEL_FONT);
                    break;
            }
        }
        );
        return nextButton;
    }

    /**
     * Creates the finish button.
     *
     * @return the finish button
     */
    private JButton createFinishButton() {
        JButton finishButton = new JButton("Finish");
        finishButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        finishButton.addActionListener((ActionEvent ae) -> {
            // TODO
        });
        return finishButton;
    }

    /**
     * Creates the cancel button.
     *
     * @return the cancel button
     */
    private JButton createCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(BUTTON_PREFERRED_SIZE);

        cancelButton.addActionListener((ActionEvent ae) -> {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the operation?", "Cancel Simulation", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        return cancelButton;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setVisible(true);

        SimulateFlightDialog d = new SimulateFlightDialog(f);
        d.setVisible(true);
    }
}
