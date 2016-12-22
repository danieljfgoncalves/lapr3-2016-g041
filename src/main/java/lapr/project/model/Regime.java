/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Power;
import javax.measure.quantity.Quantity;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import lapr.project.utils.CustomUnits;
import org.jscience.physics.amount.Amount;

/**
 * Represents a Regime class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Regime {

    /**
     * The regimeID.
     */
    private String regimeID;

    /**
     * The tsfc (thrust specific fuel consumption).
     */
    private Amount tsfc;

    /**
     * The speed (Mach).
     */
    private Amount<Velocity> speed;

    /**
     * The thrust (SI: N).
     */
    private Amount<Force> thrust;

    /**
     * The altitude (SI: m).
     */
    private Amount<Length> altitude;

    /**
     * The default regimeID.
     */
    private static final String DEFAULT_REGIME_ID = "Cruise";

    /**
     * The default tsfc (thrust specific fuel consumption).
     */
    private static final Amount DEFAULT_TSFC = Amount.valueOf(0d, CustomUnits.TSFC_SI);

    /**
     * The default speed.
     */
    private static final Amount<Velocity> DEFAULT_SPEED = Amount.valueOf(0d, NonSI.MACH);

    /**
     * The default thrust.
     */
    private static final Amount<Force> DEFAULT_THRUST = Amount.valueOf(0d, SI.NEWTON);

    /**
     * The default altitude.
     */
    private static final Amount<Length> DEFAULT_ALTITUDE = Amount.valueOf(100d, SI.METER);

    /**
     * Creates an instance of Regime with given attributes.
     *
     * @param regimeID the given regimeID
     * @param tsfc the given tsfc
     * @param speed the given speed
     * @param thrust the given thrust
     * @param altitude the given altitude
     */
    public Regime(String regimeID, Amount tsfc, Amount<Velocity> speed, Amount<Force> thrust, Amount<Length> altitude) {
        this.regimeID = regimeID;
        this.tsfc = tsfc;
        this.speed = speed;
        this.thrust = thrust;
        this.altitude = altitude;
    }

    /**
     * Creates an instance of Regime with default values.
     */
    public Regime() {
        this.regimeID = DEFAULT_REGIME_ID;
        this.tsfc = DEFAULT_TSFC;
        this.speed = DEFAULT_SPEED;
        this.thrust = DEFAULT_THRUST;
        this.altitude = DEFAULT_ALTITUDE;
    }

    /**
     * Creates a copy of a Regime.
     *
     * @param other the given Regime object
     */
    public Regime(Regime other) {
        this.regimeID = other.regimeID;
        this.tsfc = other.tsfc;
        this.speed = other.speed;
        this.thrust = other.thrust;
        this.altitude = other.altitude;
    }

    /**
     * Gets the regimeID.
     *
     * @return the regimeID
     */
    public String getRegimeID() {
        return regimeID;
    }

    /**
     * Modifies the regimeID.
     *
     * @param regimeID the regimeID to set
     */
    public void setRegimeID(String regimeID) {
        this.regimeID = regimeID;
    }

    /**
     * Gets the tsfc (thrust specific fuel consumption).
     *
     * @return the tsfc
     */
    public Amount getTsfc() {
        return tsfc;
    }

    /**
     * Modifes the tsfc (thrust specific fuel consumption).
     *
     * @param tsfc the tsfc to set
     */
    public void setTsfc(Amount tsfc) {
        this.tsfc = tsfc;
    }

    /**
     * Gets the speed.
     *
     * @return the speed
     */
    public Amount<Velocity> getSpeed() {
        return speed;
    }

    /**
     * Modifies the speed.
     *
     * @param speed the speed to set
     */
    public void setSpeed(Amount<Velocity> speed) {
        this.speed = speed;
    }

    /**
     * Gets the thrust.
     *
     * @return the thrust
     */
    public Amount<Force> getThrust() {
        return thrust;
    }

    /**
     * Modifies the thrust.
     *
     * @param thrust the thrust to set
     */
    public void setThrust(Amount<Force> thrust) {
        this.thrust = thrust;
    }

    /**
     * Gets the altitude.
     *
     * @return the altitude
     */
    public Amount<Length> getAltitude() {
        return altitude;
    }

    /**
     * Modifies the altitude.
     *
     * @param altitude the altitude to set
     */
    public void setAltitude(Amount<Length> altitude) {
        this.altitude = altitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.regimeID);
        hash = 17 * hash + Objects.hashCode(this.tsfc);
        hash = 17 * hash + Objects.hashCode(this.speed);
        hash = 17 * hash + Objects.hashCode(this.thrust);
        hash = 17 * hash + Objects.hashCode(this.altitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Regime other = (Regime) obj;

        return this.regimeID.equals(other.regimeID)
                && this.tsfc.approximates(other.tsfc)
                && this.speed.approximates(other.speed)
                && this.thrust.approximates(other.thrust)
                && this.altitude.approximates(other.altitude);
    }

    @Override
    public String toString() {
        return String.format("Regime ID: %s\n"
                + "TSFC: %s\n"
                + "Speed: %s\n"
                + "Thrust: %s\n"
                + "Altitude: %s", regimeID, tsfc, speed, thrust, altitude);
    }

}
