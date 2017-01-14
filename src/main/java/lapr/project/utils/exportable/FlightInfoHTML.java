/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.exportable;

import static j2html.TagCreator.body;
import static j2html.TagCreator.document;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.h2;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.li;
import static j2html.TagCreator.p;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.th;
import static j2html.TagCreator.title;
import static j2html.TagCreator.tr;
import static j2html.TagCreator.ul;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import lapr.project.model.FlightInfo;

/**
 *
 * @author Ivo Ferro
 */
public class FlightInfoHTML implements Exportable {

    /**
     * The list of aircaft models.
     */
    private final List<FlightInfo> flightsInfo;

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
        "Max Passengers per Class",
        "Max Crew Members",
        "Max Cargo",
        "Max Fuel"};

    /**
     * Creates an instance of aircraft model html.
     *
     * @param flightsInfo the flights info list
     */
    public FlightInfoHTML(List<FlightInfo> flightsInfo) {
        this.flightsInfo = flightsInfo;
    }

    @Override
    public void export(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.canWrite()) {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();

            StringBuilder html = new StringBuilder(document().renderOpenTag());
            html.append(html().renderOpenTag())
                    .append(head().renderOpenTag())
                    .append(title("Flight Information Results").render())
                    .append(STYLE)
                    .append(head().renderCloseTag())
                    .append(body().renderOpenTag())
                    .append(h1("Flights Information").render())
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

            for (int j = 0; j < flightsInfo.size(); j++) {

                FlightInfo flightInfo = flightsInfo.get(j);
                switch (i) {
                    case 0:
                        strBuilder.append(th(flightInfo.getDesignator()).render());
                        break;
                    case 1:
                        strBuilder.append(td(flightInfo.getAircraft().getAircraftModel().getDescription()));
                        break;
                    case 2:
                        strBuilder.append(td(flightInfo.getOriginAirport().getName()));
                        break;
                    case 3:
                        strBuilder.append(td(flightInfo.getDestinationAirport().getName()));
                        break;
                    case 4:
                        strBuilder.append(td().renderOpenTag()).append(ul().renderOpenTag());
                        List<Integer> passPerClass = flightInfo.getAircraft().getMaxPassengerPerClass();
                        for (int k = 0; k < passPerClass.size(); k++) {
                            strBuilder.append(li(String.format("Class #%d: %d passengers", (k + 1), passPerClass.get(k))).render());
                        }
                        strBuilder.append(ul().renderCloseTag()).append(td().renderCloseTag());
                        break;
                    case 5:
                        strBuilder.append(td(String.valueOf(flightInfo.getAircraft().getMaxCrew() + " members")));
                        break;
                    case 6:
                        double cargo = flightInfo.getAircraft().getMaxCargo().doubleValue(SI.KILOGRAM);
                        strBuilder.append(td(String.format("%.2f KG", cargo)));
                        break;
                    case 7:
                        double liters = flightInfo.getAircraft().getAircraftModel().getMaxFuelCapacity().doubleValue(NonSI.LITER);
                        strBuilder.append(td(String.format("%.2f L", liters)));
                        break;
                }
            }
            strBuilder.append(tr().renderCloseTag());
        }
        strBuilder.append(table().renderCloseTag());
        return strBuilder.toString();
    }

}
