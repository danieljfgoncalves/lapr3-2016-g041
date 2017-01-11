/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the flight info.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class FlightInfo implements Comparable<FlightInfo> {

    /**
     * FlightInfo type
     */
    private FlightType flightType;

    /**
     * Unique flight designator
     */
    private String designator;

    /**
     * FlightInfo's aircraft
     */
    private Aircraft aircraft;

    /**
     * FlightInfo's airport of origin
     */
    private Airport originAirport;

    /**
     * FlightInfo's airport of destination
     */
    private Airport destinationAirport;

    /**
     * FlightInfo's ordered list of stops
     */
    private List<Stop> stops;

    /**
     * FlightInfo's planned mandatory.
     */
    private List<Coordinate> waypoints;

    /**
     * Default flight type.
     */
    private static final FlightType DEFAULT_FLIGHT_TYPE = FlightType.CHARTER;

    /**
     * Default flight designator.
     */
    private static final String DEFAULT_DESIGNATOR = "YY0001a";

    /**
     * Constructs an empty air network
     */
    public FlightInfo() {

        this.flightType = DEFAULT_FLIGHT_TYPE;
        this.designator = DEFAULT_DESIGNATOR;
        this.originAirport = new Airport();
        this.destinationAirport = new Airport();
        this.aircraft = new Aircraft();
        this.waypoints = new LinkedList<>();
        this.stops = new LinkedList<>();
    }

    /**
     * Constructs an air network receiving a created netwrok by param.
     *
     * @param type flight type
     * @param designator flight designator
     * @param originAirport airport of origin
     * @param destinationAirport airport of destination
     * @param aircraft aircraft of flight
     * @param waypoints flight plan
     * @param stops stops of flight
     */
    public FlightInfo(FlightType type, String designator, Airport originAirport,
            Airport destinationAirport, Aircraft aircraft, List waypoints, List stops) {

        this.flightType = type;
        this.designator = designator;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.aircraft = aircraft;
        this.waypoints = new LinkedList<>(waypoints);
        this.stops = new LinkedList<>(stops);
    }

    /**
     * Constructs a air network by coping from another air network
     *
     * @param other the other airnetwrok to copy
     */
    public FlightInfo(FlightInfo other) {
        this.flightType = other.flightType;
        this.designator = other.designator;
        this.originAirport = other.originAirport;
        this.destinationAirport = other.destinationAirport;
        this.aircraft = other.aircraft;
        this.waypoints = new LinkedList<>(other.getWaypoints());
        this.stops = new LinkedList<>(other.getStops());
    }

    /**
     * Obtain's FlightInfo type
     *
     * @return the flightType
     */
    public FlightType getFlightType() {
        return flightType;
    }

    /**
     * Set's FlightInfo type
     *
     * @param flightType the flightType to set
     */
    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    /**
     * Obtain's Unique flight designator
     *
     * @return the designator
     */
    public String getDesignator() {
        return designator;
    }

    /**
     * Sets's Unique flight designator
     *
     * @param designator the designator to set
     */
    public void setDesignator(String designator) {
        this.designator = designator;
    }

    /**
     * Obtain's FlightInfo's aircraft
     *
     * @return the aircraft
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * Set's FlightInfo's aircraft
     *
     * @param aircraft the aircraft to set
     */
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    /**
     * Obtain's FlightInfo's airport of origin.
     *
     * @return the airpot of origin
     */
    public Airport getOriginAirport() {
        return originAirport;
    }

    /**
     * Set's FlightInfo's airport of origin
     *
     * @param originAirport the airport to set
     */
    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    /**
     * Obtain's FlightInfo's airport of destination
     *
     * @return the destination Airport
     */
    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Set's FlightInfo's airport of destination
     *
     * @param destinationAirport the airport to set
     */
    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * Obtain's FlightInfo's ordered list of stops
     *
     * @return the stops
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * Set's FlightInfo's ordered list of stops
     *
     * @param stops the stops to set
     */
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    /**
     * Obtain's FlightInfo plan with ordered list of segments
     *
     * @return the flightPlan
     */
    public List<Coordinate> getWaypoints() {
        return waypoints;
    }

    /**
     * Set's FlightInfo plan with ordered list of coordinates
     *
     * @param waypoints the list of waypoints to set
     */
    public void setWaypoints(List<Coordinate> waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.designator);
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

        final FlightInfo other = (FlightInfo) obj;

        return this.designator.equalsIgnoreCase(other.designator);
    }

    @Override
    public String toString() {

        StringBuilder flightWaypoints = new StringBuilder();
        waypoints.stream().forEach((Coordinate coordinate) -> {
            flightWaypoints.append(coordinate).append(",%n");
        });

        StringBuilder stopsString = new StringBuilder();
        stops.stream().forEach((Stop stop) -> {
            stopsString.append(stop).append(",%n");
        });

        return String.format("Flight{%nfightType=%s, designator=%s,"
                + "origin=%s, destination=%s,%naircraft=%s,%n"
                + "%nstops=%n%s,%nWaypoints=%n",
                flightType, designator, originAirport, destinationAirport, aircraft, stopsString, flightWaypoints);
    }

    @Override
    public int compareTo(FlightInfo otherFlight) {
        return this.designator.compareToIgnoreCase(otherFlight.designator);
    }
}
