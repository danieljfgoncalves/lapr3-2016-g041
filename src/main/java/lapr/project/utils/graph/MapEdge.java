/*
 * Package for generic concepts related to graphs.
 */
package lapr.project.utils.graph;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * Represents a Map Edge.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 * 
 * @param <V> Generic Vertex
 * @param <E> Generic Edge
 */
public class MapEdge<V, E> implements Comparable {

    private E element;           // MapEdge information
    private double weight;       // MapEdge weight
    private MapVertex<V, E> vOrig;  // vertex origin
    private MapVertex<V, E> vDest;  // vertex destination

    public MapEdge() {
        element = null;
        weight = 0.0;
        vOrig = null;
        vDest = null;
    }

    public MapEdge(E eInf, double ew, MapVertex<V, E> vo, MapVertex<V, E> vd) {
        element = eInf;
        weight = ew;
        vOrig = vo;
        vDest = vd;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E eInf) {
        element = eInf;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double ew) {
        weight = ew;
    }

    public V getVOrig() {
        return vOrig.getElement();
    }

    public void setVOrig(MapVertex<V, E> vo) {
        vOrig = vo;
    }

    public V getVDest() {
        return vDest.getElement();
    }

    public void setVDest(MapVertex<V, E> vd) {
        vDest = vd;
    }

    public V[] getEndpoints() {

        V oElem = vOrig.getElement();
        V dElem = vDest.getElement(); // To get type

        V[] endverts = (V[]) Array.newInstance(oElem.getClass(), 2);

        endverts[0] = oElem;
        endverts[1] = dElem;

        return endverts;
    }

    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        MapEdge<V, E> otherEdge = (MapEdge<V, E>) otherObj;

        // if endpoints vertices are not equal
        if (!this.vOrig.equals(otherEdge.vOrig) || !this.vDest.equals(otherEdge.vDest)) {
            return false;
        }

        if (this.weight != otherEdge.weight) {
            return false;
        }

        if (this.element != null && otherEdge.element != null) {
            return this.element.equals(otherEdge.element);
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.element);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.vOrig);
        hash = 47 * hash + Objects.hashCode(this.vDest);
        return hash;
    }

    @Override
    public int compareTo(Object otherObject) {

        MapEdge<V, E> other = (MapEdge<V, E>) otherObject;
        if (this.weight < other.weight) {
            return -1;
        }
        if (this.weight == other.weight) {
            return 0;
        }
        return 1;
    }

    @Override
    public MapEdge<V, E> clone() throws CloneNotSupportedException {

        MapEdge<V, E> newEdge = new MapEdge<>();

        newEdge.element = element;
        newEdge.weight = weight;
        newEdge.vOrig = vOrig;
        newEdge.vDest = vDest;

        return newEdge;
    }

    @Override
    public String toString() {
        String st = "";
        if (element != null) {
            st = "      (" + element + ") - ";
        } else {
            st = "\t ";
        }

        if (weight != 0) {
            st += weight + " - " + vDest.getElement() + "\n";
        } else {
            st += vDest.getElement() + "\n";
        }
        return st;
    }

}
