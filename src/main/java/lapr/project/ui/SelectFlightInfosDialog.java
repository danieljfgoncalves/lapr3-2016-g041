/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import lapr.project.model.FlightInfo;
import lapr.project.ui.components.TableModelFlightInfo;
import lapr.project.utils.exportable.FlightInfoHTML;

/**
 * The frame select flight infos to export.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class SelectFlightInfosDialog extends JDialog {

    /**
     * The flight simulations
     */
    private final List<FlightInfo> flightInfoList;

    /**
     * The simulations table.
     */
    private JTable flightInfosTable;

    /**
     * The export simulations button.
     */
    private JButton exportButton;

    /**
     * The parent window.
     */
    private final Window parentWindow;

    /**
     * Title for the frame.
     */
    private static final String WINDOW_TITLE = "Select Flight Infos";

    /**
     * The button prefered size.
     */
    private static final Dimension BUTTON_PREFERED_SIZE = new Dimension(150, 30);

    /**
     * Padding border.
     */
    private final static EmptyBorder PADDING_BORDER = new EmptyBorder(10, 10, 10, 10);

    /**
     * The bold label font.
     */
    private final static Font BOLD_LABEL_FONT = new Font("Helvetica", Font.BOLD, 15);

    /**
     * Creates an instance of a SelectFlightInfosDialog.
     *
     * @param parentWindow
     * @param flightInfoList
     */
    public SelectFlightInfosDialog(Window parentWindow, List<FlightInfo> flightInfoList) {
        super(parentWindow, WINDOW_TITLE);
        setModal(true);
        setResizable(false);

        this.flightInfoList = flightInfoList;
        this.parentWindow = parentWindow;

        createComponents();

        pack();
        setLocationRelativeTo(parentWindow);
    }

    /**
     * Creates the UI components.
     */
    private void createComponents() {
        JPanel componentsPanel = new JPanel(new BorderLayout(10, 10));

        componentsPanel.add(createTable(), BorderLayout.NORTH);
        componentsPanel.add(createExportButton(), BorderLayout.CENTER);

        componentsPanel.setBorder(PADDING_BORDER);
        add(componentsPanel);
    }

    /**
     * Creates a JTable with a list of flight infos.
     * 
     * @return the panel with the JTable
     */
    private JPanel createTable() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel tableLabel = new JLabel("Select the flight infos to export:", SwingConstants.CENTER);
        tableLabel.setFont(BOLD_LABEL_FONT);
        flightInfosTable = new JTable(new TableModelFlightInfo(flightInfoList));
        ListSelectionModel listSelectionModel = flightInfosTable.getSelectionModel();
        listSelectionModel.addListSelectionListener((ListSelectionEvent lse) -> {
            exportButton.setEnabled(!listSelectionModel.isSelectionEmpty());
        });

        panel.add(tableLabel, BorderLayout.NORTH);
        panel.add(flightInfosTable, BorderLayout.CENTER);

        return panel;
    }

    /**
     * The panel with the export button.
     * 
     * @return 
     */
    private JPanel createExportButton() {
        JPanel panel = new JPanel();

        exportButton = new JButton("Export");
        exportButton.setEnabled(false);
        exportButton.setPreferredSize(BUTTON_PREFERED_SIZE);
        exportButton.addActionListener((ActionEvent ae) -> {
            List<FlightInfo> selectedFlightInfos = new ArrayList<>();
            int selectedRow[] = flightInfosTable.getSelectedRows();
            for (int i : selectedRow) {
                selectedFlightInfos.add(flightInfoList.get(i));
            }
            JFileChooser fileChooser = new JFileChooser();
            int resposta = fileChooser.showSaveDialog(this);
            if (resposta == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                FlightInfoHTML flightInfoHTML = new FlightInfoHTML(selectedFlightInfos);
                try {
                    flightInfoHTML.export(file);
                    dispose();
                    parentWindow.dispose();
                    JOptionPane.showMessageDialog(this,
                            "The file was successfully exported!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Invalid file.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(FilteredSimulationsUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        panel.add(exportButton);

        return panel;
    }

}
