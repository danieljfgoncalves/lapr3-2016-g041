/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.controller.ImportAirNetworkController;
import lapr.project.model.Project;
import org.xml.sax.SAXException;

/**
 * Custom import file chooser.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class ImportAirNetworkUI extends JFileChooser {

    /**
     * Selected Project.
     */
    private Project selectedProject;

    /**
     * Creates an instance of the custom file chooser.
     *
     * @param project
     */
    public ImportAirNetworkUI(Project project) {

        this.selectedProject = project;
    }

    /**
     * Sets the custom settings.
     */
    public void setSettings() {

        // Select only files
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Add importable file types (e.g. xml, ...)
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files", "xml");
        addChoosableFileFilter(xmlFilter);
        setFileFilter(xmlFilter);
        // Set the import button
        setApproveButtonText("Import");
        // Set the mnemonic
        setApproveButtonMnemonic('i');
        // Show Open Dialog
        showOpenDialog(null);
    }

    @Override
    public void approveSelection() {
        super.approveSelection();

        // Create import controller
        ImportAirNetworkController controller = new ImportAirNetworkController(selectedProject);

        try {
            // Import selected File
            controller.importAirNetwork(getSelectedFile());

        } catch (SAXException | IOException | NumberFormatException | ParserConfigurationException ex) {

            JOptionPane.showMessageDialog(this,
                    "Invalid File!.%nPlease select a different file",
                    "Invalid file",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
