/*
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Objects;

/**
 * Represents an aircraft model (motorized commercial aircraft, there are many
 * types of motorizations: turboprop, 9 turbofan, turbojet, electric propeller,
 * etc.)
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AircraftModel {

    /**
     * AircraftType's registration
     */
    private Integer registration;

    /**
     * AircraftType's type
     */
    private AircraftType type;

    /**
     * AircraftType's empty weight
     */
    private Double emptyWeight;

    /**
     * AircraftType's Maximum take-off weight
     */
    private Double mtow;

    /**
     * AircraftType's Maximum Zero Fuel Weight
     */
    private Double mzfw;

    /**
     * AircraftType's cruise speed
     */
    private Double cruiseSpeed;

    /**
     * AircraftType's wing area
     */
    private Double wingArea;

    /**
     * The epsilon of the allowed error.
     */
    private final static Double EPSILON = 0.01d;

    /**
     * The AircraftType's registration by default
     */
    private final static Integer REGISTRATION_BY_DEFAULT = 0;

    /**
     * The AircraftType's type by default
     */
    private final static AircraftType TYPE_BY_DEFAULT = AircraftType.PASSENGER;

    /**
     * The AircraftType's empty weight by default
     */
    private final static Double EMPTY_WEIGHT_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's mtow by default
     */
    private final static Double MTOW_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's mzfw by default
     */
    private final static Double MZFW_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's cruise speed by default
     */
    private final static Double CRUISE_SPEED_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's wing area by default
     */
    private final static Double WING_AREA_BY_DEFAULT = 0.0d;

    /**
     * Constructs an instance of AircraftModel receiving it's parameters.
     *
     * @param registration the AircraftType's registration
     * @param type the AircraftType's type
     * @param emptyWheight the AircraftType's empty weight
     * @param mtow the AircraftType's maximum take off weight
     * @param mzfw the AircraftType's maximum zero flight weight
     * @param cruiseSpeed the AircraftType's cruise speed
     * @param wingArea the AircraftType's wing area
     */
    public AircraftModel(Integer registration, AircraftType type, Double emptyWheight, Double mtow, Double mzfw, Double cruiseSpeed, Double wingArea) {
        this.registration = registration;
        this.type = type;
        this.emptyWeight = emptyWheight;
        this.mtow = mtow;
        this.mzfw = mzfw;
        this.cruiseSpeed = cruiseSpeed;
        this.wingArea = wingArea;
    }

    /**
     * Constructs an instance of AircraftModel using it's parameters by default.
     */
    public AircraftModel() {
        this.registration = REGISTRATION_BY_DEFAULT;
        this.type = TYPE_BY_DEFAULT;
        this.emptyWeight = EMPTY_WEIGHT_BY_DEFAULT;
        this.mtow = MTOW_BY_DEFAULT;
        this.mzfw = MTOW_BY_DEFAULT;
        this.cruiseSpeed = CRUISE_SPEED_BY_DEFAULT;
        this.wingArea = WING_AREA_BY_DEFAULT;
    }

    /**
     * Constructs a copy of an instance of AircraftModel
     *
     * @param otherAircraftModel the instance of aircraftModel to copy
     */
    public AircraftModel(AircraftModel otherAircraftModel) {
        this.registration = otherAircraftModel.registration;
        this.type = otherAircraftModel.type;
        this.emptyWeight = otherAircraftModel.emptyWeight;
        this.mtow = otherAircraftModel.mtow;
        this.mzfw = otherAircraftModel.mzfw;
        this.cruiseSpeed = otherAircraftModel.cruiseSpeed;
        this.wingArea = otherAircraftModel.wingArea;
    }

    /**
     * Obtains the AircraftModel's registration
     *
     * @return the registration
     */
    public Integer getRegistration() {
        return registration;
    }

    /**
     * Modifies the AircraftModel's registration
     *
     * @param registration the registration to set
     */
    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    /**
     * Obtains the AircraftModel's type
     *
     * @return the type
     */
    public AircraftType getType() {
        return type;
    }

    /**
     * Modifies the AircraftModel's type
     *
     * @param type the type to set
     */
    public void setType(AircraftType type) {
        this.type = type;
    }

    /**
     * Obtains the AircraftModel's empty weight
     *
     * @return the emptyWeight
     */
    public Double getEmptyWeight() {
        return emptyWeight;
    }

    /**
     * Modifies the AircraftModel's empty weight
     *
     * @param emptyWheight the emptyWheight to set
     */
    public void setEmptyWeight(Double emptyWheight) {
        this.emptyWeight = emptyWheight;
    }

    /**
     * Obtains the AircraftModel's maximum take off weight
     *
     * @return the mtow
     */
    public Double getMtow() {
        return mtow;
    }

    /**
     * Modifies the AircraftModel's maximum take off weight
     *
     * @param mtow the mtow to set
     */
    public void setMtow(Double mtow) {
        this.mtow = mtow;
    }

    /**
     * Obtains the AircraftModel's maximum zero fuel weight
     *
     * @return the mzfw
     */
    public Double getMzfw() {
        return mzfw;
    }

    /**
     * Modifies the AircraftModel's maximum zero fuel weight
     *
     * @param mzfw the mzfw to set
     */
    public void setMzfw(Double mzfw) {
        this.mzfw = mzfw;
    }

    /**
     * Obtains the AircraftModel's cruise speed
     *
     * @return the cruiseSpeed
     */
    public Double getCruiseSpeed() {
        return cruiseSpeed;
    }

    /**
     * Modifies the AircraftModel's cruise speed
     *
     * @param cruiseSpeed the cruiseSpeed to set
     */
    public void setCruiseSpeed(Double cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    /**
     * Obtains the AircraftModel's wing area
     *
     * @return the wingArea
     */
    public Double getWingArea() {
        return wingArea;
    }

    /**
     * Modifies the AircraftModel's wing area
     *
     * @param wingArea the wingArea to set
     */
    public void setWingArea(Double wingArea) {
        this.wingArea = wingArea;
    }

    public Double calculateCd(Double drag, Double dynamicPressure, Double referenceArea) {
        // TODO: Implement calculateCd
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Double calculateCl(Double lift, Double dynamicPressure, Double referenceArea) {
        // TODO: Implement calculateCl
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.registration);
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
        final AircraftModel other = (AircraftModel) obj;
        // we just compared registration because it is an unique id
        return this.registration.equals(other.registration);
    }

    @Override
    public String toString() {
        return String.format("Aircraft Model{registration=%s, type=%s, empty weight=%4f, "
                + "maximum take off weight=%4f, maximum zero fuel weight=%4f, cruise speed=&4f, wing area=%4f}",
                this.registration, this.type, this.emptyWeight, this.mtow, this.mzfw, this.cruiseSpeed, this.wingArea);
    }

}
