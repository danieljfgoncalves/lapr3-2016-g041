/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.graph.matrix;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents the algorithms to manipulate a matrix graph.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class MatrixGraphAlgorithms {

    /**
     * Reverse a linked list's order.
     *
     * @param <T> The linked list element's type
     * @param list the linekd list
     * @return the reversed linked list
     */
    private static <T> LinkedList<T> reverse(LinkedList<T> list) {

        LinkedList<T> reversed = new LinkedList<>();
        Iterator<T> it = list.iterator();

        while (it.hasNext()) {

            reversed.push(it.next()); // .push -> FIFO
        }

        return reversed;
    }

    /**
     * Performs depth-first search of the graph starting at vertex. Calls
     * package recursive version of the method.
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (including vertex), null if
     * vertex does not exist
     */
    public static <V, E> LinkedList<V> DFS(MatrixGraph<V, E> graph, V vertex) {

        int index = graph.toIndex(vertex);

        if (index == -1) {
            return null;
        }

        LinkedList<V> resultQueue = new LinkedList<>();

        resultQueue.add(vertex);

        boolean[] knownVertices = new boolean[graph.numVertices];

        DFS(graph, index, knownVertices, resultQueue); // Private overload method (Recursive call)
        return resultQueue;
    }

    /**
     * Actual depth-first search of the graph starting at vertex. The method
     * adds discovered vertices (including vertex) to the queue of vertices
     *
     * @param graph Graph object
     * @param index Index of vertex of graph that will be the source of the
     * search
     * @param known previously discovered vertices
     * @param verticesQueue queue of vertices found by search
     *
     */
    private static <V, E> void DFS(MatrixGraph<V, E> graph,
            int index,
            boolean[] knownVertices,
            LinkedList<V> verticesQueue) {

        knownVertices[index] = true;

        for (int i = 0; i < graph.numVertices; i++) {
//
            if (graph.edgeMatrix[index][i] != null && knownVertices[i] == false) {

                verticesQueue.add(graph.vertices.get(i));
                DFS(graph, i, knownVertices, verticesQueue);
            }
        }
    }

    /**
     * Performs breath-first search of the graph starting at vertex. The method
     * adds discovered vertices (including vertex) to the queue of vertices
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param vertex Vertex of graph that will be the source of the search
     * @return queue of vertices found by search (including vertex), null if
     * vertex does not exist
     *
     */
    public static <V, E> LinkedList<V> BFS(MatrixGraph<V, E> graph, V vertex) {

        int index = graph.toIndex(vertex);

        if (index == -1) {
            return null;
        }

        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();
        boolean[] knownVertices = new boolean[graph.numVertices];

        qbfs.add(vertex);
        qaux.add(vertex);
        knownVertices[index] = true;

        while (!qaux.isEmpty()) {

            V v_tmp = qaux.removeFirst();

            index = graph.toIndex(v_tmp);

            for (int col = 0; col < graph.numVertices; col++) {

                if (graph.edgeMatrix[index][col] != null && knownVertices[col] == false) {

                    qbfs.add(graph.vertices.get(col));
                    qaux.add(graph.vertices.get(col));
                    knownVertices[col] = true;
                }
            }

        }

        return qbfs;
    }

    /**
     * All paths between two vertices Calls recursive version of the method.
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param source Source vertex of path
     * @param dest Destination vertex of path
     * @param paths LinkedList with paths (queues)
     * @return false if vertices not in the graph
     *
     */
    public static <V, E> boolean allPaths(MatrixGraph<V, E> graph, V source, V dest, LinkedList<LinkedList<V>> paths) {
        int indexSource = graph.toIndex(source);
        if (indexSource == -1) {
            return false;
        }

        int indexDest = graph.toIndex(dest);
        if (indexDest == -1) {
            return false;
        }

        paths.clear();

        boolean[] knownVertices = new boolean[graph.numVertices];
        LinkedList<V> path = new LinkedList<>();
        allPaths(graph, indexSource, indexDest, knownVertices, path, paths);

        return true;
    }

    /**
     * Actual paths search The method adds vertices to the current path (stack
     * of vertices) when destination is found, the current path is saved to the
     * list of paths
     *
     * @param graph Graph object
     * @param sourceIdx Index of source vertex
     * @param destIdx Index of destination vertex
     * @param knownVertices previously discovered vertices
     * @param auxStack stack of vertices in the path
     * @param path LinkedList with paths (queues)
     *
     */
    private static <V, E> void allPaths(MatrixGraph<V, E> graph,
            int sourceIdx, int destIdx,
            boolean[] knownVertices,
            LinkedList<V> auxStack, LinkedList<LinkedList<V>> paths) {

        V vOrig = graph.vertices.get(sourceIdx);
        V vDest = graph.vertices.get(destIdx);
        knownVertices[sourceIdx] = true;
        auxStack.add(vOrig);

        for (V v : graph.directConnections(graph.vertices.get(sourceIdx))) {

            if (v.equals(vDest)) {

                auxStack.add(vDest);
                paths.add((LinkedList<V>) auxStack.clone());
                auxStack.removeLast();
            } else if (!knownVertices[graph.toIndex(v)]) {

                allPaths(graph, graph.toIndex(v), destIdx, knownVertices, auxStack, paths);
            }

        }

        knownVertices[sourceIdx] = false;
        auxStack.removeLast();
    }

    /**
     * Transforms a graph into its transitive closure uses the Floyd-Warshall
     * algorithm
     *
     * @param <V>
     * @param <E>
     * @param graph Graph object
     * @param dummyEdge object to insert in the newly created edges
     * @return the new graph
     */
    public static <V, E> MatrixGraph<V, E> transitiveClosure(MatrixGraph<V, E> graph, E dummyEdge) {

        MatrixGraph<V, E> transitiveClosureGraph = new MatrixGraph<>();

        for (V vertex : graph.vertices()) {
            transitiveClosureGraph.insertVertex(vertex);
        }

        for (V vertexInicial : graph.vertices()) {
            for (V vertexIntermedio : graph.vertices()) {

                if (vertexInicial != vertexIntermedio && graph.getEdge(vertexInicial, vertexIntermedio) != null) {
                    for (V vertexFinal : graph.vertices()) {

                        if (vertexInicial != vertexFinal && vertexIntermedio != vertexFinal
                                && graph.getEdge(vertexIntermedio, vertexFinal) != null) {
                            transitiveClosureGraph.insertEdge(vertexInicial, vertexFinal, dummyEdge);
                        }
                    }
                }
            }
        }

        return transitiveClosureGraph;
    }
}
