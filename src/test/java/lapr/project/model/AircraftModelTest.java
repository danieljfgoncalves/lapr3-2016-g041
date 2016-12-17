/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for AircraftModel class
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AircraftModelTest {

    /**
     * The instance to be tested.
     */
    private AircraftModel instance;

    /**
     * The epsilon of the allowed error.
     */
    private final static Double EPSILON = 0.01d;

    @Before
    public void setUp() {
        instance = new AircraftModel();
    }

    /**
     * Test of getRegistration method and setRegistration, of class
     * AircraftModel.
     */
    @Test
    public void testGetSetRegistration() {
        System.out.println("getRegistration and setRegistration");
        Integer expResult = 10;
        instance.setModelID(expResult);
        Integer result = instance.getModelID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType and setType method, of class AircraftModel.
     */
    @Test
    public void testGetSetType() {
        System.out.println("getType and setType");
        AircraftType expResult = AircraftType.PASSENGER;
        instance.setType(expResult);
        AircraftType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmptyWeight method and setEmptyWeight, of class AircraftModel.
     */
    @Test
    public void testGetSetEmptyWeight() {
        System.out.println("getEmptyWeight");
        Double expResult = 4000.0;
        instance.setEmptyWeight(expResult);
        Double result = instance.getEmptyWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMtow method and setMtow, of class AircraftModel.
     */
    @Test
    public void testGetSetMtow() {
        System.out.println("getMtow and setMtow");
        Double expResult = 100000000.0;
        instance.setMtow(expResult);
        Double result = instance.getMtow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMzfw and setMzfw methods, of class AircraftModel.
     */
    @Test
    public void testGetSetMzfw() {
        System.out.println("getMzfw and setMzfw");
        Double expResult = 20000.0;
        instance.setMzfw(expResult);
        Double result = instance.getMzfw();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWingArea and setWingArea methods, of class AircraftModel.
     */
    @Test
    public void testGetSetWingArea() {
        System.out.println("getWingArea and setWingArea");
        Double expResult = 200.0;
        instance.setWingArea(expResult);
        Double result = instance.getWingArea();
        assertEquals(expResult, result);
    }
    
     /**
     * Test of getMotorization and setMotorization methods, of class AircraftModel.
     */
    @Test
    public void testGetSetMotorization() {
        System.out.println("getEngine and setEngine");
        Motorization expResult = new Motorization(){};
        instance.setMotorization(expResult);
        Motorization result = instance.getMotorization();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class AircraftModel.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = 235;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AircraftModel, expecting result true for
     * comparing different objects.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Motorization m = new Motorization() {};
        AircraftModel obj = new AircraftModel(1, AircraftType.MIXED, 1.0, 1.0, 1.0, 1.0, m, "motor1",
                "Boeing Vertol Company (United States)", 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AircraftModel, expecting result true for
     * comparing same objects.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        AircraftModel obj = new AircraftModel();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class AircraftModel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "Aircraft Model{modelID=0, type=PASSENGER, empty weight=0,000000,"
                + " maximum take off weight=0,000000, maximum zero fuel weight=0,000000,"
                + " wing area=0,000000, Motorization=Motorization{description=model,"
                + " mFlow0=0.0, mFlowE=0.0, mFlowC=0.0, V0=0.0, V1=0.0, Ve=0.0, Vf=0.0, bpr=0.0,"
                + " serviceCeiling=10000.0}, description=DefaultDescription, maker=DefaultMaker,"
                + " max payload=0,000000, max fuel capacity=0,000000, max operating speed=0,000000,"
                + " max mach operating speed=0,0, wing span=0,0, e=0,00 }";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
