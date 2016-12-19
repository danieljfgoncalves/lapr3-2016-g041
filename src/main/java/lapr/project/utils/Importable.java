/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

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
     * @throws javax.xml.parsers.ParserConfigurationException configurations
     * errors
     * @throws java.io.IOException invalid file or path
     * @throws org.xml.sax.SAXException
     */
    boolean importXml(File fileToImport) throws SAXException, IOException, ParserConfigurationException;
}
