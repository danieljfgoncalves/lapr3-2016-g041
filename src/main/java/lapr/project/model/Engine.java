/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 * Represents an engine.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241 Tiago Correia - 1151031
 */
public abstract class Engine {

    /**
     * @param mFlow - mass flow rate (mass / time)
     * @param V - Velocity
     * @param bpr - Bypass Ratio (mFlowF / mFlowC) Extensions 0 - free stream
     * conditions 1 - propeller exit conditions e - core exit conditions c-
     * entrance to the core f - fan flow (or bypass flow)
     *
     *
     */
 
    
    /**
     * Description of the engine
     */
    
    private String description;
    
    /**
     * Mass flow rate on free stream conditions
     *
     */
   
    private double mFlow0;

    /**
     * Mass flow on core exit conditions
     */
    private double mFlowE;

    /**
     * Mass flow on the entrance to the core
     */
    private double mFlowC;

    /**
     * Velocity on free stream conditions
     */
    private double V0;

    /**
     * Velocity on propeller exit conditions
     */
    private double V1;

    /**
     * Velocity on core exit conditions
     */
    private double Ve;

    /**
     * Fan flow velocity
     */
    private double Vf;

    /**
     * Bypass Ratio
     */
    private double bpr;

    /**
     * Maximum height for sustained climb
     */
    private double serviceCeiling;

    /**
     * default value for V0
     */
    private final double defaultV0 = 0;

    /**
     * default value for V1
     */
    private final double defaultV1 = 0;

    /**
     * default value for Ve
     */
    private final double defaultVe = 0;

    /**
     * default value for Vf
     */
    private final double defaultVf = 0;

    /**
     * default value for bpr
     */
    private final double defaultbpr = 0;

    /**
     * default value for mFlow0
     */
    private final double defaultmFlow0 = 0;

    /**
     * default value for mFlowC
     */
    private final double defaultmFlowC = 0;

    /**
     * default value for mFlowE
     */
    private final double defaultmFlowE = 0;

    /**
     * default value for serviceCeiling;
     */
    private final double defaultServiceCeiling = 10000;
    
    /**
     * default value for Description
     */
    private final String defaultDescription = "model";

    /**
     * Creats an instance of Engine with it's default values
     */
    public Engine() {
        this.description = defaultDescription;
        this.V0 = defaultV0;
        this.V1 = defaultV1;
        this.Ve = defaultVe;
        this.Vf = defaultVf;
        this.bpr = defaultbpr;
        this.mFlow0 = defaultmFlow0;
        this.mFlowC = defaultmFlowC;
        this.mFlowE = defaultmFlowE;
        this.serviceCeiling = defaultServiceCeiling;
    }

    /**
     *Creates an instance of Engine receiving their atribbutes
     * 
     * @param description
     * @param V0
     * @param V1
     * @param Ve
     * @param Vf
     * @param bpr
     * @param mFlow0
     * @param mFlowC
     * @param mFlowE
     * @param serviceCeiling
     */
    public Engine(String description, double V0, double V1, double Ve, double Vf, double bpr, double mFlow0, double mFlowC, double mFlowE, double serviceCeiling) {
        this.description = description;
        this.V0 = V0;
        this.V1 = V1;
        this.Ve = Ve;
        this.Vf = Vf;
        this.bpr = bpr;
        this.mFlow0 = mFlow0;
        this.mFlowC = mFlowC;
        this.mFlowE = mFlowE;
        this.serviceCeiling = serviceCeiling;
    }

    /**
     *Creates an Engine receiving other engine
     * @param otherEngine
     */
    public Engine(Engine otherEngine) {
        this.description = otherEngine.description;
        this.V0 = otherEngine.V0;
        this.V1 = otherEngine.V1;
        this.Ve = otherEngine.Ve;
        this.Vf = otherEngine.Vf;
        this.bpr = otherEngine.bpr;
        this.mFlow0 = otherEngine.mFlow0;
        this.mFlowC = otherEngine.mFlowC;
        this.mFlowE = otherEngine.mFlowE;
        this.serviceCeiling = otherEngine.serviceCeiling;

    }
    /**
     * Gets the value for V0
     * 
     * @return V0
     */
    public double getV0() {
        return V0;
    }

    /**
     * Sets the value for V0
     * 
     * @param V0 Velocity on free stream conditions
     */
    public void setV0(double V0) {
        this.V0 = V0;
    }

    /**
     * Gets the Description
     * 
     * @return  description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Description
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *Gets the value of mFlow0
     * 
     * @return mass flow on free stream conditions
     */
    public double getmFlow0() {
        return mFlow0;
    }
    
    /**
     * Sets the mflow0
     * 
     * @param mFlow0 mass flow on free stream conditions 
     */
    public void setmFlow0(double mFlow0) {
        this.mFlow0 = mFlow0;
    }
    
    /**
     * Gets the value of mFlowE
     * 
     * @return mass flow on core exit conditions
     */
    public double getmFlowE() {
        return mFlowE;
    }
    
    /**
     * Sets the value of mFlowE
     * 
     * @param mFlowE mass flow on core exit conditions
     */
    public void setmFlowE(double mFlowE) {
        this.mFlowE = mFlowE;
    }

    /**
     * Gets the value of mFlowC
     * 
     * @return mass flow on the entrance to the core 
     */
    public double getmFlowC() {
        return mFlowC;
    }

    /**
    * Sets the value of mFlowC
    * 
    * @param mFlowC mass flow on the entrance to the core 
    */
    public void setmFlowC(double mFlowC) {
        this.mFlowC = mFlowC;
    }
    
    /**
     * Gets the value of V1
     * 
     * @return velocity on propeller exit conditions
     */
    public double getV1() {
        return V1;
    }

    /**
     * Sets the value of V1
     * 
     * @param V1 velocity on propeller exit conditions
     */
    public void setV1(double V1) {
        this.V1 = V1;
    }

    /**
     * Gets the value of Ve
     * 
     * @return velocity on core exit conditions
     */
    public double getVe() {
        return Ve;
    }

    /**
     * Sets the value of Ve
     * 
     * @param Ve velocity on core exit conditions
     */
    public void setVe(double Ve) {
        this.Ve = Ve;
    }

    /**
     * Gets the value of Vf
     * 
     * @return fan flow velocity
     */
    public double getVf() {
        return Vf;
    }

    /**
     * Sets the value of Vf
     * 
     * @param Vf fan flow velocity
     */
    public void setVf(double Vf) {
        this.Vf = Vf;
    }

    /**
     * Gets the value of bpr
     * 
     * @return bypass ratio 
     */
    public double getBpr() {
        return bpr;
    }

    /**
     * Sets the value of bpr
     * 
     * @param bpr bypass ratio 
     */
    public void setBpr(double bpr) {
        this.bpr = bpr;
    }

    /**
     * Gets the value of serviceCeiling
     * 
     * @return Service ceiling
     */
    public double getServiceCeiling() {
        return serviceCeiling;
    }

    /**
     * Sets the value of serviceCeiling
     * 
     * @param serviceCeiling Service ceiling
     */
    public void setServiceCeiling(double serviceCeiling) {
        this.serviceCeiling = serviceCeiling;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.mFlow0) ^ (Double.doubleToLongBits(this.mFlow0) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.mFlowE) ^ (Double.doubleToLongBits(this.mFlowE) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.mFlowC) ^ (Double.doubleToLongBits(this.mFlowC) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.V0) ^ (Double.doubleToLongBits(this.V0) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.V1) ^ (Double.doubleToLongBits(this.V1) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.Ve) ^ (Double.doubleToLongBits(this.Ve) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.Vf) ^ (Double.doubleToLongBits(this.Vf) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.bpr) ^ (Double.doubleToLongBits(this.bpr) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.serviceCeiling) ^ (Double.doubleToLongBits(this.serviceCeiling) >>> 32));
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

        final Engine other = (Engine) obj;
        return this.description.equals(other.description);              
    }

    @Override
    public String toString() {
        return "Engine{" + "description=" + description + ", mFlow0=" + mFlow0 + ", mFlowE=" + mFlowE + ", mFlowC=" + mFlowC + ", V0=" + V0 + ", V1=" + V1 + ", Ve=" + Ve + ", Vf=" + Vf + ", bpr=" + bpr + ", serviceCeiling=" + serviceCeiling + '}';
    }
    
}
