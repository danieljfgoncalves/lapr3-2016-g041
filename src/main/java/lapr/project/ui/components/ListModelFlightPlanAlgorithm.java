/*
 * Package location for UI components.
 */
package lapr.project.ui.components;

import java.util.List;
import javax.swing.AbstractListModel;
import lapr.project.model.FlightPlanAlgorithm;

/**
 * List model for flight plan algorithms.
 *
 * @author Daniel Gonçalves - 1151452
 * @author Eric Amaral - 1141570
 * @author Ivo Ferro - 1151159
 * @author Tiago Correia - 1151031
 */
public class ListModelFlightPlanAlgorithm extends AbstractListModel<FlightPlanAlgorithm> {

    /**
     * Flight plan algorithms.
     */
    private final List<FlightPlanAlgorithm> flightPlanAlgorithms;

    /**
     * Creates an instance of list model flight plan algorithm.
     *
     * @param flightPlanAlgorithms flight plan algorithms.
     */
    public ListModelFlightPlanAlgorithm(List<FlightPlanAlgorithm> flightPlanAlgorithms) {
        this.flightPlanAlgorithms = flightPlanAlgorithms;
    }

    @Override
    public int getSize() {
        return this.flightPlanAlgorithms.size();
    }

    @Override
    public FlightPlanAlgorithm getElementAt(int i) {
        return this.flightPlanAlgorithms.get(i);
    }

}
