/**
 * Package location for Pure Fabrication util classes.
 */
package lapr.project.utils;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.datalayer.DbConnection;
import lapr.project.model.AircraftType;
import lapr.project.model.MotorType;
import org.jscience.physics.amount.Amount;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class responsible to import items.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class Import {

    /**
     * Imports the aircraft models from xml, saving them on database.
     *
     * @param xmlFile XML file with the aircraft models
     * @param projectSerieNumber the project serie number (id_project)
     * @return true if it is successfully imported, false otherwise
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.sql.SQLException
     */
    public static boolean importAircraftModelsFromXml(File xmlFile, int projectSerieNumber) throws SAXException, IOException, ParserConfigurationException, SQLException {

        // set up dom
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
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
                Unit tsfcUnit = Regex.getUnit(tsfcContentor).equalsIgnoreCase("SI") ? CustomUnits.TSFC_SI : CustomUnits.TSFC_US;

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

                // TODO test this
                addAircraftModelToDatabase(projectSerieNumber, modelID, description, 
                        maker, type, motor, numberOfMotors, motorType, 
                        Amount.valueOf(cruiseAltitudeValue, cruiseAltitudeUnit).doubleValue(SI.METER), 
                        Amount.valueOf(cruiseSpeedValue, cruiseSpeedUnit).doubleValue(SI.METERS_PER_SECOND), 
                        Amount.valueOf(tsfcValue, tsfcUnit).doubleValue(CustomUnits.TSFC_SI),
                        lapseRateFactor,
                        Amount.valueOf(thrust0Value, thrust0Unit).doubleValue(SI.NEWTON), 
                        Amount.valueOf(thrustMaxSpeedValue, thrustMaxSpeedUnit).doubleValue(SI.NEWTON),
                        Amount.valueOf(maxSpeedValue, maxSpeedUnit).doubleValue(SI.METERS_PER_SECOND), 
                        Amount.valueOf(emptyWeightValue, emptyWeightUnit).doubleValue(SI.KILOGRAM),
                        Amount.valueOf(mtowValue, mtowUnit).doubleValue(SI.KILOGRAM),
                        Amount.valueOf(maxPayloadValue, maxPayloadUnit).doubleValue(SI.KILOGRAM),
                        Amount.valueOf(fuelCapacityValue, fuelCapacityUnit).doubleValue(SI.CUBIC_METRE),
                        Amount.valueOf(vmoValue, vmoUnit).doubleValue(SI.METERS_PER_SECOND),
                        Amount.valueOf(mmoValue, mmoUnit).doubleValue(SI.METERS_PER_SECOND),
                        Amount.valueOf(wingAreaValue, wingAreaUnit).doubleValue(SI.SQUARE_METRE),
                        Amount.valueOf(wingSpanValue, wingSpanUnit).doubleValue(SI.METER),
                        aspectRatio, e);

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

                        // TODO test this
                        addCdragFunctionToDatabase(cdragFunction[j][0], cdragFunction[j][1], modelID);
                    }
                }
            }
        }

        return true;
    }

    /**
     * Imports the airports from xml, saving them on database.
     *
     * @param xmlFile XML file with the airports
     * @return true if it is successfully imported, false otherwise
     */
    public static boolean importAirportsFromXml(File xmlFile) {
        // TODO implement this
        return true;
    }

    /**
     * Imports the air network from xml, saving them on database.
     *
     * @param xmlFile XML file with the air network
     * @return true if it is successfully imported, false otherwise
     */
    public static boolean importAirNetworkFromXml(File xmlFile) {
        // TODO implement this
        return true;
    }

    /**
     * Adds the aircraft model to database.
     */
    private static void addAircraftModelToDatabase(
            int idProject, String idAircraftModel, String description,
            String maker, AircraftType aircraftType, String motor,
            int numberMotors, MotorType motorType, double cruiseAltitude,
            double cruiseSpeed, double tsfc, double lapseRateFactor,
            double thrust0, double thrustMaxSped, double maxSpeed,
            double emptyWeight, double mtow, double maxPayLoad,
            double fuelCapacity, double vmo, double mmo,
            double wingArea, double wingSpan, double aspectRatio,
            double e
    ) throws SQLException {
        String query = "{call PC_CREATE_AIRCRAFT_MODEL "
                + "(?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setDouble(1, idProject);
            callableStatement.setString(2, idAircraftModel);
            callableStatement.setString(3, description);
            callableStatement.setString(4, maker);
            callableStatement.setString(5, aircraftType.equals(AircraftType.PASSENGER)
                    ? "Passenger" : aircraftType.equals(AircraftType.CARGO) ? "Cargo" : "Mixed");
            callableStatement.setString(6, motor);
            callableStatement.setDouble(7, numberMotors);
            callableStatement.setString(8, motorType.equals(MotorType.TURBOFAN)
                    ? "Turbofan" : motorType.equals(MotorType.TURBOJET) ? "Turbojet" : "Turboprop");
            callableStatement.setDouble(9, cruiseAltitude);
            callableStatement.setDouble(10, cruiseSpeed);
            callableStatement.setDouble(11, tsfc);
            callableStatement.setDouble(12, lapseRateFactor);
            callableStatement.setDouble(13, thrust0);
            callableStatement.setDouble(14, thrustMaxSped);
            callableStatement.setDouble(15, maxSpeed);
            callableStatement.setDouble(16, emptyWeight);
            callableStatement.setDouble(17, mtow);
            callableStatement.setDouble(18, maxPayLoad);
            callableStatement.setDouble(19, fuelCapacity);
            callableStatement.setDouble(20, vmo);
            callableStatement.setDouble(21, mmo);
            callableStatement.setDouble(22, wingArea);
            callableStatement.setDouble(23, wingSpan);
            callableStatement.setDouble(24, aspectRatio);
            callableStatement.setDouble(25, e);
            callableStatement.executeUpdate();
        }
    }

    /**
     * Adds a cdrag function to database.
     */
    private static void addCdragFunctionToDatabase(double cdrag0, double speed, String idAircraftModel) throws SQLException {
        String query = "{call PC_CREATE_CDRAG_FUNCTION (?, ?, ?)}";

        try (Connection connection = DbConnection.getConnection(); CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setDouble(1, cdrag0);
            callableStatement.setDouble(2, speed);
            callableStatement.setString(3, idAircraftModel);
            callableStatement.executeUpdate();
        }
    }
}
