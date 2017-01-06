/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.FlightInfo;

/**
 * Table model for flights.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class TableModelFlight extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {"Designator", "Flight Type",
        "Aircraft", "Origin airport", "Destination Airport", "Scheduled Arrival"};

    /**
     * The list of flights.
     */
    private final List<FlightInfo> flights;

    /**
     * Constructs an instance of table model flight.
     *
     * @param flights the list of flights
     */
    public TableModelFlight(List<FlightInfo> flights) {
        this.flights = flights;
    }

    @Override
    public int getRowCount() {
        return flights.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_NAMES.length;
    }

    @Override
    public String getColumnName(int i) {
        return COLUMNS_NAMES[i];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return flights.get(rowIndex).getDesignator();
            case 1:
                return flights.get(rowIndex).getFlightType();
            case 2:
                return flights.get(rowIndex).getAircraft();
            case 3:
                return flights.get(rowIndex).getOriginAirport();
            case 4:
                return flights.get(rowIndex).getDestinationAirport();
            default:
                return null;
        }
    }

}
