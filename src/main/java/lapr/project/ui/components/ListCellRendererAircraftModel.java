/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import lapr.project.model.AircraftModel;

/**
 * List cell renderer for the Aircraft Model.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ListCellRendererAircraftModel extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            AircraftModel aircraftModel = (AircraftModel) value;
            value = String.format("%s %s", aircraftModel.getMaker(), aircraftModel.getModelID());
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

}