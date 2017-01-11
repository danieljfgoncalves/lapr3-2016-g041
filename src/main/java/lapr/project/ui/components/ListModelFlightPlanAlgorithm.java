/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.AbstractListModel;
import lapr.project.model.flightplan.FlightPlan;

/**
 * List model for flight plan algorithms.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ListModelFlightPlanAlgorithm extends AbstractListModel<FlightPlan> {

    /**
     * Flight plan algorithms.
     */
    private final List<FlightPlan> flightPlanAlgorithms;

    /**
     * Creates an instance of list model flight plan algorithm.
     *
     * @param flightPlanAlgorithms flight plan algorithms.
     */
    public ListModelFlightPlanAlgorithm(List<FlightPlan> flightPlanAlgorithms) {
        this.flightPlanAlgorithms = flightPlanAlgorithms;
    }

    @Override
    public int getSize() {
        return this.flightPlanAlgorithms.size();
    }

    @Override
    public FlightPlan getElementAt(int i) {
        return this.flightPlanAlgorithms.get(i);
    }

}
