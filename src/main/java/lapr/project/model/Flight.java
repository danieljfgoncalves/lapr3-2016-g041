/**
 * Package location for Model concepts.
 */
package lapr.project.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a flight.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author João Pereira - 1151241
 * @author Tiago Correia - 1151031
 */
public class Flight {

    /**
     * Flight type
     */
    private FlightType flightType;

    /**
     * Unique flight designator
     */
    private String designator;

    /**
     * Airline company of flight
     */
    private String airline;

    /**
     * Flight's aircraft
     */
    private Aircraft aircraft;

    /**
     * Flight's airport of origin
     */
    private Airport origin;

    /**
     * Flight's airport of destination
     */
    private Airport destination;

    /**
     * Flight's departure Date (If a regular flight only a date)
     */
    private Calendar departureDate;

    /**
     * Flight's scheduled arrival time
     */
    private Calendar scheduledArrival;

    /**
     * Flight's ordered list of stops
     */
    private List<Stop> stops;

    /**
     * Flight plan with ordered list of segments
     */
    private List<Segment> flightPlan;

    /**
     * Default flight type.
     */
    private static final FlightType DEFAULT_FLIGHT_TYPE = FlightType.CHARTER;

    /**
     * Default flight designator.
     */
    private static final String DEFAULT_DESIGNATOR = "YY0001a";

    /**
     * Default airline.
     */
    private static final String DEFAULT_AIRLINE = "default airline";

    /**
     * Constructs an empty air network
     */
    public Flight() {

        this.flightType = DEFAULT_FLIGHT_TYPE;
        this.designator = DEFAULT_DESIGNATOR;
        this.airline = DEFAULT_AIRLINE;
        this.origin = new Airport();
        this.destination = new Airport();
        this.aircraft = new Aircraft();
        this.departureDate = new GregorianCalendar();
        this.scheduledArrival = new GregorianCalendar();
        this.flightPlan = new LinkedList<>();
        this.stops = new LinkedList<>();
    }

    /**
     * Constructs an air network receiving a created netwrok by param.
     *
     * @param type flight type
     * @param designator flight designator
     * @param airline airline company
     * @param origin orgin's airport
     * @param destination destination's origin
     * @param aircraft aircraft of flight
     * @param departureDate departure date (Week day for regular flights)
     * @param scheduledArrival schedule arrival time
     * @param flightPlan flight plan
     * @param stops stops of flight
     */
    public Flight(FlightType type, String designator, String airline, Airport origin,
            Airport destination, Aircraft aircraft, Calendar departureDate, Calendar scheduledArrival, List flightPlan, List stops) {

        this.flightType = type;
        this.designator = designator;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.aircraft = aircraft;
        this.departureDate = departureDate;
        this.scheduledArrival = scheduledArrival;
        this.flightPlan = new LinkedList<>(flightPlan);
        this.stops = new LinkedList<>(stops);
    }

    /**
     * Constructs a air network by coping from another air network
     *
     * @param other the other airnetwrok to copy
     */
    public Flight(Flight other) {
        this.flightType = other.flightType;
        this.designator = other.designator;
        this.airline = other.airline;
        this.origin = other.origin;
        this.destination = other.destination;
        this.aircraft = other.aircraft;
        this.departureDate = other.departureDate;
        this.scheduledArrival = other.scheduledArrival;
        this.flightPlan = new LinkedList<>(other.getFlightPlan());
        this.stops = new LinkedList<>(other.getStops());
    }

    /**
     * Obtain's Flight type
     *
     * @return the flightType
     */
    public FlightType getFlightType() {
        return flightType;
    }

    /**
     * Set's Flight type
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
     * Obtain's Airline company of flight
     *
     * @return the airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * Set's Airline company of flight
     *
     * @param airline the airline to set
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * Obtain's Flight's aircraft
     *
     * @return the aircraft
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * Set's Flight's aircraft
     *
     * @param aircraft the aircraft to set
     */
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    /**
     * Obtain's Flight's airport of origin
     *
     * @return the origin
     */
    public Airport getOrigin() {
        return origin;
    }

    /**
     * Set's Flight's airport of origin
     *
     * @param origin the origin to set
     */
    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    /**
     * Obtain's Flight's airport of destination
     *
     * @return the destination
     */
    public Airport getDestination() {
        return destination;
    }

    /**
     * Set's Flight's airport of destination
     *
     * @param destination the destination to set
     */
    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    /**
     * Obtain's Flight's departure Date (If a regular flight only a date)
     *
     * @return the departureDate
     */
    public Calendar getDepartureDate() {
        return departureDate;
    }

    /**
     * Set's Flight's departure Date (If a regular flight only a date)
     *
     * @param departureDate the departureDate to set
     */
    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Obtain's Flight's scheduled arrival time
     *
     * @return the scheduledArrival
     */
    public Calendar getScheduledArrival() {
        return scheduledArrival;
    }

    /**
     * Set's Flight's scheduled arrival time
     *
     * @param scheduledArrival the scheduledArrival to set
     */
    public void setScheduledArrival(Calendar scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    /**
     * Obtain's Flight's ordered list of stops
     *
     * @return the stops
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * Set's Flight's ordered list of stops
     *
     * @param stops the stops to set
     */
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    /**
     * Obtain's Flight plan with ordered list of segments
     *
     * @return the flightPlan
     */
    public List<Segment> getFlightPlan() {
        return flightPlan;
    }

    /**
     * Set's Flight plan with ordered list of segments
     *
     * @param flightPlan the flightPlan to set
     */
    public void setFlightPlan(List<Segment> flightPlan) {
        this.flightPlan = flightPlan;
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

        final Flight other = (Flight) obj;

        return this.designator.equalsIgnoreCase(other.designator);
    }

    @Override
    public String toString() {

        StringBuilder fpString = new StringBuilder();
        flightPlan.stream().forEach((Segment segment) -> {
            fpString.append(segment).append(",%n");
        });

        StringBuilder stopsString = new StringBuilder();
        stops.stream().forEach((Stop stop) -> {
            stopsString.append(stop).append(",%n");
        });

        return String.format("Flight{%nfightType=%s, designator=%s, "
                + "airline=%s, origin=%s, destination=%s,%naircraft=%s,%n"
                + "departureDate=%s, scheduledArrival=%s,%nstops=%n%s,%nflight plan=%n",
                flightType, designator, airline, origin, destination, aircraft, departureDate,
                scheduledArrival, stopsString, fpString);
    }
}
