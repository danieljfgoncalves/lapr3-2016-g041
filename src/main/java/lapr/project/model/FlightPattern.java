/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Arrays;
import javax.measure.quantity.Length;
import javax.measure.quantity.Velocity;
import org.jscience.physics.amount.Amount;

/**
 * Represents a FlightPattern class.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightPattern {

    /**
     * The matrix with the flight pattern data.
     */
    private Amount[][] matrix;

    /**
     * Counts the number of filled lines in the matrix.
     */
    private int lineIndex;

    /**
     * Initial number of lines of the matrix.
     */
    public static final int INITIAL_NUM_LINES = 15;

    /**
     * Number of columns in the matrix.
     */
    public static final int NUM_COLUMNS = 4;

    /**
     * Factor in which the matrix is resized.
     */
    public static final float RESIZE_FACTOR = 1.5F;

    /**
     * Creates an instance of a FlightPattern.
     */
    public FlightPattern() {
        matrix = new Amount[INITIAL_NUM_LINES][NUM_COLUMNS];
        lineIndex = 0;
    }

    /**
     * Resizes the matrix when the existing lines are filled.
     */
    public void resizeMatrix() {
        if (lineIndex >= matrix.length) {
            int newSize = (int) (matrix.length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            Amount[][] temp = new Amount[newSize][NUM_COLUMNS];

            for (int i = 0; i < matrix.length; i++) {
                temp[i] = Arrays.copyOf(matrix[i], NUM_COLUMNS);
            }
            matrix = temp;
        }
    }

    /**
     * Gets the matrix matrix.
     *
     * @return the matrix
     */
    public Amount[][] getFlightProfile() {
        return matrix;
    }

    /**
     * Modifies the matrix.
     *
     * @param matrix the matrix to set
     */
    public void setFlightProfile(Amount[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Inserts a line in the matrix and verifies if the matrix needs to be
     * increased in size.
     *
     * @param altitude the given altitude
     * @param vClimb the given vClimb
     * @param vDesc the given vDesc
     * @param rDesc the given rDesc
     */
    public void insertLine(Amount<Length> altitude, Amount<Velocity> vClimb, Amount<Velocity> vDesc, Amount rDesc) {
        this.matrix[lineIndex][0] = altitude;
        this.matrix[lineIndex][1] = vClimb;
        this.matrix[lineIndex][2] = vDesc;
        this.matrix[lineIndex][3] = rDesc;

        lineIndex++;
        resizeMatrix();
    }

    /**
     * Gets the index of the last filled line.
     * 
     * @return the lineNumber
     */
    public int numLines() {
        return lineIndex + 1;
    }
}
