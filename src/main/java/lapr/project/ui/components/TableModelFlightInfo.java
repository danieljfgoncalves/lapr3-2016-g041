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
 * @author Tiago Correia - 1151031
 */
public class TableModelFlightInfo extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {"Designator", "Type",
        "Model", "Origin", "Destination", "Classes"};

    /**
     * The list of flights.
     */
    private final List<FlightInfo> flights;

    /**
     * Constructs an instance of table model flight.
     *
     * @param flights the list of flights
     */
    public TableModelFlightInfo(List<FlightInfo> flights) {
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
                return flights.get(rowIndex).getAircraft()
                        .getAircraftModel().getModelID();
            case 3:
                return flights.get(rowIndex).getOriginAirport().getIATA();
            case 4:
                return flights.get(rowIndex).getDestinationAirport().getIATA();
            case 5:
                return flights.get(rowIndex).getAircraft()
                        .getMaxPassengerPerClass().size();
            default:
                return null;
        }
    }

}
