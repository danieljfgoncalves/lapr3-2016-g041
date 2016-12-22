/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.quantity.VolumetricDensity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import org.jscience.physics.amount.Amount;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the calculus class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class CalculusTest {

    /**
     * Test 1 of getAirDensity method, of class Calculus.
     */
    @Test
    public void testGetAirDensity1() {
        System.out.println("getAirDensity 1");

        Amount<Length> altitude = Amount.valueOf(7499, SI.METER);
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(5.900, SI.KILOGRAM.divide(SI.CUBIC_METRE));
        Amount<VolumetricDensity> result = Calculus.getAirDensity(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test 2 of getAirDensity method, of class Calculus.
     */
    @Test
    public void testGetAirDensity2() {
        System.out.println("getAirDensity 2");

        Amount<Length> altitude = Amount.valueOf(7501, SI.METER);
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(5.258, SI.KILOGRAM.divide(SI.CUBIC_METRE));
        Amount<VolumetricDensity> result = Calculus.getAirDensity(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test 3 of getAirDensity method, of class Calculus.
     */
    @Test
    public void testGetAirDensity3() {
        System.out.println("getAirDensity 3");

        Amount<Length> altitude = Amount.valueOf(10, SI.METER);
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(12.25, SI.KILOGRAM.divide(SI.CUBIC_METRE));
        Amount<VolumetricDensity> result = Calculus.getAirDensity(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test 4 of getAirDensity method, of class Calculus.
     */
    @Test
    public void testGetAirDensity4() {
        System.out.println("getAirDensity 4");

        Amount<Length> altitude = Amount.valueOf(991231, SI.METER);
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(0.0001846, SI.KILOGRAM.divide(SI.CUBIC_METRE));
        Amount<VolumetricDensity> result = Calculus.getAirDensity(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test 1 of getSpeedOfSound method, of class Calculus.
     */
    @Test
    public void testGetSpeedOfSound1() {
        System.out.println("getSpeedOfSound 1");

        Amount<Length> altitude = Amount.valueOf(23.4, NonSI.FOOT);
        Amount<Velocity> expResult = Amount.valueOf(309.6, SI.METERS_PER_SECOND);
        Amount<Velocity> result = Calculus.getSpeedOfSound(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test 2 of getSpeedOfSound method, of class Calculus.
     */
    @Test
    public void testGetSpeedOfSound2() {
        System.out.println("getSpeedOfSound 2");

        Amount<Length> altitude = Amount.valueOf(55, NonSI.FOOT);
        Amount<Velocity> expResult = Amount.valueOf(294.9, SI.METERS_PER_SECOND);
        Amount<Velocity> result = Calculus.getSpeedOfSound(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test 3 of getSpeedOfSound method, of class Calculus.
     */
    @Test
    public void testGetSpeedOfSound3() {
        System.out.println("getSpeedOfSound 3");

        Amount<Length> altitude = Amount.valueOf(21.9, NonSI.FOOT);
        Amount<Velocity> expResult = Amount.valueOf(316.0, SI.METERS_PER_SECOND);
        Amount<Velocity> result = Calculus.getSpeedOfSound(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test 4 of getSpeedOfSound method, of class Calculus.
     */
    @Test
    public void testGetSpeedOfSound4() {
        System.out.println("getSpeedOfSound 4");

        Amount<Length> altitude = Amount.valueOf(-10, NonSI.FOOT);
        Amount<Velocity> expResult = Amount.valueOf(340.3, SI.METERS_PER_SECOND);
        Amount<Velocity> result = Calculus.getSpeedOfSound(altitude);

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test of getDragCoefficient method, of class Calculus.
     */
    @Test
    public void testGetDragCoefficient() {
        System.out.println("getDragCoefficient");

        Amount<Dimensionless> expResult = Amount.valueOf((0.025003301540594155), Unit.ONE);
        Amount<Dimensionless> result = Calculus.getDragCoefficient(Amount.valueOf(69.0, SI.METER),
                Amount.valueOf(250000, SI.KILOGRAM),
                Amount.valueOf(0.025, Unit.ONE),
                Amount.valueOf(64.8, SI.METER),
                Amount.valueOf(512, SI.SQUARE_METRE),
                Amount.valueOf(0.95, Unit.ONE));

        assertTrue(expResult.approximates(result));
    }

    /**
     * Test of distance method, of class Calculus.
     */
    @Test
    public void testDistance01() {
        System.out.println("distance01");
        Coordinate first = new Coordinate("01", 43.6426, -79.3871);
        Coordinate second = new Coordinate("02", 38.6916, -9.2160);
        Amount<Length> altitude = Amount.valueOf(0.0, SI.METER);
        double expResult = 5722d; // KM
        double result = Calculus.distance(first, second, altitude).doubleValue(SI.KILOMETER);
        assertEquals(expResult, result, 1);
    }

    /**
     * Test of distance method, of class Calculus.
     */
    @Test
    public void testDistance02() {
        System.out.println("distance02");
        Coordinate first = new Coordinate("01", 43.6426, -79.3871);
        Coordinate second = new Coordinate("02", 38.6916, -9.2160);
        Amount<Length> altitude = Amount.valueOf(0.0, SI.METER);
        double expResult = 3555.486d; // Mile
        double result = Calculus.distance(first, second, altitude).doubleValue(NonSI.MILE);
        assertEquals(expResult, result, 1);
    }

    /**
     * Test of distance method, of class Calculus.
     */
    @Test
    public void testDistance03() {
        System.out.println("distance03");
        Coordinate first = new Coordinate("01", 43.6426, -79.3871);
        Coordinate second = new Coordinate("02", 38.6916, -9.2160);
        Amount<Length> altitude = Amount.valueOf(0.0, SI.METER);
        double expResult = 5722d; // KM
        double result = Calculus.distance(first, second, altitude).doubleValue(NonSI.MILE); // Returns in Miles
        assertFalse(Math.abs(expResult - result) < 1);
    }
}
