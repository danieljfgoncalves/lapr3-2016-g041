/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Arrays;
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
    private Amount[][] flightProfile;

    /**
     * Counts the number of filled lines in the matrix.
     */
    private int lineNumber;

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
        flightProfile = new Amount[INITIAL_NUM_LINES][NUM_COLUMNS];
    }

    /**
     * Resizes the matrix when the existing lines are filled.
     */
    public void resizeMatrix() {
        if (lineNumber >= getFlightProfile().length) {
            int newSize = (int) (getFlightProfile().length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            Amount[][] temp = (Amount[][]) new Object[newSize][NUM_COLUMNS];

            for (int i = 0; i < getFlightProfile().length; i++) {
                temp[i] = Arrays.copyOf(getFlightProfile()[i], newSize);
            }
            setFlightProfile(temp);
        }
    }

    /**
     * Gets the flightProfile matrix.
     * 
     * @return the flightProfile
     */
    public Amount[][] getFlightProfile() {
        return flightProfile;
    }

    /**
     * Modifies the flightProfile matrix.
     * 
     * @param flightProfile the flightProfile to set
     */
    public void setFlightProfile(Amount[][] flightProfile) {
        this.flightProfile = flightProfile;
    }

}
