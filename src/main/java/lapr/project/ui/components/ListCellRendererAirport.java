/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import lapr.project.model.Airport;

/**
 * List cell renderer for airport.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ListCellRendererAirport extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            Airport airport = (Airport) value;
            value = String.format("%s - %s, %s", airport.getName(), airport.getTown(), airport.getCountry());
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

}
