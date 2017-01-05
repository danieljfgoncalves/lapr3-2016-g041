/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.Unit;
import lapr.project.utils.CustomUnits;
import org.jscience.physics.amount.Amount;

/**
 * Represents a motorization.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Motorization {

    /**
     * The number of motors.
     */
    private Integer numberOfMotors;

    /**
     * The motor.
     */
    private String motor;

    /**
     * The motor type.
     */
    private MotorType motorType;

    /**
     * The cruise altitude (SI = m/s).
     */
    private Amount<Length> cruiseAltitude;

    /**
     * The cruise speed (SI = m/s).
     */
    private Amount<Velocity> cruiseSpeed;

    /**
     * The thrust specific fuel consumption.
     */
    private Amount tsfc;

    /**
     * The lapse rate factor
     */
    private Amount<Dimensionless> lapseRateFactor;

    /**
     * The thrust function.
     */
    private ThrustFunction thrustFunction;

    /**
     * The default number of motors.
     */
    private static final Integer DEFAULT_NUMBER_OF_MOTORS = 2;

    /**
     * The default motor.
     */
    private static final String DEFAULT_MOTOR = "GE 90B";

    /**
     * The default motor type.
     */
    private static final MotorType DEFAULT_MOTOR_TYPE = MotorType.TURBOFAN;

    /**
     * The default cruise altitude.
     */
    private static final Amount<Length> DEFAULT_CRUISE_ALTITUDE = Amount.valueOf(43.1E+03, NonSI.FOOT);

    /**
     * The default cruise speed.
     */
    private static final Amount<Velocity> DEFAULT_CRUISE_SPEED = Amount.valueOf(0.84, NonSI.MACH);

    /**
     * The default thrust specific fuel consumption.
     */
    private static final Amount DEFAULT_TSFC = Amount.valueOf(1.6E-4, CustomUnits.TSFC_SI);

    /**
     * The default lapse rate factor.
     */
    private static final Amount<Dimensionless> DEFAULT_LAPSE_RATE_FACTOR = Amount.valueOf(1.0, Unit.ONE);

    /**
     * Creates an instance of motorization with it's default values.
     */
    public Motorization() {
        numberOfMotors = DEFAULT_NUMBER_OF_MOTORS;
        motor = DEFAULT_MOTOR;
        motorType = DEFAULT_MOTOR_TYPE;
        cruiseAltitude = DEFAULT_CRUISE_ALTITUDE;
        cruiseSpeed = DEFAULT_CRUISE_SPEED;
        tsfc = DEFAULT_TSFC;
        lapseRateFactor = DEFAULT_LAPSE_RATE_FACTOR;
        thrustFunction = new ThrustFunction();
    }

    /**
     * Creates an instance of motorization receiving their parameter.
     *
     * @param numberOfMotors the number of motors
     * @param motor the motor
     * @param motorType the motor type
     * @param cruiseAltitude the cruise altitude
     * @param cruiseSpeed the cruise speed
     * @param tsfc the thrust specific fuel consumption
     * @param lapseRateFactor the lapse rate factor
     * @param thrustFunction
     */
    public Motorization(Integer numberOfMotors, String motor, MotorType motorType,
            Amount<Length> cruiseAltitude, Amount<Velocity> cruiseSpeed,
            Amount tsfc, Amount<Dimensionless> lapseRateFactor,
            ThrustFunction thrustFunction) {
        this.numberOfMotors = numberOfMotors;
        this.motor = motor;
        this.motorType = motorType;
        this.cruiseAltitude = cruiseAltitude;
        this.cruiseSpeed = cruiseSpeed;
        this.tsfc = tsfc;
        this.lapseRateFactor = lapseRateFactor;
        this.thrustFunction = thrustFunction;
    }

    /**
     * Creates an instance of motorization copying another motorization.
     *
     * @param anotherMotorization another motorization
     */
    public Motorization(Motorization anotherMotorization) {
        this.numberOfMotors = anotherMotorization.numberOfMotors;
        this.motor = anotherMotorization.motor;
        this.motorType = anotherMotorization.motorType;
        this.cruiseAltitude = anotherMotorization.cruiseAltitude;
        this.cruiseSpeed = anotherMotorization.cruiseSpeed;
        this.tsfc = anotherMotorization.tsfc;
        this.lapseRateFactor = anotherMotorization.lapseRateFactor;
        this.thrustFunction = new ThrustFunction(anotherMotorization.thrustFunction);
    }

    /**
     * gets the number of motors.
     *
     * @return number of motors
     */
    public Integer getNumberOfMotors() {
        return numberOfMotors;
    }

    /**
     * Sets the number of motors
     *
     * @param numberOfMotors number of motors
     */
    public void setNumberOfMotors(Integer numberOfMotors) {
        this.numberOfMotors = numberOfMotors;
    }

    /**
     * Gets the motor.
     *
     * @return motor
     */
    public String getMotor() {
        return motor;
    }

    /**
     * Sets the motor.
     *
     * @param motor motor
     */
    public void setMotor(String motor) {
        this.motor = motor;
    }

    /**
     * Gets the motor type.
     *
     * @return motor type
     */
    public MotorType getMotorType() {
        return motorType;
    }

    /**
     * Sets the motor type.
     *
     * @param motorType motor type
     */
    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    /**
     * Gets the cruise altitude.
     *
     * @return cruise altitude
     */
    public Amount<Length> getCruiseAltitude() {
        return cruiseAltitude;
    }

    /**
     * Sets the cruise altitude.
     *
     * @param cruiseAltitude cruise altitude
     */
    public void setCruiseAltitude(Amount<Length> cruiseAltitude) {
        this.cruiseAltitude = cruiseAltitude;
    }

    /**
     * Gets the cruise speed.
     *
     * @return cruise speed
     */
    public Amount<Velocity> getCruiseSpeed() {
        return cruiseSpeed;
    }

    /**
     * Sets the cruise speed.
     *
     * @param cruiseSpeed cruise speed
     */
    public void setCruiseSpeed(Amount<Velocity> cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    /**
     * Gets the thrust specific fuel consumption.
     *
     * @return thrust specific fuel consumption
     */
    public Amount getTsfc() {
        return tsfc;
    }

    /**
     * Sets the thrust specific fuel consumption.
     *
     * @param tsfc thrust specific fuel consumption
     */
    public void setTsfc(Amount tsfc) {
        this.tsfc = tsfc;
    }

    /**
     * Gets the lapse rate factor.
     *
     * @return lapse rate factor
     */
    public Amount<Dimensionless> getLapseRateFactor() {
        return lapseRateFactor;
    }

    /**
     * Sets the lapse rate factor.
     *
     * @param lapseRateFactor lapse rate factor
     */
    public void setLapseRateFactor(Amount<Dimensionless> lapseRateFactor) {
        this.lapseRateFactor = lapseRateFactor;
    }

    /**
     * Gets the thrust function.
     *
     * @return thrust function
     */
    public ThrustFunction getThrustFunction() {
        return thrustFunction;
    }

    /**
     * Sets the thrust function.
     *
     * @param thrustFunction thrust function
     */
    public void setThrustFunction(ThrustFunction thrustFunction) {
        this.thrustFunction = thrustFunction;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.numberOfMotors);
        hash = 67 * hash + Objects.hashCode(this.motor);
        hash = 67 * hash + Objects.hashCode(this.motorType);
        hash = 67 * hash + Objects.hashCode(this.cruiseAltitude);
        hash = 67 * hash + Objects.hashCode(this.cruiseSpeed);
        hash = 67 * hash + Objects.hashCode(this.tsfc);
        hash = 67 * hash + Objects.hashCode(this.lapseRateFactor);
        hash = 67 * hash + Objects.hashCode(this.thrustFunction);
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

        final Motorization other = (Motorization) obj;
        return this.numberOfMotors.equals(other.numberOfMotors)
                && this.motor.equalsIgnoreCase(other.motor)
                && this.motorType.equals(other.motorType)
                && this.cruiseAltitude.equals(other.cruiseAltitude)
                && this.cruiseSpeed.equals(other.cruiseSpeed)
                && this.tsfc.equals(other.tsfc)
                && this.lapseRateFactor.equals(other.lapseRateFactor)
                && this.thrustFunction.equals(other.thrustFunction);
    }

    @Override
    public String toString() {
        return String.format("Motorization{numberOfMotors=%d, motor=%s, motorType=%s, "
                + "cruiseAltitude=%s, cruiseSpeed=%s, tsfc=%s, lapseRateFactor=%s, "
                + "thrustFunction=%s}",
                this.numberOfMotors, this.motor, this.motorType, this.cruiseAltitude,
                this.cruiseSpeed, this.tsfc, this.lapseRateFactor, this.thrustFunction);
    }

}
