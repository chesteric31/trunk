package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.management.EditProductDialog;
import be.jsams.common.bean.builder.ProductBeanBuilder;

/**
 * {@link AbstractAction} to launch {@link EditProductDialog}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class NewProductAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3836214162446560188L;

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        ProductBeanBuilder builder = ApplicationContext.getProductBeanBuilder();
        builder.setSociety(Desktop.getInstance().getCurrentSociety());
        new EditProductDialog(I18nResource.TITLE_EDIT_PRODUCT, builder.build(true, false));
    }

}
