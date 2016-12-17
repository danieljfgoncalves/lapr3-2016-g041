/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a motorization.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
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
     * The available regimes.
     */
    private List<Regime> regimes;

    /**
     * The default number of motors.
     */
    private static final Integer DEFAULT_NUMBER_OF_MOTORS = 4;

    /**
     * The default motor.
     */
    private static final String DEFAULT_MOTOR = "GE CF6-80C2B1F";

    /**
     * The default motor type.
     */
    private static final MotorType DEFAULT_MOTOR_TYPE = MotorType.TURBOFAN;

    /**
     * Creates an instance of motorization with it's default values.
     */
    public Motorization() {
        numberOfMotors = DEFAULT_NUMBER_OF_MOTORS;
        motor = DEFAULT_MOTOR;
        motorType = DEFAULT_MOTOR_TYPE;
        regimes = new ArrayList<>();
    }

    /**
     * Creates an instance of motorization receiving their parameter.
     *
     * @param numberOfMotors the number of motors
     * @param motor the motor
     * @param motorType the motor type
     * @param regimes available regimes
     */
    public Motorization(Integer numberOfMotors, String motor, MotorType motorType, List<Regime> regimes) {
        this.numberOfMotors = numberOfMotors;
        this.motor = motor;
        this.motorType = motorType;
        this.regimes = regimes;
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
     * Gets the regimes.
     *
     * @return regimes
     */
    public List<Regime> getRegimes() {
        return regimes;
    }

    /**
     * Sets the regimes.
     *
     * @param regimes regimes
     */
    public void setRegimes(List<Regime> regimes) {
        this.regimes = regimes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.numberOfMotors);
        hash = 67 * hash + Objects.hashCode(this.motor);
        hash = 67 * hash + Objects.hashCode(this.motorType);
        hash = 67 * hash + Objects.hashCode(this.regimes);
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
                && this.regimes.equals(other.regimes);
    }

    @Override
    public String toString() {
        return String.format("Motorization{numberOfMotors=%d, motor=%s, motorType=%s, regimes=%s}",
                this.numberOfMotors, this.motor, this.motorType, this.regimes);
    }

}
