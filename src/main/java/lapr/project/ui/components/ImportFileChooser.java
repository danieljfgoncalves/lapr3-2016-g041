/**
 * Package location for UI classes.
 */
package lapr.project.ui.components;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Custom import file chooser.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ImportFileChooser extends JFileChooser {

    /**
     * Sets the custom settings.
     */
    public void setSettings() {

        // Select only files
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Add importable file types (e.g. xml, ...)
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Files", "xml");
        addChoosableFileFilter(xmlFilter);
        /* ***** add more if needed ***** */
        // Set Default Filter
        setFileFilter(xmlFilter);
        // Set the import button
        setApproveButtonText("Import");
        // Set the mnemonic
        setApproveButtonMnemonic('i');
        // Show Open Dialog
        showOpenDialog(null);
    }
}
