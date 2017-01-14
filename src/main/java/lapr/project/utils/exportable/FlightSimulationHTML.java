/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.exportable;

import java.io.File;
import java.util.List;
import lapr.project.model.FlightSimulation;
import static j2html.TagCreator.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import javax.measure.unit.SI;
import lapr.project.model.Segment;
import lapr.project.utils.Consts;

/**
 * Class responsable to export to an HTML file.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightSimulationHTML implements Exportable {

    /**
     * flights to export
     */
    private final List<FlightSimulation> flights;

    /**
     * Predefined table style.
     */
    private static final String STYLE = "<style>table {font-family: arial, sans-serif;"
            + "border-collapse: collapse;width: 100%;}td, th {border: 1px solid #dddddd;text-align: left;"
            + "padding: 8px;}tr:nth-child(even) {background-color: #dddddd;}</style>";

    private static final String[] HEADERS = {
        "Flight Designator",
        "Aircraft Model",
        "Origin",
        "Destination",
        "# of passengers",
        "# of crew members",
        "cargo",
        "fuel",
        "flight plan"};

    /**
     * Creates a flight export html object
     *
     * @param flights flights to export
     */
    public FlightSimulationHTML(List<FlightSimulation> flights) {

        this.flights = flights;
    }

    @Override
    public void export(File file) throws IOException {

        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.canWrite()) {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance(); //2016/01/11 12:08:43

            StringBuilder html = new StringBuilder(document().renderOpenTag());
            html.append(html().renderOpenTag())
                    .append(head().renderOpenTag())
                    .append(title("Flight Simulation Results").render())
                    .append(STYLE)
                    .append(head().renderCloseTag())
                    .append(body().renderOpenTag())
                    .append(h1("Flight Simulations").render())
                    .append(h2("Side by side comparision").render())
                    .append(buildTable())
                    .append(p(dateFormat.format(cal.getTime())).render())
                    .append(body().renderCloseTag())
                    .append(html().renderCloseTag());

            try (BufferedWriter output = new BufferedWriter(new FileWriter(file))) {
                output.write(html.toString());
            }

        }
    }

    private String buildTable() {

        StringBuilder strBuilder = new StringBuilder(table().renderOpenTag());

        for (int i = 0; i < HEADERS.length; i++) {

            strBuilder.append(tr().renderOpenTag()).append(th(HEADERS[i]).render());

            for (int j = 0; j < flights.size(); j++) {

                FlightSimulation flight = flights.get(j);
                switch (i) {
                    case 0:
                        strBuilder.append(th(flight.getFlightInfo().getDesignator()).render());
                        break;
                    case 1:
                        strBuilder.append(td(flight.getFlightInfo().getAircraft().getAircraftModel().getDescription()));
                        break;
                    case 2:
                        strBuilder.append(td(flight.getFlightInfo().getOriginAirport().getName()));
                        break;
                    case 3:
                        strBuilder.append(td(flight.getFlightInfo().getDestinationAirport().getName()));
                        break;
                    case 4:
                        strBuilder.append(td().renderOpenTag()).append(ul().renderOpenTag());
                        List<Integer> passPerClass = flight.getPassengersPerClass();
                        for (int k = 0; k < passPerClass.size(); k++) {
                            strBuilder.append(li(String.format("Class #%d: %d passengers", (k + 1), passPerClass.get(k))).render());
                        }
                        strBuilder.append(ul().renderCloseTag()).append(td().renderCloseTag());
                        break;
                    case 5:
                        strBuilder.append(td(String.valueOf(flight.getEffectiveCrew() + " members")));
                        break;
                    case 6:
                        double cargo = flight.getEffectiveCargo().doubleValue(SI.KILOGRAM);
                        strBuilder.append(td(String.format("%.2f KG", cargo)));
                        break;
                    case 7:
                        double liters = flight.getEffectiveFuel().doubleValue(SI.KILOGRAM) * Consts.LITER_CONV;
                        strBuilder.append(td(String.format("%.2f L", liters)));
                        break;
                    case 8:
                        LinkedList<Segment> flightplan = new LinkedList<>(flight.getFlightplan());
                        strBuilder.append(td().renderOpenTag()).append(ol().renderOpenTag());
                        while (!flightplan.isEmpty()) {
                            strBuilder.append(li(flightplan.pop().getId()).render());
                        }
                        strBuilder.append(ol().renderCloseTag()).append(td().renderCloseTag());
                        break;
                }
            }
            strBuilder.append(tr().renderCloseTag());
        }
        strBuilder.append(table().renderCloseTag());
        return strBuilder.toString();
    }
}
