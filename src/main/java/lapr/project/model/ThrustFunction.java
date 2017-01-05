/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import javax.measure.quantity.Force;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;

/**
 * Represents a thrust function.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ThrustFunction {

    /**
     * The thrust 0 (SI = N).
     */
    private Amount<Force> thrust0;

    /**
     * The thrust max speed (SI = m/s).
     */
    private Amount<Velocity> thrustMaxSpeed;

    /**
     * The max speed.
     */
    private Amount<Velocity> maxSpeed;

    /**
     * The default thrust 0.
     */
    private static final Amount<Force> DEFAULT_THRUST_0 = Amount.valueOf(4.89E+05, SI.NEWTON);

    /**
     * The default thrust max speed.
     */
    private static final Amount<Velocity> DEFAULT_THRUST_MAX_SPEED = Amount.valueOf(3.0E+05, SI.METERS_PER_SECOND);

    /**
     * The default max speed.
     */
    private static final Amount<Velocity> DEFAULT_MAX_SPEED = Amount.valueOf(1.0, NonSI.MACH);

    /**
     * Constructs a thrust function with default values.
     */
    public ThrustFunction() {
        this.thrust0 = DEFAULT_THRUST_0;
        this.thrustMaxSpeed = DEFAULT_THRUST_MAX_SPEED;
        this.maxSpeed = DEFAULT_MAX_SPEED;
    }

    /**
     * Constructs a thrust function receiving their parameters.
     *
     * @param thrust0 the thrust 0
     * @param thrustMaxSpeed the thrust max speed
     * @param maxSpeed the max speed
     */
    public ThrustFunction(Amount<Force> thrust0, Amount<Velocity> thrustMaxSpeed, Amount<Velocity> maxSpeed) {
        this.thrust0 = thrust0;
        this.thrustMaxSpeed = thrustMaxSpeed;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Creates an instance of thrust function copying from another thrust
     * function.
     *
     * @param otherThrustFunction another thrust function
     */
    public ThrustFunction(ThrustFunction otherThrustFunction) {
        this.thrust0 = otherThrustFunction.thrust0;
        this.thrustMaxSpeed = otherThrustFunction.thrustMaxSpeed;
        this.maxSpeed = otherThrustFunction.maxSpeed;
    }

    /**
     * Gets the thrust 0.
     *
     * @return thrust 0
     */
    public Amount<Force> getThrust0() {
        return thrust0;
    }

    /**
     * Sets the thrust 0.
     *
     * @param thrust0 thrust 0
     */
    public void setThrust0(Amount<Force> thrust0) {
        this.thrust0 = thrust0;
    }

    /**
     * Gets the thrust max speed.
     *
     * @return thrust max speed
     */
    public Amount<Velocity> getThrustMaxSpeed() {
        return thrustMaxSpeed;
    }

    /**
     * Sets the thrust max speed.
     *
     * @param thrustMaxSpeed thrust max speed
     */
    public void setThrustMaxSpeed(Amount<Velocity> thrustMaxSpeed) {
        this.thrustMaxSpeed = thrustMaxSpeed;
    }

    /**
     * Gets the max speed.
     *
     * @return max speed
     */
    public Amount<Velocity> getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Sets the max speed.
     *
     * @param maxSpeed max speed
     */
    public void setMaxSpeed(Amount<Velocity> maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.thrust0);
        hash = 59 * hash + Objects.hashCode(this.thrustMaxSpeed);
        hash = 59 * hash + Objects.hashCode(this.maxSpeed);
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

        final ThrustFunction other = (ThrustFunction) obj;
        final double EPSILON = 0.01;

        return Math.abs(this.thrust0.doubleValue(SI.NEWTON) - other.thrust0.doubleValue(SI.NEWTON)) < EPSILON
                && Math.abs(this.thrustMaxSpeed.doubleValue(SI.METERS_PER_SECOND) - other.thrustMaxSpeed.doubleValue(SI.METERS_PER_SECOND)) < EPSILON
                && Math.abs(this.maxSpeed.doubleValue(NonSI.MACH) - other.maxSpeed.doubleValue(NonSI.MACH)) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("ThrustFunction{thrust0=%s, thrustMaxSpeed=%s, maxSpeed=%s}",
                thrust0, thrustMaxSpeed, maxSpeed);
    }

}
