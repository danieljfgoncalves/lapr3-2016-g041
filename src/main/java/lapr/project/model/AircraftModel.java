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
     * AircraftType's modelID
     */
    private Integer modelID;

    /**
     * AircraftType's type
     */
    private AircraftType type;

    /**
     * AircraftType's empty weight (EWeight)
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
     * AircraftType's wing area
     */
    private Double wingArea;

    /**
     * AircraftType's motorization
     */
    private Motorization motorization;

    /**
     * AircraftType's description;
     */
    private String description;

    /**
     * AircraftType's maker
     */
    private String maker;

    /**
     * AircraftType's maxPayload
     */
    private Double maxPayload;

    /**
     * AircraftType's maxFuelCapacity
     */
    private Double maxFuelCapacity;

    /**
     * AircraftType's maxOperatingSpeed (VMO)
     */
    private Double vmo;

    /**
     * AircraftType's maxMachOperatingSpeed (MMO)
     */
    private Double mmo;

    /**
     * AircraftType's wingSpan
     */
    private Double wingSpan;

    /**
     * AircraftType's e
     */
    private Double e;

    /**
     * The epsilon of the allowed error.
     */
    private static final Double EPSILON = 0.01d;

    /**
     * The AircraftType's modelID by default
     */
    private static final Integer MODEL_ID_BY_DEFAULT = 0;

    /**
     * The AircraftType's type by default
     */
    private static final AircraftType TYPE_BY_DEFAULT = AircraftType.PASSENGER;

    /**
     * The AircraftType's empty weight by default
     */
    private static final Double EMPTY_WEIGHT_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's mtow by default
     */
    private static final Double MTOW_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's mzfw by default
     */
    private static final Double MZFW_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's wing area by default
     */
    private static final Double WING_AREA_BY_DEFAULT = 0.0d;

    /**
     * The AircraftType's description by default
     */
    private static final String DESCRIPTION_BY_DEFAULT = "DefaultDescription";

    /**
     * The AircraftType's maker by default
     */
    private static final String MAKER_BY_DEFAULT = "DefaultMaker";

    /**
     * The AircraftType's max payload by default
     */
    private static final Double MAX_PAYLOAD_BY_DEFAULT = 0.0;

    /**
     * The AircraftType's max fuel capacity by default
     */
    private static final Double MAX_FUEL_CAPACITY_BY_DEFAULT = 0.0;

    /**
     * The AircraftType's VMO (maxOperatingSpeed) by default
     */
    private static final Double VMO_BY_DEFAULT = 0.0;

    /**
     * The AircraftType's MMO (maxMachOperatingSpeed) by default
     */
    private static final Double MMO_BY_DEFAULT = 0.0;

    /**
     * The AircraftType's wing span by default
     */
    private static final Double WING_SPAN_BY_DEFAULT = 0.0;

    /**
     * The AircraftType's e by default
     */
    private static final Double E_BY_DEFAULT = 0.0;

    /**
     * Constructs an instance of AircraftModel receiving it's parameters.
     *
     * @param modelID the AircraftType's modelID
     * @param type the AircraftType's type
     * @param emptyWheight the AircraftType's empty weight
     * @param mtow the AircraftType's maximum take off weight
     * @param mzfw the AircraftType's maximum zero flight weight
     * @param wingArea the AircraftType's wing area
     * @param motorization the AircraftType's motorization
     * @param description the AircraftType's description
     * @param maker the AircraftType's maker
     * @param maxPayload the AircraftType's maker
     * @param maxFuelCapacity the AircraftType's max fuel capacity
     * @param vmo the AircraftType's vmo (max operating system velocity)
     * @param mmo the AircraftType's mmo (max mach operating system velocity)
     * @param wingSpan the AircraftType's wing span
     * @param e the AircraftType's e
     */
    public AircraftModel(Integer modelID, AircraftType type, Double emptyWheight, Double mtow, Double mzfw, Double wingArea, Motorization motorization, String description, String maker, Double maxPayload, Double maxFuelCapacity, Double vmo, Double mmo, Double wingSpan, Double e) {
        this.modelID = modelID;
        this.type = type;
        this.emptyWeight = emptyWheight;
        this.mtow = mtow;
        this.mzfw = mzfw;
        this.wingArea = wingArea;
        this.motorization = motorization;
        this.description = description;
        this.maker = maker;
        this.maxPayload = maxPayload;
        this.maxFuelCapacity = maxFuelCapacity;
        this.vmo = vmo;
        this.mmo = mmo;
        this.wingSpan = wingSpan;
        this.e = e;
    }

    /**
     * Constructs an instance of AircraftModel using it's parameters by default.
     */
    public AircraftModel() {
        this.modelID = MODEL_ID_BY_DEFAULT;
        this.type = TYPE_BY_DEFAULT;
        this.emptyWeight = EMPTY_WEIGHT_BY_DEFAULT;
        this.mtow = MTOW_BY_DEFAULT;
        this.mzfw = MZFW_BY_DEFAULT;
        this.wingArea = WING_AREA_BY_DEFAULT;
        this.description = DESCRIPTION_BY_DEFAULT;
        this.motorization = new Motorization();
        this.description = DESCRIPTION_BY_DEFAULT;
        this.maker = MAKER_BY_DEFAULT;
        this.maxPayload = MAX_PAYLOAD_BY_DEFAULT;
        this.maxFuelCapacity = MAX_FUEL_CAPACITY_BY_DEFAULT;
        this.vmo = VMO_BY_DEFAULT;
        this.mmo = MMO_BY_DEFAULT;
        this.wingSpan = WING_SPAN_BY_DEFAULT;
        this.e = E_BY_DEFAULT;
    }

    /**
     * Constructs a copy of an instance of AircraftModel
     *
     * @param otherAircraftModel the instance of aircraftModel to copy
     */
    public AircraftModel(AircraftModel otherAircraftModel) {
        this.modelID = otherAircraftModel.modelID;
        this.type = otherAircraftModel.type;
        this.emptyWeight = otherAircraftModel.emptyWeight;
        this.mtow = otherAircraftModel.mtow;
        this.mzfw = otherAircraftModel.mzfw;
        this.wingArea = otherAircraftModel.wingArea;
        this.motorization = otherAircraftModel.motorization;
        this.description = otherAircraftModel.description;
        this.maker = otherAircraftModel.maker;
        this.maxPayload = otherAircraftModel.maxPayload;
        this.maxFuelCapacity = otherAircraftModel.maxFuelCapacity;
        this.vmo = otherAircraftModel.vmo;
        this.mmo = otherAircraftModel.mmo;
        this.wingSpan = otherAircraftModel.wingSpan;
        this.e = otherAircraftModel.e;
    }

    /**
     * Obtains the AircraftModel's modelID
     *
     * @return the modelID
     */
    public Integer getModelID() {
        return this.modelID;
    }

    /**
     * Modifies the AircraftModel's modelID
     *
     * @param modelID the modelID to set
     */
    public void setModelID(Integer modelID) {
        this.modelID = modelID;
    }

    /**
     * Obtains the AircraftModel's type
     *
     * @return the type
     */
    public AircraftType getType() {
        return this.type;
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
        return this.emptyWeight;
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
        return this.mtow;
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
        return this.mzfw;
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
     * Obtains the AircraftModel's wing area
     *
     * @return the wingArea
     */
    public Double getWingArea() {
        return this.wingArea;
    }

    /**
     * Modifies the AircraftModel's wing area
     *
     * @param wingArea the wingArea to set
     */
    public void setWingArea(Double wingArea) {
        this.wingArea = wingArea;
    }

    /**
     * Obtains the AircraftModel's motorization
     *
     * @return the motorization
     */
    public Motorization getMotorization() {
        return this.motorization;
    }

    /**
     * Modifies the AircraftModel's motorization
     *
     * @param motorization the motorization to set
     */
    public void setMotorization(Motorization motorization) {
        this.motorization = motorization;
    }

    /**
     * Obtains the AircraftModel's description
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Modifies the AircraftModel's description
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtains the AircraftModel's maker
     *
     * @return the maker
     */
    public String getMaker() {
        return this.maker;
    }

    /**
     * Modifies the AircraftModel's maker
     *
     * @param maker the maker to set
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * Obtains the AircraftModel's max payload
     *
     * @return the max payload
     */
    public Double getMaxPayload() {
        return this.maxPayload;
    }

    /**
     * Modifies the AircraftModel's max payload
     *
     * @param maxPayload the max payload to set
     */
    public void setMaxPayload(Double maxPayload) {
        this.maxPayload = maxPayload;
    }

    /**
     * Obtains the AircraftModel's max fuel capacity
     *
     * @return the max fuel capacity
     */
    public Double getMaxFuelCapacity() {
        return this.maxFuelCapacity;
    }

    /**
     * Modifies the c max fuel capacity
     *
     * @param maxFuelCapacity the max fuel capacity to set
     */
    public void setMaxFuelCapacity(Double maxFuelCapacity) {
        this.maxFuelCapacity = maxFuelCapacity;
    }

    /**
     * Obtains the AircraftModel's vmo (max operating speed)
     *
     * @return the vmo (max operating speed)
     */
    public Double getVmo() {
        return this.vmo;
    }

    /**
     * Modifies the AircraftModel's vmo (max operating speed)
     *
     * @param vmo the vmo (max operating speed) to set
     */
    public void setVmo(Double vmo) {
        this.vmo = vmo;
    }

    /**
     * Obtains the AircraftModel's mmo (max mach operating speed)
     *
     * @return the mmo (max mach operating speed)
     */
    public Double getMmo() {
        return this.mmo;
    }

    /**
     * Modifies the AircraftModel's mmo (max mach operating system)
     *
     * @param mmo the mmo (max mach operating system) to set
     */
    public void setMmo(Double mmo) {
        this.mmo = mmo;
    }

    /**
     * Obtains the AircraftModel's wing span
     *
     * @return the wing span
     */
    public Double getWingSpan() {
        return wingSpan;
    }

    /**
     * Modifies the AircraftModel's wing span
     *
     * @param wingSpan the wing span to set
     */
    public void setWingSpan(Double wingSpan) {
        this.wingSpan = wingSpan;
    }

    /**
     * Obtains the AircraftModel's e
     *
     * @return the e
     */
    public Double getE() {
        return e;
    }

    /**
     * Modifies the AircraftModel's e
     *
     * @param e e to set
     */
    public void setE(Double e) {
        this.e = e;
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
        hash = 47 * hash + Objects.hashCode(this.modelID);
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
        // we just compared modelID because it is an unique id
        return this.modelID.equals(other.modelID);
    }

    @Override
    public String toString() {
        return String.format("Aircraft Model{modelID=%s, type=%s, empty weight=%.2f, "
                + "maximum take off weight=%.2f, maximum zero fuel weight=%.2f, "
                + "wing area=%.2f, Motorization=%s, description=%s, "
                + "maker=%s, max payload=%.2f, max fuel capacity=%.2f, max operating speed=%.2f, max mach operating speed=%.2f, "
                + "wing span=%.2f, e=%.2f }",
                this.modelID, this.type, this.emptyWeight, this.mtow,
                this.mzfw, this.wingArea, this.motorization, this.description,
                this.maker, this.maxPayload, this.maxFuelCapacity, this.vmo, this.mmo, this.wingSpan, this.e);
    }

}
