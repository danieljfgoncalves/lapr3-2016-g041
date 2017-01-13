/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lapr.project.controller.ImportFlightPatternController;
import lapr.project.model.FlightPattern;
import lapr.project.ui.components.ImportFileChooser;

/**
 * Import Flight Pattern UI.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ImportFlightPatternUI extends ImportFileChooser {

    private FlightPattern flightPattern;

    /**
     * Creates an instance of the custom file chooser.
     *
     */
    public ImportFlightPatternUI() {
    }

    @Override
    public void approveSelection() {
        super.approveSelection();

        // Create import controller
        ImportFlightPatternController controller = new ImportFlightPatternController();

        try {
            // Import selected File
            flightPattern = controller.importFlightPattern(getSelectedFile());
            // If no critical error
            JOptionPane.showMessageDialog(this.getParent(),
                    "The flight pattern was successfully loaded!",
                    "Import Successful",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    String.format("Invalid File!%nPlease select a different file.%n(Error:%s)", ex.getMessage()),
                    "Invalid file",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void setSettings() {
        // Select only files
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Add importable file types (e.g. xml, ...)
        FileNameExtensionFilter csvFiler = new FileNameExtensionFilter("CSV Files", "csv");
        addChoosableFileFilter(csvFiler);
        /* ***** add more if needed ***** */
        // Set Default Filter
        setFileFilter(csvFiler);
        // Set the import button
        setApproveButtonText("Import");
        // Set the mnemonic
        setApproveButtonMnemonic('i');
        // Show Open Dialog
        showOpenDialog(null);
    }

    /**
     * Gets the imported flight pattern.
     * 
     * @return flight pattern
     */
    public FlightPattern getFlightPattern() {
        return flightPattern;
    }
}
