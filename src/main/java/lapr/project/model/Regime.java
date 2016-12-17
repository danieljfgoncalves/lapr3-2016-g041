/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;

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
    private Double tsfc;

    /**
     * The speed.
     */
    private Double speed;

    /**
     * The thrust.
     */
    private Double thrust;

    /**
     * The altitude.
     */
    private Double altitude;

    /**
     * The default regimeID.
     */
    private static final String DEFAULT_REGIME_ID = "Cruise";

    /**
     * The default tsfc (thrust specific fuel consumption).
     */
    private static final Double DEFAULT_TSFC = 0.1d;

    /**
     * The default speed.
     */
    private static final Double DEFAULT_SPEED = 0.1d;

    /**
     * The default thrust.
     */
    private static final Double DEFAULT_THRUST = 1.0d;

    /**
     * The default altitude.
     */
    private static final Double DEFAULT_ALTITUDE = 100.0d;

    /**
     * The epsilon of the allowed error.
     */
    private final static Double EPSILON = 0.0001d;

    /**
     * Creates an instance of Regime with given attributes.
     *
     * @param regimeID the given regimeID
     * @param tsfc the given tsfc
     * @param speed the given speed
     * @param thrust the given thrust
     * @param altitude the given altitude
     */
    public Regime(String regimeID, Double tsfc, Double speed, Double thrust, Double altitude) {
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
    public Double getTsfc() {
        return tsfc;
    }

    /**
     * Modifes the tsfc (thrust specific fuel consumption).
     *
     * @param tsfc the tsfc to set
     */
    public void setTsfc(Double tsfc) {
        this.tsfc = tsfc;
    }

    /**
     * Gets the speed.
     *
     * @return the speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Modifies the speed.
     *
     * @param speed the speed to set
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * Gets the thrust.
     *
     * @return the thrust
     */
    public Double getThrust() {
        return thrust;
    }

    /**
     * Modifies the thrust.
     *
     * @param thrust the thrust to set
     */
    public void setThrust(Double thrust) {
        this.thrust = thrust;
    }

    /**
     * Gets the altitude.
     *
     * @return the altitude
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * Modifies the altitude.
     *
     * @param altitude the altitude to set
     */
    public void setAltitude(Double altitude) {
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
                && Math.abs(this.tsfc - other.tsfc) < EPSILON
                && Math.abs(this.speed - other.speed) < EPSILON
                && Math.abs(this.thrust - other.thrust) < EPSILON
                && Math.abs(this.altitude - other.altitude) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("Regime ID: %s\n"
                + "TSFC: %.3f\n"
                + "Speed: %.2f\n"
                + "Thrust: %.1f\n"
                + "Altitude: %.1f", regimeID, tsfc, speed, thrust, altitude);
    }

}
