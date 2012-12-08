package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.AboutDialog;
import be.jsams.client.model.dialog.UpdateDialog;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;
import be.jsams.client.swing.utils.IconResource;

/**
 * Specific menu builder for help menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class HelpMenuBuilder extends AbstractMenuBuilder {

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        JsamsMenu helpMenu = new JsamsMenu(I18nResource.MENU_HELP);
        JsamsMenuItem helpMI = new JsamsMenuItem(I18nResource.MENU_ITEM_HELP, "apps/help-browser.png");
        helpMenu.add(helpMI);
        JsamsMenuItem updateMI = new JsamsMenuItem(I18nResource.MENU_ITEM_UPDATE,
                "status/software-update-available.png");
        updateMI.setAction(updateAction(updateMI.getText(), updateMI.getIcon()));
        helpMenu.add(updateMI);
        helpMenu.add(new JSeparator());
        JsamsMenuItem aboutMI = new JsamsMenuItem(I18nResource.MENU_ITEM_ABOUT,
                IconResource.JSAMS);
        aboutMI.setAction(aboutAction(aboutMI.getText(), aboutMI.getIcon()));
        helpMenu.add(aboutMI);
        return helpMenu;
    }

    /**
     * {@link AbstractAction} to open the about dialog.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} to open the about dialog
     */
    private Action aboutAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 6621546191772229680L;

            public void actionPerformed(ActionEvent event) {
                new AboutDialog(Desktop.getInstance().getMainWindow(), I18nResource.TITLE_ABOUT);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} to open the update dialog.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} to open the update dialog
     */
    private Action updateAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -7362274053727193013L;

            public void actionPerformed(ActionEvent event) {
                new UpdateDialog(Desktop.getInstance().getMainWindow(), I18nResource.TITLE_UPDATE);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableMenuItems(boolean value) {
        throw new UnsupportedOperationException();
    }

}
