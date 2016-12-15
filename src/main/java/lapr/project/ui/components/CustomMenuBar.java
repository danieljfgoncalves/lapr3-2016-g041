/*
 * Package location for view concepts.
 */
package lapr.project.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

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
     * Creates an instance of the CustomMenuBar.
     */
    public CustomMenuBar() {

        add(createMenuOptions());
    }

    /**
     * Creates the file menu
     *
     * @return menu file
     */
    private JMenu createMenuOptions() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.add(createSubMenuList());
        menu.add(createItemExit());
        return menu;
    }

    /**
     * Creates the sub menu list for Export.
     *
     * @return the submenu list
     */
    private JMenu createSubMenuList() {
        JMenu menuExport = new JMenu("Export");
        menuExport.setMnemonic(KeyEvent.VK_E);
        menuExport.add(createItemExportHTML());
        return menuExport;
    }

    /**
     * Creates the XML exportation item.
     *
     * @return XML exportation item
     */
    private JMenuItem createItemExportHTML() {
        JMenuItem item = new JMenuItem("XML", 'X');
        item.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
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
}
