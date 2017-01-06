/**
 * Package location for Pure Fabrication util class tests.
 */
package lapr.project.utils.graph.matrix;

import lapr.project.utils.graph.matrix.MatrixGraphAlgorithms;
import lapr.project.utils.graph.matrix.MatrixGraph;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the algorithms to manipulate a matrix graph.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class MatrixGraphAlgorithmsTest {

    /**
     * Undirected matrix graph representing a map
     */
    MatrixGraph<String, String> undirectedMap = new MatrixGraph<>();

    /**
     * Incompleted Undirected Map
     */
    MatrixGraph<String, String> incompletedUnMap;

    /**
     * Directed matrix graph representing a map
     */
    MatrixGraph<String, String> directedMap = new MatrixGraph<>(true);

    /**
     * Incompleted Directed Map
     */
    MatrixGraph<String, String> incompletedDMap;

    public MatrixGraphAlgorithmsTest() {
    }

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {

        // UNDIRECTED
        undirectedMap.insertVertex("Porto");
        undirectedMap.insertVertex("Braga");
        undirectedMap.insertVertex("Vila Real");
        undirectedMap.insertVertex("Aveiro");
        undirectedMap.insertVertex("Coimbra");
        undirectedMap.insertVertex("Leiria");
        undirectedMap.insertVertex("Viseu");
        undirectedMap.insertVertex("Guarda");
        undirectedMap.insertVertex("Castelo Branco");
        undirectedMap.insertVertex("Lisboa");
        undirectedMap.insertVertex("Faro");

        undirectedMap.insertEdge("Porto", "Aveiro", "A1");
        undirectedMap.insertEdge("Porto", "Braga", "A3");
        undirectedMap.insertEdge("Porto", "Vila Real", "A4");
        undirectedMap.insertEdge("Viseu", "Guarda", "A25");
        undirectedMap.insertEdge("Guarda", "Castelo Branco", "A23");
        undirectedMap.insertEdge("Aveiro", "Coimbra", "A1");
        undirectedMap.insertEdge("Coimbra", "Lisboa", "A1");
        undirectedMap.insertEdge("Coimbra", "Leiria", "A34");
        undirectedMap.insertEdge("Aveiro", "Leiria", "A17");
        undirectedMap.insertEdge("Leiria", "Lisboa", "A8");

        incompletedUnMap = (MatrixGraph<String, String>) undirectedMap.clone();

        undirectedMap.insertEdge("Aveiro", "Viseu", "A25");
        undirectedMap.insertEdge("Leiria", "Castelo Branco", "A23");
        undirectedMap.insertEdge("Lisboa", "Faro", "A2");

        // DIRECTED
        directedMap.insertVertex("Porto");
        directedMap.insertVertex("Braga");
        directedMap.insertVertex("Vila Real");
        directedMap.insertVertex("Aveiro");
        directedMap.insertVertex("Coimbra");
        directedMap.insertVertex("Leiria");
        directedMap.insertVertex("Viseu");
        directedMap.insertVertex("Guarda");
        directedMap.insertVertex("Castelo Branco");
        directedMap.insertVertex("Lisboa");
        directedMap.insertVertex("Faro");

        directedMap.insertEdge("Porto", "Aveiro", "A1");
        directedMap.insertEdge("Porto", "Braga", "A3");
        directedMap.insertEdge("Porto", "Vila Real", "A4");
        directedMap.insertEdge("Viseu", "Guarda", "A25");
        directedMap.insertEdge("Guarda", "Castelo Branco", "A23");
        directedMap.insertEdge("Aveiro", "Coimbra", "A1");
        directedMap.insertEdge("Coimbra", "Lisboa", "A1");
        directedMap.insertEdge("Coimbra", "Leiria", "A34");
        directedMap.insertEdge("Aveiro", "Leiria", "A17");
        directedMap.insertEdge("Leiria", "Lisboa", "A8");

        incompletedDMap = (MatrixGraph<String, String>) directedMap.clone();

        directedMap.insertEdge("Aveiro", "Viseu", "A25");
        directedMap.insertEdge("Leiria", "Castelo Branco", "A23");
        directedMap.insertEdge("Lisboa", "Faro", "A2");

    }

    @Test
    public void testDFS01() {
        System.out.println("Test of DFS (Undirected)");

        LinkedList<String> path;

        assertTrue("Should be null if vertex does not exist", MatrixGraphAlgorithms.DFS(undirectedMap, "LX") == null);

        path = MatrixGraphAlgorithms.DFS(incompletedUnMap, "Faro");

        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();

        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.DFS(undirectedMap, "Porto");

        assertTrue("Should give all vertices ", path.size() == 11);

        it = path.iterator();

        assertTrue("First in visit should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Vila Real", it.next().compareTo("Vila Real") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);

        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.DFS(incompletedUnMap, "Viseu");

        assertTrue("Should give 3 vertices", path.size() == 3);

        it = path.iterator();

        assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
    }

    @Test
    public void testDFS02() {
        System.out.println("Test of DFS (Directed)");

        LinkedList<String> path;

        assertTrue("Should be null if vertex does not exist", MatrixGraphAlgorithms.DFS(directedMap, "LX") == null);

        path = MatrixGraphAlgorithms.DFS(incompletedDMap, "Faro");
        assertTrue("Should be just one", path.size() == 1);
        Iterator<String> it = path.iterator();
        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.DFS(directedMap, "Porto");
        assertTrue("Should give all vertices ", path.size() == 11);
        it = path.iterator();
        assertTrue("First in visit should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Vila Real", it.next().compareTo("Vila Real") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);

        path = MatrixGraphAlgorithms.DFS(directedMap, "Leiria");

        assertTrue("Should give 4 vertices ", path.size() == 4);
        it = path.iterator();
        assertTrue("First in visit should be Porto", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.DFS(incompletedDMap, "Viseu");
        assertTrue("Should give 3 vertices", path.size() == 3);
        it = path.iterator();
        assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
    }

    @Test
    public void testBFS01() {
        System.out.println("Test of BFS (Undirected)");

        LinkedList<String> path;

        assertTrue("Should be null if vertex does not exist", MatrixGraphAlgorithms.BFS(undirectedMap, "LX") == null);

        path = MatrixGraphAlgorithms.BFS(incompletedUnMap, "Faro");

        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();

        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.BFS(undirectedMap, "Porto");

        assertTrue("Should give all vertices ", path.size() == 11);

        it = path.iterator();

        assertTrue("First in visit should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Vila Real", it.next().compareTo("Vila Real") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.BFS(incompletedUnMap, "Viseu");

        assertTrue("Should give 3 vertices", path.size() == 3);

        it = path.iterator();

        assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

    }

    @Test
    public void testBFS02() {
        System.out.println("Test of BFS (Directed)");

        LinkedList<String> path;

        assertTrue("Should be null if vertex does not exist", MatrixGraphAlgorithms.BFS(directedMap, "LX") == null);

        path = MatrixGraphAlgorithms.BFS(incompletedDMap, "Faro");
        assertTrue("Should be just one", path.size() == 1);
        Iterator<String> it = path.iterator();
        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.BFS(directedMap, "Porto");
        assertTrue("Should give all vertices ", path.size() == 11);
        it = path.iterator();
        assertTrue("First in visit should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Vila Real", it.next().compareTo("Vila Real") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);

        path = MatrixGraphAlgorithms.BFS(directedMap, "Coimbra");
        assertTrue("Should give 3 vertices", path.size() == 5);
        it = path.iterator();
        assertTrue("First in visit should be Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
        assertTrue("then Faro", it.next().compareTo("Faro") == 0);

    }

    @Test
    public void testAllPaths01() {

        System.out.println("Test of all paths (Undirected)");

        LinkedList<LinkedList<String>> paths = new LinkedList<>();

        assertFalse("Should be false if vertex does not exist",
                MatrixGraphAlgorithms.allPaths(undirectedMap, "Porto", "LX", paths));

        assertTrue("There should be paths between Porto and Lisboa in the map",
                MatrixGraphAlgorithms.allPaths(undirectedMap, "Porto", "Lisboa", paths));
        assertTrue("Should give 4 paths", paths.size() == 6);
        Iterator<LinkedList<String>> it = paths.iterator();
        // First path should be Porto, Aveiro, Coimbra, Leiria, Lisboa
        // Second path should be Porto, Aveiro, Coimbra, Lisboa
        // Third path should be Porto, Aveiro, Leiria, Coimbra, Lisboa
        // Fourth path should be Porto, Aveiro, Leiria, Lisboa
        // Fifth path should be Porto, Aveiro, Viseu, Guarda, Castelo Branco, Leiria, Coimbra, Lisboa
        // Sixth path should be Porto, Aveiro, Viseu, Guarda, Castelo Branco, Leiria, Lisboa
        String[][] pathsString = {{"Porto", "Aveiro", "Coimbra", "Leiria", "Lisboa"},
        {"Porto", "Aveiro", "Coimbra", "Lisboa"},
        {"Porto", "Aveiro", "Leiria", "Coimbra", "Lisboa"},
        {"Porto", "Aveiro", "Leiria", "Lisboa"},
        {"Porto", "Aveiro", "Viseu", "Guarda", "Castelo Branco", "Leiria", "Coimbra", "Lisboa"},
        {"Porto", "Aveiro", "Viseu", "Guarda", "Castelo Branco", "Leiria", "Lisboa"}};

        for (int i = 0; i < 6; i++) {
            LinkedList<String> path = it.next();
            Iterator<String> cities = path.iterator();
            for (int j = 0; j < path.size(); j++) {
                assertTrue("City should be" + pathsString[i][j], cities.next().compareTo(pathsString[i][j]) == 0);
            }
        }

        MatrixGraphAlgorithms.allPaths(incompletedUnMap, "Porto", "Faro", paths);

        assertTrue("There should not be paths between Porto and Faro in the incomplete map", paths.isEmpty());
    }

    @Test
    public void testAllPaths02() {

        System.out.println("Test of all paths (Undirected)");

        LinkedList<LinkedList<String>> paths = new LinkedList<>();

        assertFalse("Should be false if vertex does not exist",
                MatrixGraphAlgorithms.allPaths(directedMap, "Porto", "LX", paths));

        assertTrue("There should be paths between Porto and Lisboa in the map",
                MatrixGraphAlgorithms.allPaths(directedMap, "Porto", "Lisboa", paths));
        assertTrue("Should give 4 paths", paths.size() == 3);
        Iterator<LinkedList<String>> it = paths.iterator();
        // First path should be Porto, Aveiro, Coimbra, Leiria, Lisboa
        // Second path should be Porto, Aveiro, Coimbra, Lisboa
        // Third path should be Porto, Aveiro, Leiria, Lisboa
        String[][] pathsString = {
            {"Porto", "Aveiro", "Coimbra", "Leiria", "Lisboa"},
            {"Porto", "Aveiro", "Coimbra", "Lisboa"},
            {"Porto", "Aveiro", "Leiria", "Lisboa"}};

        for (int i = 0; i < 3; i++) {
            LinkedList<String> path = it.next();
            Iterator<String> cities = path.iterator();
            for (int j = 0; j < path.size(); j++) {
                assertTrue("City should be" + pathsString[i][j], cities.next().compareTo(pathsString[i][j]) == 0);
            }
        }

        MatrixGraphAlgorithms.allPaths(incompletedDMap, "Porto", "Faro", paths);

        assertTrue("There should not be paths between Porto and Faro in the incomplete map", paths.isEmpty());
    }
}
