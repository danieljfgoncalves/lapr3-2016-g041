/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.io.File;

/**
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public interface Importable {

    /**
     * Imports data from a xml file.
     *
     * @param fileToImport xml file to import
     * @return true if it's successfully imported, false otherwise
     */
    boolean importXml(File fileToImport);
}
