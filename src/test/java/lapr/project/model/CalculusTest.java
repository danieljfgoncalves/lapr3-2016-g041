/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Power;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Velocity;
import javax.measure.quantity.VolumetricDensity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import lapr.project.utils.CustomUnits;
import org.jscience.physics.amount.Amount;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests the calculus class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class CalculusTest {

    private FlightSimulation flight = new FlightSimulation();

    private FlightPattern flightPattern = new FlightPattern();

    @Before
    public void setUp() throws Exception {

        flightPattern.insertLine(Amount.valueOf(0, SI.METER), Amount.valueOf(210, NonSI.KNOT), Amount.valueOf(180, NonSI.KNOT), Amount.valueOf(-5, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(1000, SI.METER), Amount.valueOf(210, NonSI.KNOT), Amount.valueOf(200, NonSI.KNOT), Amount.valueOf(-7, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(2000, SI.METER), Amount.valueOf(220, NonSI.KNOT), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(-7, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(3000, SI.METER), Amount.valueOf(230, NonSI.KNOT), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(-8, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(4000, SI.METER), Amount.valueOf(250, NonSI.KNOT), Amount.valueOf(270, NonSI.KNOT), Amount.valueOf(-8, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(5000, SI.METER), Amount.valueOf(260, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(6000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(7000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(8000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(9000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(10000, SI.METER), Amount.valueOf(290, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(11000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(12000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(13000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));
        flightPattern.insertLine(Amount.valueOf(14000, SI.METER), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(300, NonSI.KNOT), Amount.valueOf(-10, SI.METERS_PER_SECOND));

        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setLapseRateFactor(Amount.valueOf(0.96, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setWingArea(Amount.valueOf(858, SI.SQUARE_METRE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setAspectRatio(Amount.valueOf(9, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setCruiseAltitude(Amount.valueOf(10061, SI.METER));
        flight.getFlightInfo().getAircraft().getAircraftModel().setE(Amount.valueOf(0.84, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrust0(Amount.valueOf(3.38E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrustMaxSpeed(Amount.valueOf(1.80E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setMaxSpeed(Amount.valueOf(0.9, NonSI.MACH));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setNumberOfMotors(4);
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setTsfc(Amount.valueOf(1.60E-04, CustomUnits.TSFC_NNS));
        flight.setEffectiveCargo(Amount.valueOf(70000, SI.KILOGRAM));
        flight.setEffectiveFuel(Amount.valueOf(144720, SI.KILOGRAM));
        flight.getFlightInfo().getAircraft().getAircraftModel().setEmptyWeight(Amount.valueOf(3.00E+05, SI.KILOGRAM));
    }

    /**
     * Test of compassToDecimal method, of class Calculus.
     */
    @Test
    public void testCompassToDecimal() {
        System.out.println("compassToDecimal");
        double degrees = 43.0;
        double minutes = 38.0;
        double seconds = 33.36;
        Calculus.DIRECTION direction = Calculus.DIRECTION.NORTH;

        double expResult = 43.6426;
        double result = Calculus.compassToDecimal(degrees, minutes, seconds, direction);
        assertEquals(expResult, result, 0.001);
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
        assertEquals(expResult, result, 0.5);
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
     * Test of virtualDistance method, of class Calculus.
     */
    @Test
    public void testVirtualDistance() {
        System.out.println("virtualDistance");
        double realDistance = 1000000.0;
        Segment segment = new Segment();
        FlightSimulation flight = new FlightSimulation();
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setCruiseAltitude(Amount.valueOf(2135.36, SI.METER));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setCruiseSpeed(Amount.valueOf(122.3242157, SI.METERS_PER_SECOND));
        segment.setWindIntensity(Amount.valueOf(80, NonSI.KNOT));
        segment.setWindDirection(Amount.valueOf(15, NonSI.DEGREE_ANGLE));
        Coordinate first = new Coordinate("a", 1.0, 20.0);
        Coordinate second = new Coordinate("b", 1.0, 100.0);

        double expResult = 919984.6120;
        double result = Calculus.virtualDistance(realDistance, flight, segment, first, second).doubleValue(SI.METER);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test 1 of getAirDensity method, of class Calculus.
     */
    @Test
    public void testGetAirDensity1() {
        System.out.println("getAirDensity 1");
        Amount<Pressure> press = Amount.valueOf(78170.97551, SI.PASCAL);
        Amount<Temperature> temp = Amount.valueOf(274.3201598, SI.KELVIN);

        double expResult = 0.992900912;
        double result = Calculus.getAirDensity(press, temp).doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test 2 of getAirDensity method, of class Calculus.
     */
    @Test
    public void testGetAirDensity2() {
        System.out.println("getAirDensity 2");
        Amount<Pressure> press = Amount.valueOf(10000.97551, SI.PASCAL);
        Amount<Temperature> temp = Amount.valueOf(274.3201598, SI.KELVIN);

        double expResult = 0.992900912;
        double result = Calculus.getAirDensity(press, temp).doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI);
        assertNotEquals(expResult, result, 0.01);
    }

    /**
     * Test 1 of getSpeedOfSound method, of class Calculus.
     */
    @Test
    public void testGetSpeedOfSound1() {
        System.out.println("getSpeedOfSound 1");
        Amount<Temperature> temp = Amount.valueOf(274.3201598, SI.KELVIN);

        double expResult = Amount.valueOf(331.9967473, SI.METERS_PER_SECOND).doubleValue(SI.METERS_PER_SECOND);
        double result = Calculus.getSpeedOfSound(temp).doubleValue(SI.METERS_PER_SECOND);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test 2 of getSpeedOfSound method, of class Calculus.
     */
    @Test
    public void testGetSpeedOfSound2() {
        System.out.println("getSpeedOfSound 2");
        Amount<Temperature> temp = Amount.valueOf(300.0, SI.KELVIN);

        double expResult = Amount.valueOf(331.9967473, SI.METERS_PER_SECOND).doubleValue(SI.METERS_PER_SECOND);
        double result = Calculus.getSpeedOfSound(temp).doubleValue(SI.METERS_PER_SECOND);
        assertNotEquals(expResult, result, 0.01);
    }

    /**
     * Test of getMachTrue method, of class Calculus.
     */
    @Test
    public void testGetMachTrue1() {
        System.out.println("getMachTrue");
        Amount<Velocity> ias = Amount.valueOf(220.0, NonSI.KNOT);
        Amount<VolumetricDensity> airDensity = Amount.valueOf(0.992900912, CustomUnits.VOLUMETRIC_DENSITY_SI);

        double expResult = Amount.valueOf(0.368450043, NonSI.MACH).doubleValue(NonSI.MACH);
        double result = Calculus.getMachTrue(ias, airDensity).doubleValue(NonSI.MACH);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateTAS method, of class Calculus.
     */
    @Test
    public void testCalculateTAS() {
        System.out.println("calculateTAS");

        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Velocity> machNumber = Amount.valueOf(0.368450043, NonSI.MACH);

        double expResult = Amount.valueOf(122.3242157, SI.METERS_PER_SECOND).doubleValue(SI.METERS_PER_SECOND);
        double result = Calculus.calculateTAS(altitude, machNumber).doubleValue(SI.METERS_PER_SECOND);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getTemperature method, of class Calculus.
     */
    @Test
    public void testGetTemperature() {
        System.out.println("getTemperature");
        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);

        double expResult = 274.3201598;
        double result = Calculus.getTemperature(altitude).doubleValue(SI.KELVIN);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getPressure method, of class Calculus.
     */
    @Test
    public void testGetPressure() {
        System.out.println("getPressure");
        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);

        double expResult = 78170.97551;
        double result = Calculus.getPressure(altitude).doubleValue(SI.PASCAL);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getPortionWindSpeed method, of class Calculus.
     */
    @Test
    public void testGetPortionWindSpeed() {
        System.out.println("getPortionWindSpeed");
        Amount<Velocity> windSpeed = Amount.valueOf(80, NonSI.KNOT);
        Amount<Angle> windDirection = Amount.valueOf(15, NonSI.DEGREE_ANGLE);
        Amount<Angle> flightDirection = Amount.valueOf(90, NonSI.DEGREE_ANGLE);

        double expResult = 10.65;
        double result = Calculus.getPortionWindSpeed(windSpeed, windDirection, flightDirection).doubleValue(SI.METERS_PER_SECOND);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of calculateGS method, of class Calculus.
     */
    @Test
    public void testCalculateGS() {
        System.out.println("calculateGS");
        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Velocity> machNumber = Amount.valueOf(0.368450043, NonSI.MACH);
        Amount<Velocity> windSpeed = Amount.valueOf(80, NonSI.KNOT);
        Amount<Angle> windDirection = Amount.valueOf(15, NonSI.DEGREE_ANGLE);
        Amount<Angle> flightDirection = Amount.valueOf(90, NonSI.DEGREE_ANGLE);

        double expResult = 132.98;
        double result = Calculus.calculateGS(altitude, machNumber, windSpeed, windDirection, flightDirection).doubleValue(SI.METERS_PER_SECOND);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getLiftCoefficient method, of class Calculus.
     */
    @Test
    public void testGetLiftCoefficient() {
        System.out.println("getLiftCoefficient");

        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Mass> mass = Amount.valueOf(5.13E+05, SI.KILOGRAM);
        Amount<Area> wingsArea = Amount.valueOf(858, SI.SQUARE_METRE);
        Amount<Velocity> machNumber = Amount.valueOf(0.368450043, NonSI.MACH);

        double expResult = 0.788830329;
        double result = Calculus.getLiftCoefficient(altitude, mass, wingsArea, machNumber).doubleValue(Unit.ONE);

        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getLiftForce method, of class Calculus.
     */
    @Test
    public void testGetLiftForce() {
        System.out.println("getLiftForce");

        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Mass> mass = Amount.valueOf(5.13E+05, SI.KILOGRAM);
        Amount<Area> wingsArea = Amount.valueOf(858, SI.SQUARE_METRE);
        Amount<Velocity> machNumber = Amount.valueOf(0.368450043, NonSI.MACH);

        double expResult = 5030811.449;
        double result = Calculus.getLiftForce(altitude, mass, wingsArea, machNumber).doubleValue(SI.NEWTON);

        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getDragCoefficient method, of class Calculus.
     */
    @Test
    public void testGetDragCoefficient() {
        System.out.println("getDragCoefficient");
        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Mass> mass = Amount.valueOf(5.13E+05, SI.KILOGRAM);
        Amount<Dimensionless> aspectRatio = Amount.valueOf(9, Unit.ONE);
        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(0.02, Unit.ONE);
        Amount<Area> wingArea = Amount.valueOf(858, SI.SQUARE_METRE);
        Amount<Dimensionless> e = Amount.valueOf(0.84, Unit.ONE);
        Amount<Velocity> machNumber = Amount.valueOf(0.368450043, NonSI.MACH);

        double expResult = 0.046199653;
        double result = Calculus.getDragCoefficient(altitude, mass, aspectRatio, dragCoefficient0, wingArea, e, machNumber).doubleValue(Unit.ONE);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getDragForce method, of class Calculus.
     */
    @Test
    public void testGetDragForce_7args() {
        System.out.println("getDragForce");
        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Mass> mass = Amount.valueOf(5.13E+05, SI.KILOGRAM);
        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(0.02, Unit.ONE);
        Amount<Area> wingArea = Amount.valueOf(858, SI.SQUARE_METRE);
        Amount<Dimensionless> e = Amount.valueOf(0.84, Unit.ONE);
        Amount<Velocity> machNumber = Amount.valueOf(0.368450043, NonSI.MACH);
        Amount<Dimensionless> aspectRatio = Amount.valueOf(9, Unit.ONE);

        double expResult = 294665.2845;
        double result = Calculus.getDragForce(altitude, mass, dragCoefficient0, wingArea, e, machNumber, aspectRatio).doubleValue(SI.NEWTON);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getDragForce method, of class Calculus.
     */
    @Test
    public void testGetDragForce_5args() {
        System.out.println("getDragForce");
        FlightSimulation flight = new FlightSimulation();
        double[][] cD0 = new double[3][2];
        cD0[0][1] = 0.02;
        flight.getFlightInfo().getAircraft().getAircraftModel().setCdragFunction(cD0);

        flight.getFlightInfo().getAircraft().getAircraftModel().setWingArea(Amount.valueOf(858, SI.SQUARE_METRE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setAspectRatio(Amount.valueOf(9, Unit.ONE));

        Amount<Length> altitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Mass> mass = Amount.valueOf(5.13E+05, SI.KILOGRAM);
        Amount<Dimensionless> e = Amount.valueOf(0.84, Unit.ONE);
        Amount<Velocity> machNumber = Amount.valueOf(0.368450043, NonSI.MACH);

        double expResult = 294665.2845;
        double result = Calculus.getDragForce(flight, altitude, mass, e, machNumber).doubleValue(SI.NEWTON);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getLambda method, of class Calculus.
     */
    @Test
    public void testGetLambda() {
        System.out.println("getLambda");
        Amount<Force> thrust_0 = Amount.valueOf(3.38E+05, SI.NEWTON);
        Amount<Force> thrustMaxSpeed = Amount.valueOf(1.80E+05, SI.NEWTON);
        Amount<Velocity> maxSpeed = Amount.valueOf(0.9, NonSI.MACH);

        double expResult = Amount.valueOf(175555.55, SI.NEWTON).doubleValue(SI.NEWTON);
        double result = Calculus.getLambda(thrust_0, thrustMaxSpeed, maxSpeed).doubleValue(SI.NEWTON);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getThrust method, of class Calculus.
     */
    @Test
    public void testGetThrust_4args() {
        System.out.println("getThrust");
        FlightSimulation flight = new FlightSimulation();
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrust0(Amount.valueOf(3.38E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setLapseRateFactor(Amount.valueOf(0.96, Unit.ONE));
        Amount<Force> lambda = Amount.valueOf(175555.55, SI.NEWTON);
        Amount<Velocity> machTrue = Amount.valueOf(0.368450043, NonSI.MACH);
        Amount<VolumetricDensity> airDensity = Amount.valueOf(0.992900912, CustomUnits.VOLUMETRIC_DENSITY_SI);

        double expResult = 223400.9205; // equals to 2,23E+05 from excel
        double result = Calculus.getThrust(flight, lambda, machTrue, airDensity).doubleValue(SI.NEWTON);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getTotalThrust method, of class Calculus.
     */
    @Test
    public void testGetTotalThrust() {
        System.out.println("getTotalThrust");
        Amount<Force> thrust = Amount.valueOf(223400.9205, SI.NEWTON);
        int numberOfMotors = 4;

        double expResult = 893603.682; // equals to 8,94E+05 from excel
        double result = Calculus.getTotalThrust(thrust, numberOfMotors).doubleValue(SI.NEWTON);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getRateOfClimb method, of class Calculus.
     */
    @Test
    public void testGetRateOfClimb() {
        System.out.println("getRateOfClimb");
        Amount<Force> totalThrust = Amount.valueOf(8.94E+05, SI.NEWTON);
        Amount<Force> drag = Amount.valueOf(294460.3277, SI.NEWTON);
        Amount<Velocity> tas = Amount.valueOf(122.3242157, SI.METERS_PER_SECOND);
        Amount<Mass> mass = Amount.valueOf(5.13E+05, SI.KILOGRAM);
        double expResult = 1.46E+01;
        double result = Calculus.getRateOfClimb(totalThrust, drag, tas, mass).doubleValue(SI.METERS_PER_SECOND);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getClimbingAngle method, of class Calculus.
     */
    @Test
    public void testGetClimbingAngle() {
        System.out.println("getClimbingAngle");
        Amount<Velocity> rateOfClimb = Amount.valueOf(1.46E+01, SI.METERS_PER_SECOND);
        Amount<Velocity> tas = Amount.valueOf(122.3242157, SI.METERS_PER_SECOND);
        double expResult = 1.19E-01; // in radian as in excel
        double result = Calculus.getClimbingAngle(rateOfClimb, tas).doubleValue(SI.RADIAN);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getTimeStepDistance method, of class Calculus.
     */
    @Test
    public void testGetTimeStepDistance() {
        System.out.println("getTimeStepDistance");
        Amount<Velocity> tas = Amount.valueOf(122.3242157, SI.METERS_PER_SECOND);
        Amount<Angle> angle = Amount.valueOf(1.19E-01, SI.RADIAN);
        Amount<Duration> timeStep = Amount.valueOf(120, SI.SECOND);
        double expResult = 14575.0944; // 1.46E+04
        double result = Calculus.getTimeStepDistance(tas, angle, timeStep).doubleValue(SI.METER);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getNewMass method, of class Calculus.
     */
    @Test
    public void testGetNewMass() {
        System.out.println("getNewMass");
        Amount<Mass> mass = Amount.valueOf(5.13E+05, SI.KILOGRAM);
        Amount<Mass> fuelBurned = Amount.valueOf(1.75E+03, SI.KILOGRAM);
        double expResult = 511249.9999; // 5.11E+05
        double result = Calculus.getNewMass(mass, fuelBurned).doubleValue(SI.KILOGRAM);
        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of getFuelBurnCalculation method, of class Calculus.
     */
    @Test
    public void testGetFuelBurnCalculation() {
        System.out.println("getFuelBurnCalculation");
        Amount<Force> totalThrust = Amount.valueOf(8.94E+05, SI.NEWTON);
        Amount<Duration> timeStep = Amount.valueOf(120, SI.SECOND);
        Amount tsfc = Amount.valueOf(1.60E-04, CustomUnits.TSFC_NNS);
        double expResult = 1750.3224; // 1.75E+03
        double result = Calculus.getFuelBurnCalculation(totalThrust, timeStep, tsfc).doubleValue(SI.KILOGRAM);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getCumulativeDistance method, of class Calculus.
     */
    @Test
    public void testGetCumulativeDistance() {
        System.out.println("getCumulativeDistance");
        Amount<Length> cumulativePreviousDistance = Amount.valueOf(1.28E+04, SI.METER);
        Amount<Length> distance = Amount.valueOf(1.46E+04, SI.METER);
        double expResult = 27399.99; // 2.74E+04
        double result = Calculus.getCumulativeDistance(cumulativePreviousDistance, distance).doubleValue(SI.METER);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getCumulativeAltitude method, of class Calculus.
     */
    @Test
    public void testGetCumulativeAltitude() {
        System.out.println("getCumulativeAltitude");
        Amount<Length> cumulativePreviousAltitude = Amount.valueOf(2135.36, SI.METER);
        Amount<Velocity> rateOfClimb = Amount.valueOf(1.46E+01, SI.METERS_PER_SECOND);
        Amount<Duration> timeStep = Amount.valueOf(120, SI.SECOND);
        double expResult = 3887.3599; // 3884.61
        double result = Calculus.getCumulativeAltitude(cumulativePreviousAltitude, rateOfClimb, timeStep).doubleValue(SI.METER);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getCumulativeFlightTime method, of class Calculus.
     */
    @Test
    public void testGetCumulativeFlightTime() {
        System.out.println("getCumulativeFlightTime");
        Amount<Duration> previousFlightTime = Amount.valueOf(120, SI.SECOND);
        Amount<Duration> timeStep = Amount.valueOf(120, SI.SECOND);
        double expResult = 240.0;
        double result = Calculus.getCumulativeFlightTime(previousFlightTime, timeStep).doubleValue(SI.SECOND);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of calculateClimb method, of class Calculus.
     */
    @Test
    public void testCalculateClimb() {
        System.out.println("calculateClimb");

        Amount<Length> airportAltitude = Amount.valueOf(0.0, SI.METER);
        flight.getFlightInfo().getAircraft().setFlightPattern(flightPattern);

        double expResult1 = 428298d;
        double expResult2 = 2220d;
        double expResult3 = 16131d;

        AlgorithmAnalysis alg = Calculus.calculateClimb(flight, airportAltitude);
        double result1 = alg.getDistance().doubleValue(SI.METER);
        double result2 = alg.getDuration().doubleValue(SI.SECOND);
        double result3 = alg.getConsumption().doubleValue(SI.KILOGRAM);

        assertEquals(expResult1, result1, 10);
        assertEquals(expResult2, result2, 10);
        assertEquals(expResult3, result3, 10);
    }

    /**
     * Test of calculateLanding method, of class Calculus.
     */
    @Test
    public void testCalculateLanding() {
        System.out.println("calculateLanding");

        Amount<Length> airportAltitude = Amount.valueOf(0.0, SI.METER);
        flight.getFlightInfo().getAircraft().setFlightPattern(flightPattern);

        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setLapseRateFactor(Amount.valueOf(0.96, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setWingArea(Amount.valueOf(858, SI.SQUARE_METRE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setAspectRatio(Amount.valueOf(9, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setCruiseAltitude(Amount.valueOf(10061, SI.METER));
        flight.getFlightInfo().getAircraft().getAircraftModel().setE(Amount.valueOf(0.84, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrust0(Amount.valueOf(3.38E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrustMaxSpeed(Amount.valueOf(1.80E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setMaxSpeed(Amount.valueOf(0.9, NonSI.MACH));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setNumberOfMotors(4);
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setTsfc(Amount.valueOf(1.60E-04, CustomUnits.TSFC_NNS));
        flight.setEffectiveCargo(Amount.valueOf(70000, SI.KILOGRAM));
        flight.setEffectiveFuel(Amount.valueOf(29748, SI.KILOGRAM));
        flight.getFlightInfo().getAircraft().getAircraftModel().setEmptyWeight(Amount.valueOf(3.00E+05, SI.KILOGRAM));

        double expResult1 = 220159d;
        double expResult2 = 1500d;
        double expResult3 = 1877d;

        AlgorithmAnalysis alg = Calculus.calculateLanding(flight, airportAltitude);
        double result1 = alg.getDistance().doubleValue(SI.METER);
        double result2 = alg.getDuration().doubleValue(SI.SECOND);
        double result3 = alg.getConsumption().doubleValue(SI.KILOGRAM);

        assertEquals(expResult1, result1, 10);
        assertEquals(expResult2, result2, 10);
        assertEquals(expResult3, result3, 10);
    }

    /**
     * Test of calculateCruise method, of class Calculus.
     */
    //@Test
    public void testCalculateCruise() {
        System.out.println("calculateCruise");

        Amount<Length> airportAltitude = Amount.valueOf(0.0, SI.METER);
        flight.getFlightInfo().getAircraft().setFlightPattern(flightPattern);

        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setLapseRateFactor(Amount.valueOf(0.96, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setWingArea(Amount.valueOf(858, SI.SQUARE_METRE));
        flight.getFlightInfo().getAircraft().getAircraftModel().setAspectRatio(Amount.valueOf(9, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setCruiseAltitude(Amount.valueOf(10061, SI.METER));
        flight.getFlightInfo().getAircraft().getAircraftModel().setE(Amount.valueOf(0.84, Unit.ONE));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrust0(Amount.valueOf(3.38E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setThrustMaxSpeed(Amount.valueOf(1.80E+05, SI.NEWTON));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().setMaxSpeed(Amount.valueOf(0.9, NonSI.MACH));
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setNumberOfMotors(4);
        flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().setTsfc(Amount.valueOf(1.60E-04, CustomUnits.TSFC_NNS));
        flight.setEffectiveCargo(Amount.valueOf(70000, SI.KILOGRAM));
        flight.setEffectiveFuel(Amount.valueOf(8040d, SI.KILOGRAM));
        flight.getFlightInfo().getAircraft().getAircraftModel().setEmptyWeight(Amount.valueOf(3.00E+05, SI.KILOGRAM));

        double expResult1 = 220159d;
        double expResult2 = 1500d;
        double expResult3 = 1877d;

        AlgorithmAnalysis alg = Calculus.calculateLanding(flight, airportAltitude);
        double result1 = alg.getDistance().doubleValue(SI.METER);
        double result2 = alg.getDuration().doubleValue(SI.SECOND);
        double result3 = alg.getConsumption().doubleValue(SI.KILOGRAM);

        assertEquals(expResult1, result1, 10);
        assertEquals(expResult2, result2, 10);
        assertEquals(expResult3, result3, 10);
    }
}
