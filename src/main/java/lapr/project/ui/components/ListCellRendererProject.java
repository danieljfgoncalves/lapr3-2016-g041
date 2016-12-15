/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import lapr.project.model.Project;

/**
 * List cell renderer for project.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class ListCellRendererProject extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            Project project = (Project) value;
            value = String.format("%s - %s", project.getName(), project.getDescription());
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

}
