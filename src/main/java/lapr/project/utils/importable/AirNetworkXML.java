/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.importable;

import java.io.File;
import java.util.logging.Logger;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lapr.project.model.AirNetwork;
import lapr.project.model.Coordinate;
import lapr.project.model.Segment;
import lapr.project.utils.Regex;
import org.jscience.physics.amount.Amount;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class responsible to manage import airnetwork from a file.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AirNetworkXML implements Importable {

    private File file;

    /**
     * Constructior of airnetwork import object.
     *
     * @param fileToImport
     */
    public AirNetworkXML(File fileToImport) throws IllegalArgumentException {

        String filename = fileToImport.getName();
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1 || !filename.substring(dotIndex).equalsIgnoreCase(".xml")) {
            throw new IllegalArgumentException("File is not a xml");
        }

        file = fileToImport;
    }

    @Override
    public Object importFile() throws Exception {

        AirNetwork network = new AirNetwork();

        // Set up XML Dom
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document root = dBuilder.parse(file);
        root.getDocumentElement().normalize();

        // Iterate nodes (graph vertices)
        NodeList verticesNL = root.getElementsByTagName("node");

        for (int i = 0; i < verticesNL.getLength(); i++) {

            Node node = verticesNL.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element aElement = (Element) node;

                Coordinate coordinate = new Coordinate();
                // Set ID
                coordinate.setId(aElement.getAttribute("id"));
                // Set lat
                coordinate.setLatitude(Double.parseDouble(aElement.getElementsByTagName("latitude").item(0).getTextContent()));
                // Set lon
                coordinate.setLongitude(Double.parseDouble(aElement.getElementsByTagName("longitude").item(0).getTextContent()));

                if (!network.addJunction(coordinate)) {
                    System.out.printf("Coordinate #%d: already inserted or malformed.%n", i);
                }

            }
        }

        // Iterate segments (graph edges)
        NodeList edgesNL = root.getElementsByTagName("segment");

        for (int i = 0; i < edgesNL.getLength(); i++) {

            Node node = edgesNL.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element aElement = (Element) node;

                Segment segment = new Segment();
                // Set ID
                segment.setId(aElement.getAttribute("id"));
                // Set wind
                Element windElement = (Element) aElement.getElementsByTagName("wind").item(0);
                // Set wind direction
                String windDirection = windElement.getElementsByTagName("wind_direction").item(0).getTextContent();
                Double valueWindD = Regex.getValue(windDirection);
                segment.setWindDirection(Amount.valueOf(valueWindD, NonSI.DEGREE_ANGLE));
                // Set wind intensity
                String windIntensity = windElement.getElementsByTagName("wind_intensity").item(0).getTextContent();
                Double valueWindI = Regex.getValue(windIntensity);
                String unitWindI = Regex.getUnit(windIntensity);
                segment.setWindIntensity(Amount.valueOf(valueWindI, (unitWindI.equalsIgnoreCase("knot")) ? NonSI.KNOT : SI.METERS_PER_SECOND));
                // Get start & end of segment
                String startID = aElement.getElementsByTagName("start_node").item(0).getTextContent();
                String endID = aElement.getElementsByTagName("end_node").item(0).getTextContent();
                // Add uni/bi-directional edges
                String direction = aElement.getElementsByTagName("direction").item(0).getTextContent();

                switch (direction) {
                    case "bidirectional":
                        boolean dir = network.addSegment(startID, endID, segment);
                        boolean inverseDir = network.addSegment(endID, startID, segment);
                        if (!dir || !inverseDir) {
                            Logger.getLogger(String.format("Segment #%d: already inserted or malformed.%n", i));
                        }
                        break;
                    case "direct":
                        if (!network.addSegment(startID, endID, segment)) {
                            Logger.getLogger(String.format("Segment #%d: already inserted or malformed.%n", i));
                        }
                        break;
                    case "reverse":
                        if (!network.addSegment(endID, startID, segment)) {
                            Logger.getLogger(String.format("Segment #%d: already inserted or malformed.%n", i));
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        return network;
    }

}
