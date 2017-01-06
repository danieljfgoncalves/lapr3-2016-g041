/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.utils.Importable;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Iterator;
import javax.measure.quantity.Length;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import lapr.project.utils.Regex;
import lapr.project.utils.graph.map.MapEdge;
import lapr.project.utils.graph.map.MapGraph;
import org.jscience.physics.amount.Amount;
import org.xml.sax.SAXException;

/**
 * Represents a airnetwork. (contains a graph of coordinates and segments)
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AirNetwork implements Importable {

    /**
     * Air Network
     */
    private MapGraph<Coordinate, Segment> network;

    /**
     * Constructs an empty air network
     */
    public AirNetwork() {

        this.network = new MapGraph<>(true);
    }

    /**
     * Constructs an air network receiving a created netwrok by param.
     *
     * @param network the network to use
     */
    public AirNetwork(MapGraph<Coordinate, Segment> network) {

        this.network = network;
    }

    /**
     * Constructs a air network by coping from another air network
     *
     * @param other the other airnetwrok to copy
     * @throws CloneNotSupportedException
     */
    public AirNetwork(AirNetwork other) throws CloneNotSupportedException {

        this.network = (MapGraph<Coordinate, Segment>) other.network.clone();

    }

    /**
     * Obtains the Air Network
     *
     * @return the network (graph)
     */
    public MapGraph<Coordinate, Segment> getNetwork() {

        return network;
    }

    /**
     * Sets the Air Network
     *
     * @param network the network to set (graph)
     */
    public void setNetwork(MapGraph<Coordinate, Segment> network) {

        this.network = network;
    }

    /**
     * Obtain number of segments
     *
     * @return number of segments
     */
    public int getNumSegments() {

        return this.network.numEdges();
    }

    /**
     * Obtain number of junctions (coordinates)
     *
     * @return number of junctions
     */
    public int getNumJunctions() {

        return this.network.numVertices();
    }

    /**
     * Obtain all segments of network
     *
     * @return all segments
     */
    public Iterable<MapEdge<Coordinate, Segment>> getSegments() {

        return this.network.edges();
    }

    /**
     * Obtain all junctions of network
     *
     * @return all junctions
     */
    public Iterable<Coordinate> getJunctions() {

        return this.network.vertices();
    }

    /**
     * Adds a new segment to the network (from A -> B)
     *
     * @param coordinateA starting coordinate
     * @param coordinateB ending coordinate
     * @param newSegment info of the new segment to add
     * @return true if segments is sucessfully added
     */
    public boolean addSegment(Coordinate coordinateA, Coordinate coordinateB, Segment newSegment) {

        // Calculate distance between coordinates
        Amount<Length> distance = Calculus.distance(coordinateA, coordinateA, newSegment.getAltitude());

        return this.network.insertEdge(coordinateA, coordinateB, newSegment, distance.doubleValue(SI.METER));
    }

    /**
     * Adds a new segment to the network (from A -> B)
     *
     * @param coordIdA starting coordinate id
     * @param coordIdB ending coordinate id
     * @param newSegment info of the new segment to add
     * @return true if segments is sucessfully added
     */
    public boolean addSegment(String coordIdA, String coordIdB, Segment newSegment) {

        // if same abort
        if (network.numVertices() < 2 || coordIdA.equalsIgnoreCase(coordIdB)) {
            return false;
        }

        Iterator<Coordinate> it = network.vertices().iterator();
        Coordinate coordinateA = null;
        Coordinate coordinateB = null;
        // iterate through vertices
        while (it.hasNext() && (coordinateA == null || coordinateB == null)) {

            Coordinate temp = it.next();

            if (temp.getId().equalsIgnoreCase(coordIdA)) {

                coordinateA = temp;

            } else if (temp.getId().equalsIgnoreCase(coordIdB)) {
                coordinateB = temp;
            }
        }
        // if either don't exist abort
        if (coordinateA == null || coordinateB == null) {
            return false;
        }

        // Calculate distance between coordinates
        Amount<Length> distance = Calculus.distance(coordinateA, coordinateA, newSegment.getAltitude());

        return this.network.insertEdge(coordinateA, coordinateB, newSegment, distance.doubleValue(SI.METER));
    }

    /**
     * Removes a new segment to the network (from A -> B)
     *
     * @param coordinateA starting coordinate
     * @param coordinateB ending coordinate
     * @return true if removed
     */
    public boolean removeSegment(Coordinate coordinateA, Coordinate coordinateB) {

        return this.network.removeEdge(coordinateA, coordinateB);
    }

    /**
     * Adds a new junction to the network (Coordinate)
     *
     * @param newJunction the new juntion to add
     * @return true if junction is sucessfully added
     */
    public boolean addJunction(Coordinate newJunction) {

        return this.network.insertVertex(newJunction);
    }

    /**
     * Removes a new junction to the network (Coordinate)
     *
     * @param junction the juntion to remove
     * @return true if junction is sucessfully removed
     */
    public boolean removeJunction(Coordinate junction) {

        return this.network.removeVertex(junction);
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.network);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final AirNetwork other = (AirNetwork) obj;

        return Objects.equals(this.network, other.network);
    }

    @Override
    public String toString() {

        return "AirNetwork{\nnetwork=\n" + network + "\n}";
    }

    @Override
    public boolean importXml(File fileToImport)
            throws SAXException, IOException, ParserConfigurationException,
            NumberFormatException {

        String filename = fileToImport.getName();
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1 || !filename.substring(dotIndex).equalsIgnoreCase(".xml")) {
            return false;
        }

        // Set up XML Dom
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document root = dBuilder.parse(fileToImport);
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

                if (!addJunction(coordinate)) {
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
                if (direction.equalsIgnoreCase("bidirectional")) {
                    // add both directions
                    boolean dir = addSegment(startID, endID, segment);
                    boolean inverseDir = addSegment(endID, startID, segment);
                    if (!dir || !inverseDir) {
                        System.out.printf("Segment #%d: already inserted or malformed.%n", i);
                    }
                } else if (!addSegment(startID, endID, segment)) {
                    System.out.printf("Segment #%d: already inserted or malformed.%n", i);
                }
            }
        }

        return true;
    }

}
