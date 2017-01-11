/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import javax.swing.JOptionPane;
import lapr.project.controller.ImportFlightPatternController;
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
            controller.importFlightPattern(getSelectedFile());
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

}
