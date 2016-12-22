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
     */
    public static Amount<Force> getLiftForce(Amount<Length> altitude, Amount<Mass> initialWeight, Amount<Area> wingsArea) {
        return (Amount<Force>) getLiftCoefficient(altitude, initialWeight, wingsArea).times(getAirDensity(altitude)).times(getSpeedOfSound(altitude).pow(2)).times(wingsArea).divide(Amount.valueOf(2, Unit.ONE));
    }

    /**
     * Gets the liftCoefficient.
     *
     * @param altitude the altitude
     * @param initialWeight the initial weight
     * @param wingsArea the wings area
     * @return lift coefficient the lift coefficient
     */
    public static Amount<Dimensionless> getLiftCoefficient(Amount<Length> altitude, Amount<Mass> initialWeight, Amount<Area> wingsArea) {
        return (Amount<Dimensionless>) Amount.valueOf(2, Unit.ONE).times(initialWeight).times(Constants.g).divide(getAirDensity(altitude).times(wingsArea).times(getSpeedOfSound(altitude).pow(2)));
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
            {-1000, 13.47},
            {0, 12.25},
            {1000, 11.12},
            {2000, 10.07},
            {3000, 9.093},
            {4000, 8.194},
            {5000, 7.364},
            {6000, 6.601},
            {7000, 5.900},
            {8000, 5.258},
            {9000, 4.671},
            {10000, 4.135},
            {15000, 1.948},
            {20000, 0.8891},
            {25000, 0.4008},
            {30000, 0.1841},
            {40000, 0.03996},
            {50000, 0.01027},
            {60000, 0.003097},
            {70000, 0.0008283},
            {80000, 0.0001846}
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
            {5, 334.4},
            {10, 328.4},
            {15, 322.2},
            {20, 316.0},
            {25, 309.6},
            {30, 303.1},
            {35, 295.4},
            {40, 294.9}
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

        return Amount.valueOf(table[index][1], SI.METERS_PER_SECOND);
    }

    /**
     * Obtains object speed (ground speed)
     *
     * @param altitude altitute of aircraft in cruise travel above sea level
     * @param machNumber mach number
     * @return the object speed (ground speed)
     */
    public static Amount<Velocity> getObjectSpeed(Amount<Length> altitude, Amount<Velocity> machNumber) {
        Amount<Velocity> speedOfSound = getSpeedOfSound(altitude);
        return (Amount<Velocity>) speedOfSound.times(machNumber);
    }

    /**
     * Obtains True Air Speed (TAS)(velocity of aircraft relative to the
     * air(m/s)
     *
     * @param altitude altitute of aircraft in cruise travel above sea level
     * @param machNumber mach number
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
        Double angleRelativeToY1 = angleRelativeToY.doubleValue(NonSI.DEGREE_ANGLE);
        Double windSpeed1 = windSpeed.doubleValue(SI.METERS_PER_SECOND);
        Double portionWindSpeed = windSpeed1 * Math.cos(90 - angleRelativeToY1);
        return Amount.valueOf(portionWindSpeed, SI.METERS_PER_SECOND);
    }
}
