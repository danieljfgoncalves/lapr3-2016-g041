/*
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Arrays;
import java.util.Objects;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Velocity;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import org.jscience.physics.amount.Amount;

/**
 * Represents an aircraft model (motorized commercial aircraft, there are many
 * types of motorizations: turboprop, 9 turbofan, turbojet, electric propeller,
 * etc.)
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AircraftModel {

    /**
     * AircraftType's modelID.
     */
    private String modelID;

    /**
     * AircraftType's description.
     */
    private String description;

    /**
     * AircraftType's maker.
     */
    private String maker;

    /**
     * AircraftType's type.
     */
    private AircraftType type;

    /**
     * AircraftType's motorization.
     */
    private Motorization motorization;

    /**
     * AircraftType's empty weight (EWeight) (SI: Kg).
     */
    private Amount<Mass> emptyWeight;

    /**
     * AircraftType's Maximum take-off weight (SI: Kg).
     */
    private Amount<Mass> mtow;

    /**
     * AircraftType's maxPayload (SI: kg).
     */
    private Amount<Mass> maxPayload;

    /**
     * AircraftType's maxFuelCapacity (SI: m3).
     */
    private Amount<Volume> maxFuelCapacity;

    /**
     * AircraftType's maxOperatingSpeed (VMO) (SI: m/s).
     */
    private Amount<Velocity> vmo;

    /**
     * AircraftType's maxMachOperatingSpeed (MMO) (Mach).
     */
    private Amount<Velocity> mmo;

    /**
     * AircraftType's wing area (SI: m2).
     */
    private Amount<Area> wingArea;

    /**
     * AircraftType's wingSpan (SI: m).
     */
    private Amount<Length> wingSpan;

    /**
     * The aspect ratio.
     */
    private Amount<Dimensionless> aspectRatio;

    /**
     * AircraftType's e (Efficiency Factor)
     */
    private Amount<Dimensionless> e;

    /**
     * The cdrag function.
     */
    private double[][] cdragFunction;

    /**
     * The AircraftType's modelID by default.
     */
    private static final String MODEL_ID_BY_DEFAULT = "01Boing474";

    /**
     * The AircraftType's description by default.
     */
    private static final String DESCRIPTION_BY_DEFAULT = "Default description";

    /**
     * The AircraftType's type by default.
     */
    private static final AircraftType TYPE_BY_DEFAULT = AircraftType.PASSENGER;

    /**
     * The AircraftType's empty weight by default.
     */
    private static final Amount<Mass> EMPTY_WEIGHT_BY_DEFAULT = Amount.valueOf(0d, SI.KILOGRAM);

    /**
     * The AircraftType's mtow by default.
     */
    private static final Amount<Mass> MTOW_BY_DEFAULT = Amount.valueOf(0d, SI.KILOGRAM);

    /**
     * The AircraftType's wing area by default.
     */
    private static final Amount<Area> WING_AREA_BY_DEFAULT = Amount.valueOf(0d, SI.SQUARE_METRE);

    /**
     * The AircraftType's maker by default.
     */
    private static final String MAKER_BY_DEFAULT = "DefaultMaker";

    /**
     * The AircraftType's max payload by default.
     */
    private static final Amount<Mass> MAX_PAYLOAD_BY_DEFAULT = Amount.valueOf(0d, SI.KILOGRAM);

    /**
     * The AircraftType's max fuel capacity by default.
     */
    private static final Amount<Volume> MAX_FUEL_CAPACITY_BY_DEFAULT = Amount.valueOf(0d, SI.CUBIC_METRE);

    /**
     * The AircraftType's VMO (maxOperatingSpeed) by default.
     */
    private static final Amount<Velocity> VMO_BY_DEFAULT = Amount.valueOf(0d, SI.METERS_PER_SECOND);

    /**
     * The AircraftType's MMO (maxMachOperatingSpeed) by default.
     */
    private static final Amount<Velocity> MMO_BY_DEFAULT = Amount.valueOf(0d, NonSI.MACH);

    /**
     * The AircraftType's wing span by default.
     */
    private static final Amount<Length> WING_SPAN_BY_DEFAULT = Amount.valueOf(0d, SI.METER);

    /**
     * The default aspect ratio.
     */
    private static final Amount<Dimensionless> DEFAULT_ASPECT_RATIO = Amount.valueOf(7.53, Unit.ONE);

    /**
     * The AircraftType's e by default.
     */
    private static final Amount<Dimensionless> E_BY_DEFAULT = Amount.valueOf(0d, Unit.ONE);

    /**
     * The default cdrag function.
     */
    private static final double[][] DEFAULT_CDRAG_FUNCTION = {{0, 0.020}, {0.75, 0.020}, {0.80, 0.0204}};

    /**
     * Constructs an instance of AircraftModel using it's parameters by default.
     */
    public AircraftModel() {
        this.modelID = MODEL_ID_BY_DEFAULT;
        this.description = DESCRIPTION_BY_DEFAULT;
        this.maker = MAKER_BY_DEFAULT;
        this.type = TYPE_BY_DEFAULT;
        this.motorization = new Motorization();
        this.emptyWeight = EMPTY_WEIGHT_BY_DEFAULT;
        this.mtow = MTOW_BY_DEFAULT;
        this.maxPayload = MAX_PAYLOAD_BY_DEFAULT;
        this.maxFuelCapacity = MAX_FUEL_CAPACITY_BY_DEFAULT;
        this.vmo = VMO_BY_DEFAULT;
        this.mmo = MMO_BY_DEFAULT;
        this.wingArea = WING_AREA_BY_DEFAULT;
        this.wingSpan = WING_SPAN_BY_DEFAULT;
        this.aspectRatio = DEFAULT_ASPECT_RATIO;
        this.e = E_BY_DEFAULT;
        this.cdragFunction = DEFAULT_CDRAG_FUNCTION;
    }

    /**
     * Constructs an aircraft model receiving their parameters.
     *
     * @param modelID the model ID
     * @param description the description
     * @param maker the maker
     * @param type the type
     * @param motorization the motorization
     * @param emptyWeight the empty weight
     * @param mtow the maximum take off weight
     * @param maxPayload the maximum payload
     * @param maxFuelCapacity the maximum fuel capacity
     * @param vmo the maximum operating speed
     * @param mmo the maximum mach operating speed
     * @param wingArea the wing area
     * @param wingSpan the wing span
     * @param aspectRatio the aspect ratio
     * @param e the e
     * @param cdragFunction the cfrag function
     */
    public AircraftModel(String modelID, String description, String maker,
            AircraftType type, Motorization motorization, Amount<Mass> emptyWeight,
            Amount<Mass> mtow, Amount<Mass> maxPayload, Amount<Volume> maxFuelCapacity,
            Amount<Velocity> vmo, Amount<Velocity> mmo, Amount<Area> wingArea,
            Amount<Length> wingSpan, Amount<Dimensionless> aspectRatio,
            Amount<Dimensionless> e, double[][] cdragFunction) {
        this.modelID = modelID;
        this.description = description;
        this.maker = maker;
        this.type = type;
        this.motorization = motorization;
        this.emptyWeight = emptyWeight;
        this.mtow = mtow;
        this.maxPayload = maxPayload;
        this.maxFuelCapacity = maxFuelCapacity;
        this.vmo = vmo;
        this.mmo = mmo;
        this.wingArea = wingArea;
        this.wingSpan = wingSpan;
        this.aspectRatio = aspectRatio;
        this.e = e;
        this.cdragFunction = cdragFunction;
    }

    /**
     * Constructs a copy of an instance of AircraftModel
     *
     * @param otherAircraftModel the instance of aircraftModel to copy
     */
    public AircraftModel(AircraftModel otherAircraftModel) {
        this.modelID = otherAircraftModel.modelID;
        this.description = otherAircraftModel.description;
        this.maker = otherAircraftModel.maker;
        this.type = otherAircraftModel.type;
        this.motorization = new Motorization(otherAircraftModel.motorization);
        this.emptyWeight = otherAircraftModel.emptyWeight;
        this.mtow = otherAircraftModel.mtow;
        this.maxPayload = otherAircraftModel.maxPayload;
        this.maxFuelCapacity = otherAircraftModel.maxFuelCapacity;
        this.vmo = otherAircraftModel.vmo;
        this.mmo = otherAircraftModel.mmo;
        this.wingArea = otherAircraftModel.wingArea;
        this.wingSpan = otherAircraftModel.wingSpan;
        this.aspectRatio = otherAircraftModel.aspectRatio;
        this.e = otherAircraftModel.e;

        this.cdragFunction = new double[cdragFunction.length][cdragFunction[0].length];
        for (int i = 0; i < otherAircraftModel.cdragFunction.length; i++) {
            this.cdragFunction[i] = otherAircraftModel.cdragFunction[i].clone();
        }
    }

    /**
     * Obtains the AircraftModel's modelID
     *
     * @return the modelID
     */
    public String getModelID() {
        return this.modelID;
    }

    /**
     * Modifies the AircraftModel's modelID
     *
     * @param modelID the modelID to set
     */
    public void setModelID(String modelID) {
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
    public Amount<Mass> getEmptyWeight() {
        return this.emptyWeight;
    }

    /**
     * Modifies the AircraftModel's empty weight
     *
     * @param emptyWheight the emptyWheight to set
     */
    public void setEmptyWeight(Amount<Mass> emptyWheight) {
        this.emptyWeight = emptyWheight;
    }

    /**
     * Obtains the AircraftModel's maximum take off weight
     *
     * @return the mtow
     */
    public Amount<Mass> getMtow() {
        return this.mtow;
    }

    /**
     * Modifies the AircraftModel's maximum take off weight
     *
     * @param mtow the mtow to set
     */
    public void setMtow(Amount<Mass> mtow) {
        this.mtow = mtow;
    }

    /**
     * Obtains the AircraftModel's wing area
     *
     * @return the wingArea
     */
    public Amount<Area> getWingArea() {
        return this.wingArea;
    }

    /**
     * Modifies the AircraftModel's wing area
     *
     * @param wingArea the wingArea to set
     */
    public void setWingArea(Amount<Area> wingArea) {
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
    public Amount<Mass> getMaxPayload() {
        return this.maxPayload;
    }

    /**
     * Modifies the AircraftModel's max payload
     *
     * @param maxPayload the max payload to set
     */
    public void setMaxPayload(Amount<Mass> maxPayload) {
        this.maxPayload = maxPayload;
    }

    /**
     * Obtains the AircraftModel's max fuel capacity
     *
     * @return the max fuel capacity
     */
    public Amount<Volume> getMaxFuelCapacity() {
        return this.maxFuelCapacity;
    }

    /**
     * Modifies the c max fuel capacity
     *
     * @param maxFuelCapacity the max fuel capacity to set
     */
    public void setMaxFuelCapacity(Amount<Volume> maxFuelCapacity) {
        this.maxFuelCapacity = maxFuelCapacity;
    }

    /**
     * Obtains the AircraftModel's vmo (max operating speed)
     *
     * @return the vmo (max operating speed)
     */
    public Amount<Velocity> getVmo() {
        return this.vmo;
    }

    /**
     * Modifies the AircraftModel's vmo (max operating speed)
     *
     * @param vmo the vmo (max operating speed) to set
     */
    public void setVmo(Amount<Velocity> vmo) {
        this.vmo = vmo;
    }

    /**
     * Obtains the AircraftModel's mmo (max mach operating speed)
     *
     * @return the mmo (max mach operating speed)
     */
    public Amount<Velocity> getMmo() {
        return this.mmo;
    }

    /**
     * Modifies the AircraftModel's mmo (max mach operating system)
     *
     * @param mmo the mmo (max mach operating system) to set
     */
    public void setMmo(Amount<Velocity> mmo) {
        this.mmo = mmo;
    }

    /**
     * Obtains the AircraftModel's wing span
     *
     * @return the wing span
     */
    public Amount<Length> getWingSpan() {
        return wingSpan;
    }

    /**
     * Modifies the AircraftModel's wing span
     *
     * @param wingSpan the wing span to set
     */
    public void setWingSpan(Amount<Length> wingSpan) {
        this.wingSpan = wingSpan;
    }

    /**
     * Obtains the AircraftModel's e.
     *
     * @return the e
     */
    public Amount<Dimensionless> getE() {
        return e;
    }

    /**
     * Modifies the AircraftModel's e.
     *
     * @param e e to set
     */
    public void setE(Amount<Dimensionless> e) {
        this.e = e;
    }

    /**
     * Gets the aspect ratio.
     *
     * @return aspect ratio
     */
    public Amount<Dimensionless> getAspectRatio() {
        return aspectRatio;
    }

    /**
     * Sets the aspect ratio.
     *
     * @param aspectRatio aspect ratio
     */
    public void setAspectRatio(Amount<Dimensionless> aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Gets the cdrag function.
     *
     * @return cdrag function
     */
    public double[][] getCdragFunction() {
        return cdragFunction;
    }

    /**
     * Sets the cdrag function.
     *
     * @param cdragFunction cdrag function
     */
    public void setCdragFunction(double[][] cdragFunction) {
        this.cdragFunction = cdragFunction;
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
        return String.format("AircraftModel{modelID=%s, description=%s, maker=%s, "
                + "type=%s, motorization=%s, emptyWeight=%s, mtow=%s, maxPayload=%s, "
                + "maxFuelCapacity=%s, vmo=%s, mmo=%s, wingArea=%s, wingSpan=%s, "
                + "aspectRatio=%s, e=%s, cdragFunction=%s}",
                modelID, description, maker, type, motorization, emptyWeight,
                mtow, maxPayload, maxFuelCapacity, vmo, mmo, wingArea, wingSpan,
                aspectRatio, e, Arrays.toString(cdragFunction));
    }

}
