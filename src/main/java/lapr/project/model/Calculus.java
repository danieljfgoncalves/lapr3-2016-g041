/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import javax.measure.quantity.Angle;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Force;
import javax.measure.quantity.Mass;
import javax.measure.quantity.VolumetricDensity;
import javax.measure.quantity.Pressure;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import org.jscience.physics.amount.Constants;
import lapr.project.utils.CustomUnits;
import lapr.project.utils.Consts;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import org.jscience.physics.amount.Amount;
import javax.measure.quantity.Temperature;

/**
 * Responsible to make calculations related to model business.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Calculus {

    /**
     * Earth radius in meters (Assuming that Earth is perfect sphere)
     */
    private static final double EARTH_RADIUS = 6371e3;

    /**
     * Time step for fuel burn calculation.
     */
    private static final double TIMESTEP = 60;

    /**
     * Minimal climbing rate. (m/s)
     */
    private static final Amount<Velocity> MIN_CLIMB_RATE = Amount.valueOf(0.2, SI.METERS_PER_SECOND);

    /**
     * Compass directions.
     */
    public static enum DIRECTION {
        NORTH, EAST, SOUTH, WEST
    }

    /**
     * Converts a compass coordinate (DD MM SS) to decimal format.
     *
     * @param degrees degrees
     * @param minutes minutes
     * @param seconds seconds
     * @param direction direction (N, E, S & W)
     * @return coordinate in decimal format
     */
    public static double compassToDecimal(double degrees, double minutes, double seconds, DIRECTION direction) {

        int sign = (direction == DIRECTION.NORTH || direction == DIRECTION.EAST) ? 1 : -1;

        return degrees + minutes / 60 + seconds / 3600 * sign;
    }

    /**
     * Calculate the great circle distance between two numerical coordinates
     * (Haversine Formula).
     *
     * @param first first coordinate
     * @param second second coordinate
     * @return an length amount (conversionable to m, ft, etc.)
     */
    public static Amount<Length> distance(Coordinate first, Coordinate second) {
        // return with altitude being 0.
        return distance(first, second, Amount.valueOf(0.0, SI.METER));
    }

    /**
     * Calculate the great circle distance between two numerical coordinates
     * (Haversine Formula).
     *
     * @param first first coordinate
     * @param second second coordinate
     * @param altitude average flight altitude
     * @return an length amount (conversionable to m, ft, etc.)
     */
    public static Amount<Length> distance(Coordinate first, Coordinate second, Amount<Length> altitude) {

        // Haversine:
        // a = sin²(Δφ/2) + cos φ1 x cos φ2 x sin²(Δλ/2)
        // c = 2 x atan2( √a, √(1−a) )
        // d = R x c
        // Earth radius plus airport altitude.
        double radius = EARTH_RADIUS + altitude.doubleValue(SI.METER);

        // Stores lat/lon pairs in degrees
        Amount<Angle> lat1 = Amount.valueOf(first.getLatitude(), NonSI.DEGREE_ANGLE);
        Amount<Angle> lat2 = Amount.valueOf(second.getLatitude(), NonSI.DEGREE_ANGLE);
        Amount<Angle> lon1 = Amount.valueOf(first.getLongitude(), NonSI.DEGREE_ANGLE);
        Amount<Angle> lon2 = Amount.valueOf(second.getLongitude(), NonSI.DEGREE_ANGLE);
        // Variation of lat/lat & lon/lon pairs in radians
        double deltaLat = lat2.minus(lat1).doubleValue(SI.RADIAN);
        double deltaLon = lon2.minus(lon1).doubleValue(SI.RADIAN);

        double a = (Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2))
                + Math.cos(lat1.doubleValue(SI.RADIAN)) * Math.cos(lat2.doubleValue(SI.RADIAN))
                * (Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Amount.valueOf(radius * c, SI.METER);
    }

    /**
     * Calculate the direction of a segment relative the polar axis.
     *
     * @param first first coordinate
     * @param second second coordinate
     * @return the angle amount (conversionable to degree, radian, etc.)
     */
    public static Amount<Angle> direction(Coordinate first, Coordinate second) {

        /*
        Δφ = ln( tan( latB / 2 + π / 4 ) / tan( latA / 2 + π / 4) ) 
        Δlon = abs( lonA - lonB ) if Δlon > 180°  then   Δlon = Δlon (mod 180)
        rolamento :  θ = atan2( Δlon ,  Δφ ) 
         */
        // Stores lat/lon pairs in degrees
        Amount<Angle> lat1 = Amount.valueOf(first.getLatitude(), NonSI.DEGREE_ANGLE);
        Amount<Angle> lat2 = Amount.valueOf(second.getLatitude(), NonSI.DEGREE_ANGLE);
        Amount<Angle> lon1 = Amount.valueOf(first.getLongitude(), NonSI.DEGREE_ANGLE);
        Amount<Angle> lon2 = Amount.valueOf(second.getLongitude(), NonSI.DEGREE_ANGLE);
        // Variation of lon/lon pairs in radians
        double deltaLat
                = Math.log((Math.tan(lat2.doubleValue(SI.RADIAN) / 2 + Math.PI / 4)
                        / Math.tan(lat1.doubleValue(SI.RADIAN) / 2 + Math.PI / 4))
                );

        double deltaLon = Math.abs((lon2.minus(lon1)).doubleValue(SI.RADIAN));
        deltaLon = (deltaLon > Math.PI) ? deltaLon % Math.PI : deltaLon;

        Amount<Angle> angle = Amount.valueOf(Math.atan2(deltaLon, deltaLat), SI.RADIAN);
        angle.doubleValue(NonSI.DEGREE_ANGLE);

        // Normalize angle to 0˚-360˚ (atan2 returns values in the range -pi ... +pi) 
        // -> (θ+360) % 360
        return Amount.valueOf(((angle.doubleValue(NonSI.DEGREE_ANGLE) + 360.0) % 360.0), NonSI.DEGREE_ANGLE);
    }

    /**
     * Virtual distance to calculate the range (with wind factor).
     *
     * @param realDistance real distance
     * @param flight flight
     * @param segment segment
     * @param first the first coordinate
     * @param second the second coordinate
     *
     * @return distance to calculate the range.
     */
    public static Amount<Length> virtualDistance(double realDistance, FlightSimulation flight,
            Segment segment, Coordinate first, Coordinate second) {

        Amount<Length> altitude = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseAltitude();
        Amount<Velocity> mach = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseSpeed();
        Amount<Velocity> windSpeed = segment.getWindIntensity();
        Amount<Angle> windDirection = segment.getWindDirection();
        Amount<Angle> flightDirection = Calculus.direction(first, second);

        double realDuration = realDistance / calculateTAS(altitude, mach).doubleValue(SI.METERS_PER_SECOND);
        double virtualDuration = realDistance / calculateGS(altitude, mach, windSpeed, windDirection, flightDirection).doubleValue(SI.METERS_PER_SECOND);
        double factor = (virtualDuration / realDuration) - 1;
        double virtualDistance = realDistance + (realDistance * factor);

        return Amount.valueOf(virtualDistance, SI.METER);
    }

    /**
     * Gets the lift force.
     *
     * @param altitude the altitude
     * @param mass the mass of the aircraft
     * @param wingsArea the wings area
     * @param machNumber Mach number return liftForte the lift force
     * @return the lift force
     */
    public static Amount<Force> getLiftForce(Amount<Length> altitude, Amount<Mass> mass,
            Amount<Area> wingsArea, Amount<Velocity> machNumber) {

        Amount<Pressure> press = getPressure(altitude);
        Amount<Temperature> temp = getTemperature(altitude);

        return (Amount<Force>) getLiftCoefficient(altitude, mass, wingsArea, machNumber)
                .times(getAirDensity(press, temp))
                .times(calculateTAS(altitude, machNumber).pow(2))
                .times(wingsArea)
                .divide(Amount.valueOf(2, Unit.ONE));
    }

    /**
     * Gets the liftCoefficient.
     *
     * @param altitude the altitude
     * @param mass the mass of the aircraft
     * @param wingsArea the wings area
     * @param machNumber mach number
     * @return lift coefficient the lift coefficient
     */
    public static Amount<Dimensionless> getLiftCoefficient(Amount<Length> altitude, Amount<Mass> mass,
            Amount<Area> wingsArea, Amount<Velocity> machNumber) {

        Amount<Pressure> press = getPressure(altitude);
        Amount<Temperature> temp = getTemperature(altitude);

        return (Amount<Dimensionless>) Amount.valueOf(2, Unit.ONE)
                .times(mass)
                .times(Constants.g)
                .divide(getAirDensity(press, temp)
                        .times(wingsArea)
                        .times(calculateTAS(altitude, machNumber).pow(2)));
    }

    /**
     * Obtains the temperature for a specific altitude and temperature lapse
     * rate
     *
     * @param altitude the given altitude (m)
     * @return the temperature
     */
    public static Amount<Temperature> getTemperature(Amount<Length> altitude) {
        if (altitude.isLessThan(Amount.valueOf(11000, SI.METER))) {
            return (Amount<Temperature>) (altitude.times(Consts.TEMP_LAPSE_RATE).plus(Consts.REF_TEMP_SEA_LEVEL));
        }
        return (Amount<Temperature>) (Amount.valueOf(216.7, SI.KELVIN));
    }

    /**
     * @param altitude the given altitude (m)
     * @return the pressure (N/m2)
     */
    public static Amount<Pressure> getPressure(Amount<Length> altitude) {

        double i = altitude.doubleValue(SI.METER) / Consts.REF_TEMP_SEA_LEVEL.doubleValue(SI.KELVIN);
        double j = Consts.TEMP_LAPSE_RATE.doubleValue(CustomUnits.TEMP_GRADIENT_SI) * i;
        double k = Math.pow(1 + j, 5.2561);
        double result = Consts.AIR_PRESSURE_SEA_LEVEL.doubleValue(SI.PASCAL) * k;

        return Amount.valueOf(result, SI.PASCAL);
    }

    /**
     * Get the air density of a given temperature.
     *
     * @param pressure the given pressure (Pascal = N/m^2)
     * @param temp the given temperature (k)
     * @return the air density at a given temperature
     */
    public static Amount<VolumetricDensity> getAirDensity(Amount<Pressure> pressure, Amount<Temperature> temp) {

        return (Amount<VolumetricDensity>) pressure.divide(temp.times(Consts.UNIV_GAS_CONST));
    }

    /**
     * Gets the speed of sound (tabulated value) of a given altitude.
     *
     * @param temp the given temperature (k)
     * @return the speed of sound at the given altitude (m/s)
     */
    public static Amount<Velocity> getSpeedOfSound(Amount<Temperature> temp) {

        return (Amount<Velocity>) ((Consts.GAMMA.times(Consts.UNIV_GAS_CONST).times(temp)).sqrt());
    }

    /**
     * Obtains True Air Speed (TAS)(velocity of aircraft relative to the
     * air(m/s)
     *
     * @param altitude altitude of aircraft relative to sea level in meters (m)
     * @param machNumber mach number (Mach)
     * @return the True Air Speed (TAS) (velocity of aircraft relative to the
     * air) (m/s)
     */
    public static Amount<Velocity> calculateTAS(Amount<Length> altitude, Amount<Velocity> machNumber) {
        Amount<Temperature> temp = getTemperature(altitude);

        return (Amount<Velocity>) getSpeedOfSound(temp).times(machNumber.doubleValue(NonSI.MACH));
    }

    /**
     * Obtains ground speed (GS)
     *
     * @param altitude altitude of aircraft relative to sea level (m)
     * @param machNumber mach number (Mach)
     * @param windSpeed wind speed (m/s)
     * @param windDirection angle relative to y
     * @param flightDirection the flight direction
     * @return the ground speed (GS) (m/s)
     */
    public static Amount<Velocity> calculateGS(Amount<Length> altitude, Amount<Velocity> machNumber,
            Amount<Velocity> windSpeed, Amount<Angle> windDirection, Amount<Angle> flightDirection) {

        Amount<Velocity> tas = calculateTAS(altitude, machNumber);
        Amount<Velocity> portionWindSpeed = getPortionWindSpeed(windSpeed, windDirection, flightDirection);

        return tas.plus(portionWindSpeed);
    }

    /**
     * Obtains wind speed portion (vx)
     *
     * @param windSpeed wind speed
     * @param windDirection angle relative to Y in degrees
     * @param flightDirection the flight direction
     * @return the wind speed portion (vx)
     */
    public static Amount<Velocity> getPortionWindSpeed(Amount<Velocity> windSpeed, Amount<Angle> windDirection, Amount<Angle> flightDirection) {

        double angle = flightDirection.minus(windDirection).doubleValue(SI.RADIAN);
        double windSpeed1 = windSpeed.doubleValue(SI.METERS_PER_SECOND);
        double portionWindSpeed = windSpeed1 * Math.cos(angle);

        return Amount.valueOf(portionWindSpeed, SI.METERS_PER_SECOND);
    }

    /**
     * Gets the drag coefficient.
     *
     * @param altitude altitude in meters
     * @param mass mass of the aircraft in Kg
     * @param dragCoefficient0 coefficient drag zero
     * @param aspectRatio the aspect ratio (wingSpan^2 / wingsArea)
     * (dimensionless)
     * @param wingArea wings area in square meters
     * @param machNumber Mach number
     * @param e the efficiency factor
     * @return the calculated drag coefficient
     */
    public static Amount<Dimensionless> getDragCoefficient(Amount<Length> altitude, Amount<Mass> mass,
            Amount<Dimensionless> aspectRatio, Amount<Dimensionless> dragCoefficient0,
            Amount<Area> wingArea, Amount<Dimensionless> e, Amount<Velocity> machNumber) {

        Amount<Dimensionless> cl = getLiftCoefficient(altitude, mass, wingArea, machNumber);
        Amount<Dimensionless> cl2 = (Amount<Dimensionless>) cl.pow(2);

        return (Amount<Dimensionless>) (cl2.divide(aspectRatio.times(Constants.π).times(e))).plus(dragCoefficient0);
    }

    /**
     * Gets the Drag Force.
     *
     * @param altitude the altitude in meters
     * @param mass the initial mass in kg
     * @param dragCoefficient0 the coefficient drag zero (N)
     * @param wingArea the wings area in square meters
     * @param e the efficiency factor
     * @param machNumber mach number
     * @param aspectRatio the aspect ratio (wingSpan^2 / wingsArea)
     * (dimensionless)
     * @return the calculated drag force
     */
    public static Amount<Force> getDragForce(Amount<Length> altitude, Amount<Mass> mass,
            Amount<Dimensionless> dragCoefficient0, Amount<Area> wingArea, Amount<Dimensionless> e,
            Amount<Velocity> machNumber, Amount<Dimensionless> aspectRatio) {

        Amount<Pressure> press = getPressure(altitude);
        Amount<Temperature> temp = getTemperature(altitude);
        double cDrag = getDragCoefficient(altitude, mass, aspectRatio, dragCoefficient0, wingArea, e, machNumber).doubleValue(Unit.ONE);
        double airDensity = getAirDensity(press, temp).doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI);
        double squaredTas = Math.pow((calculateTAS(altitude, machNumber).doubleValue(SI.METERS_PER_SECOND)), 2);
        double wingsArea = wingArea.doubleValue(SI.SQUARE_METRE);

        double result = 0.5 * airDensity * squaredTas * wingsArea * cDrag;

        return Amount.valueOf(result, SI.NEWTON);
    }

    /**
     * Obtains the drag force (N)
     *
     * @param flight the flight
     * @param altitude the altitude (m)
     * @param mass the mass (Kg)
     * @param e the efficiency factor
     * @param machNumber the Mach number (M)
     * @return the calculation of drag force (N)
     */
    public static Amount<Force> getDragForce(FlightSimulation flight, Amount<Length> altitude, Amount<Mass> mass,
            Amount<Dimensionless> e, Amount<Velocity> machNumber) {

        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(flight.getFlightInfo().getAircraft().getAircraftModel().
                getCdragFunction(machNumber.doubleValue(NonSI.MACH)), Unit.ONE);
        Amount<Area> wingArea = flight.getFlightInfo().getAircraft().getAircraftModel().getWingArea();
        Amount<Dimensionless> aspectRatio = flight.getFlightInfo().getAircraft().getAircraftModel().getAspectRatio();

        Amount<Pressure> press = getPressure(altitude);
        Amount<Temperature> temp = getTemperature(altitude);

        return (Amount<Force>) getDragCoefficient(altitude, mass, aspectRatio, dragCoefficient0, wingArea, e, machNumber)
                .times(getAirDensity(press, temp))
                .times((calculateTAS(altitude, machNumber)).pow(2))
                .times(wingArea)
                .divide(Amount.valueOf(2, Unit.ONE));
    }

    /**
     * Obtains the mach true number (M), using the standard expression to
     * estimate the true mach number of the aircraft at a given altitude
     *
     * @param ias the indicated airspeed (m/s) (SI)
     * @param airDensity the air density (Kg/m3) (SI)
     * @return the mach true number
     */
    public static Amount<Velocity> getMachTrue(Amount<Velocity> ias, Amount<VolumetricDensity> airDensity) {

        double density1 = Consts.AIR_DENSITY_SEA_LEVEL.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI);
        double density2 = airDensity.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI);

        double i = Math.pow(ias.doubleValue(NonSI.KNOT) / 661.5, 2);
        double j = Math.pow(1 + 0.2 * i, 3.5) - 1;
        double k = (density1 / density2) * j + 1;
        double l = (Math.pow(k, 0.286) - 1) * 5;
        double result = Math.sqrt(l);

        return Amount.valueOf(result, NonSI.MACH);
    }

    /**
     * Obtains the rate of climb (m/s)
     *
     * @param totalThrust the total thrust (N)
     * @param drag drag force (N)
     * @param tas the true air speed (m/s)
     * @param mass the aircraft weight (Kg)
     * @return the rate of climb (m/s)
     */
    public static Amount<Velocity> getRateOfClimb(Amount<Force> totalThrust, Amount<Force> drag, Amount<Velocity> tas, Amount<Mass> mass) {

        return (Amount<Velocity>) ((totalThrust.minus(drag)).times(tas)).divide(mass.times(Constants.g));
    }

    /**
     * Obtains the climbing angle (Degree)
     *
     * @param rateOfClimb the climbing rate (m/s)
     * @param tas the true air speed (m/s)
     * @return the climbing angle (degree)
     */
    public static Amount<Angle> getClimbingAngle(Amount<Velocity> rateOfClimb, Amount<Velocity> tas) {

        double rateOfclimb1 = rateOfClimb.doubleValue(SI.METERS_PER_SECOND);
        double tas1 = tas.doubleValue(SI.METERS_PER_SECOND);
        double result = Math.asin(rateOfclimb1 / tas1);

        return Amount.valueOf(result, SI.RADIAN);
    }

    /**
     * Obtains the distance for a specific time step, tas and angle
     *
     * @param tas the true air speed (m/s)
     * @param angle the angle (degree)
     * @param timeStep the time step (s)
     * @return a distance (m)
     */
    public static Amount<Length> getTimeStepDistance(Amount<Velocity> tas, Amount<Angle> angle, Amount<Duration> timeStep) {

        return (Amount<Length>) tas.times(Math.cos(angle.doubleValue(SI.RADIAN))).times(timeStep);
    }

    /**
     * Obtains the new mass subtracting to the old mass the amount of fuel
     * burned
     *
     * @param mass the mass of the aircraft (Kg)
     * @param fuelBurned the mass of fuel burned (Kg)
     * @return the new mass (Kg)
     */
    public static Amount<Mass> getNewMass(Amount<Mass> mass, Amount<Mass> fuelBurned) {

        return mass.minus(fuelBurned);
    }

    /**
     * Obtains the fuel burn calculation (dw/dt) (N/s)
     *
     * @param totalThrust the total thrust (N)
     * @param timeStep the time step (dt) (s)
     * @param tsfc the thrust specific fuel consumption (Kg/s/N)_SI (N/s/N)_US
     * @return the fuel burn calculation (dw/dt) (N/s)
     */
    public static Amount<Mass> getFuelBurnCalculation(Amount<Force> totalThrust, Amount<Duration> timeStep, Amount tsfc) {

        return (Amount<Mass>) totalThrust.times(timeStep).times(tsfc).divide(Constants.g);
    }

    /**
     * Obtains cumulative distance
     *
     * @param cumulativePreviousDistance the cumulative previous distance (m)
     * @param distance the distance traveled (m)
     * @return the actual cumulative distance (m)
     */
    public static Amount<Length> getCumulativeDistance(Amount<Length> cumulativePreviousDistance, Amount<Length> distance) {

        double cumulativePrevDist = cumulativePreviousDistance.doubleValue(SI.METER);
        double dist = distance.doubleValue(SI.METER);
        double result = (cumulativePrevDist + dist);

        return Amount.valueOf(result, SI.METER);
    }

    /**
     * Obtains cumulative altitude
     *
     * @param cumulativePreviousAltitude the cumulative previous altitude (m)
     * @param rateOfClimb the rate of climb (m/s)
     * @param timeStep the time step (s)
     * @return the cumulative altitude (m)
     */
    public static Amount<Length> getCumulativeAltitude(Amount<Length> cumulativePreviousAltitude,
            Amount<Velocity> rateOfClimb, Amount<Duration> timeStep) {

        return cumulativePreviousAltitude.plus((rateOfClimb).times(timeStep));
    }

    /**
     * Obtains cumulative flight time (s)
     *
     * @param previousFlightTime the previous flight time (s)
     * @param timeStep the time step (s)
     * @return the cumulativeFlighTime
     */
    public static Amount<Duration> getCumulativeFlightTime(Amount<Duration> previousFlightTime, Amount<Duration> timeStep) {

        return previousFlightTime.plus(timeStep);
    }

    /**
     * Obtains the lambda value to calculate thrust aircraft (N)
     *
     * @param thrust_0 the thrust zero (T 0,M=0) (N)
     * @param thrustMaxSpeed the thrust max speed (T 0,M) (N)
     * @param maxSpeed the aircraft max speed (M)
     * @return the lambda value (N)
     */
    public static Amount<Force> getLambda(Amount<Force> thrust_0, Amount<Force> thrustMaxSpeed, Amount<Velocity> maxSpeed) {

        return Amount.valueOf(((thrust_0.doubleValue(SI.NEWTON)
                - thrustMaxSpeed.doubleValue(SI.NEWTON))
                / maxSpeed.doubleValue(NonSI.MACH)), SI.NEWTON);
    }

    /**
     * Obtains the thrust value (N)
     *
     * @param flight the flight
     * @param lambda lambda the lambda value to be used to calculate the
     * thrust(N)
     * @param machTrue the Mach true number (M)
     * @param airDensity the air density (Kg/m3)
     * @return the thrust value (N)
     */
    public static Amount<Force> getThrust(FlightSimulation flight, Amount<Force> lambda,
            Amount<Velocity> machTrue, Amount<VolumetricDensity> airDensity) {

        Amount<Force> thrust_0 = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().getThrust0();
        Amount<Dimensionless> lapseRateFactor = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getLapseRateFactor();

        return Amount.valueOf((thrust_0.doubleValue(SI.NEWTON) - lambda.doubleValue(SI.NEWTON) * machTrue.doubleValue(NonSI.MACH))
                * Math.pow((airDensity.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI)
                        / Consts.AIR_DENSITY_SEA_LEVEL.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI)),
                        lapseRateFactor.doubleValue(Unit.ONE)), SI.NEWTON);
    }

    /**
     * Obtains the total thrust value (N)
     *
     * @param thrust the thrust (N)
     * @param numberOfMotors the number of motors of the aircraft
     * (n)(dimensionless)
     * @return the total thrust value (N)
     */
    public static Amount<Force> getTotalThrust(Amount<Force> thrust, int numberOfMotors) {
        return thrust.times(numberOfMotors);
    }

    /**
     * Enumeration of the flight regimes.
     */
    private static enum REGIME {
        CLIMB, CRUISE, LANDING
    }

    private static Amount<Velocity> getIAS(FlightSimulation flight, Amount<Length> altitude, REGIME regime) {

        switch (regime) {
            case CLIMB:
                return flight.getFlightInfo().getAircraft().getFlightPattern().getVclimb(altitude);
            case LANDING:
                return flight.getFlightInfo().getAircraft().getFlightPattern().getVdesc(altitude);
            default:
                // CRUISE
                return Amount.valueOf(0, SI.METERS_PER_SECOND);
        }
    }

    /**
     * Calculates the climb of a flight.
     *
     * @param flight the flight
     * @param airportAltitude the airports altitude
     * @return a analysis with distance, duration & consumption of flight climb
     */
    public static AlgorithmAnalysis calculateClimb(FlightSimulation flight,
            Amount<Length> airportAltitude) {

        return calculateFlightAnalysis(flight, REGIME.CLIMB, airportAltitude, Amount.valueOf(0.0, SI.METER));
    }

    /**
     * Calculates the landing of a flight.
     *
     * @param flight the flight
     * @param airportAltitude the airports altitude
     * @return a analysis with distance, duration & consumption of flight
     * landing
     */
    public static AlgorithmAnalysis calculateLanding(FlightSimulation flight,
            Amount<Length> airportAltitude) {

        return calculateFlightAnalysis(flight, REGIME.LANDING, airportAltitude, Amount.valueOf(0.0, SI.METER));
    }

    /**
     * Calculates the landing of a flight.
     *
     * @param flight the flight
     * @param segmentDistance
     * @return a analysis with distance, duration & consumption of flight
     * landing
     */
    public static AlgorithmAnalysis calculateCruise(FlightSimulation flight, Amount<Length> segmentDistance) {

        return calculateFlightAnalysis(flight, REGIME.CRUISE, Amount.valueOf(0.0, SI.METER), segmentDistance);
    }

    /**
     * Calculates the flight ananlysis by regime.
     *
     * @param flight the flight
     * @param regime the regime (climb, cruise & landing)
     * @param airportAltitude the airports altitude
     * @param segmentDistance the segment distance in criuse
     * @return a analysis with distance, duration & consumption of flight regime
     */
    private static AlgorithmAnalysis calculateFlightAnalysis(FlightSimulation flight, REGIME regime,
            Amount<Length> airportAltitude, Amount<Length> segmentDistance) {

        // Fuel Consumption
        Amount<Mass> consumption = Amount.valueOf(0, SI.KILOGRAM);

        // Atributes from flight
        double cruiseAltitude = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseAltitude().doubleValue(SI.METER);
        Amount e = flight.getFlightInfo().getAircraft().getAircraftModel().getE();
        Amount<Force> thrust_0 = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().getThrust0();
        Amount<Force> thrustMaxSpeed = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().getThrustMaxSpeed();
        Amount<Velocity> maxSpeed = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().getMaxSpeed();
        int numMotors = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getNumberOfMotors();
        Amount tsfc = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getTsfc();
        Amount<Mass> mass = flight.getEffectiveCargo().plus(flight.getEffectiveFuel()).plus(flight.getFlightInfo().getAircraft().getAircraftModel().getEmptyWeight());

        // Intialized variables to calculate during iteration
        int lapsedTime = 0;
        double startingAltitude = (regime == REGIME.CLIMB) ? airportAltitude.doubleValue(SI.METER) : cruiseAltitude;
        Amount<Mass> fuelBurn = Amount.valueOf(0, SI.KILOGRAM);
        Amount<Length> distance = Amount.valueOf(0, SI.METER);
        Amount<Force> lambda = getLambda(thrust_0, thrustMaxSpeed, maxSpeed);
        Amount<Velocity> climbRate = Amount.valueOf(Double.POSITIVE_INFINITY, SI.METERS_PER_SECOND);
        // Unintialized variables to calculate during iteration
        Amount<Length> altitude;
        Amount<VolumetricDensity> airDensity;
        Amount<Velocity> ias;
        Amount<Velocity> mTrue;
        Amount<Velocity> tas;
        Amount<Force> drag;
        Amount<Force> thrust;
        Amount<Force> totalThrust;
        Amount<Angle> climbAngle;
        Amount<Length> stepDistance;
        Amount<Pressure> pressure;
        Amount<Temperature> temperature;

        boolean stopCriteria = true;
        while (stopCriteria) {

            // Variables to calculate during iteration
            altitude = Amount.valueOf(startingAltitude, SI.METER);
            mass = getNewMass(mass, fuelBurn);
            pressure = getPressure(altitude);
            temperature = getTemperature(altitude);
            airDensity = getAirDensity(pressure, temperature);
            if (!regime.equals(REGIME.CRUISE)) {
                ias = getIAS(flight, altitude, regime);
                mTrue = getMachTrue(ias, airDensity);
            } else {
                mTrue = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseSpeed();
            }
            tas = calculateTAS(altitude, mTrue);
            drag = getDragForce(flight, altitude, mass, e, mTrue);
            double landingFactor = (regime == REGIME.LANDING) ? 0.1 : 1;
            thrust = (regime == REGIME.CRUISE) ? Amount.valueOf(0, SI.NEWTON)
                    : getThrust(flight, lambda, mTrue, airDensity).times(landingFactor);
            totalThrust = (regime == REGIME.CRUISE) ? drag : getTotalThrust(thrust, numMotors);
            fuelBurn = getFuelBurnCalculation(totalThrust, Amount.valueOf(TIMESTEP, SI.SECOND), tsfc);
            climbRate = getRateOfClimb(totalThrust, drag, tas, mass);
            climbAngle = getClimbingAngle(climbRate, tas);
            stepDistance = getTimeStepDistance(tas, climbAngle, Amount.valueOf(TIMESTEP, SI.SECOND));
            distance = getCumulativeDistance(distance, stepDistance);
            consumption = consumption.plus(fuelBurn);
            startingAltitude = getCumulativeAltitude(altitude, climbRate, Amount.valueOf(TIMESTEP, SI.SECOND)).doubleValue(SI.METER);
            lapsedTime += TIMESTEP;

            switch (regime) {
                case CLIMB:
                    stopCriteria = (startingAltitude < cruiseAltitude) && (climbRate.isGreaterThan(MIN_CLIMB_RATE));
                    break;
                case LANDING:
                    stopCriteria = startingAltitude > airportAltitude.doubleValue(SI.METER);
                    break;
                default:
                    // CRUISE
                    stopCriteria = (distance.isLessThan(segmentDistance));
                    break;
            }
        }

        return new AlgorithmAnalysis(distance, Amount.valueOf(lapsedTime, SI.SECOND), consumption);
    }
}
