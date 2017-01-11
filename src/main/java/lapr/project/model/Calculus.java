/**
 * Package location for Model concepts.
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
import javax.measure.quantity.Pressure;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import org.jscience.physics.amount.Amount;
import org.jscience.physics.amount.Constants;
import lapr.project.utils.CustomUnits;
import lapr.project.utils.Consts;
import javax.measure.quantity.Duration;

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
        
        return (Amount<Force>) getLiftCoefficient(altitude, mass, wingsArea, machNumber)
                .times(getAirDensity(altitude))
                .times(getTAS(altitude, machNumber).pow(2))
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
        
        return (Amount<Dimensionless>) Amount.valueOf(2, Unit.ONE)
                .times(mass)
                .times(Constants.g)
                .divide(getAirDensity(altitude)
                        .times(wingsArea)
                        .times(getTAS(altitude, machNumber).pow(2)));
    }

    /**
     * Get the air density (tabulated value) of a given altitude.
     *
     * @param altitude the altitude above sea level
     * @return the air density at a given altitude
     */
    public static Amount<VolumetricDensity> getAirDensity(Amount<Length> altitude) {

        // first colmumn = altitude above sea level (m)
        // second column = air density (kg/(m^3))
        final double table[][] = {
            {-1000, 13.47e-1},
            {0, 12.25e-1},
            {1000, 11.12e-1},
            {2000, 10.07e-1},
            {3000, 9.093e-1},
            {4000, 8.194e-1},
            {5000, 7.364e-1},
            {6000, 6.601e-1},
            {7000, 5.900e-1},
            {8000, 5.258e-1},
            {9000, 4.671e-1},
            {10000, 4.135e-1},
            {15000, 1.948e-1},
            {20000, 0.8891e-1},
            {25000, 0.4008e-1},
            {30000, 0.1841e-1},
            {40000, 0.03996e-1},
            {50000, 0.01027e-1},
            {60000, 0.003097e-1},
            {70000, 0.0008283e-1},
            {80000, 0.0001846e-1}
        };

        double altitudeInMeters = altitude.doubleValue(SI.METER), minimumDifference = Double.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < table.length; i++) {
            double diference = Math.abs(altitudeInMeters - table[i][0]);
            if (diference < minimumDifference) {
                minimumDifference = diference;
                index = i;
            }
        }

        return (Amount<VolumetricDensity>) Amount.valueOf(table[index][1], SI.KILOGRAM.divide(SI.CUBIC_METRE));
    }

    /**
     * Gets the speed of sound (tabulated value) of a given altitude.
     *
     * @param altitude the altitude above sea level
     * @return the speed of sound at the given altitude
     */
    public static Amount<Velocity> getSpeedOfSound(Amount<Length> altitude) {

        // first colmumn = altitude above sea level (feet)
        // second column = speed of sound (m/s)
        final double table[][] = {
            {0, 340.3},
            {5000, 334.4},
            {10000, 328.4},
            {15000, 322.2},
            {20000, 316.0},
            {25000, 309.6},
            {30000, 303.1},
            {35000, 295.4},
            {40000, 294.9}
        };

        double altitudeInFeet = altitude.doubleValue(NonSI.FOOT), minimumDifference = Double.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < table.length; i++) {
            double diference = Math.abs(altitudeInFeet - table[i][0]);
            if (diference < minimumDifference) {
                minimumDifference = diference;
                index = i;
            }
        }

        return (Amount<Velocity>) Amount.valueOf(table[index][1], SI.METERS_PER_SECOND);
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
    public static Amount<Velocity> getTAS(Amount<Length> altitude, Amount<Velocity> machNumber) {
        
        double tas = getSpeedOfSound(altitude).doubleValue(SI.METERS_PER_SECOND) * machNumber.doubleValue(NonSI.MACH);
        
        return Amount.valueOf(tas, SI.METERS_PER_SECOND);
    }

    /**
     * Obtains ground speed (GS)
     *
     * @param altitude altitude of aircraft relative to sea level (m)
     * @param machNumber mach number (Mach)
     * @param windSpeed wind speed (m/s)
     * @param angleRelativeToY angle relative to y
     * @return the ground speed (GS) (m/s)
     */
    public static Amount<Velocity> getGS(Amount<Length> altitude, Amount<Velocity> machNumber,
            Amount<Velocity> windSpeed, Amount<Angle> angleRelativeToY) {
        
        Amount<Velocity> tas = getTAS(altitude, machNumber);
        Amount<Velocity> portionWindSpeed = getPortionWindSpeed(windSpeed, angleRelativeToY);
        
        return tas.plus(portionWindSpeed);
    }

    /**
     * Obtains wind speed portion (vx)
     *
     * @param windSpeed wind speed
     * @param angleRelativeToY angle relative to Y in degrees
     * @return the wind speed portion (vx)
     */
    public static Amount<Velocity> getPortionWindSpeed(Amount<Velocity> windSpeed, Amount<Angle> angleRelativeToY) {
        Double angleRelativeToY1 = angleRelativeToY.doubleValue(SI.RADIAN);
        Double windSpeed1 = windSpeed.doubleValue(SI.METERS_PER_SECOND);
        Double portionWindSpeed = windSpeed1 * Math.cos((Math.PI / 2) - angleRelativeToY1);
        
        return Amount.valueOf(portionWindSpeed, SI.METERS_PER_SECOND);
    }

    /**
     * Gets the drag coefficient.
     *
     * @param altitude altitude in meters
     * @param mass mass of the aircraft in Kg
     * @param dragCoefficient0 coefficient drag zero
     * @param aspectRatio the aspect ratio (wingSpan^2 / wingsArea) (dimensionless)
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
    
    public static Amount<Dimensionless> getDragCoefficient(FlightSimulation flight, Amount<Length> altitude,
            Amount<Mass> mass, Amount<Velocity> machNumber) {

        Amount<Area> wingArea = flight.getFlightInfo().getAircraft().getAircraftModel().getWingArea();
        Amount<Dimensionless> aspectRatio = flight.getFlightInfo().getAircraft().getAircraftModel().getAspectRatio();
        Amount<Dimensionless> e = flight.getFlightInfo().getAircraft().getAircraftModel().getE();
        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(flight.getFlightInfo().getAircraft().getAircraftModel().
                getCdragFunction(machNumber.doubleValue(NonSI.MACH)), Unit.ONE);
        
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
     * @param aspectRatio the aspect ratio (wingSpan^2 / wingsArea) (dimensionless)
     * @return the calculated drag force
     */
    public static Amount<Force> getDragForce(Amount<Length> altitude, Amount<Mass> mass,
            Amount<Dimensionless> dragCoefficient0, Amount<Area> wingArea, Amount<Dimensionless> e,
            Amount<Velocity> machNumber, Amount<Dimensionless> aspectRatio) {
        
        return (Amount<Force>) getDragCoefficient(altitude, mass, dragCoefficient0, aspectRatio, wingArea, e, machNumber)
                .times(getAirDensity(altitude))
                .times((getTAS(altitude, machNumber)).pow(2))
                .times(wingArea)
                .divide(Amount.valueOf(2, Unit.ONE));
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
        
        return (Amount<Force>) getDragCoefficient(altitude, mass, dragCoefficient0, aspectRatio, wingArea, e, machNumber)
                .times(getAirDensity(altitude))
                .times((getTAS(altitude, machNumber)).pow(2))
                .times(wingArea)
                .divide(Amount.valueOf(2, Unit.ONE));
    }

    /**
     * Obtains the maximum range.
     *
     * @param tsfc the thrust specific fuel consumption
     * @param initialWeight the initial weight
     * @param finalWeight the final weight
     * @param altitude the altitude
     * @param machNumber the mach number
     * @param aspectRatio the aspect ratio (wingSpan^2 / wingsArea) (dimensionless)
     * @param dragCoefficient0 the initial drag coefficient
     * @param e the efficiency factor
     * @param wingsArea the wings area
     * @return the calculated maximum range
     */
    public static Amount<Length> getMaximumRange(Amount<Power> tsfc, Amount<Mass> initialWeight, Amount<Mass> finalWeight,
            Amount<Length> altitude, Amount<Velocity> machNumber, Amount<Dimensionless> aspectRatio,
            Amount<Dimensionless> dragCoefficient0, Amount<Dimensionless> e, Amount<Area> wingsArea) {

        Amount<Force> liftForce = getLiftForce(altitude, initialWeight, wingsArea, machNumber);
        Amount<Force> dragForce = getDragForce(altitude, initialWeight, dragCoefficient0,
                wingsArea, e, machNumber, aspectRatio);
        Amount<Velocity> tas = getTAS(altitude, machNumber);
        
        Double resultLN = Math.log(initialWeight.doubleValue(SI.KILOGRAM)) - Math.log(finalWeight.doubleValue(SI.KILOGRAM));
        Double maxRange = (tas.doubleValue(SI.METERS_PER_SECOND) / (tsfc.doubleValue(CustomUnits.TSFC_SI)))
                * (liftForce.doubleValue(SI.NEWTON) / (dragForce.doubleValue(SI.NEWTON))) * resultLN;

        return Amount.valueOf(maxRange, SI.KILOMETER);
    }
    
    /**
     * Obtains the maximum range for a given flight with a given initial weight and a final weight. (m)
     * @param flight the flight
     * @param initialWeight the initial weight (Kg)
     * @param finalWeight the final weight (Kg)
     * @param machNumber the mach number (m)
     * @return the maximum range for a given flight with a given initial weight and final weight 
     */
    public static Amount<Length> getMaximumRange(FlightSimulation flight, Amount<Velocity> machNumber,
            Amount<Mass> initialWeight, Amount<Mass> finalWeight ) {
        
        Amount tsfc = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getTsfc();
        Amount<Length> altitude = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseAltitude();
        Amount<Area> wingsArea = flight.getFlightInfo().getAircraft().getAircraftModel().getWingArea();
        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(flight.getFlightInfo().getAircraft().getAircraftModel().
                getCdragFunction(machNumber.doubleValue(NonSI.MACH)), Unit.ONE);
        Amount<Force> liftForce = getLiftForce(altitude, initialWeight, wingsArea, machNumber);
        Amount<Dimensionless> e = flight.getFlightInfo().getAircraft().getAircraftModel().getE();
        Amount<Dimensionless> aspectRatio = flight.getFlightInfo().getAircraft().getAircraftModel().getAspectRatio();
        Amount<Force> dragForce = getDragForce(altitude, initialWeight, dragCoefficient0,
                wingsArea, e, machNumber, aspectRatio);
        Amount<Velocity> tas = getTAS(altitude, machNumber);
        
        Double resultLN = Math.log(initialWeight.doubleValue(SI.KILOGRAM)) - Math.log(finalWeight.doubleValue(SI.KILOGRAM));
        Double maxRange = (tas.doubleValue(SI.METERS_PER_SECOND) / (tsfc.doubleValue(CustomUnits.TSFC_SI)))
                * (liftForce.doubleValue(SI.NEWTON) / (dragForce.doubleValue(SI.NEWTON))) * resultLN;

        return Amount.valueOf(maxRange, SI.KILOMETER);
    }

    /**
     * Obtains the fuel consumption. 
     *
     * @param tsfc the thrust specific fuel consumption
     * @param initialWeight the initial weight
     * @param altitude the altitude
     * @param machNumber the mach number
     * @param dragCoefficient0 the initial drag coefficient
     * @param aspectRatio the aspect ratio (wingSpan^2 / wingsArea) (dimensionless)
     * @param e the efficiency factor
     * @param wingsArea the wings area
     * @param distance the distance
     * @return the calculated fuel consumption
     */
    public static Amount<Volume> getFuelConsumption(Amount<Power> tsfc, Amount<Mass> initialWeight,
            Amount<Length> altitude, Amount<Velocity> machNumber,
            Amount<Dimensionless> dragCoefficient0, Amount<Dimensionless> aspectRatio, Amount<Dimensionless> e,
            Amount<Area> wingsArea, Amount<Length> distance) {

        Amount<Force> liftForce = getLiftForce(altitude, initialWeight, wingsArea, machNumber);
        Amount<Force> dragForce = getDragForce(altitude, initialWeight, dragCoefficient0,
                wingsArea, e, machNumber, aspectRatio);
        Amount<Velocity> tas = getTAS(altitude, machNumber);
        
        Double finalWeight = initialWeight.doubleValue(SI.KILOGRAM) / Math.pow(Math.E, (distance.doubleValue(SI.KILOMETER)
                * tsfc.doubleValue(CustomUnits.TSFC_SI) / ((liftForce.doubleValue(SI.NEWTON) / dragForce.doubleValue(SI.NEWTON))
                * tas.doubleValue(SI.METERS_PER_SECOND))));

        return Amount.valueOf((initialWeight.doubleValue(SI.KILOGRAM) - finalWeight), NonSI.LITER);
    }
    
    /**
     * Obtains the fuel consumption for a given flight, for a given initial weight and final weight (Kg)
     * @param flight the flight
     * @param initialWeight the initial weight (Kg)
     * @param machNumber the mach number (m)
     * @param distance the distance (m)
     * @return the fuel consumption for a given flight, for a given initial weight and final weight
     */
    public static Amount<Volume> getFuelConsumption(FlightSimulation flight, Amount<Mass> initialWeight,
            Amount<Velocity> machNumber, Amount<Length> distance) {

        Amount tsfc = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getTsfc();
        Amount<Length> altitude = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getCruiseAltitude();
        Amount<Area> wingsArea = flight.getFlightInfo().getAircraft().getAircraftModel().getWingArea();
        Amount<Dimensionless> dragCoefficient0 = Amount.valueOf(flight.getFlightInfo().getAircraft().getAircraftModel().
                getCdragFunction(machNumber.doubleValue(NonSI.MACH)), Unit.ONE);
        Amount<Dimensionless> aspectRatio = flight.getFlightInfo().getAircraft().getAircraftModel().getAspectRatio();
        Amount<Dimensionless> e = flight.getFlightInfo().getAircraft().getAircraftModel().getE();         
        Amount<Force> liftForce = getLiftForce(altitude, initialWeight, wingsArea, machNumber);
        Amount<Force> dragForce = getDragForce(altitude, initialWeight, dragCoefficient0,
                wingsArea, e, machNumber, aspectRatio);
        Amount<Velocity> tas = getTAS(altitude, machNumber);
        
        Double finalWeight = initialWeight.doubleValue(SI.KILOGRAM) / Math.pow(Math.E, (distance.doubleValue(SI.KILOMETER)
                * tsfc.doubleValue(CustomUnits.TSFC_SI) / ((liftForce.doubleValue(SI.NEWTON) / dragForce.doubleValue(SI.NEWTON))
                * tas.doubleValue(SI.METERS_PER_SECOND))));

        return Amount.valueOf((initialWeight.doubleValue(SI.KILOGRAM) - finalWeight), NonSI.LITER);
    }

    /**
     * Obtains the pressure in a given altitude.
     *
     * @param altitude the given altitude (m)
     * @return the pressure (N/m2)
     */
    public static Amount<Pressure> getPressure(Amount<Length> altitude) {
        
        return Amount.valueOf(Consts.AIR_PRESSURE_SEA_LEVEL.doubleValue(CustomUnits.PRESSURE_SI)
                * (1.0d + Consts.TEMP_LAPSE_RATE.doubleValue(CustomUnits.TEMP_GRADIENT_SI)
                * (altitude.doubleValue(SI.METER)
                / Consts.REF_TEMP_SEA_LEVEL.doubleValue(CustomUnits.TEMP_GRADIENT_SI))), CustomUnits.PRESSURE_SI);
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
       
        return Amount.valueOf(Math.sqrt(5.0d * (Math.pow(((Consts.AIR_DENSITY_SEA_LEVEL.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI)
                / airDensity.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI))
                * (Math.pow(Math.pow(ias.doubleValue(SI.METERS_PER_SECOND)
                        / 661.5d, 2.0d), 3.5d) - 1.0d) + 1.0d), 0.286d) - 1.0d)), NonSI.MACH);
    }

    /**
     * Obtains the rate of climb (m/s)
     *
     * @param totalThrust the total thrust (N)
     * @param tas the true air speed (m/s)
     * @param mass the aircraft weight (Kg)
     * @return the rate of climb (m/s)
     */
    public static Amount<Velocity> getRateOfClimb(Amount<Force> totalThrust, Amount<Velocity> tas, Amount<Mass> mass) {
        
        return Amount.valueOf((totalThrust.doubleValue(SI.NEWTON) * tas.doubleValue(SI.METERS_PER_SECOND))
                / (mass.doubleValue(SI.KILOGRAM) * Constants.g.doubleValue(SI.METERS_PER_SQUARE_SECOND)), SI.METERS_PER_SECOND);
    }

    /**
     * Obtains the climbing angle (Degree)
     *
     * @param rateOfClimb the climbing rate (m/s)
     * @param tas the true air speed (m/s)
     * @return the climbing angle (degree)
     */
    public static Amount<Angle> getClimbingAngle(Amount<Velocity> rateOfClimb, Amount<Velocity> tas) {
        
        return Amount.valueOf(Math.asin(rateOfClimb.doubleValue(SI.METERS_PER_SECOND)
                / tas.doubleValue(SI.METERS_PER_SECOND)), NonSI.DEGREE_ANGLE);
    }

    /**
     * Obtains the distance for a specific time step, tas and angle
     *
     * @param tas the true air speed (m/s)
     * @param angle the angle (degree)
     * @param timeStep the time step (s)
     * @return a distance (m)
     */
    public static Amount<Length> getDistance(Amount<Velocity> tas, Amount<Angle> angle, Amount<Duration> timeStep) {
        
        return Amount.valueOf(tas.doubleValue(SI.METERS_PER_SECOND)
                * Math.cos(angle.doubleValue(NonSI.DEGREE_ANGLE)
                        * timeStep.doubleValue(SI.SECOND)), SI.METER);
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
        
        return Amount.valueOf(mass.doubleValue(SI.KILOGRAM)
                - fuelBurned.doubleValue(SI.KILOGRAM), SI.KILOGRAM);
    }

    /**
     * Obtains the fuel burn calculation (dw/dt) (N/s)
     *
     * @param totalThrust the total thrust (N)
     * @param timeStep the time step (dt) (s)
     * @param tsfc the thrust specific fuel consumption (Kg/s/N)_SI (N/s/N)_US
     * @return the fuel burn calculation (dw/dt) (N/s)
     */
    public static Amount getFuelBurnCalculation(Amount<Force> totalThrust, Amount<Duration> timeStep, Amount tsfc) {
        
        return Amount.valueOf(totalThrust.doubleValue(CustomUnits.TSFC_SI)
                * timeStep.doubleValue(SI.SECOND)
                * tsfc.doubleValue(CustomUnits.TSFC_SI), CustomUnits.FUEL_BURN_SI);
    }

    /**
     * Obtains cumulative distance
     *
     * @param cumulativePreviousDistance the cumulative previous distance (m)
     * @param distance the distance traveled (m)
     * @return the actual cumulative distance (m)
     */
    public static Amount<Length> getCumulativeDistance(Amount<Length> cumulativePreviousDistance, Amount<Length> distance) {
        
        return cumulativePreviousDistance.plus(distance);
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
     * @param thrust_0 the thrust zero (N)
     * @param lambda the lambda value to be used to calculate the thrust (N)
     * @param machTrue the mach true number (M)
     * @param airDensity the air density (Kg/m3)
     * @param lapseRateFactor the lapse rate factor (dimensionless)
     * @return the thrust value (N)
     */
    public static Amount<Force> getThrust(Amount<Force> thrust_0, Amount<Force> lambda,
            Amount<Velocity> machTrue, Amount<VolumetricDensity> airDensity, Amount<Dimensionless> lapseRateFactor) {
        
        return Amount.valueOf((thrust_0.doubleValue(SI.NEWTON) - lambda.doubleValue(SI.NEWTON))
                * Math.pow((airDensity.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI)
                        / Consts.AIR_DENSITY_SEA_LEVEL.doubleValue(CustomUnits.VOLUMETRIC_DENSITY_SI)),
                        lapseRateFactor.doubleValue(Unit.ONE)), SI.NEWTON);
    }
    
    /**
     * Obtains the thrust value (N)
     * 
     * @param flight the flight
     * @param lambda lambda the lambda value to be used to calculate the thrust (N)
     * @param machTrue the mach true number (M)
     * @param airDensity the air density (Kg/m3)
     * @return the thrust value (N)
     */
    public static Amount<Force> getThrust(FlightSimulation flight, Amount<Force> lambda,
            Amount<Velocity> machTrue, Amount<VolumetricDensity> airDensity) {
        
        Amount<Force> thrust_0 = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getThrustFunction().getThrust0();
        Amount<Dimensionless> lapseRateFactor = flight.getFlightInfo().getAircraft().getAircraftModel().getMotorization().getLapseRateFactor();
        
        return Amount.valueOf((thrust_0.doubleValue(SI.NEWTON) - lambda.doubleValue(SI.NEWTON))
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

}
