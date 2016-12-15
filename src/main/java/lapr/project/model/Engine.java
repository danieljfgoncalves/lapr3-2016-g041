/*
 * Package location for Model concepts
 */
package lapr.project.model;

import java.util.Objects;

/**
 * Represents an engine.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public abstract class Engine {

 
    
    /**
     * Description of the engine
     */
    
    private String description;
    
    /**
     * Mass flow rate on free stream conditions
     *
     */
   
    private Double mFlow0;

    /**
     * Mass flow on core exit conditions
     */
    private Double mFlowE;

    /**
     * Mass flow on the entrance to the core
     */
    private Double mFlowC;

    /**
     * Velocity on free stream conditions
     */
    private Double V0;

    /**
     * Velocity on propeller exit conditions
     */
    private Double V1;

    /**
     * Velocity on core exit conditions
     */
    private Double Ve;

    /**
     * Fan flow velocity
     */
    private Double Vf;

    /**
     * Bypass Ratio
     */
    private Double bpr;

    /**
     * Maximum height for sustained climb
     */
    private Double serviceCeiling;

    /**
     * default value for V0
     */
    private final static Double DEFAULT_V0 = 0d;

    /**
     * default value for V1
     */
    private final static Double DEFAULT_V1 = 0d;

    /**
     * default value for Ve
     */
    private final static Double DEFAULT_VE = 0d;

    /**
     * default value for Vf
     */
    private final static Double DEFAULT_VF = 0d;

    /**
     * default value for bpr
     */
    private final static Double DEFAULT_BPR = 0d;

    /**
     * default value for mFlow0
     */
    private final static Double DEFAULT_MFLOW0 = 0d;

    /**
     * default value for mFlowC
     */
    private final static Double DEFAULT_MFLOWC = 0d;

    /**
     * default value for mFlowE
     */
    private final static Double DEFAULT_MFLOWE = 0d;

    /**
     * default value for serviceCeiling;
     */
    private final static Double DEFAULT_SERVICE_CEILING = 10000d;
    
    /**
     * default value for Description
     */
    private final static String DEFAULT_DESCRIPTION = "model";

    /**
     * Creats an instance of Engine with it's default values
     */
    public Engine() {
        this.description = DEFAULT_DESCRIPTION;
        this.V0 = DEFAULT_V0;
        this.V1 = DEFAULT_V1;
        this.Ve = DEFAULT_VE;
        this.Vf = DEFAULT_VF;
        this.bpr = DEFAULT_BPR;
        this.mFlow0 = DEFAULT_MFLOW0;
        this.mFlowC = DEFAULT_MFLOWC;
        this.mFlowE = DEFAULT_MFLOWE;
        this.serviceCeiling = DEFAULT_SERVICE_CEILING;
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
    public Engine(String description, Double V0, Double V1, Double Ve, Double Vf, Double bpr, Double mFlow0, Double mFlowC, Double mFlowE, Double serviceCeiling) {
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
    public Double getV0() {
        return V0;
    }

    /**
     * Sets the value for V0
     * 
     * @param V0 Velocity on free stream conditions
     */
    public void setV0(Double V0) {
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
    public Double getmFlow0() {
        return mFlow0;
    }
    
    /**
     * Sets the mflow0
     * 
     * @param mFlow0 mass flow on free stream conditions 
     */
    public void setmFlow0(Double mFlow0) {
        this.mFlow0 = mFlow0;
    }
    
    /**
     * Gets the value of mFlowE
     * 
     * @return mass flow on core exit conditions
     */
    public Double getmFlowE() {
        return mFlowE;
    }
    
    /**
     * Sets the value of mFlowE
     * 
     * @param mFlowE mass flow on core exit conditions
     */
    public void setmFlowE(Double mFlowE) {
        this.mFlowE = mFlowE;
    }

    /**
     * Gets the value of mFlowC
     * 
     * @return mass flow on the entrance to the core 
     */
    public Double getmFlowC() {
        return mFlowC;
    }

    /**
    * Sets the value of mFlowC
    * 
    * @param mFlowC mass flow on the entrance to the core 
    */
    public void setmFlowC(Double mFlowC) {
        this.mFlowC = mFlowC;
    }
    
    /**
     * Gets the value of V1
     * 
     * @return velocity on propeller exit conditions
     */
    public Double getV1() {
        return V1;
    }

    /**
     * Sets the value of V1
     * 
     * @param V1 velocity on propeller exit conditions
     */
    public void setV1(Double V1) {
        this.V1 = V1;
    }

    /**
     * Gets the value of Ve
     * 
     * @return velocity on core exit conditions
     */
    public Double getVe() {
        return Ve;
    }

    /**
     * Sets the value of Ve
     * 
     * @param Ve velocity on core exit conditions
     */
    public void setVe(Double Ve) {
        this.Ve = Ve;
    }

    /**
     * Gets the value of Vf
     * 
     * @return fan flow velocity
     */
    public Double getVf() {
        return Vf;
    }

    /**
     * Sets the value of Vf
     * 
     * @param Vf fan flow velocity
     */
    public void setVf(Double Vf) {
        this.Vf = Vf;
    }

    /**
     * Gets the value of bpr
     * 
     * @return bypass ratio 
     */
    public Double getBpr() {
        return bpr;
    }

    /**
     * Sets the value of bpr
     * 
     * @param bpr bypass ratio 
     */
    public void setBpr(Double bpr) {
        this.bpr = bpr;
    }

    /**
     * Gets the value of serviceCeiling
     * 
     * @return Service ceiling
     */
    public Double getServiceCeiling() {
        return serviceCeiling;
    }

    /**
     * Sets the value of serviceCeiling
     * 
     * @param serviceCeiling Service ceiling
     */
    public void setServiceCeiling(Double serviceCeiling) {
        this.serviceCeiling = serviceCeiling;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.mFlow0);
        hash = 29 * hash + Objects.hashCode(this.mFlowE);
        hash = 29 * hash + Objects.hashCode(this.mFlowC);
        hash = 29 * hash + Objects.hashCode(this.V0);
        hash = 29 * hash + Objects.hashCode(this.V1);
        hash = 29 * hash + Objects.hashCode(this.Ve);
        hash = 29 * hash + Objects.hashCode(this.Vf);
        hash = 29 * hash + Objects.hashCode(this.bpr);
        hash = 29 * hash + Objects.hashCode(this.serviceCeiling);
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
