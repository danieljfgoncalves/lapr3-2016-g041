/*
 * Package location for view concepts.
 */
package lapr.project.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import lapr.project.model.FlightSimulator;
import lapr.project.ui.CopyProjectDialog;
import lapr.project.ui.CreateProjectDialog;
import lapr.project.ui.EditProjectPropertiesDialog;
import lapr.project.ui.FlightInfoDialog;
import lapr.project.ui.MainFrame;

/**
 * Creates a custom JMenuBar.
 *
 * @author Eric Amaral 1141570
 * @author Daniel Gonçalves 1151452
 * @author Ivo Ferro 1151159
 * @author Tiago Correia 1151031
 */
public class CustomMenuBar extends JMenuBar {

    /**
     * The main frame.
     */
    private final MainFrame mainFrame;

    /**
     * The simulator.
     */
    private final FlightSimulator simulator;

    /**
     * Creates an instance of the CustomMenuBar.
     *
     * @param mainFrame the main frame
     * @param simulator the simulator
     */
    public CustomMenuBar(MainFrame mainFrame, FlightSimulator simulator) {

        this.mainFrame = mainFrame;
        this.simulator = simulator;

        add(createMenuFile());
        add(createMenuSimulations());
        add(createMenuView());
    }

    /**
     * Creates the file menu
     *
     * @return menu file
     */
    private JMenu createMenuFile() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);

        menu.add(createNewProjectItem());
        menu.add(createEditPropertiesItem());
        menu.add(createSwitchProjectItem());
        menu.add(createCopyProjectItem());
        menu.add(createCloseProjectItem());
        menu.add(createItemExit());
        return menu;
    }

    /**
     * Creates the simulations menu
     *
     * @return menu simulations
     */
    private JMenu createMenuSimulations() {
        JMenu menu = new JMenu("Simulations");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.add(createFlightInfoItem());
        menu.add(createSimulation1Item());
        menu.add(createSimulation2Item());
        menu.add(createSimulation3Item());
        menu.add(createSubMenuList());
        return menu;
    }

    /**
     * Creates the view menu
     *
     * @return menu view
     */
    private JMenu createMenuView() {
        JMenu menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.add(createAirportsItem());
        menu.add(createAircraftModelsItem());
        return menu;
    }

    /**
     * Creates the New Project menu item.
     *
     * @return New Project menu item
     */
    private JMenuItem createNewProjectItem() {
        JMenuItem item = new JMenuItem("New Project", 'N');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        item.addActionListener((ActionEvent e) -> {
            CreateProjectDialog createProjectDialog = new CreateProjectDialog(mainFrame, simulator);
            createProjectDialog.setVisible(true);
        });
        return item;
    }

    /**
     * Creates the Edit Properties menu item.
     *
     * @return Edit Properties menu item
     */
    private JMenuItem createEditPropertiesItem() {
        JMenuItem item = new JMenuItem("Edit Properties", 'E');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
        item.addActionListener((ActionEvent e) -> {
            if (mainFrame.getActiveProject() != null) {
                EditProjectPropertiesDialog editProjectPropertiesDialog = new EditProjectPropertiesDialog(mainFrame, simulator, mainFrame.getActiveProject());
                editProjectPropertiesDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                        "There is no project selected!\nPlease select a project first.",
                        "Invalid Project",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        return item;
    }

    /**
     * Creates the Switch Project menu item.
     *
     * @return Switch Project menu item
     */
    private JMenuItem createSwitchProjectItem() {
        JMenuItem item = new JMenuItem("Switch Project", 'O');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        item.addActionListener((ActionEvent e) -> {
            mainFrame.openProjectSelection();
        });
        return item;
    }

    /**
     * Creates the Copy Project menu item.
     *
     * @return Copy Project menu item
     */
    private JMenuItem createCopyProjectItem() {
        JMenuItem item = new JMenuItem("Copy Project", 'C');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        item.addActionListener((ActionEvent e) -> {
            if (mainFrame.getActiveProject() != null) {
                CopyProjectDialog copyProjectDialog = new CopyProjectDialog(mainFrame, simulator, mainFrame.getActiveProject());
                copyProjectDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                        "There is no project selected!\nPlease select a project first.",
                        "Invalid Project",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        return item;
    }

    /**
     * Creates the Close Project menu item.
     *
     * @return Close Project menu item
     */
    private JMenuItem createCloseProjectItem() {
        JMenuItem item = new JMenuItem("Close Project", 'L');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the Exit item.
     *
     * @return Exit item
     */
    private JMenuItem createItemExit() {
        JMenuItem item = new JMenuItem("Exit", 'Q');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));

        item.addActionListener((ActionEvent e) -> {
            // TODO
            System.exit(0);
        });

        return item;
    }

    /**
     * Creates the create flight info menu item.
     *
     * @return create flight info menu item
     */
    private JMenuItem createFlightInfoItem() {
        JMenuItem item = new JMenuItem("New Flight Info", 'I');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
        item.addActionListener((ActionEvent e) -> {
            if (mainFrame.getActiveProject() != null) {
                FlightInfoDialog flightInfoDialog = new FlightInfoDialog(mainFrame, mainFrame.getActiveProject());
                flightInfoDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(mainFrame,
                        "There is no project selected!\nPlease select a project first.",
                        "Invalid Project",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        return item;
    }

    /**
     * Creates the Simulation 1 menu item.
     *
     * @return Simulation 1 menu item
     */
    private JMenuItem createSimulation1Item() {
        JMenuItem item = new JMenuItem("Simulation 1", '1');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl 1"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the Simulation 2 menu item.
     *
     * @return Simulation 2 menu item
     */
    private JMenuItem createSimulation2Item() {
        JMenuItem item = new JMenuItem("Simulation 2", '2');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl 2"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the Simulation 3 menu item.
     *
     * @return Simulation 3 menu item
     */
    private JMenuItem createSimulation3Item() {
        JMenuItem item = new JMenuItem("Simulation 3", '3');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl 3"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the sub menu list for Export.
     *
     * @return the submenu list
     */
    private JMenu createSubMenuList() {
        JMenu menuExport = new JMenu("Export");
        menuExport.setMnemonic(KeyEvent.VK_E);
        menuExport.add(createSubMenuListHTML());
        menuExport.setEnabled(true);
        return menuExport;
    }

    /**
     * Creates the sub menu list for HTML.
     *
     * @return the submenu list
     */
    private JMenu createSubMenuListHTML() {
        JMenu menuExport = new JMenu("HTML");
        menuExport.setMnemonic(KeyEvent.VK_H);
        menuExport.add(createItemExportByAirports());
        menuExport.add(createItemExportByAircraft());
        menuExport.add(createItemExportByFlightInfo());
        menuExport.setEnabled(true);
        return menuExport;
    }

    /**
     * Creates the export by airports item.
     *
     * @return HTML exportation item
     */
    private JMenuItem createItemExportByAirports() {
        JMenuItem item = new JMenuItem("Export by Airport", 'A');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the export by aircraft item.
     *
     * @return HTML exportation item
     */
    private JMenuItem createItemExportByAircraft() {
        JMenuItem item = new JMenuItem("Export by Aircraft", 'B');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl B"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the export by flight info item.
     *
     * @return HTML exportation item
     */
    private JMenuItem createItemExportByFlightInfo() {
        JMenuItem item = new JMenuItem("Export by Flight Info", 'F');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the Airports menu item.
     *
     * @return Airports menu item
     */
    private JMenuItem createAirportsItem() {
        JMenuItem item = new JMenuItem("Airports", 'A');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }

    /**
     * Creates the Aircraft Models menu item.
     *
     * @return Aircraft Models menu item
     */
    private JMenuItem createAircraftModelsItem() {
        JMenuItem item = new JMenuItem("Aircraft Models", 'M');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl M"));
        item.addActionListener((ActionEvent e) -> {
            // TODO
        });
        return item;
    }
}
