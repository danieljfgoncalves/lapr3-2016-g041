/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.exceptions;

/**
 * Represents a custom insufficient fuel exception.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class InsufficientFuelException extends Exception {

    private static final String DEFAULT_MSG = "Insufficient fuel for generated flight plan";

    /**
     * Creates a new instance of <code>FailedAnalysisException</code> without
     * detail message.
     */
    public InsufficientFuelException() {
        super(DEFAULT_MSG);
    }

    /**
     * Constructs an instance of <code>FailedAnalysisException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InsufficientFuelException(String msg) {
        super(DEFAULT_MSG + "\n" + msg);
    }
}
