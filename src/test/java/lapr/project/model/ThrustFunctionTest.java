/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import javax.measure.quantity.Force;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import org.jscience.physics.amount.Amount;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ivo Ferro
 */
public class ThrustFunctionTest {

    private ThrustFunction instance;

    private static final double EPSILON = 0.1d;

    @Before
    public void setUp() {
        instance = new ThrustFunction(Amount.valueOf(348.31E+03, SI.NEWTON),
                Amount.valueOf(1.8E+05, SI.METERS_PER_SECOND),
                Amount.valueOf(0.9, NonSI.MACH));
    }

    /**
     * Test of getThrust0 and setThrust0 methods, of class ThrustFunction.
     */
    @Test
    public void testGetSetThrust0() {
        System.out.println("getThrust0 and setThrust0");

        Amount<Force> thrust0 = Amount.valueOf(10E3, SI.NEWTON);
        instance.setThrust0(thrust0);

        assertEquals(instance.getThrust0().doubleValue(SI.NEWTON), thrust0.doubleValue(SI.NEWTON), EPSILON);
    }

    /**
     * Test of getThrustMaxSpeed and setThrustMaxSpeed methods, of class
     * ThrustFunction.
     */
    @Test
    public void testGetSetThrustMaxSpeed() {
        System.out.println("getThrustMaxSpeed and setThrustMaxSpeed");

        Amount<Velocity> thrustMaxSpeed = Amount.valueOf(9.9e6, SI.METERS_PER_SECOND);
        instance.setThrustMaxSpeed(thrustMaxSpeed);

        assertEquals(instance.getThrustMaxSpeed().doubleValue(SI.METERS_PER_SECOND), thrustMaxSpeed.doubleValue(SI.METERS_PER_SECOND), EPSILON);
    }

    /**
     * Test of getMaxSpeed and setMaxSpeed methods, of class ThrustFunction.
     */
    @Test
    public void testGetSetMaxSpeed() {
        System.out.println("getMaxSpeed and setMaxSpeed");

        Amount<Velocity> maxSpeed = Amount.valueOf(0.1, NonSI.MACH);
        instance.setMaxSpeed(maxSpeed);

        assertEquals(instance.getMaxSpeed().doubleValue(NonSI.MACH), maxSpeed.doubleValue(NonSI.MACH), EPSILON);
    }

    /**
     * Test 1 of equals method, of class ThrustFunction.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals 1");

        Object obj = null;

        assertFalse(instance.equals(obj));
    }

    /**
     * Test 2 of equals method, of class ThrustFunction.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals 2");

        Object obj = new ThrustFunction(Amount.valueOf(1E+01, SI.NEWTON),
                Amount.valueOf(1E1, SI.METERS_PER_SECOND),
                Amount.valueOf(0.0001, NonSI.MACH));

        assertFalse(instance.equals(obj));
    }

    /**
     * Test 3 of equals method, of class ThrustFunction.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals 3");

        Object obj = new ThrustFunction(Amount.valueOf(348.31E+03, SI.NEWTON),
                Amount.valueOf(1.8E+05, SI.METERS_PER_SECOND),
                Amount.valueOf(0.9, NonSI.MACH));

        assertTrue(instance.equals(obj));
    }

}
