/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import javax.measure.quantity.Duration;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests an Algorithm analysis.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AlgorithmAnalysisTest {

    private AlgorithmAnalysis instance;

    @Before
    public void setUp() {
        instance = new AlgorithmAnalysis(Amount.valueOf(1d, SI.METER), Amount.valueOf(1d, SI.SECOND), Amount.valueOf(1d, SI.KILOGRAM));
    }

    /**
     * Test of getDistance method, of class AlgorithmAnalysis.
     */
    @Test
    public void testGetSetDistance() {
        System.out.println("getSetDistance");
        Amount<Length> expResult = Amount.valueOf(10d, SI.METER);
        instance.setDistance(expResult);
        Amount<Length> result = instance.getDistance();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDuration method, of class AlgorithmAnalysis.
     */
    @Test
    public void testGetSetDuration() {
        System.out.println("getSetDuration");
        Amount<Duration> expResult = Amount.valueOf(10d, SI.SECOND);
        instance.setDuration(expResult);
        Amount<Duration> result = instance.getDuration();
        assertEquals(expResult, result);
    }

    /**
     * Test of getConsumption method, of class AlgorithmAnalysis.
     */
    @Test
    public void testGetSetConsumption() {
        System.out.println("getSetConsumption");
        Amount<Mass> expResult = Amount.valueOf(10d, SI.KILOGRAM);
        instance.setConsumption(expResult);
        Amount<Mass> result = instance.getConsumption();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class AlgorithmAnalysis.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Object instance2 = new AlgorithmAnalysis(Amount.valueOf(1d, SI.METER), Amount.valueOf(1d, SI.SECOND), Amount.valueOf(1d, SI.KILOGRAM));
        int expResult = instance2.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AlgorithmAnalysis.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object instance2 = new AlgorithmAnalysis(Amount.valueOf(1d, SI.METER), Amount.valueOf(1d, SI.SECOND), Amount.valueOf(1d, SI.KILOGRAM));
        boolean result = instance.equals(instance2);
        assertTrue(result);
    }

    /**
     * Test of equals method, of class AlgorithmAnalysis.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Object obj = null;
        AlgorithmAnalysis instance2 = new AlgorithmAnalysis();
        boolean result = instance.equals(obj);
        assertFalse(result);
    }

}
