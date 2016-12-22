/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import javax.measure.quantity.Area;
import javax.measure.quantity.Power;
import javax.measure.quantity.Quantity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Custom Conversion algorithms.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
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
    public static final Unit TSFC_SI = (SI.KILOGRAM.divide(SI.SECOND)).divide(SI.NEWTON);
    
    /**
     * Square Foot unit (ft2)
     */
    public static final Unit<Area> SQUARE_FOOT = NonSI.FOOT.pow(2).asType(Area.class);

}
