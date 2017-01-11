/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.graph.matrix;

import lapr.project.utils.graph.MatrixGraph;
import lapr.project.utils.graph.WeightedMatrixGraphAlgorithms;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests extra algorithms to manipulate a weighted matrix graph.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class WeightedMatrixGraphAlgorithmsTest {

    /**
     * A test graph of a map with distances (Undirected).
     */
    MatrixGraph<String, Double> distanceMapUndirected;

    /**
     * A test graph of a map with distances (Directed).
     */
    MatrixGraph<String, Double> distanceMapDirected;

    public WeightedMatrixGraphAlgorithmsTest() {
        this.distanceMapUndirected = new MatrixGraph<>();
        this.distanceMapDirected = new MatrixGraph<>(true);
    }

    @Before
    public void setUp() throws Exception {
        // Build the undirected graph
        distanceMapUndirected.insertVertex("Porto");
        distanceMapUndirected.insertVertex("Braga");
        distanceMapUndirected.insertVertex("Vila Real");
        distanceMapUndirected.insertVertex("Aveiro");
        distanceMapUndirected.insertVertex("Coimbra");
        distanceMapUndirected.insertVertex("Leiria");
        distanceMapUndirected.insertVertex("Viseu");
        distanceMapUndirected.insertVertex("Guarda");
        distanceMapUndirected.insertVertex("Castelo Branco");
        distanceMapUndirected.insertVertex("Lisboa");
        distanceMapUndirected.insertVertex("Faro");
        distanceMapUndirected.insertVertex("Évora");

        distanceMapUndirected.insertEdge("Porto", "Aveiro", 75.0);
        distanceMapUndirected.insertEdge("Porto", "Braga", 60.0);
        distanceMapUndirected.insertEdge("Porto", "Vila Real", 100.0);
        distanceMapUndirected.insertEdge("Viseu", "Guarda", 75.0);
        distanceMapUndirected.insertEdge("Guarda", "Castelo Branco", 100.0);
        distanceMapUndirected.insertEdge("Aveiro", "Coimbra", 60.0);
        distanceMapUndirected.insertEdge("Coimbra", "Lisboa", 200.0);
        distanceMapUndirected.insertEdge("Coimbra", "Leiria", 80.0);
        distanceMapUndirected.insertEdge("Aveiro", "Leiria", 120.0);
        distanceMapUndirected.insertEdge("Leiria", "Lisboa", 150.0);
        distanceMapUndirected.insertEdge("Aveiro", "Viseu", 85.0);
        distanceMapUndirected.insertEdge("Leiria", "Castelo Branco", 170.0);
        distanceMapUndirected.insertEdge("Lisboa", "Faro", 280.0);

        // Build the directed graph
        distanceMapDirected.insertVertex("Porto");
        distanceMapDirected.insertVertex("Braga");
        distanceMapDirected.insertVertex("Vila Real");
        distanceMapDirected.insertVertex("Aveiro");
        distanceMapDirected.insertVertex("Coimbra");
        distanceMapDirected.insertVertex("Leiria");
        distanceMapDirected.insertVertex("Viseu");
        distanceMapDirected.insertVertex("Guarda");
        distanceMapDirected.insertVertex("Castelo Branco");
        distanceMapDirected.insertVertex("Lisboa");
        distanceMapDirected.insertVertex("Faro");
        distanceMapDirected.insertVertex("Évora");

        distanceMapDirected.insertEdge("Porto", "Aveiro", 75.0);
        distanceMapDirected.insertEdge("Porto", "Braga", 60.0);
        distanceMapDirected.insertEdge("Porto", "Vila Real", 100.0);
        distanceMapDirected.insertEdge("Viseu", "Guarda", 75.0);
        distanceMapDirected.insertEdge("Guarda", "Castelo Branco", 100.0);
        distanceMapDirected.insertEdge("Aveiro", "Coimbra", 60.0);
        distanceMapDirected.insertEdge("Coimbra", "Lisboa", 200.0);
        distanceMapDirected.insertEdge("Coimbra", "Leiria", 80.0);
        distanceMapDirected.insertEdge("Aveiro", "Leiria", 120.0);
        distanceMapDirected.insertEdge("Leiria", "Lisboa", 150.0);
        distanceMapDirected.insertEdge("Aveiro", "Viseu", 85.0);
        distanceMapDirected.insertEdge("Leiria", "Castelo Branco", 170.0);
        distanceMapDirected.insertEdge("Lisboa", "Faro", 280.0);
    }

    @Test
    public void testShortestPath01() {
        System.out.println("Test of shortest path (Undirected)");

        LinkedList<String> path = new LinkedList<>();

        assertTrue("Should be -1 if vertex does not exist", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapUndirected, "Porto", "LX", path) == -1);

        assertTrue("Should be -1 if there is no path", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapUndirected, "Porto", "Évora", path) == -1);

        assertTrue("Should be 0 if source and vertex are the same", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapUndirected, "Porto", "Porto", path) == 0);

        assertTrue("Path should be single vertex if source and vertex are the same", path.size() == 1);

        assertTrue("Path between Porto and Lisboa should be 335 Km", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapUndirected, "Porto", "Lisboa", path) == 335);

        Iterator<String> it = path.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);

        assertTrue("Path between Braga and Leiria should be 255 Km", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapUndirected, "Braga", "Leiria", path) == 255);

        it = path.iterator();

        assertTrue("First in path should be Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);

        assertTrue("Path between Porto and Castelo Branco should be 335 Km", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapUndirected, "Porto", "Castelo Branco", path) == 335);
        assertTrue("Path between Porto and Castelo Branco should be 5 cities", path.size() == 5);

        it = path.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

        // Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco
        distanceMapUndirected.removeEdge("Viseu", "Guarda");
        distanceMapUndirected.insertEdge("Viseu", "Guarda", 125.0);

        assertTrue("Path between Porto and Castelo Branco should now be 365 Km", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapUndirected, "Porto", "Castelo Branco", path) == 365);
        assertTrue("Path between Porto and Castelo Branco should be 4 cities", path.size() == 4);

        it = path.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

    }

    @Test
    public void testShortestPath02() {
        System.out.println("Test of shortest path (Directed)");

        LinkedList<String> path = new LinkedList<>();

        assertTrue("Should be -1 if vertex does not exist", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapDirected, "Porto", "LX", path) == -1);

        assertTrue("Should be -1 if there is no path", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapDirected, "Porto", "Évora", path) == -1);

        assertTrue("Should be 0 if source and vertex are the same", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapDirected, "Porto", "Porto", path) == 0);

        assertTrue("Path should be single vertex if source and vertex are the same", path.size() == 1);

        assertTrue("Path between Porto and Lisboa should be 335 Km", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapDirected, "Porto", "Lisboa", path) == 335);

        Iterator<String> it = path.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);

        assertTrue("Should be -1 if there is no path", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapDirected, "Braga", "Leiria", path) == -1);

        assertTrue("Path between Porto and Castelo Branco should be 335 Km", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapDirected, "Porto", "Castelo Branco", path) == 335);
        assertTrue("Path between Porto and Castelo Branco should be 5 cities", path.size() == 5);

        it = path.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

        // Changing Viseu to Guarda should change shortest path between Porto and Castelo Branco
        distanceMapDirected.removeEdge("Viseu", "Guarda");
        distanceMapDirected.insertEdge("Viseu", "Guarda", 125.0);

        assertTrue("Path between Porto and Castelo Branco should now be 365 Km", WeightedMatrixGraphAlgorithms.shortestPath(distanceMapDirected, "Porto", "Castelo Branco", path) == 365);
        assertTrue("Path between Porto and Castelo Branco should be 4 cities", path.size() == 4);

        it = path.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
    }
}
