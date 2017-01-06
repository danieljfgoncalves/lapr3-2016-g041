/**
 * Package location for Model concept tests.
 */
package lapr.project.model;

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

    @Before
    public void setUp() {
        instance = new AircraftModel();
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
        Amount<Mass> expResult = Amount.valueOf(4000d, SI.KILOGRAM);
        instance.setEmptyWeight(expResult);
        Amount<Mass> result = instance.getEmptyWeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMtow method and setMtow, of class AircraftModel.
     */
    @Test
    public void testGetSetMtow() {
        System.out.println("getMtow and setMtow");
        Amount<Mass> expResult = Amount.valueOf(1000000d, SI.KILOGRAM);
        instance.setMtow(expResult);
        Amount<Mass> result = instance.getMtow();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWingArea and setWingArea methods, of class AircraftModel.
     */
    @Test
    public void testGetSetWingArea() {
        System.out.println("getWingArea and setWingArea");
        Amount<Area> expResult = Amount.valueOf(124.6d, SI.SQUARE_METRE); // 124.60 m2 wing area of Boeing 737
        instance.setWingArea(expResult);
        Amount<Area> result = instance.getWingArea();
        assertEquals(expResult, result);
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

        String modelID = "33BOING474";
        instance.setModelID(modelID);

        assertEquals(instance.getModelID(), modelID);
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
        Amount<Mass> expResult = Amount.valueOf(100d, SI.KILO(SI.KILOGRAM));
        instance.setMaxPayload(expResult);
        Amount<Mass> result = instance.getMaxPayload();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetMaxFuelCapacity method, of class AircraftModel.
     */
    @Test
    public void testGetSetMaxFuelCapacity() {
        System.out.println("getSetMaxFuelCapacity");
        Amount<Volume> expResult = Amount.valueOf(29666.27, SI.CUBIC_METRE); //max fuel capacity of Boeing 737-900ER in liters
        instance.setMaxFuelCapacity(expResult);
        Amount<Volume> result = instance.getMaxFuelCapacity();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetVmo method, of class AircraftModel.
     */
    @Test
    public void testGetSetVmo() {
        System.out.println("getSetVmo");
        Amount<Velocity> expResult = Amount.valueOf(340d, NonSI.KNOT); // max operating speed of boeing 700 = 340.0kt
        instance.setVmo(expResult);
        Amount<Velocity> result = instance.getVmo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetMmo method, of class AircraftModel.
     */
    @Test
    public void testGetSetMmo() {
        System.out.println("getSetMmo");
        Amount<Velocity> expResult = Amount.valueOf(0.82d, NonSI.MACH); // max mach operating speed for boeing 700 = 0.82 Mach
        instance.setMmo(expResult);
        Amount<Velocity> result = instance.getMmo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetWingSpan method, of class AircraftModel.
     */
    @Test
    public void testGetSetWingSpan() {
        System.out.println("getSetWingSpan");
        Amount<Length> expResult = Amount.valueOf(35.79d, SI.METER); // 35.79 m with winglets(little wings) for Boeing 737 without winglets: 34.32 m
        instance.setWingSpan(expResult);
        Amount<Length> result = instance.getWingSpan();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSetE method, of class AircraftModel.
     */
    @Test
    public void testGetSetE() {
        System.out.println("getSetE");
        Amount<Dimensionless> expResult = Amount.valueOf(0.90d, Unit.ONE);
        instance.setE(expResult);
        Amount<Dimensionless> result = instance.getE();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AircraftModel, expecting result true for
     * comparing different objects.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals");

        Object obj = new AircraftModel();
        ((AircraftModel)obj).setModelID("12abc67");

        assertFalse(instance.equals(obj));
    }

    /**
     * Test of equals method, of class AircraftModel, expecting result true for
     * comparing same objects.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals");
        Object obj = new AircraftModel();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
}
