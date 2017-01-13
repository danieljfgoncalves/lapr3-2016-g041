/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import com.github.lgooddatepicker.components.DateTimePicker;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Represents an Util class that provides usefull methods.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Util {

    public static Calendar toCalendar(DateTimePicker dateTimePicker) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, dateTimePicker.datePicker.getDate().getDayOfMonth());
        calendar.set(Calendar.MONTH, dateTimePicker.datePicker.getDate().getMonthValue() - 1);
        calendar.set(Calendar.YEAR, dateTimePicker.datePicker.getDate().getYear());
        calendar.set(Calendar.HOUR_OF_DAY, dateTimePicker.timePicker.getTime().getHour());
        calendar.set(Calendar.MINUTE, dateTimePicker.timePicker.getTime().getMinute());
        calendar.set(Calendar.SECOND, dateTimePicker.timePicker.getTime().getSecond());
        return calendar;
    }
}
