/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Power;
import javax.measure.quantity.Velocity;
import javax.measure.quantity.Volume;
import javax.measure.quantity.VolumetricDensity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import lapr.project.utils.CustomUnits;
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
        assertFalse(Math.abs(expResult - result) < 1 /* Error Margin */);
    }

    /**
     * Test of direction method, of class Calculus.
     */
    @Test
    public void testDirection01() {
        System.out.println("direction01");
        Coordinate first = new Coordinate("01", 1.0, -79.3871);
        Coordinate second = new Coordinate("02", 1.0, 9.2160);
        double expResult = 90d; // º Degree
        double result = Calculus.direction(first, second).doubleValue(NonSI.DEGREE_ANGLE); // Returns in Degrees
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of direction method, of class Calculus.
     */
    @Test
    public void testDirection02() {
        System.out.println("direction02");
        Coordinate first = new Coordinate("01", 2.0, -79.3871);
        Coordinate second = new Coordinate("02", 1.0, 9.2160);
        double expResult = 1.5708d; // Radians
        double result = Calculus.direction(first, second).doubleValue(SI.RADIAN); // Returns in Radians
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of direction method, of class Calculus. //
     */
//    @Test
    public void testDirection03() {
        System.out.println("direction02");
        Coordinate first = new Coordinate("01", 43.6426, -79.3871);
        Coordinate second = new Coordinate("02", 38.6916, -9.2160);
        double expResult = (180 + 360) % 360; // Radians
        double result = Calculus.direction(first, second).doubleValue(NonSI.DEGREE_ANGLE); // Returns in Degrees
        assertFalse(Math.abs(expResult - result) < 0.1 /* Error Margin */);
    }

    /**
     * Test of getLiftForce method, of class Calculus.
     */
    @Test
    public void testGetLiftForce() {
        System.out.println("getLiftForce");

        Amount<Length> altitude = Amount.valueOf(10668, SI.METER);
        Amount<Mass> mass = Amount.valueOf(372800, SI.KILOGRAM);
        Amount<Area> wingsArea = Amount.valueOf(512, SI.SQUARE_METRE);
        Amount<Velocity> machNumber = Amount.valueOf(0.85, NonSI.MACH);
        Amount<Velocity> windSpeed = Amount.valueOf(41.1556, SI.METERS_PER_SECOND);
        Amount<Angle> angleRelativeToY = Amount.valueOf(15, NonSI.DEGREE_ANGLE);

        Amount<Force> expResult = Amount.valueOf(3655919.12, SI.NEWTON);
        Amount<Force> result = Calculus.getLiftForce(altitude, mass, wingsArea, machNumber, windSpeed, angleRelativeToY);

        assertEquals(expResult.doubleValue(SI.NEWTON), result.doubleValue(SI.NEWTON), EPSILON);
    }

    /**
     * Test of getLiftCoefficient method, of class Calculus.
     */
    @Test
    public void testGetLiftCoefficient() {
        System.out.println("getLiftCoefficient");

        Amount<Length> altitude = Amount.valueOf(35000, NonSI.FOOT);
        Amount<Mass> mass = Amount.valueOf(372800, SI.KILOGRAM);
        Amount<Area> wingsArea = Amount.valueOf(512, SI.SQUARE_METRE);
        Amount<Velocity> machNumber = Amount.valueOf(0.85, NonSI.MACH);
        Amount<Velocity> windSpeed = Amount.valueOf(41.1556, SI.METERS_PER_SECOND);
        Amount<Angle> angleRelativeToY = Amount.valueOf(15, NonSI.DEGREE_ANGLE);

        Amount<Dimensionless> expResult = Amount.valueOf(0.5, Unit.ONE);
        Amount<Dimensionless> result = Calculus.getLiftCoefficient(altitude, mass, wingsArea, machNumber, windSpeed, angleRelativeToY);

        assertEquals(expResult.doubleValue(Unit.ONE), result.doubleValue(Unit.ONE), EPSILON);
    }

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
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(5.900e-1, SI.KILOGRAM.divide(SI.CUBIC_METRE));
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
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(5.258e-1, SI.KILOGRAM.divide(SI.CUBIC_METRE));
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
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(12.25e-1, SI.KILOGRAM.divide(SI.CUBIC_METRE));
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
        Amount<VolumetricDensity> expResult = (Amount<VolumetricDensity>) Amount.valueOf(0.0001846e-1, SI.KILOGRAM.divide(SI.CUBIC_METRE));
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

        Amount<Dimensionless> expResult = Amount.valueOf((0.035), Unit.ONE);
        Amount<Dimensionless> result = Calculus.getDragCoefficient(Amount.valueOf(10668.0, SI.METER),
                Amount.valueOf(372800, SI.KILOGRAM),
                Amount.valueOf(0.025, Unit.ONE),
                Amount.valueOf(64.8, SI.METER),
                Amount.valueOf(512, SI.SQUARE_METRE),
                Amount.valueOf(0.95, Unit.ONE),
                Amount.valueOf(0.85, NonSI.MACH),
                Amount.valueOf(41.1556, SI.METERS_PER_SECOND),
                Amount.valueOf(15, NonSI.DEGREE_ANGLE));

        assertEquals(expResult.doubleValue(Unit.ONE), result.doubleValue(Unit.ONE), 0.001d);
    }

    /**
     * Test of getDragCoefficient method, of class Calculus.
     */
    @Test
    public void testGetDragForce() {
        System.out.println("testGetDragForce");

        Amount<Force> expResult = Amount.valueOf((25058.4), SI.NEWTON);
        Amount<Force> result = Calculus.getDragForce(Amount.valueOf(10668.0, SI.METER),
                Amount.valueOf(372800, SI.KILOGRAM),
                Amount.valueOf(0.025, Unit.ONE),
                Amount.valueOf(64.8, SI.METER),
                Amount.valueOf(512, SI.SQUARE_METRE),
                Amount.valueOf(0.95, Unit.ONE),
                Amount.valueOf(50, SI.SQUARE_METRE),
                Amount.valueOf(0.85, NonSI.MACH),
                Amount.valueOf(41.1556, SI.METERS_PER_SECOND),
                Amount.valueOf(15, NonSI.DEGREE_ANGLE));

        assertEquals(expResult.doubleValue(SI.NEWTON), result.doubleValue(SI.NEWTON), EPSILON);
    }

    /**
     * Test of getMaximumRange method, of class Calculus.
     */
    @Test
    public void testGetMaximumRange() {
        System.out.println("getMaximumRange");

        Amount<Power> tsfc = Amount.valueOf(0.057, CustomUnits.TSFC_SI);
        Amount<Mass> initialWeight = Amount.valueOf(372800, SI.KILOGRAM);
        Amount<Mass> finalWeight = Amount.valueOf(272800, SI.KILOGRAM);
        Amount<Length> altitude = Amount.valueOf(10668, SI.METER);
        Amount<Velocity> machNumber = Amount.valueOf(0.85, NonSI.MACH);
        Amount<Velocity> windSpeed = Amount.valueOf(41.1556, SI.METERS_PER_SECOND);
        Amount<Angle> angleRelativeToY = Amount.valueOf(15, NonSI.DEGREE_ANGLE);
        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(0.025, Unit.ONE);
        Amount<Length> wingSpan = Amount.valueOf(64.8, SI.METER);
        Amount<Dimensionless> e = Amount.valueOf(0.95, Unit.ONE);
        Amount<Area> referenceAircraftArea = Amount.valueOf(50, SI.SQUARE_METRE);
        Amount<Area> wingsArea = Amount.valueOf(512, SI.SQUARE_METRE);

        Amount<Length> expResult = Amount.valueOf(209226.5, SI.KILOMETER);
        Amount<Length> result = Calculus.getMaximumRange(tsfc, initialWeight, finalWeight,
                altitude, machNumber, windSpeed, angleRelativeToY,
                dragCoefficient0, wingSpan, e, referenceAircraftArea, wingsArea);
        assertEquals(expResult.doubleValue(SI.KILOMETER), result.doubleValue(SI.KILOMETER), EPSILON);
    }

    /**
     * Test of getFuelConsumption method, of class Calculus.
     */
    @Test
    public void testGetFuelConsumption() {
        System.out.println("getFuelConsumption");

        Amount<Power> tsfc = Amount.valueOf(0.057, CustomUnits.TSFC_SI);
        Amount<Mass> initialWeight = Amount.valueOf(372800, SI.KILOGRAM);
        Amount<Length> altitude = Amount.valueOf(10668, SI.METER);
        Amount<Velocity> machNumber = Amount.valueOf(0.85, NonSI.MACH);
        Amount<Velocity> windSpeed = Amount.valueOf(41.1556, SI.METERS_PER_SECOND);
        Amount<Angle> angleRelativeToY = Amount.valueOf(15, NonSI.DEGREE_ANGLE);
        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(0.025, Unit.ONE);
        Amount<Length> wingSpan = Amount.valueOf(64.8, SI.METER);
        Amount<Dimensionless> e = Amount.valueOf(0.95, Unit.ONE);
        Amount<Area> referenceAircraftArea = Amount.valueOf(50, SI.SQUARE_METRE);
        Amount<Area> wingsArea = Amount.valueOf(512, SI.SQUARE_METRE);
        Amount<Length> distance = Amount.valueOf(209226.5, SI.KILOMETER);

        Amount<Volume> expResult = Amount.valueOf(100000, NonSI.LITER);
        Amount<Volume> result = Calculus.getFuelConsumption(tsfc, initialWeight, altitude, machNumber,
                windSpeed, angleRelativeToY, dragCoefficient0,
                wingSpan, e, referenceAircraftArea, wingsArea, distance);
        assertEquals(expResult.doubleValue(NonSI.LITER), result.doubleValue(NonSI.LITER), EPSILON);
    }

}
