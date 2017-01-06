/*
 * Package for generic concepts related to graphs.
 */
package lapr.project.utils.graph.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Map Vertex.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 * @param <V> Generic Vertex
 * @param <E> Map of Edges
 */
public class MapVertex<V, E> {

    private int key;                     //Vertex key number
    private V element;                 //Vertex information
    private final Map<V, MapEdge<V, E>> outVerts; //adjacent vertices

    public MapVertex() {
        key = -1;
        element = null;
        outVerts = new LinkedHashMap<>();
    }

    public MapVertex(int k, V vInf) {
        key = k;
        element = vInf;
        outVerts = new LinkedHashMap<>();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int k) {
        key = k;
    }

    public V getElement() {
        return element;
    }

    public void setElement(V vInf) {
        element = vInf;
    }

    public void addAdjVert(V vAdj, MapEdge<V, E> edge) {
        outVerts.put(vAdj, edge);
    }

    public V getAdjVert(MapEdge<V, E> edge) {

        for (V vert : outVerts.keySet()) {
            if (edge.equals(outVerts.get(vert))) {
                return vert;
            }
        }

        return null;
    }

    public void remAdjVert(V vAdj) {
        outVerts.remove(vAdj);
    }

    public MapEdge<V, E> getEdge(V vAdj) {
        return outVerts.get(vAdj);
    }

    public int numAdjVerts() {
        return outVerts.size();
    }

    public Iterable<V> getAllAdjVerts() {
        return outVerts.keySet();
    }

    public Iterable<MapEdge<V, E>> getAllOutEdges() {
        return outVerts.values();
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }
        MapVertex<V, E> otherVertex = (MapVertex<V, E>) otherObj;

        return (this.key == otherVertex.key
                && this.element != null && otherVertex.element != null
                && this.element.equals(otherVertex.element));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.key;
        hash = 59 * hash + Objects.hashCode(this.element);
        return hash;
    }

    @Override
    public MapVertex<V, E> clone() throws CloneNotSupportedException {

        MapVertex<V, E> newVertex = new MapVertex<>();

        newVertex.key = key;
        newVertex.element = element;

        outVerts.keySet().stream().forEach((vert) -> {
            newVertex.outVerts.put(vert, outVerts.get(vert));
        });

        return newVertex;
    }

    @Override
    public String toString() {
        String st = "";
        if (element != null) {
            st = element + " (" + key + "): \n";
        }
        if (!outVerts.isEmpty()) {
            for (V vert : outVerts.keySet()) {
                st += outVerts.get(vert);
            }
        }

        return st;
    }

}
