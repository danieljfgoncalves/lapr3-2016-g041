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
        assertEquals(expResult, result, EPSILON);
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
        assertEquals(expResult, result, EPSILON);
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
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of getWingArea and setWingArea methods, of class AircraftModel.
     */
    @Test
    public void testGetSetWingArea() {
        System.out.println("getWingArea and setWingArea");
        Double expResult = 124.60; // 124.60 m2 wing area of Boeing 737
        instance.setWingArea(expResult);
        Double result = instance.getWingArea();
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of getMotorization and setMotorization methods, of class
     * AircraftModel.
     */
    @Test
    public void testGetSetMotorization() {
        System.out.println("getEngine and setEngine");
        Motorization expResult = new Motorization();
        instance.setMotorization(expResult);
        Motorization result = instance.getMotorization();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetModelID method, of class AircraftModel.
     */
    @Test
    public void testGetSetModelID() {
        System.out.println("getSetModelID");
        Integer expResult = 10;
        instance.setModelID(expResult);
        Integer result = instance.getModelID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetDescription method, of class AircraftModel.
     */
    @Test
    public void testGetSetDescription() {
        System.out.println("getSetDescription");
        String expResult = "description 1";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetMaker method, of class AircraftModel.
     */
    @Test
    public void testGetSetMaker() {
        System.out.println("getSetMaker");
        String expResult = "DAE(Dubai Aerospace Enterprise)";
        instance.setMaker(expResult);
        String result = instance.getMaker();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetMaxPayload method, of class AircraftModel.
     */
    @Test
    public void testGetSetMaxPayload() {
        System.out.println("getSetMaxPayload");
        Double expResult = 100000000.0;
        instance.setMaxPayload(expResult);
        Double result = instance.getMaxPayload();
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of getSetMaxFuelCapacity method, of class AircraftModel.
     */
    @Test
    public void testGetSetMaxFuelCapacity() {
        System.out.println("getSetMaxFuelCapacity");
        Double expResult = 29666.27; //max fuel capacity of Boeing 737-900ER in liters
        instance.setMaxFuelCapacity(expResult);
        Double result = instance.getMaxFuelCapacity();
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of getSetVmo method, of class AircraftModel.
     */
    @Test
    public void testGetSetVmo() {
        System.out.println("getSetVmo");
        Double expResult = 340.0; // max operating speed of boeing 700 = 340.0kt
        instance.setVmo(expResult);
        Double result = instance.getVmo();
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of getSetMmo method, of class AircraftModel.
     */
    @Test
    public void testGetSetMmo() {
        System.out.println("getSetMmo");
        Double expResult = 0.82; // max mach operating speed for boeing 700 = 0.82 Mach
        instance.setMmo(expResult);
        Double result = instance.getMmo();
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of getSetWingSpan method, of class AircraftModel.
     */
    @Test
    public void testGetSetWingSpan() {
        System.out.println("getSetWingSpan");
        Double expResult = 35.79; // 35.79 m with winglets(little wings) for Boeing 737 without winglets: 34.32 m
        instance.setWingSpan(expResult);
        Double result = instance.getWingSpan();
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of getSetE method, of class AircraftModel.
     */
    @Test
    public void testGetSetE() {
        System.out.println("getSetE");
        Double expResult = 10.0;
        instance.setE(expResult);
        Double result = instance.getE();
        assertEquals(expResult, result, EPSILON);
    }

    /**
     * Test of equals method, of class AircraftModel, expecting result true for
     * comparing different objects.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");
        Motorization m = new Motorization();
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
        String expResult = "Aircraft Model{modelID=0, type=PASSENGER, empty weight=0,00,"
                + " maximum take off weight=0,00, maximum zero fuel weight=0,00,"
                + " wing area=0,00, "
                + "Motorization=Motorization{numberOfMotors=4, motor=GE CF6-80C2B1F, motorType=TURBOFAN, regimes=[]},"
                + " description=DefaultDescription, maker=DefaultMaker,"
                + " max payload=0,00, max fuel capacity=0,00, max operating speed=0,00,"
                + " max mach operating speed=0,00, wing span=0,00, e=0,00 }";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
