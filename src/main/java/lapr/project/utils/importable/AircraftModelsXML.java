/**
 * Package location for import concepts.
 */
package lapr.project.utils.importable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.AircraftModel;
import lapr.project.model.AircraftType;
import lapr.project.model.MotorType;
import lapr.project.model.Motorization;
import lapr.project.model.ThrustFunction;
import lapr.project.utils.CustomUnits;
import lapr.project.utils.Regex;
import org.jscience.physics.amount.Amount;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class responsible to manage aircraft model import from a file.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class AircraftModelsXML implements Importable {

    /**
     * The file to import.
     */
    private File file;

    /**
     * Creates an instance of aircraft model xml.
     *
     * @param fileToImport file to import
     */
    public AircraftModelsXML(File fileToImport) throws IllegalArgumentException {

        String filename = fileToImport.getName();
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1 || !filename.substring(dotIndex).equalsIgnoreCase(".xml")) {
            throw new IllegalArgumentException("File is not a xml");
        }

        file = fileToImport;
    }

    @Override
    public Object importFile() throws ParserConfigurationException, SAXException, IOException {

        List<AircraftModel> aircraftModels = new ArrayList<>();

        // set up dom
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
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

                String cruiseAltitudeContentor = motorizationElement.getElementsByTagName("cruise_altitude").item(0).getTextContent();
                double cruiseAltitudeValue = Regex.getValue(cruiseAltitudeContentor);
                Unit cruiseAltitudeUnit = Regex.getUnit(cruiseAltitudeContentor).equalsIgnoreCase("SI") ? SI.METER : NonSI.FOOT;

                String cruiseSpeedContentor = motorizationElement.getElementsByTagName("cruise_speed").item(0).getTextContent();
                double cruiseSpeedValue = Regex.getValue(cruiseSpeedContentor);
                Unit cruiseSpeedUnit = NonSI.MACH;

                String tsfcContentor = motorizationElement.getElementsByTagName("TSFC").item(0).getTextContent();
                double tsfcValue = Regex.getValue(tsfcContentor);
                Unit tsfcUnit = Regex.getUnit(tsfcContentor).equalsIgnoreCase("SI") ? CustomUnits.TSFC_NNS : CustomUnits.TSFC_US;

                double lapseRateFactor = Double.parseDouble(motorizationElement.getElementsByTagName("lapse_rate_factor").item(0).getTextContent());

                // set up the thrust function
                Element thrustFunctionElement = (Element) motorizationElement.getElementsByTagName("thrust_function").item(0);

                String thrust0Contentor = thrustFunctionElement.getElementsByTagName("thrust_0").item(0).getTextContent();
                double thrust0Value = Regex.getValue(thrust0Contentor);
                Unit thrust0Unit = Regex.getUnit(thrust0Contentor).equalsIgnoreCase("SI") ? SI.NEWTON : NonSI.POUND_FORCE;

                String thrustMaxSpeedContentor = thrustFunctionElement.getElementsByTagName("thrust_max_speed").item(0).getTextContent();
                double thrustMaxSpeedValue = Regex.getValue(thrustMaxSpeedContentor);
                Unit thrustMaxSpeedUnit = Regex.getUnit(thrustMaxSpeedContentor).equalsIgnoreCase("SI") ? SI.NEWTON : NonSI.POUND_FORCE;

                String maxSpeedContentor = thrustFunctionElement.getElementsByTagName("max_speed").item(0).getTextContent();
                double maxSpeedValue = Regex.getValue(maxSpeedContentor);
                Unit maxSpeedUnit = NonSI.MACH;

                ThrustFunction thrustFunction = new ThrustFunction(
                        Amount.valueOf(thrust0Value, thrust0Unit),
                        Amount.valueOf(thrustMaxSpeedValue, thrustMaxSpeedUnit),
                        Amount.valueOf(maxSpeedValue, maxSpeedUnit));

                Motorization motorization = new Motorization(
                        numberOfMotors,
                        motor,
                        motorType,
                        Amount.valueOf(cruiseAltitudeValue, cruiseAltitudeUnit),
                        Amount.valueOf(cruiseSpeedValue, cruiseSpeedUnit),
                        Amount.valueOf(tsfcValue, tsfcUnit),
                        Amount.valueOf(lapseRateFactor, Unit.ONE),
                        thrustFunction);

                // back to aircraft
                String emptyWeightContentor = aircraftElement.getElementsByTagName("EWeight").item(0).getTextContent();
                double emptyWeightValue = Regex.getValue(emptyWeightContentor);
                Unit emptyWeightUnit = Regex.getUnit(emptyWeightContentor).equalsIgnoreCase("SI") ? SI.KILOGRAM : NonSI.POUND;

                String mtowContentor = aircraftElement.getElementsByTagName("MTOW").item(0).getTextContent();
                double mtowValue = Regex.getValue(mtowContentor);
                Unit mtowUnit = Regex.getUnit(mtowContentor).equalsIgnoreCase("SI") ? SI.KILOGRAM : NonSI.POUND;

                String maxPayloadContentor = aircraftElement.getElementsByTagName("max_payload").item(0).getTextContent();
                double maxPayloadValue = Regex.getValue(maxPayloadContentor);
                Unit maxPayloadUnit = Regex.getUnit(maxPayloadContentor).equalsIgnoreCase("SI") ? SI.KILOGRAM : NonSI.POUND;

                String fuelCapacityContentor = aircraftElement.getElementsByTagName("fuel_capacity").item(0).getTextContent();
                double fuelCapacityValue = Regex.getValue(fuelCapacityContentor);
                Unit fuelCapacityUnit = Regex.getUnit(fuelCapacityContentor).equalsIgnoreCase("SI") ? SI.CUBIC_METRE : NonSI.GALLON_LIQUID_US;

                String vmoContentor = aircraftElement.getElementsByTagName("VMO").item(0).getTextContent();
                double vmoValue = Regex.getValue(vmoContentor);
                Unit vmoUnit = NonSI.KNOT;

                String mmoContentor = aircraftElement.getElementsByTagName("MMO").item(0).getTextContent();
                double mmoValue = Regex.getValue(mmoContentor);
                Unit mmoUnit = NonSI.MACH;

                String wingAreaContentor = aircraftElement.getElementsByTagName("wing_area").item(0).getTextContent();
                double wingAreaValue = Regex.getValue(wingAreaContentor);
                Unit wingAreaUnit = Regex.getUnit(wingAreaContentor).equalsIgnoreCase("SI") ? SI.SQUARE_METRE : CustomUnits.SQUARE_FOOT;

                String wingSpanContentor = aircraftElement.getElementsByTagName("wing_span").item(0).getTextContent();
                double wingSpanValue = Regex.getValue(wingSpanContentor);
                Unit wingSpanUnit = Regex.getUnit(wingSpanContentor).equalsIgnoreCase("SI") ? SI.METER : NonSI.FOOT;

                double aspectRatio = Double.parseDouble(aircraftElement.getElementsByTagName("aspect_ratio").item(0).getTextContent());

                double e = Double.parseDouble(aircraftElement.getElementsByTagName("e").item(0).getTextContent());

                // set up cdrag function
                double[][] cdragFunction; //first column is speed in mack, second column is cdrag0
                NodeList cdragFunctionNodeList = aircraftElement.getElementsByTagName("iten");
                int cdragFunctionLength = cdragFunctionNodeList.getLength();
                cdragFunction = new double[cdragFunctionLength][2];

                for (int j = 0; j < cdragFunctionLength; j++) {
                    Node cdragFunctionNode = cdragFunctionNodeList.item(j);
                    if (cdragFunctionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element cdragFunctionElement = (Element) cdragFunctionNode;

                        String speedContentor = cdragFunctionElement.getElementsByTagName("speed").item(0).getTextContent();
                        cdragFunction[j][0] = Regex.getValue(speedContentor);
                        cdragFunction[j][1] = Double.parseDouble(cdragFunctionElement.getElementsByTagName("Cdrag_0").item(0).getTextContent());
                    }
                }

                // TODO add the aircraft to the list
                aircraftModels.add(new AircraftModel(
                        modelID,
                        description,
                        maker,
                        type,
                        motorization,
                        Amount.valueOf(emptyWeightValue, emptyWeightUnit),
                        Amount.valueOf(mtowValue, mtowUnit),
                        Amount.valueOf(maxPayloadValue, maxPayloadUnit),
                        Amount.valueOf(fuelCapacityValue, fuelCapacityUnit),
                        Amount.valueOf(vmoValue, vmoUnit),
                        Amount.valueOf(mmoValue, mmoUnit),
                        Amount.valueOf(wingAreaValue, wingAreaUnit),
                        Amount.valueOf(wingSpanValue, wingSpanUnit),
                        Amount.valueOf(aspectRatio, Unit.ONE),
                        Amount.valueOf(e, Unit.ONE),
                        cdragFunction));

            }
        }

        return aircraftModels;
    }

}
