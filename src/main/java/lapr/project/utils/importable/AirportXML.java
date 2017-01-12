/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils.importable;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.measure.unit.SI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lapr.project.datalayer.dao.AirportDAO;
import lapr.project.datalayer.oracle.AirportOracle;
import lapr.project.model.Airport;
import lapr.project.model.Coordinate;
import lapr.project.model.Project;
import lapr.project.utils.Regex;
import org.jscience.physics.amount.Amount;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class responsible to manage import airports from a file.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AirportXML implements Importable {

    /**
     * The file to import.
     */
    private File file;
    
    /**
     * The active project.
     */
    private Project project;

    /**
     * Opens the import file.
     * 
     * @param fileToImport
     * @param project 
     */
    public AirportXML(File fileToImport, Project project) {
        String filename = fileToImport.getName();
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1 || !filename.substring(dotIndex).equalsIgnoreCase(".xml")) {
            throw new IllegalArgumentException("File is not a xml");
        }
        file = fileToImport;
        this.project = project;
    }

    @Override
    public Object importFile() throws Exception {

        List<Airport> listAirports = new ArrayList();
        AirportDAO airportDAO = new AirportOracle(project.getSerieNumber());

        // set up dom
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        // iterate airports dom
        NodeList airportsNodeList = doc.getElementsByTagName("airport");
        int airportsLength = airportsNodeList.getLength();
        for (int i = 0; i < airportsLength; i++) {
            Airport airport = new Airport();
            Node airportNode = airportsNodeList.item(i);
            if (airportNode.getNodeType() == Node.ELEMENT_NODE) {
                // airport
                Element airportElement = (Element) airportNode;
                airport.setIATA(airportElement.getAttribute("id"));
                airport.setName(airportElement.getElementsByTagName("name").item(0).getTextContent());
                airport.setTown(airportElement.getElementsByTagName("town").item(0).getTextContent());
                airport.setCountry(airportElement.getElementsByTagName("country").item(0).getTextContent());
                // location
                Element locationElement = (Element) airportElement.getElementsByTagName("location").item(0);
                Double latitude = (Double.parseDouble(locationElement.getElementsByTagName("latitude").item(0).getTextContent()));
                Double longitude = (Double.parseDouble(locationElement.getElementsByTagName("longitude").item(0).getTextContent()));

                try {

                    String id = airportDAO.getCoordinateIdFromDB(latitude, longitude, project.getSerieNumber());
                    if (!id.isEmpty()) {
                        Coordinate coordinate = new Coordinate(id, latitude, longitude);

                        airport.setCoordinates(coordinate);

                        String altitude = locationElement.getElementsByTagName("altitude").item(0).getTextContent();
                        airport.setAltitude(Amount.valueOf(Regex.getValue(altitude), SI.METER));

                        listAirports.add(airport);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AirportXML.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listAirports;
    }
}
