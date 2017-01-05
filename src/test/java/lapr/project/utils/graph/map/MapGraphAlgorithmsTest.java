/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.graph.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Goncalves <1151452@isep.ipp.pt>
 */
public class MapGraphAlgorithmsTest {

    /**
     * Test instance
     */
    private final MapGraph<String, String> completeMap = new MapGraph<>(false);

    @Before
    public void setUp() throws Exception {

        completeMap.insertVertex("Porto");
        completeMap.insertVertex("Braga");
        completeMap.insertVertex("Vila Real");
        completeMap.insertVertex("Aveiro");
        completeMap.insertVertex("Coimbra");
        completeMap.insertVertex("Leiria");

        completeMap.insertVertex("Viseu");
        completeMap.insertVertex("Guarda");
        completeMap.insertVertex("Castelo Branco");
        completeMap.insertVertex("Lisboa");
        completeMap.insertVertex("Faro");

        completeMap.insertEdge("Porto", "Aveiro", "A1", 75);
        completeMap.insertEdge("Porto", "Braga", "A3", 60);
        completeMap.insertEdge("Porto", "Vila Real", "A4", 100);
        completeMap.insertEdge("Viseu", "Guarda", "A25", 75);
        completeMap.insertEdge("Guarda", "Castelo Branco", "A23", 100);
        completeMap.insertEdge("Aveiro", "Coimbra", "A1", 60);
        completeMap.insertEdge("Coimbra", "Lisboa", "A1", 200);
        completeMap.insertEdge("Coimbra", "Leiria", "A34", 80);
        completeMap.insertEdge("Aveiro", "Leiria", "A17", 120);
        completeMap.insertEdge("Leiria", "Lisboa", "A8", 150);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of BreadthFirstSearch method, of class GraphAlgorithms.
     */
    @Test
    public void testBreadthFirstSearch() {
        System.out.println("Test BreadthFirstSearch");

        assertTrue("Should be null if vertex does not exist", MapGraphAlgorithms.BFS(completeMap, "LX") == null);

        LinkedList<String> path = MapGraphAlgorithms.BFS(completeMap, "Faro");
        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();
        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = MapGraphAlgorithms.BFS(completeMap, "Porto");
        assertTrue("Should give seven vertices ", path.size() == 7);

        path = MapGraphAlgorithms.BFS(completeMap, "Viseu");
        assertTrue("Should give 3 vertices", path.size() == 3);
    }

    /**
     * Test of DFS method, of class GraphAlgorithms.
     */
    @Test
    public void testDFS() {
        System.out.println("Test of DFS");

        LinkedList<String> path;

        assertTrue("Should be null if vertex does not exist", MapGraphAlgorithms.DFS(completeMap, "LX") == null);

        path = MapGraphAlgorithms.DFS(completeMap, "Faro");
        assertTrue("Should be just one", path.size() == 1);

        Iterator<String> it = path.iterator();
        assertTrue("it should be Faro", it.next().compareTo("Faro") == 0);

        path = MapGraphAlgorithms.DFS(completeMap, "Porto");
        assertTrue("Should give seven vertices ", path.size() == 7);

        path = MapGraphAlgorithms.DFS(completeMap, "Viseu");
        assertTrue("Should give 3 vertices", path.size() == 3);

        it = path.iterator();
        assertTrue("First in visit should be Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);
    }

    /**
     * Test of allPaths method, of class GraphAlgorithms.
     */
    @Test
    public void testAllPaths() {
        System.out.println("Test of all paths");

        ArrayList<LinkedList<String>> paths = new ArrayList<>();

        assertFalse("There should not be paths if vertex does not exist",
                MapGraphAlgorithms.allPaths(completeMap, "Porto", "LX") == null);

        paths = MapGraphAlgorithms.allPaths(completeMap, "Porto", "Lisboa");
        assertTrue("There should be 4 paths", paths.size() == 4);

        paths = MapGraphAlgorithms.allPaths(completeMap, "Porto", "Faro");
        assertTrue("There should not be paths between Porto and Faro in the incomplete map", paths.isEmpty());
    }

    /**
     * Test of shortestPath method, of class GraphAlgorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<>();
        double lenpath = 0;
        lenpath = MapGraphAlgorithms.shortestPath(completeMap, "Porto", "LX", shortPath);
        assertTrue("Length path should be 0 if vertex does not exist", shortPath.size() == 0);

        lenpath = MapGraphAlgorithms.shortestPath(completeMap, "Porto", "Faro", shortPath);
        assertTrue("Length path should be 0 if there is no path", shortPath.size() == 0);

        lenpath = MapGraphAlgorithms.shortestPath(completeMap, "Porto", "Porto", shortPath);
        assertTrue("Length path should be 1 if source and vertex are the same", shortPath.size() == 1);

        lenpath = MapGraphAlgorithms.shortestPath(completeMap, "Porto", "Lisboa", shortPath);
        assertTrue("Path between Porto and Lisboa should be 335 Km", lenpath == 335);

        Iterator<String> it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Coimbra", it.next().compareTo("Coimbra") == 0);
        assertTrue("then Lisboa", it.next().compareTo("Lisboa") == 0);

        lenpath = MapGraphAlgorithms.shortestPath(completeMap, "Braga", "Leiria", shortPath);
        assertTrue("Path between Braga and Leiria should be 255 Km", lenpath == 255);

        it = shortPath.iterator();

        assertTrue("First in path should be Braga", it.next().compareTo("Braga") == 0);
        assertTrue("then Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);

        completeMap.insertEdge("Aveiro", "Viseu", "A25", 85);

        shortPath.clear();
        lenpath = MapGraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue("Path between Porto and Castelo Branco should be 335 Km", lenpath == 335);
        assertTrue("N. cities between Porto and Castelo Branco should be 5 ", shortPath.size() == 5);

        it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Viseu", it.next().compareTo("Viseu") == 0);
        assertTrue("then Guarda", it.next().compareTo("Guarda") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco 
        //should change shortest path between Porto and Castelo Branco
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.insertEdge("Leiria", "Castelo Branco", "A23", 170);
        shortPath.clear();
        lenpath = MapGraphAlgorithms.shortestPath(completeMap, "Porto", "Castelo Branco", shortPath);
        assertTrue("Path between Porto and Castelo Branco should now be 365 Km", lenpath == 365);
        assertTrue("Path between Porto and Castelo Branco should be 4 cities", shortPath.size() == 4);

        it = shortPath.iterator();

        assertTrue("First in path should be Porto", it.next().compareTo("Porto") == 0);
        assertTrue("then Aveiro", it.next().compareTo("Aveiro") == 0);
        assertTrue("then Leiria", it.next().compareTo("Leiria") == 0);
        assertTrue("then Castelo Branco", it.next().compareTo("Castelo Branco") == 0);

    }
}
