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
import javax.measure.quantity.Velocity;
import javax.measure.quantity.VolumetricDensity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import org.jscience.physics.amount.Amount;
import org.jscience.physics.amount.Constants;

/**
 * Resposible to make calculations related to model business.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Calculus {

    /**
     * Gets the lift force.
     *
     * @param altitude the altitude
     * @param initialWeight the initial weight
     * @param wingsArea the wings area
     * @return lift force the lift force
     * @param machNumber mach number
     * @param windSpeed wind speed
     * @param angleRelativeToY angle relative to y
     */
    public static Amount<Force> getLiftForce(Amount<Length> altitude, Amount<Mass> initialWeight, Amount<Area> wingsArea, Amount<Velocity> machNumber, Amount<Velocity> windSpeed, Amount<Angle> angleRelativeToY) {
        return (Amount<Force>) getLiftCoefficient(altitude, initialWeight, wingsArea, machNumber, windSpeed, angleRelativeToY)
                .times(getAirDensity(altitude))
                .times(getTAS(altitude, machNumber, windSpeed, angleRelativeToY).pow(2))
                .times(wingsArea)
                .divide(Amount.valueOf(2, Unit.ONE));
    }

    /**
     * Gets the liftCoefficient.
     *
     * @param altitude the altitude
     * @param initialWeight the initial weight
     * @param wingsArea the wings area
     * @param machNumber mach number
     * @param windSpeed wind speed
     * @param angleRelativeToY angle relative to y
     * @return lift coefficient the lift coefficient
     */
    public static Amount<Dimensionless> getLiftCoefficient(Amount<Length> altitude, Amount<Mass> initialWeight, Amount<Area> wingsArea, Amount<Velocity> machNumber, Amount<Velocity> windSpeed, Amount<Angle> angleRelativeToY) {
        return (Amount<Dimensionless>) Amount.valueOf(2, Unit.ONE)
                .times(initialWeight)
                .times(Constants.g)
                .divide(getAirDensity(altitude)
                        .times(wingsArea)
                        .times(getTAS(altitude, machNumber, windSpeed, angleRelativeToY).pow(2)));
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
     * @return the speed of light at the given altitude
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
     * Obtains object speed (ground speed)
     *
     * @param altitude altitute of aircraft in cruise travel above sea level
     * @param machNumber mach number
     * @return the object speed (ground speed)
     */
    public static Amount<Velocity> getObjectSpeed(Amount<Length> altitude, Amount<Velocity> machNumber) {
        double objectSpeed = getSpeedOfSound(altitude).doubleValue(SI.METERS_PER_SECOND) * machNumber.doubleValue(NonSI.MACH);
        return Amount.valueOf(objectSpeed, SI.METERS_PER_SECOND);
    }

    /**
     * Obtains True Air Speed (TAS)(velocity of aircraft relative to the
     * air(m/s)
     *
     * @param altitude altitute of aircraft in cruise travel above sea level
     * @param machNumber mach number
     * @param windSpeed wind speed
     * @param angleRelativeToY angle relative to y
     * @return the True Air Speed (TAS) (velocity of aircraft relative to the
     * air)
     */
    public static Amount<Velocity> getTAS(Amount<Length> altitude, Amount<Velocity> machNumber, Amount<Velocity> windSpeed, Amount<Angle> angleRelativeToY) {
        Amount<Velocity> objectSpeed = getObjectSpeed(altitude, machNumber);
        Amount<Velocity> portionWindSpeed = getPortionWindSpeed(windSpeed, angleRelativeToY);
        return objectSpeed.plus(portionWindSpeed);
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
     * @param altitude in meters
     * @param initialWeight of the aircraft in kg
     * @param dragCoefficient0
     * @param wingSpan in meters
     * @param wingArea in square meters
     * @param machNumber mach number
     * @param windSpeed wind speed
     * @param angleRelativeToY angle relative to y
     * @param e e
     * @return the calculated drag coefficient
     */
    public static Amount<Dimensionless> getDragCoefficient(Amount<Length> altitude, Amount<Mass> initialWeight, Amount<Dimensionless> dragCoefficient0,
            Amount<Length> wingSpan, Amount<Area> wingArea, Amount<Dimensionless> e, Amount<Velocity> machNumber, Amount<Velocity> windSpeed, Amount<Angle> angleRelativeToY) {

        Amount<Dimensionless> cl = getLiftCoefficient(altitude, initialWeight, wingArea, machNumber, windSpeed, angleRelativeToY);
        Amount<Dimensionless> cl2 = (Amount<Dimensionless>) cl.pow(2);
        Amount<Dimensionless> aspectRatio = (Amount<Dimensionless>) (wingSpan.times(wingSpan)).divide(wingArea);
        return (Amount<Dimensionless>) (cl2.divide(aspectRatio.times(Constants.π).times(e))).plus(dragCoefficient0);

    }

    /**
     * Gets the Drag Force.
     *
     * @param altitude the altitude in meters
     * @param initialWeight the initial weight in kg
     * @param dragCoefficient0
     * @param wingSpan the wingSpan e meters
     * @param wingArea the wings area in square meters
     * @param e e
     * @param referenceAircraftArea reference aircraft area in square meters
     * @param machNumber mach number
     * @param windSpeed wind speed
     * @param angleRelativeToY angle relative to y
     * @return the calculated drag force
     */
    public static Amount<Force> getDragForce(Amount<Length> altitude, Amount<Mass> initialWeight, Amount<Dimensionless> dragCoefficient0,
            Amount<Length> wingSpan, Amount<Area> wingArea, Amount<Dimensionless> e, Amount<Area> referenceAircraftArea,
            Amount<Velocity> machNumber, Amount<Velocity> windSpeed, Amount<Angle> angleRelativeToY) {
        return (Amount<Force>) getDragCoefficient(altitude, initialWeight, dragCoefficient0, wingSpan, wingArea, e, machNumber, windSpeed, angleRelativeToY)
                .times(getAirDensity(altitude))
                .times(getSpeedOfSound(altitude).pow(2))
                .times(referenceAircraftArea)
                .divide(Amount.valueOf(2, Unit.ONE));

    }
}
