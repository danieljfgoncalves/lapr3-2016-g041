/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Angle;
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

    private static final double EPSILON = 0.1d;

    /**
     * Test 1 of getObjectSpeed method, of class Calculus.
     */
    @Test
    public void testGetObjectSpeed1() {
        System.out.println("getObjectSpeed 1");

        Amount<Length> altitude = Amount.valueOf(10668, SI.METER);
        Amount<Velocity> machNumber = Amount.valueOf(0.85, NonSI.MACH);

        Amount<Velocity> expResult = Amount.valueOf(251.1, SI.METERS_PER_SECOND);
        Amount<Velocity> result = Calculus.getObjectSpeed(altitude, machNumber);

        assertEquals(expResult.doubleValue(SI.METERS_PER_SECOND), result.doubleValue(SI.METERS_PER_SECOND), EPSILON);
    }

    /**
     * Test 1 of getPortionWindSpeed method, of class Calculus.
     */
    @Test
    public void testGetPortionWindSpeed1() {
        System.out.println("getPortionWindSpeed 1");

        Amount<Velocity> windSpeed = Amount.valueOf(41.1556, SI.METERS_PER_SECOND);
        Amount<Angle> angleRelativeToY = Amount.valueOf(15, NonSI.DEGREE_ANGLE);

        Amount<Velocity> expResult = Amount.valueOf(10.65, SI.METERS_PER_SECOND);
        Amount<Velocity> result = Calculus.getPortionWindSpeed(windSpeed, angleRelativeToY);

        assertEquals(expResult.doubleValue(SI.METERS_PER_SECOND), result.doubleValue(SI.METERS_PER_SECOND), EPSILON);
    }

    /**
     * Test 1 of getTAS method, of class Calculus.
     */
    @Test
    public void testGetTAS1() {
        System.out.println("getTAS 1");

        Amount<Length> altitude = Amount.valueOf(10668, SI.METER);
        Amount<Velocity> machNumber = Amount.valueOf(0.85, NonSI.MACH);
        Amount<Velocity> windSpeed = Amount.valueOf(41.1556, SI.METERS_PER_SECOND);
        Amount<Angle> angleRelativeToY = Amount.valueOf(15, NonSI.DEGREE_ANGLE);

        Amount<Velocity> expResult = Amount.valueOf(261.75, SI.METERS_PER_SECOND);
        Amount<Velocity> result = Calculus.getTAS(altitude, machNumber, windSpeed, angleRelativeToY);

        assertEquals(expResult.doubleValue(SI.METERS_PER_SECOND), result.doubleValue(SI.METERS_PER_SECOND), EPSILON);
    }

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

        Amount<Length> altitude = Amount.valueOf(23000.4, NonSI.FOOT);
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

        Amount<Length> altitude = Amount.valueOf(55000, NonSI.FOOT);
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

        Amount<Length> altitude = Amount.valueOf(21000.9, NonSI.FOOT);
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

        Amount<Length> altitude = Amount.valueOf(-10000, NonSI.FOOT);
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

        Amount<Dimensionless> expResult = Amount.valueOf((0.025001861949660857), Unit.ONE);
        Amount<Dimensionless> result = Calculus.getDragCoefficient(Amount.valueOf(69.0, SI.METER),
                Amount.valueOf(250000, SI.KILOGRAM),
                Amount.valueOf(0.025, Unit.ONE),
                Amount.valueOf(64.8, SI.METER),
                Amount.valueOf(512, SI.SQUARE_METRE),
                Amount.valueOf(0.95, Unit.ONE));

        assertTrue(expResult.approximates(result));
    }

}
