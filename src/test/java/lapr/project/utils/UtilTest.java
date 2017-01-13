/**
 * Package location for Pure Fabrication util classes tests.
 */
package lapr.project.utils;

import com.github.lgooddatepicker.components.DateTimePicker;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the util class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class UtilTest {

    /**
     * Test of toCalendar method, of class Util.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testToCalendar() throws Exception {
        System.out.println("toCalendar");
        
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.datePicker.setDate(LocalDate.of(1995, 1, 11));
        dateTimePicker.timePicker.setTime(LocalTime.of(18, 30, 00));
        
        Calendar expResult = new GregorianCalendar();
        expResult.set(Calendar.DAY_OF_MONTH, 11);
        expResult.set(Calendar.MONTH, 0);
        expResult.set(Calendar.YEAR, 1995);
        expResult.set(Calendar.HOUR_OF_DAY, 18);
        expResult.set(Calendar.MINUTE, 30);
        expResult.set(Calendar.SECOND, 00);
        
        Calendar result = Util.toCalendar(dateTimePicker);
        
        assertEquals(expResult.getTime().toString(), result.getTime().toString());
    }

}
