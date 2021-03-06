/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import javax.measure.quantity.Area;
import javax.measure.quantity.Velocity;
import javax.measure.quantity.VolumetricDensity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Custom Conversion algorithms.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class CustomUnits {

    /**
     * TSFC unit in US System of units (lbm/s/lbs)
     */
    public static final Unit TSFC_US = (NonSI.POUND.divide(SI.SECOND)).divide(NonSI.POUND_FORCE);

    /**
     * TSFC unit in SI System of units (kg/s/N)
     */
    public static final Unit TSFC_SI = (SI.NEWTON).divide(SI.NEWTON).divide(SI.SECOND);
    
    /**
     * TSFC unit in SI System of units (N/N/S)
     */
    public static final Unit TSFC_NNS = (SI.NEWTON).divide(SI.NEWTON).divide(SI.SECOND);

    /**
     * Square Foot unit (ft2)
     */
    public static final Unit<Area> SQUARE_FOOT = NonSI.FOOT.pow(2).asType(Area.class);

    /**
     * Temperature lapse rate unit (K/m)
     */
    public static final Unit TEMP_GRADIENT_SI = SI.KELVIN.divide(SI.METER);

    /**
     * Volumetric density (Kg/m3)
     */
    public static final Unit<VolumetricDensity> VOLUMETRIC_DENSITY_SI = SI.KILOGRAM.divide(SI.METER.pow(3)).asType(VolumetricDensity.class);

    /**
     * dw/dt fuel burn (N/s)
     */
    public static final Unit FUEL_BURN_SI = SI.NEWTON.divide(SI.SECOND);

    /**
     * Rdesc (ft/s)
     */
    public static final Unit<Velocity> FEET_PER_SECOND = NonSI.FOOT.divide(SI.SECOND).asType(Velocity.class);

}
