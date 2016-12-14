/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jp_fp
 */
public class EngineTest {
    
    public EngineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of getV0 method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetV0() {
        System.out.println("getmV0");
        
        Engine instance = new EngineImpl();
        double t = 20.0;
        instance.setV0(t);
        assertEquals(instance.getV0(),t, 0.01);
        
    }
    
    /**
     * Test of getDescription method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetDescription() {
        System.out.println("getmFlow0");
        
        Engine instance = new EngineImpl();
        String t = "new";
        instance.setDescription(t);
        assertEquals(instance.getDescription(),t);
        
    }
    
    /**
     * Test of getmFlow0 method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetmFlow0() {
        System.out.println("getmFlow0");
        
        Engine instance = new EngineImpl();
        double t = 20.0;
        instance.setmFlow0(t);
        assertEquals(instance.getmFlow0(),t, 0.01);
        
    }


    /**
     * Test of getmFlowE method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetmFlowE() {
        System.out.println("getmFlowE");
        Engine instance = new EngineImpl();
        double expResult = 20.0;
        instance.setmFlowE(expResult);
        assertEquals(expResult, instance.getmFlowE(), 0.01);
    }

    /**
     * Test of getmFlowC method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetmFlowC() {
        System.out.println("getmFlowC");
        Engine instance = new EngineImpl();
        double expResult = 20.0;
        instance.setmFlowC(expResult);
        assertEquals(expResult, instance.getmFlowC(), 0.01);
    }


    /**
     * Test of getV1 method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetV1() {
        System.out.println("getV1");
        Engine instance = new EngineImpl();
        double expResult = 20.0;
        instance.setV1(expResult);
        assertEquals(expResult, instance.getV1(), 0.01);
       
    }


    /**
     * Test of getVe method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetVe() {
        System.out.println("getVe");
        Engine instance = new EngineImpl();
        double expResult = 20.0;
        instance.setVe(expResult);
        assertEquals(expResult, instance.getVe(), 0.01);
        
    }


    /**
     * Test of getVf method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetVf() {
        System.out.println("getVf");
        Engine instance = new EngineImpl();
        double expResult = 20.0;
        instance.setVf(expResult);
        assertEquals(expResult, instance.getVf(), 0.01);
    }    

    /**
     * Test of getBpr method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetBpr() {
        System.out.println("getBpr");
        Engine instance = new EngineImpl();
        double expResult = 20.0;
        instance.setBpr(expResult);
        assertEquals(expResult, instance.getBpr(), 0.01);
    }

    /**
     * Test of getServiceCeiling method, of class Engine.
     */
    @org.junit.Test
    public void testGetSetServiceCeiling() {
        System.out.println("getServiceCeiling");
        Engine instance = new EngineImpl();
        double expResult = 20.0;
        instance.setServiceCeiling(expResult);
        assertEquals(expResult, instance.getServiceCeiling(), 0.01);
    }

      /**
     * Test of hashCode method, of class Engine.
     */
    @org.junit.Test
    public void testHashCode() {
        System.out.println("hashCode");
        Engine instance = new EngineImpl();
        int expResult = 1024004007;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Engine.
     */
    @org.junit.Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Engine instance = new EngineImpl();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Engine.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Engine instance = new EngineImpl();
        instance.setBpr(1);
        instance.setDescription("new");
        instance.setServiceCeiling(1);
        instance.setV0(1);
        instance.setV1(1);
        instance.setVe(1);
        instance.setVf(1);
        instance.setmFlow0(1);
        instance.setmFlowC(1);
        instance.setmFlowE(1);
        String description = "new";
        double bpr = 1;
        double mFlow0 = 1;
        double serviceCeiling =1;
        double mFlowE =1;
        double mFlowC =1;
        double V0 =1;
        double V1 =1;
        double Ve = 1;
        double Vf =1;
        String expResult = "Engine{" + "description=" + description + ", mFlow0=" + mFlow0 + ", mFlowE=" + mFlowE + ", mFlowC=" + mFlowC + ", V0=" + V0 + ", V1=" + V1 + ", Ve=" + Ve + ", Vf=" + Vf + ", bpr=" + bpr + ", serviceCeiling=" + serviceCeiling + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    public class EngineImpl extends Engine {
    }
    
}
