/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import lapr.project.utils.Importable;
import lapr.project.utils.matrix.graph.MatrixGraph;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Iterator;
import lapr.project.utils.Regex;
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
    private MatrixGraph<Coordinate, Segment> network;

    /**
     * Constructs an empty air network
     */
    public AirNetwork() {

        this.network = new MatrixGraph<>(true);
    }

    /**
     * Constructs an air network receiving a created netwrok by param.
     *
     * @param network the network to use
     */
    public AirNetwork(MatrixGraph<Coordinate, Segment> network) {

        this.network = network;
    }

    /**
     * Constructs a air network by coping from another air network
     *
     * @param other the other airnetwrok to copy
     * @throws CloneNotSupportedException
     */
    public AirNetwork(AirNetwork other) throws CloneNotSupportedException {

        this.network = (MatrixGraph<Coordinate, Segment>) other.network.clone();

    }

    /**
     * Obtains the Air Network
     *
     * @return the network (graph)
     */
    public MatrixGraph<Coordinate, Segment> getNetwork() {

        return network;
    }

    /**
     * Sets the Air Network
     *
     * @param network the network to set (graph)
     */
    public void setNetwork(MatrixGraph<Coordinate, Segment> network) {

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
    public Iterable<Segment> getSegments() {

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

        return this.network.insertEdge(coordinateA, coordinateB, newSegment);
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

        return this.network.insertEdge(coordinateA, coordinateB, newSegment);
    }

    /**
     * Removes a new segment to the network (from A -> B)
     *
     * @param coordinateA starting coordinate
     * @param coordinateB ending coordinate
     * @return the removed segment
     */
    public Segment removeSegment(Coordinate coordinateA, Coordinate coordinateB) {

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
                segment.setIdentification(aElement.getAttribute("id"));
                // Set wind
                Element windElement = (Element) aElement.getElementsByTagName("wind").item(0);
                // Set wind direction
                String windDirection = windElement.getElementsByTagName("wind_direction").item(0).getTextContent();
                segment.setWindDirection(Regex.getValue(windDirection));
                // Set wind intensity
                String windIntensity = windElement.getElementsByTagName("wind_intensity").item(0).getTextContent();
                segment.setWindIntensity(Regex.getValue(windIntensity));
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
