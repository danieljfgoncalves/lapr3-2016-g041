/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import javax.measure.quantity.Temperature;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Velocity;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import javax.measure.quantity.VolumetricDensity;
import javax.measure.quantity.Acceleration;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

/**
 * Constant values used in the project.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Constants {

    /**
     * Reference temperature at sea level. (Kelvin)
     */
    private static final Amount<Temperature> REF_TEMP_SEA_LEVEL = Amount.valueOf(288.2, SI.KELVIN);

    /**
     * Temperature lapse rate. (Kelvin/meter)(K/m)
     */
    private static final Amount<Temperature> TEMP_LAPSE_RATE = Amount.valueOf(-0.0065, CustomUnits.TEMP_GRADIENT_SI);

    /**
     * Air density sea level. (Kg/m3)
     */
    private static final Amount<VolumetricDensity> AIR_DENSITY_SEA_LEVEL = Amount.valueOf(1.225, CustomUnits.VOLUMETRIC_DENSITY_SI);

    /**
     * Pressure at sea level. (N/m2)
     */
    private static final Amount<Pressure> AIR_PRESSURE_SEA_LEVEL = Amount.valueOf(101325, CustomUnits.PRESSURE_SI);

    /**
     * Speed of sound at sea level. (m/s)
     */
    private static final Amount<Velocity> SPEED_OF_SOUND_SEA_LEVEL = Amount.valueOf(340.3, SI.METERS_PER_SECOND);

    /**
     * Gravity acceleration on earth. (m/s2)
     */
    private static final Amount<Acceleration> GRAVITY_ACCELERATION_EARTH = Amount.valueOf(9.81, SI.METERS_PER_SQUARE_SECOND);

    /**
     * Ratio of specific heat at constant value. (dimensionless)
     */
    private static final Amount<Dimensionless> GAMMA = Amount.valueOf(1.4, Unit.ONE);

    /**
     * The value of a knot in SI(m/s).
     */
    private static final Amount<Velocity> KNOT = Amount.valueOf(0.514444, SI.METERS_PER_SECOND);
}
