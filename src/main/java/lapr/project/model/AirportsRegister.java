/*
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.utils.Importable;
import lapr.project.utils.Regex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Represents an register for aircraft models.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class AirportsRegister implements Importable {

    /**
     * The list of airports.
     */
    private List<Airport> airports;

    /**
     * Creates an instance of AirportsRegister, receiving a list of aiports.
     *
     * @param airports the given list of airports
     */
    public AirportsRegister(List<Airport> airports) {
        this.airports = airports;
    }

    /**
     * Creates an instance of AirportsRegister with an empty list.
     */
    public AirportsRegister() {
        this.airports = new ArrayList();
    }

    /**
     * Creates a copy of a AirportsRegister.
     *
     * @param airportsRegister the given AirportsRegister
     */
    public AirportsRegister(AirportsRegister airportsRegister) {
        this.airports = new ArrayList<>(airportsRegister.getAirports());
    }

    /**
     * Gets the airports list.
     *
     * @return the airports
     */
    public List<Airport> getAirports() {
        return airports;
    }

    /**
     * Modifies the airports list.
     *
     * @param airports the airports to set
     */
    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.airports);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AirportsRegister other = (AirportsRegister) obj;
        return this.airports.equals(other.airports);
    }

    @Override
    public String toString() {
        return String.format("Airports: %s", airports);
    }

    @Override
    public boolean importXml(File fileToImport) throws SAXException, IOException, ParserConfigurationException {

        // set up dom
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileToImport);
        doc.getDocumentElement().normalize();

        // iterate airports dom
        NodeList airportsNodeList = doc.getElementsByTagName("airport");
        int airportsLength = airportsNodeList.getLength();
        for (int i = 0; i < airportsLength; i++) {

            Node airportNode = airportsNodeList.item(i);
            if (airportNode.getNodeType() == Node.ELEMENT_NODE) {

                // airport
                Element airportElement = (Element) airportNode;
                String IATA = airportElement.getAttribute("id");
                String name = airportElement.getElementsByTagName("name").item(0).getTextContent();
                String town = airportElement.getElementsByTagName("town").item(0).getTextContent();
                String country = airportElement.getElementsByTagName("country").item(0).getTextContent();

                // location
                Element locationElement = (Element) airportElement.getElementsByTagName("location").item(0);

                Double latitude = (Double.parseDouble(locationElement.getElementsByTagName("latitude").item(0).getTextContent()));
                Double longitude = (Double.parseDouble(locationElement.getElementsByTagName("longitude").item(0).getTextContent()));

                String altitude = locationElement.getElementsByTagName("altitude").item(0).getTextContent();
                Double doubleAltitude = Regex.getValue(altitude);

                // creates the airport
                this.airports.add(new Airport(name, town, country, IATA, latitude, longitude, doubleAltitude));
            }
        }
        return true;
    }

}
