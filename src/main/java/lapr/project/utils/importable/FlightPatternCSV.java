/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.importable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import lapr.project.model.FlightPattern;
import lapr.project.utils.CustomUnits;
import org.jscience.physics.amount.Amount;

/**
 * Class responsible to manage import flight patterns from a csv file.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightPatternCSV implements Importable {

    private File file;

    public FlightPatternCSV(File fileToImport) {
        String filename = fileToImport.getName();
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1 || !filename.substring(dotIndex).equalsIgnoreCase(".csv")) {
            throw new IllegalArgumentException("File is not a csv");
        }
        file = fileToImport;
    }

    /*
     * |Altitude| vClimb | vDesc | rDesc | *not in the matrix*
       |  value |  value | value | value |
       |  value |  value | value | value | 
       |  value |  value | value | value | 
       |  (...) |  (...) | (...) | (...) |
     */
    @Override
    public Object importFile() throws Exception {
        FlightPattern fp = new FlightPattern();

        String[][] aux = csvToMatrix(file);

        Unit altitudeUnit = getUnit(aux[0][1]);
        Unit vClimbUnit = getUnit(aux[1][1]);
        Unit vDescUnit = getUnit(aux[2][1]);
        Unit rDescUnit = getUnit(aux[3][1]);

        for (int i = 2; i < aux[0].length; i++) {

            Double altitude = Double.parseDouble(aux[0][i]);
            Double vClimb = Double.parseDouble(aux[1][i]);
            Double vDesc = Double.parseDouble(aux[2][i]);
            Double rDesc = Double.parseDouble(aux[3][i]);

            fp.insertLine(Amount.valueOf(altitude, altitudeUnit),
                    Amount.valueOf(vClimb, vClimbUnit),
                    Amount.valueOf(vDesc, vDescUnit),
                    Amount.valueOf(rDesc, rDescUnit));
        }
        return fp;
    }

    /**
     * Imports the data from a csv file to a matrix.
     *
     * @param file the given file
     *
     * @return matrix:
     *
     * Altitude | unit | value | value | value | value | value | value | value
     * vClimb | unit | value | value | value | value | value | value | value
     * vDesc | unit | value | value | value | value | value | value | value
     * rDesc | unit | value | value | value | value | value | value | value
     *
     * @throws Exception
     */
    private static String[][] csvToMatrix(File file) throws Exception {

        String[][] matrix = new String[4][];

        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader(file));
        String currentLine;
        int i = 0;
        while ((currentLine = bufferedReader.readLine()) != null) {
            matrix[i] = currentLine.replaceAll("\\s|\\t", "").split(",");
            i++;
        }
        bufferedReader.close();

        return matrix;
    }

    /**
     * Returns the unit for each of the import csv file cases.
     *
     * @param string the received string
     * @return the unit
     */
    private static Unit getUnit(String string) {
        Unit unit = null;
        switch (string.toLowerCase().trim()) {
            case "ft":
                unit = NonSI.FOOT;
                break;
            case "m":
                unit = SI.METER;
                break;
            case "knot":
                unit = NonSI.KNOT;
                break;
            case "m/s":
                unit = SI.METERS_PER_SECOND;
                break;
            case "ft/s":
                unit = CustomUnits.FEET_PER_SECOND;
                break;
        }
        return unit;
    }

}
