/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.matrix_graph;

import java.util.LinkedList;

/**
 * Represents extra algorithms to manipulate a weighted matrix graph.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class WeightedMatrixGraphAlgorithms {

    /**
     * Determine the shortest path to all vertices from a vertex using
     * Dijkstra's algorithm To be called by public short method
     *
     * @param graph Graph object
     * @param sourceIdx Source vertex
     * @param knownVertices previously discovered vertices
     * @param verticesIndex index of vertices in the minimum path
     * @param minDist minimum distances in the path
     *
     */
    private static <V> void shortestPath(MatrixGraph<V, Double> graph,
            int sourceIdx, boolean[] knownVertices, int[] verticesIndex, double[] minDist) {

        double maxDistance = Double.POSITIVE_INFINITY;
        int[] path = verticesIndex;
        double[] dist = minDist;
        boolean[] visited = knownVertices;
        int vOrig = sourceIdx;

        for (V v : graph.vertices()) {
            int index = graph.toIndex(v);
            minDist[index] = maxDistance;
            path[index] = -1;
            visited[index] = false;
        }

        dist[vOrig] = 0;

        while (vOrig != -1) {
            visited[vOrig] = true;
            V vOrigVertex = graph.vertices.get(vOrig);

            for (V v : graph.directConnections(vOrigVertex)) {
                int vAdj = graph.toIndex(v);
                double edgeWeight = (Double) graph.getEdge(vOrigVertex, v);

                if (!visited[vAdj] && dist[vAdj] > dist[vOrig] + edgeWeight) {
                    dist[vAdj] = dist[vOrig] + edgeWeight;
                    path[vAdj] = vOrig;
                }
            }

            int new_vOrig = -1;
            double minimunDistance = maxDistance;
            for (int i = 0; i < dist.length; i++) {
                if (knownVertices[i] == false && dist[i] < minimunDistance) {
                    new_vOrig = i;
                    minimunDistance = dist[i];
                }
            }
            vOrig = new_vOrig;
        }

    }

    /**
     * Determine the shortest path between two vertices using Dijkstra's
     * algorithm
     *
     * @param <V> Vertex
     * @param graph Graph object
     * @param source Source vertex
     * @param dest Destination vertices
     * @param path Returns the vertices in the path (empty if no path)
     * @return minimum distance, -1 if vertices not in graph or no path
     *
     */
    public static <V> double shortestPath(MatrixGraph<V, Double> graph, V source, V dest, LinkedList<V> path) {
        int sourceIndex = graph.toIndex(source);
        int destIndex = graph.toIndex(dest);

        if (sourceIndex == -1 || destIndex == -1) {
            return -1;
        }

        path.clear();
        boolean knownVertices[] = new boolean[graph.numVertices];
        int verticesIndex[] = new int[graph.numVertices];
        double minDist[] = new double[graph.numVertices];
        shortestPath(graph, sourceIndex, knownVertices, verticesIndex, minDist);

        if (knownVertices[destIndex] == false) {
            return -1;
        }

        double minDistanceValue = minDist[destIndex];

        recreatePath(graph, sourceIndex, destIndex, verticesIndex, path);

        LinkedList<V> auxList = new LinkedList();
        int pathSize = path.size();
        for (int i = 0; i < pathSize; i++) {
            auxList.addFirst(path.removeFirst());
        }

        for (int i = 0; i < pathSize; i++) {
            path.addFirst(auxList.removeLast());
        }

        return minDistanceValue;
    }

    /**
     * Recreates the minimum path between two vertex, from the result of
     * Dikstra's algorithm
     *
     * @param graph Graph object
     * @param sourceIdx Source vertex
     * @param destIdx Destination vertices
     * @param verticesIndex index of vertices in the minimum path
     * @param Queue Vertices in the path (empty if no path)
     */
    private static <V> void recreatePath(MatrixGraph<V, Double> graph, int sourceIdx,
            int destIdx, int[] verticesIndex, LinkedList<V> path) {

        path.add(graph.vertices.get(destIdx));
        if (sourceIdx != destIdx) {
            destIdx = verticesIndex[destIdx];
            recreatePath(graph, sourceIdx, destIdx, verticesIndex, path);
        }
    }

    /**
     * Creates new graph with minimum distances between all pairs uses the
     * Floyd-Warshall algorithm
     *
     * @param <V>
     * @param graph Graph object
     * @return the new graph
     * @throws java.lang.CloneNotSupportedException
     */
    public static <V> MatrixGraph<V, Double> minDistGraph(MatrixGraph<V, Double> graph) throws CloneNotSupportedException {

        MatrixGraph<V, Double> graphClone = (MatrixGraph) graph.clone();
        int num = graphClone.numVertices;

        MatrixGraph<V, Double>[] newGraph = new MatrixGraph[num + 1];
        newGraph[0] = graphClone;

        for (int k = 1; k <= num; k++) {
            newGraph[k] = (MatrixGraph<V, Double>) newGraph[k - 1].clone();

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {

                    if (i != j && i != k && j != k) {

                        if (newGraph[k - 1].edgeMatrix[i][k] != null && newGraph[k - 1].edgeMatrix[k][j] != null) {

                            if (newGraph[k - 1].edgeMatrix[i][j] == null) {
                                newGraph[k].edgeMatrix[i][j] = newGraph[k - 1].edgeMatrix[i][k] + newGraph[k - 1].edgeMatrix[k][j];
                            } else if (newGraph[k - 1].edgeMatrix[i][k] + newGraph[k - 1].edgeMatrix[k][j] < newGraph[k - 1].edgeMatrix[i][j]) {
                                newGraph[k].edgeMatrix[i][j] = newGraph[k - 1].edgeMatrix[i][k] + newGraph[k - 1].edgeMatrix[k][j];
                            }
                        }
                    }
                }
            }
        }

        return newGraph[num];
    }

}
