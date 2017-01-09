/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.importable;

import java.io.File;

/**
 * Interface to manage import from a file.
 * 
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface Importable {

    /**
     * Imports data from a file.
     *
     * @return a java object from the imported file
     * @throws java.lang.Exception
     */
    Object importFile() throws Exception;
}
