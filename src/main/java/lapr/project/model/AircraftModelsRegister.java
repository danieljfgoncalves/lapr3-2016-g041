/*
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.utils.CustomUnits;
import lapr.project.utils.Importable;
import lapr.project.utils.Regex;
import org.jscience.physics.amount.Amount;
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
     * Creates an instance of aircraft model register copying another aircraft
     * register.
     *
     * @param otherAircraftModelsRegister another aircraft register
     */
    public AircraftModelsRegister(AircraftModelsRegister otherAircraftModelsRegister) {
        this.aircraftModels = new ArrayList<>(otherAircraftModelsRegister.aircraftModels);
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
        NodeList aircraftsNodeList = doc.getElementsByTagName("aircraft");
        int aircraftsLength = aircraftsNodeList.getLength();
        for (int i = 0; i < aircraftsLength; i++) {

            Node aircraftNode = aircraftsNodeList.item(i);
            if (aircraftNode.getNodeType() == Node.ELEMENT_NODE) {

                Element aircraftElement = (Element) aircraftNode;
                String modelID = aircraftElement.getAttribute("model_ID");
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
                NodeList regimesNodeList = motorizationElement.getElementsByTagName("regime");
                int regimesLength = regimesNodeList.getLength();
                for (int j = 0; j < regimesLength; j++) {
                    Node regimeNode = regimesNodeList.item(i);
                    if (regimeNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element regimeElement = (Element) regimeNode;
                        String regimeID = regimeElement.getAttribute("ID");

                        String tsfcContentor = regimeElement.getElementsByTagName("TSFC").item(0).getTextContent();
                        double tsfcValue = Regex.getValue(tsfcContentor);
                        Unit tsfcUnit = (Regex.getUnit(tsfcContentor).equalsIgnoreCase("SI")) ? CustomUnits.TSFC_SI : CustomUnits.TSFC_US;
                        String speedContentor = regimeElement.getElementsByTagName("speed").item(0).getTextContent();
                        double speedValue = Regex.getValue(speedContentor);
                        String thrustContentor = regimeElement.getElementsByTagName("thrust").item(0).getTextContent();
                        double thrustValue = Regex.getValue(thrustContentor);
                        Unit thrustUnit = Regex.getUnit(thrustContentor).equalsIgnoreCase("SI") ? SI.NEWTON : NonSI.POUND_FORCE;
                        String altitudeContentor = regimeElement.getElementsByTagName("altitude").item(0).getTextContent();
                        double altitudeValue = Regex.getValue(altitudeContentor);

                        regimes.add(new Regime(regimeID, Amount.valueOf(tsfcValue, tsfcUnit), Amount.valueOf(speedValue, NonSI.MACH),
                                Amount.valueOf(thrustValue, thrustUnit), Amount.valueOf(altitudeValue, NonSI.FOOT)));
                    }
                }
                Motorization motorization = new Motorization(numberOfMotors, motor, motorType, regimes);

                String emptyWeightContentor = aircraftElement.getElementsByTagName("EWeight").item(0).getTextContent();
                double emptyWeightValue = Regex.getValue(emptyWeightContentor);
                Unit emptyWeightUnit = Regex.getUnit(emptyWeightContentor).equalsIgnoreCase("SI") ? SI.KILOGRAM : NonSI.POUND;

                String mtowContentor = aircraftElement.getElementsByTagName("MTOW").item(0).getTextContent();
                double mtowValue = Regex.getValue(mtowContentor);
                Unit mtowUnit = Regex.getUnit(mtowContentor).equalsIgnoreCase("SI") ? SI.KILOGRAM : NonSI.POUND;

                String mzfwContentor = aircraftElement.getElementsByTagName("MZFW").item(0).getTextContent();
                double mzfwValue = Regex.getValue(mzfwContentor);
                Unit mzfwUnit = Regex.getUnit(mzfwContentor).equalsIgnoreCase("SI") ? SI.KILOGRAM : NonSI.POUND;

                String maxPayloadContentor = aircraftElement.getElementsByTagName("max_payload").item(0).getTextContent();
                double maxPayloadValue = Regex.getValue(maxPayloadContentor);
                Unit maxPayloadUnit = Regex.getUnit(maxPayloadContentor).equalsIgnoreCase("SI") ? SI.KILOGRAM : NonSI.POUND;

                String fuelCapacityContentor = aircraftElement.getElementsByTagName("fuel_capacity").item(0).getTextContent();
                double fuelCapacityValue = Regex.getValue(fuelCapacityContentor);
                Unit fuelCapacityUnit = Regex.getUnit(fuelCapacityContentor).equalsIgnoreCase("SI") ? SI.CUBIC_METRE : NonSI.GALLON_LIQUID_US;

                String vmoContentor = aircraftElement.getElementsByTagName("VMO").item(0).getTextContent();
                double vmoValue = Regex.getValue(vmoContentor);

                String mmoContentor = aircraftElement.getElementsByTagName("MMO").item(0).getTextContent();
                double mmoValue = Regex.getValue(mmoContentor);

                String wingAreaContentor = aircraftElement.getElementsByTagName("wing_area").item(0).getTextContent();
                double wingAreaValue = Regex.getValue(wingAreaContentor);
                Unit wingAreaUnit = Regex.getUnit(wingAreaContentor).equalsIgnoreCase("SI") ? SI.SQUARE_METRE : CustomUnits.SQUARE_FOOT;

                String wingSpanContentor = aircraftElement.getElementsByTagName("wing_span").item(0).getTextContent();
                double wingSpanValue = Regex.getValue(wingSpanContentor);
                Unit wingSpanUnit = Regex.getUnit(wingSpanContentor).equalsIgnoreCase("SI") ? SI.METER : NonSI.FOOT;

                double dragCoeficient = Double.parseDouble(aircraftElement.getElementsByTagName("Cdrag_0").item(0).getTextContent());

                double e = Double.parseDouble(aircraftElement.getElementsByTagName("e").item(0).getTextContent());

                // cretes the aircraft with all the gathered information
                this.aircraftModels.add(new AircraftModel(modelID, type,
                        Amount.valueOf(emptyWeightValue, emptyWeightUnit), Amount.valueOf(mtowValue, mtowUnit),
                        Amount.valueOf(mzfwValue, mzfwUnit), Amount.valueOf(wingAreaValue, wingAreaUnit),
                        motorization, description, maker, Amount.valueOf(maxPayloadValue, maxPayloadUnit),
                        Amount.valueOf(fuelCapacityValue, fuelCapacityUnit), Amount.valueOf(vmoValue, NonSI.KNOT),
                        Amount.valueOf(mmoValue, NonSI.MACH), Amount.valueOf(wingSpanValue, wingSpanUnit),
                        Amount.valueOf(dragCoeficient, Unit.ONE), Amount.valueOf(e, Unit.ONE)));
            }
        }

        return true;
    }
}
