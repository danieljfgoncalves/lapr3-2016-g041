/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.utils.Importable;
import lapr.project.utils.matrix.graph.MatrixGraph;
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
    public boolean importXml(File fileToImport) throws SAXException, IOException, ParserConfigurationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
