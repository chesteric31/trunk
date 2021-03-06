package be.jsams.client.desktop;

import java.awt.event.KeyEvent;

import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.action.ChooseSocietyAction;
import be.jsams.client.swing.action.EditSocietyAction;
import be.jsams.client.swing.action.ExitAction;
import be.jsams.client.swing.action.GeneralParametersAction;
import be.jsams.client.swing.action.RestartAction;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.utils.IconResource;

/**
 * Specific menu builder for file menu.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class FileMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenuItem closeMI;
    private JsamsMenuItem societyParametersMI;

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        JsamsMenu fileMenu = new JsamsMenu(I18nResource.MENU_FILE);
        JsamsMenuItem newMI = new JsamsMenuItem(I18nResource.MENU_ITEM_NEW, IconResource.NEW);
        newMI.setAction(new EditSocietyAction(newMI.getText(), newMI.getIcon(), EditSocietyAction.NEW_ONE_MODE));
        newMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        fileMenu.add(newMI);
        JsamsMenuItem openMI = new JsamsMenuItem(I18nResource.MENU_ITEM_OPEN, IconResource.OPEN);
        openMI.setAction(new ChooseSocietyAction(openMI.getText(), openMI.getIcon()));
        openMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        fileMenu.add(openMI);
        closeMI = new JsamsMenuItem(I18nResource.MENU_ITEM_CLOSE, IconResource.CLOSE);
        closeMI.setAction(new RestartAction(closeMI.getText(), closeMI.getIcon()));
        fileMenu.add(closeMI);
        fileMenu.add(new JSeparator());
        societyParametersMI = new JsamsMenuItem(I18nResource.MENU_ITEM_SOCIETY_PARAMETERS,
                IconResource.SOCIETY_PARAMETERS);
        societyParametersMI.setAction(new EditSocietyAction(societyParametersMI.getText(), societyParametersMI
                .getIcon(), EditSocietyAction.CURRENT_SOCIETY_MODE));
        fileMenu.add(societyParametersMI);
        JsamsMenuItem generalParametersMI = new JsamsMenuItem(I18nResource.MENU_ITEM_GENERAL_PARAMETERS,
                IconResource.GENERAL_PARAMETERS);
        generalParametersMI.setAction(new GeneralParametersAction(generalParametersMI.getText(), generalParametersMI
                .getIcon()));
        fileMenu.add(generalParametersMI);
        fileMenu.add(new JSeparator());
        JsamsMenuItem exitMI = new JsamsMenuItem(I18nResource.MENU_ITEM_EXIT_APPLICATION, IconResource.EXIT);
        exitMI.setAction(new ExitAction(exitMI.getText(), exitMI.getIcon()));
        exitMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        fileMenu.add(exitMI);

        enableMenuItems(false);

        return fileMenu;
    }

    /**
     * @return the societyParametersMI
     */
    public JsamsMenuItem getSocietyParametersMI() {
        return societyParametersMI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableMenuItems(boolean value) {
        closeMI.setEnabled(value);
        societyParametersMI.setEnabled(value);
    }

}
