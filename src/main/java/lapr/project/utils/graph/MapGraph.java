/*
 * Package for generic concepts related to graphs.
 */
package lapr.project.utils.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a Map Graph.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 *
 * @param <V> Generic Vertex
 * @param <E> Generic Edge
 */
public class MapGraph<V, E> implements MapGraphInterface<V, E> {

    private int numVert;
    private int numEdge;
    private final boolean isDirected;
    private final Map<V, MapVertex<V, E>> vertices;  //all Vertices of the graph 

    // Constructs an empty graph (either undirected or directed)
    public MapGraph(boolean directed) {
        numVert = 0;
        numEdge = 0;
        isDirected = directed;
        vertices = new LinkedHashMap<>();
    }

    @Override
    public int numVertices() {
        return numVert;
    }

    @Override
    public Iterable<V> vertices() {
        return vertices.keySet();
    }

    public boolean validVertex(V vert) {

        return vertices.get(vert) != null;
    }

    public int getKey(V vert) {
        return vertices.get(vert).getKey();
    }

    public V[] allkeyVerts() {

        V[] keyverts = (V[]) new Object[numVert];

        vertices.values().stream().forEach((vert) -> {
            keyverts[vert.getKey()] = vert.getElement();
        });

        return keyverts;
    }

    @Override
    public int numEdges() {
        return numEdge;
    }

    @Override
    public Iterable<MapEdge<V, E>> edges() {
        ArrayList<MapEdge<V, E>> edges = new ArrayList<>();

        vertices.values().stream().forEach((v) -> {
            for (MapEdge<V, E> e : v.getAllOutEdges()) {
                edges.add(e);
            }
        });

        return edges;
    }

    @Override
    public MapEdge<V, E> getEdge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest)) {
            return null;
        }

        MapVertex<V, E> vorig = vertices.get(vOrig);

        return vorig.getEdge(vDest);
    }

    @Override
    public V[] endVertices(MapEdge<V, E> edge) {

        if (edge == null) {
            return null;
        }

        if (!validVertex(edge.getVOrig()) || !validVertex(edge.getVDest())) {
            return null;
        }

        MapVertex<V, E> vorig = vertices.get(edge.getVOrig());

        if (!edge.equals(vorig.getEdge(edge.getVDest()))) {
            return null;
        }

        return edge.getEndpoints();
    }

    @Override
    public V opposite(V vert, MapEdge<V, E> edge) {

        if (!validVertex(vert)) {
            return null;
        }

        MapVertex<V, E> vertex = vertices.get(vert);

        return vertex.getAdjVert(edge);
    }

    @Override
    public int outDegree(V vert) {

        if (!validVertex(vert)) {
            return -1;
        }

        MapVertex<V, E> vertex = vertices.get(vert);

        return vertex.numAdjVerts();
    }

    @Override
    public int inDegree(V vert) {

        if (!validVertex(vert)) {
            return -1;
        }

        int degree = 0;
        for (V otherVert : vertices.keySet()) {
            if (getEdge(otherVert, vert) != null) {
                degree++;
            }
        }

        return degree;
    }

    @Override
    public Iterable<MapEdge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert)) {
            return null;
        }

        MapVertex<V, E> vertex = vertices.get(vert);

        return vertex.getAllOutEdges();
    }

    @Override
    public Iterable<MapEdge<V, E>> incomingEdges(V vert) {

        ArrayList<MapEdge<V, E>> incoming_edges = new ArrayList<>();

        if (!validVertex(vert)) {
            return null;
        }

        MapVertex<V, E> vertex = vertices.get(vert);

        Iterable<MapEdge<V, E>> list_all_edges = edges();

        for (MapEdge<V, E> e : list_all_edges) {
            if (e.getVDest() == vertex.getElement()) {
                incoming_edges.add(e);
            }
        }

        return incoming_edges;
    }

    @Override
    public boolean insertVertex(V vert) {

        if (validVertex(vert)) {
            return false;
        }

        MapVertex<V, E> vertex = new MapVertex<>(numVert, vert);
        vertices.put(vert, vertex);
        numVert++;

        return true;
    }

    @Override
    public boolean insertEdge(V vOrig, V vDest, E eInf, double eWeight) {

        if (getEdge(vOrig, vDest) != null) {
            return false;
        }

        if (!validVertex(vOrig)) {
            insertVertex(vOrig);
        }

        if (!validVertex(vDest)) {
            insertVertex(vDest);
        }

        MapVertex<V, E> vorig = vertices.get(vOrig);
        MapVertex<V, E> vdest = vertices.get(vDest);

        MapEdge<V, E> newEdge = new MapEdge<>(eInf, eWeight, vorig, vdest);
        vorig.addAdjVert(vDest, newEdge);
        numEdge++;

        //if graph is not direct insert other edge in the opposite direction 
        if (!isDirected) {
            if (getEdge(vDest, vOrig) == null) {
                MapEdge<V, E> otherEdge = new MapEdge<>(eInf, eWeight, vdest, vorig);
                vdest.addAdjVert(vOrig, otherEdge);
                numEdge++;
            }
        }

        return true;
    }

    @Override
    public boolean removeVertex(V vert) {

        if (!validVertex(vert)) {
            return false;
        }

        this.vertices.keySet().stream().map((v) -> {
            this.removeEdge(v, vert);
            return v;
        }).forEach((v) -> {
            this.removeEdge(vert, v);
        });

        this.vertices.remove(vert);
        this.numVert--;

        return true;
    }

    @Override
    public boolean removeEdge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest)) {
            return false;
        }

        MapEdge<V, E> edge = getEdge(vOrig, vDest);

        if (edge == null) {
            return false;
        }

        MapVertex<V, E> vorig = vertices.get(vOrig);

        vorig.remAdjVert(vDest);
        numEdge--;

        //if graph is not direct 
        if (!isDirected) {
            edge = getEdge(vDest, vOrig);
            if (edge != null) {
                MapVertex<V, E> vdest = vertices.get(vDest);
                vdest.remAdjVert(vOrig);
                numEdge--;
            }
        }
        return true;
    }

    @Override
    public MapGraph<V, E> clone() throws CloneNotSupportedException {

        MapGraph<V, E> newObject = new MapGraph<>(this.isDirected);

        //insert all vertices
        vertices.keySet().stream().forEach((vert) -> {
            newObject.insertVertex(vert);
        });

        //insert all edges
        vertices.keySet().stream().forEach((vert1) -> {
            for (MapEdge<V, E> e : this.outgoingEdges(vert1)) {
                if (e != null) {
                    V vert2 = this.opposite(vert1, e);
                    newObject.insertEdge(vert1, vert2, e.getElement(), e.getWeight());
                }
            }
        });

        return newObject;
    }

    @Override
    public boolean equals(Object otherObj) {

        if (otherObj == null) {
            return false;
        }

        if (this == otherObj) {
            return true;
        }

        if (!(otherObj instanceof MapGraph<?, ?>)) {
            return false;
        }

        MapGraph<V, E> otherGraph = (MapGraph<V, E>) otherObj;

        if (numVert != otherGraph.numVertices() || numEdge != otherGraph.numEdges()) {
            return false;
        }

        //graph must have same vertices
        boolean eqvertex;
        for (V v1 : this.vertices()) {
            eqvertex = false;
            for (V v2 : otherGraph.vertices()) {
                if (v1.equals(v2)) {
                    eqvertex = true;
                }
            }

            if (!eqvertex) {
                return false;
            }
        }

        //graph must have same edges
        boolean eqedge;
        for (MapEdge<V, E> e1 : this.edges()) {
            eqedge = false;
            for (MapEdge<V, E> e2 : otherGraph.edges()) {
                if (e1.equals(e2)) {
                    eqedge = true;
                }
            }

            if (!eqedge) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.numVert;
        hash = 47 * hash + this.numEdge;
        hash = 47 * hash + (this.isDirected ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.vertices);
        return hash;
    }

    @Override
    public String toString() {
        String s = "";
        if (numVert == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVert + " vertices, " + numEdge + " edges\n";
            s = vertices.values().stream().map((vert) -> vert + "\n").reduce(s, String::concat);
        }
        return s;
    }
}
