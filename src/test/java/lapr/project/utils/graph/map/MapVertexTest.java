package lapr.project.utils.graph.map;

import lapr.project.utils.graph.MapEdge;
import lapr.project.utils.graph.MapVertex;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a Map Vertex.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class MapVertexTest {

    /**
     * Test vertex object.
     */
    private MapVertex<Integer, Integer> instance;

    @Before
    public void setUp() {

        instance = new MapVertex<>(1, 1);
    }

    /**
     * Test of getKey method, of class MapVertex.
     */
    @Test
    public void testGetSetKey() {
        System.out.println("getSetKey");
        instance.setKey(0);
        int expResult = 0;
        int result = instance.getKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getElement method, of class MapVertex.
     */
    @Test
    public void testGetSetElement() {
        System.out.println("getSetElement");
        instance.setElement(0);
        int expResult = 0;
        int result = instance.getElement();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAdjVert method, of class MapVertex.
     */
    @Test
    public void testAddAdjVert01() {
        System.out.println("addAdjVert");
        instance.addAdjVert(2, new MapEdge<>());
        assertTrue(instance.numAdjVerts() == 1);
    }

    /**
     * Test of addAdjVert method, of class MapVertex.
     */
    @Test
    public void testAddAdjVert02() {
        System.out.println("addAdjVert");
        instance.addAdjVert(2, new MapEdge<>());
        assertFalse(instance.numAdjVerts() == 0);
    }

    /**
     * Test of getAdjVert method, of class MapVertex.
     */
    @Test
    public void testGetAdjVert() {
        System.out.println("getAdjVert");
        MapEdge<Integer, Integer> edge = new MapEdge<>(
                0,
                1d,
                new MapVertex<Integer, Integer>(1, 1),
                new MapVertex<Integer, Integer>(1, 2));
        instance.addAdjVert(1, edge);
        int expResult = 1;
        Object result = instance.getAdjVert(edge);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class MapVertex.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object otherObj = new MapVertex();
        MapVertex instance2 = (MapVertex) otherObj;
        boolean result = instance2.equals(otherObj);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class MapVertex.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        Object otherObj = new MapVertex();
        boolean result = instance.equals(otherObj);
        assertFalse(result);
    }
}
