package lapr.project.utils.graph.map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests a Map Edge.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class MapEdgeTest {

    private MapEdge<Integer, Integer> instance;

    @Before
    public void setUp() {

        instance = new MapEdge<>(
                0,
                1d,
                new MapVertex<Integer, Integer>(1, 1),
                new MapVertex<Integer, Integer>(1, 2));
    }

    /**
     * Test of getElement method, of class MapEdge.
     */
    @Test
    public void testGetSetElement() {
        System.out.println("getSetElement");
        instance.setElement(1);
        int expResult = 1;
        int result = instance.getElement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class MapEdge.
     */
    @Test
    public void testGetSetWeight() {
        System.out.println("getSetWeight");
        instance.setWeight(2.0);
        double expResult = 2.0;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getVOrig method, of class MapEdge.
     */
    @Test
    public void testGetSetVOrig() {
        System.out.println("getSetVOrig");
        instance.setVOrig(new MapVertex<>(1, 0));
        int expResult = 0;
        int result = instance.getVOrig();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVDest method, of class MapEdge.
     */
    @Test
    public void testGetSetVDest() {
        System.out.println("getSetVDest");
        instance.setVDest(new MapVertex<>(1, 1));
        int expResult = 1;
        int result = instance.getVDest();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndpoints method, of class MapEdge.
     */
    @Test
    public void testGetEndpoints() {
        System.out.println("getEndpoints");
        Integer[] expResult = {1, 2};
        Integer[] result = instance.getEndpoints();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of equals method, of class MapEdge.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object instance2 = new MapEdge<>(
                0,
                1d,
                new MapVertex<Integer, Integer>(1, 1),
                new MapVertex<Integer, Integer>(1, 2));
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class MapEdge.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        Object instance2 = new MapEdge();
        boolean result = instance.equals(instance2);
        assertFalse(result);
    }
}
