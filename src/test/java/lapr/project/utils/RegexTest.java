/**
 * Package location for Pure Fabrication util classes tests.
 */
package lapr.project.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the regex class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class RegexTest {

    private static final Double EPSILON = 0.01d;

    /**
     * Test 1 of getValue method, of class Regex.
     */
    @Test
    public void testGetValue1() {
        System.out.println("getValue 1");

        String valueWithUnit = "12820 US";

        Double expResult = 12820d;
        Double result = Regex.getValue(valueWithUnit);

        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test 1 of getUnit method, of class Regex.
     */
    @Test
    public void testGetUnit1() {
        System.out.println("getUnit 1");

        String valueWithUnit = "12820 US";

        String expResult = "US";
        String result = Regex.getUnit(valueWithUnit);

        assertEquals(expResult, result);
    }

    /**
     * Test 2 of getValue method, of class Regex.
     */
    @Test
    public void testGetValue2() {
        System.out.println("getValue 2");

        String valueWithUnit = "875E+03 US";

        Double expResult = 875E+03;
        Double result = Regex.getValue(valueWithUnit);

        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test 2 of getUnit method, of class Regex.
     */
    @Test
    public void testGetUnit2() {
        System.out.println("getUnit 2");

        String valueWithUnit = "875E+03 US";

        String expResult = "US";
        String result = Regex.getUnit(valueWithUnit);

        assertEquals(expResult, result);
    }

    /**
     * Test 3 of getValue method, of class Regex.
     */
    @Test
    public void testGetValue3() {
        System.out.println("getValue 3");

        String valueWithUnit = "365 Knot";

        Double expResult = 365D;
        Double result = Regex.getValue(valueWithUnit);

        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test 3 of getUnit method, of class Regex.
     */
    @Test
    public void testGetUnit3() {
        System.out.println("getUnit 3");

        String valueWithUnit = "365 Knot";

        String expResult = "Knot";
        String result = Regex.getUnit(valueWithUnit);

        assertEquals(expResult, result);
    }

    /**
     * Test 4 of getValue method, of class Regex.
     */
    @Test
    public void testGetValue4() {
        System.out.println("getValue 4");

        String valueWithUnit = "0.564 UTF-8S";

        Double expResult = 0.564d;
        Double result = Regex.getValue(valueWithUnit);

        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test 4 of getUnit method, of class Regex.
     */
    @Test
    public void testGetUnit4() {
        System.out.println("getUnit 4");

        String valueWithUnit = "0.564 UTF-8S";

        String expResult = "UTF-8S";
        String result = Regex.getUnit(valueWithUnit);

        assertEquals(expResult, result);
    }

    /**
     * Test 5 of getValue method, of class Regex.
     */
    @Test
    public void testGetValue5() {
        System.out.println("getValue 5");

        String valueWithUnit = "394.09E+03 US";

        Double expResult = 394.09E+03;
        Double result = Regex.getValue(valueWithUnit);

        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test 5 of getUnit method, of class Regex.
     */
    @Test
    public void testGetUnit5() {
        System.out.println("getUnit 5");

        String valueWithUnit = "394.09E+03 US";

        String expResult = "US";
        String result = Regex.getUnit(valueWithUnit);

        assertEquals(expResult, result);
    }
}
