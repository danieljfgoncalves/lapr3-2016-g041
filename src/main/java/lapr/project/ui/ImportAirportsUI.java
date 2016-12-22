/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.controller.ImportAirportsController;
import lapr.project.model.Project;
import lapr.project.ui.components.ImportFileChooser;
import org.xml.sax.SAXException;

/**
 * Import Airports UI.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class ImportAirportsUI extends ImportFileChooser {

    /**
     * Selected Project.
     */
    private final Project activeProject;

    /**
     * Creates an instance of the custom file chooser.
     *
     * @param project
     */
    public ImportAirportsUI(Project project) {

        this.activeProject = project;
    }

    @Override
    public void approveSelection() {
        super.approveSelection();

        // Create import controller
        ImportAirportsController controller = new ImportAirportsController(activeProject);

        try {
            // Import selected File
            controller.importFile(getSelectedFile());
            // If no critical error
            JOptionPane.showMessageDialog(this.getParent(),
                    "The airports were successfully added!",
                    "Import Successful",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SAXException | IOException | NumberFormatException | ParserConfigurationException ex) {

            JOptionPane.showMessageDialog(this,
                    String.format("Invalid File!%nPlease select a different file.%n(Error:%s)", ex.getMessage()),
                    "Invalid file",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
