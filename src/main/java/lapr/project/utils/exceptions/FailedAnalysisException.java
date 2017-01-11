/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.exceptions;

/**
 * Represents a custom failed analysis exception.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FailedAnalysisException extends Exception {

    private static final String DEFAULT_MSG = "Path algorithm failed to complete analysis. please review given information.";

    /**
     * Creates a new instance of <code>FailedAnalysisException</code> without
     * detail message.
     */
    public FailedAnalysisException() {
        super(DEFAULT_MSG);
    }

    /**
     * Constructs an instance of <code>FailedAnalysisException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FailedAnalysisException(String msg) {
        super(DEFAULT_MSG + "\n" + msg);
    }
}
