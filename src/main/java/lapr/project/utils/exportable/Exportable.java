/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.exportable;

import java.io.File;

/**
 * Interface to manage export to a file.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public interface Exportable {

    /**
     * Export an object to a file.
     *
     * @param file the new file's path
     * @throws java.lang.Exception
     */
    void export(File file) throws Exception;
}
