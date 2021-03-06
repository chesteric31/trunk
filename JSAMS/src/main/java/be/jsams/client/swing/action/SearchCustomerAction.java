package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ListSelectionModel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.model.panel.management.SearchCustomerPanel;
import be.jsams.client.model.table.management.CustomerTableModel;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.management.CustomerTableMouseListener;
import be.jsams.client.validator.search.management.SearchCustomerValidator;
import be.jsams.common.bean.model.management.CustomerBean;

/**
 * {@link AbstractAction} to launch {@link SearchCustomerPanel}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCustomerAction extends AbstractAction {

    private final CustomerBean bean;
    private final JsamsDialog dialog;

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5028730923752573568L;

    /**
     * Constructor.
     * 
     * @param name the name
     * @param icon the icon
     * @param bean the {@link CustomerBean}
     * @param dialog the {@link JsamsDialog}
     */
    public SearchCustomerAction(String name, Icon icon, CustomerBean bean, JsamsDialog dialog) {
        super(name, icon);
        this.bean = bean;
        this.dialog = dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CustomerTableMouseListener customListener = new CustomerTableMouseListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                JsamsTable table = (JsamsTable) e.getSource();
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow > -1) {
                        int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                        CustomerTableModel model = (CustomerTableModel) table.getModel();
                        CustomerBean selectedBean = model.getRow(selectedRowModel);
                        bean.refresh(selectedBean);
                        dialog.dispose();
                    }
                }
            }
        };
        CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(null,
                Desktop.getInstance().getCurrentSociety());
        SearchCustomerPanel searchCustomerPanel = new SearchCustomerPanel(customerBean, customListener,
                ApplicationContext.getCustomerService(), new SearchCustomerValidator(), new CustomerTableModel(),
                false, ListSelectionModel.SINGLE_SELECTION);
        dialog.add(searchCustomerPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(((JsamsButton) e.getSource()).getRootPane());
        dialog.setVisible(true);
    }

}
