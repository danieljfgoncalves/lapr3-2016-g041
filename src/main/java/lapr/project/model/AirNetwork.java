/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import java.util.Iterator;
import javax.measure.quantity.Length;
import javax.measure.unit.SI;
import lapr.project.utils.graph.MapEdge;
import lapr.project.utils.graph.MapGraph;
import org.jscience.physics.amount.Amount;

/**
 * Represents a airnetwork. (contains a graph of coordinates and segments)
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AirNetwork {

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
}
