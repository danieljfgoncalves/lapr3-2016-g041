/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an util class to regex related operations.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Regex {

    /**
     * Gets the value of a given value with unit (e.g. '34.2e-3 SI' would return
     * 34.2e-3).
     *
     * @param valueWithUnit value with unit
     * @return extracted number
     */
    public static Double getValue(String valueWithUnit) {
        Pattern patternValue = Pattern.compile("\\d+(.\\d+)?((E|e)(\\+|\\-)?\\d+)?");
        Matcher matcherValue = patternValue.matcher(valueWithUnit);
        matcherValue.find();
        return Double.parseDouble(matcherValue.group());
    }

    /**
     * Gets the unit of a given value with unit (e.g. '34.2e-3 SI' would return
     * SI).
     *
     * @param valueWithUnit value with unit
     * @return extracted unit
     */
    public static String getUnit(String valueWithUnit) {
        return valueWithUnit.split(" ")[1].trim();
    }

}
