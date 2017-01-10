/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import lapr.project.model.FlightSimulation;

/**
 * Table model for flights.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class TableModelFlightSimulation extends AbstractTableModel {

    /**
     * Name of the table columns.
     */
    private static final String[] COLUMNS_NAMES = {"Designator", "Flight Type",
        "Aircraft Model", "Origin airport", "Destination Airport",
        "Departure Date", "Scheduled Arrival"};

    /**
     * The list of flights.
     */
    private final List<FlightSimulation> flightSimulations;

    /**
     * Constructs an instance of table model flight.
     *
     * @param flightSimulations the flight simulations
     */
    public TableModelFlightSimulation(List<FlightSimulation> flightSimulations) {
        this.flightSimulations = flightSimulations;
    }

    @Override
    public int getRowCount() {
        return flightSimulations.size();
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
                return flightSimulations.get(rowIndex).getFlightInfo()
                        .getDesignator();
            case 1:
                return flightSimulations.get(rowIndex).getFlightInfo()
                        .getFlightType();
            case 2:
                return flightSimulations.get(rowIndex).getFlightInfo()
                        .getAircraft().getAircraftModel().getModelID();
            case 3:
                return flightSimulations.get(rowIndex).getFlightInfo()
                        .getOriginAirport().getIATA();
            case 4:
                return flightSimulations.get(rowIndex).getFlightInfo()
                        .getDestinationAirport().getIATA();
            case 5:
                return flightSimulations.get(rowIndex).getDepartureDate()
                        .getTime();
            case 6:
                return flightSimulations.get(rowIndex).getScheduledArrival()
                        .getTime();
            default:
                return null;
        }
    }

}
