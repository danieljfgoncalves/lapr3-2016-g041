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
public class AircraftModelsRegister implements Importable {

    /**
     * The list of aircraft models.
     */
    private List<AircraftModel> aircraftModels;

    /**
     * Creates an instance of aircraft models register with their default
     * values.
     */
    public AircraftModelsRegister() {
        this.aircraftModels = new ArrayList<>();
    }

    /**
     * Creates an instance of aircraft models register receiving the aircraft
     * models.
     *
     * @param aircraftModels aircraft models
     */
    public AircraftModelsRegister(List<AircraftModel> aircraftModels) {
        this.aircraftModels = aircraftModels;
    }

    /**
     * Gets the aircraft models.
     *
     * @return aircraft models
     */
    public List<AircraftModel> getAircraftModels() {
        return aircraftModels;
    }

    /**
     * Sets the aircraft models.
     *
     * @param aircraftModels aircraft models
     */
    public void setAircraftModels(List<AircraftModel> aircraftModels) {
        this.aircraftModels = aircraftModels;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.aircraftModels);
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

        final AircraftModelsRegister other = (AircraftModelsRegister) obj;
        return this.aircraftModels.equals(other.aircraftModels);
    }

    @Override
    public String toString() {
        return String.format("AircraftModelsRegister{aircraftModels=%s}", this.aircraftModels);
    }

    @Override
    public boolean importXml(File fileToImport) throws SAXException, IOException, ParserConfigurationException {

        // set up dom
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileToImport);
        doc.getDocumentElement().normalize();

        // iterate aircraft dom
        NodeList aircraftsNodeList = doc.getElementsByTagName("aircraft_list");
        int aircraftsLength = aircraftsNodeList.getLength();
        for (int i = 0; i < aircraftsLength; i++) {

            Node aircraftNode = aircraftsNodeList.item(i);
            if (aircraftNode.getNodeType() == Node.ELEMENT_NODE) {

                Element aircraftElement = (Element) aircraftNode;
                Integer modelID = Integer.parseInt(aircraftElement.getAttribute("model_ID"));
                String description = aircraftElement.getAttribute("description");
                String maker = aircraftElement.getElementsByTagName("maker").item(0).getTextContent();

                AircraftType type = null;
                String typeStr = aircraftElement.getElementsByTagName("type").item(0).getTextContent().toLowerCase();
                switch (typeStr) {
                    case "passenger":
                        type = AircraftType.PASSENGER;
                        break;
                    case "cargo":
                        type = AircraftType.CARGO;
                        break;
                    case "mixed":
                        type = AircraftType.MIXED;
                        break;
                }

                // set up the motorization
                Element motorizationElement = (Element) aircraftElement.getElementsByTagName("motorization").item(0);

                Integer numberOfMotors = Integer.parseInt(motorizationElement.getElementsByTagName("number_motors").item(0).getTextContent());
                String motor = motorizationElement.getElementsByTagName("motor").item(0).getTextContent();

                MotorType motorType = null;
                String motorTypeStr = motorizationElement.getElementsByTagName("motor_type").item(0).getTextContent();
                switch (motorTypeStr) {
                    case "turbofan":
                        motorType = MotorType.TURBOFAN;
                        break;
                    case "turboprop":
                        motorType = MotorType.TURBOPROP;
                        break;
                    case "turbojet":
                        motorType = MotorType.TURBOJET;
                        break;
                }

                List<Regime> regimes = new ArrayList<>();
                NodeList regimesNodeList = motorizationElement.getElementsByTagName("aircraft_list");
                int regimesLength = regimesNodeList.getLength();
                for (int j = 0; j < regimesLength; j++) {
                    Node regimeNode = aircraftsNodeList.item(i);
                    if (regimeNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element regimeElement = (Element) regimeNode;
                        String regimeID = regimeElement.getAttribute("ID");

                        String tsfcContentor = regimeElement.getElementsByTagName("TSFC").item(0).getTextContent();
                        Double tsfcValue = Regex.getValue(tsfcContentor);
                        //String tsfcUnit = Regex.getUnit(tsfcContentor);
                        String speedContentor = regimeElement.getElementsByTagName("speed").item(0).getTextContent();
                        Double speedValue = Regex.getValue(speedContentor);
                        //String speedUnit = Regex.getUnit(speedContentor);
                        String thrustContentor = regimeElement.getElementsByTagName("thrust").item(0).getTextContent();
                        Double thrustValue = Regex.getValue(thrustContentor);
                        //String thrustUnit = Regex.getUnit(thrustContentor);
                        String altitudeContentor = regimeElement.getElementsByTagName("altitude").item(0).getTextContent();
                        Double altitudeValue = Regex.getValue(altitudeContentor);
                        //String altitudeUnit = Regex.getUnit(altitudeContentor);

                        regimes.add(new Regime(regimeID, tsfcValue, speedValue, thrustValue, altitudeValue));
                    }
                }
                Motorization motorization = new Motorization(numberOfMotors, motor, motorType, regimes);

                String emptyWeightContentor = aircraftElement.getElementsByTagName("EWeight").item(0).getTextContent();
                Double emptyWeightValue = Regex.getValue(emptyWeightContentor);
                //String emptyWeightUnit = Regex.getUnit(emptyWeightContentor);
                String mtowContentor = aircraftElement.getElementsByTagName("MTOW").item(0).getTextContent();
                Double mtowValue = Regex.getValue(mtowContentor);
                //String mtowUnit = Regex.getUnit(mtowContentor);
                String mzfwContentor = aircraftElement.getElementsByTagName("MZFW").item(0).getTextContent();
                Double mzfwValue = Regex.getValue(mzfwContentor);
                //String mzfwUnit = Regex.getUnit(mzfwContentor);
                String maxPlayloadContentor = aircraftElement.getElementsByTagName("max_payload").item(0).getTextContent();
                Double maxPlayloadValue = Regex.getValue(maxPlayloadContentor);
                //String maxPlayloadUnit = Regex.getUnit(maxPlayloadContentor);
                String fuelCapacityContentor = aircraftElement.getElementsByTagName("fuel_capacity").item(0).getTextContent();
                Double fuelCapacityValue = Regex.getValue(fuelCapacityContentor);
                //String fuelCapacityUnit = Regex.getUnit(fuelCapacityContentor);
                String vmoContentor = aircraftElement.getElementsByTagName("VMO").item(0).getTextContent();
                Double vmoValue = Regex.getValue(vmoContentor);
                //String vmoUnit = Regex.getUnit(vmoContentor);
                String mmoContentor = aircraftElement.getElementsByTagName("MMO").item(0).getTextContent();
                Double mmoValue = Regex.getValue(mmoContentor);
                //String mmoUnit = Regex.getUnit(mmoContentor);
                String wingAreaContentor = aircraftElement.getElementsByTagName("wing_area").item(0).getTextContent();
                Double wingAreaValue = Regex.getValue(wingAreaContentor);
                //String wingAreaUnit = Regex.getUnit(wingAreaContentor);
                String wingSpanContentor = aircraftElement.getElementsByTagName("wing_span").item(0).getTextContent();
                Double wingSpanValue = Regex.getValue(wingSpanContentor);
                //String wingSpanUnit = Regex.getUnit(wingSpanContentor);

                //Double dragCoeficient = Double.parseDouble(aircraftElement.getElementsByTagName("Cdrag_0").item(0).getTextContent());
                Double e = Double.parseDouble(aircraftElement.getElementsByTagName("e").item(0).getTextContent());

                // cretes the aircraft with all the gathered information
                this.aircraftModels.add(new AircraftModel(modelID, type,
                        emptyWeightValue, mtowValue, mzfwValue, wingAreaValue,
                        motorization, description, maker, maxPlayloadValue,
                        fuelCapacityValue, vmoValue, mmoValue, wingSpanValue, e));
            }
        }

        return true;
    }
}
