/**
 * Package location for UI classes.
 */
package lapr.project.ui;

import javax.swing.JOptionPane;
import lapr.project.controller.ImportAirNetworkController;
import lapr.project.model.Project;
import lapr.project.ui.components.ImportFileChooser;

/**
 * Import Air Network UI.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ImportAirNetworkUI extends ImportFileChooser {

    /**
     * Selected Project.
     */
    private final Project selectedProject;

    /**
     * If succesfully imported.
     */
    private boolean success = false;

    /**
     * Creates an instance of the custom file chooser.
     *
     * @param project
     */
    public ImportAirNetworkUI(Project project) {

        this.selectedProject = project;
    }

    @Override
    public void approveSelection() {
        super.approveSelection();

        // Create import controller
        ImportAirNetworkController controller = new ImportAirNetworkController(selectedProject);

        try {
            // Import selected File
            if (controller.importAirNetwork(getSelectedFile())) {
                controller.saveToDatabase();
            };
            // If no critical error
            success = true;
            JOptionPane.showMessageDialog(this.getParent(),
                    "The air network was successfully added!",
                    "Import Successful",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    String.format("Invalid File!%nPlease select a different file.%n(Error:%s)", ex.getMessage()),
                    "Invalid file",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isSuccess() {
        return success;
    }
}
