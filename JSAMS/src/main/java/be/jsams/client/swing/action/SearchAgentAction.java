package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ListSelectionModel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.model.panel.management.SearchAgentPanel;
import be.jsams.client.model.table.management.AgentTableModel;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.management.AgentTableMouseListener;
import be.jsams.client.validator.search.management.SearchAgentValidator;
import be.jsams.common.bean.builder.management.AgentBeanBuilder;
import be.jsams.common.bean.model.management.AgentBean;

/**
 * {@link AbstractAction} to launch {@link SearchAgentPanel}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class SearchAgentAction extends AbstractAction {

    private final AgentBean bean;
    private final JsamsDialog dialog;
    
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4903401985131831848L;

    /**
     * Constructor.
     * 
     * @param name the name
     * @param icon the icon
     * @param bean the {@link AgentBean}
     * @param dialog the {@link JsamsDialog}
     */
    public SearchAgentAction(String name, Icon icon, AgentBean bean, JsamsDialog dialog) {
        super(name, icon);
        this.bean = bean;
        this.dialog = dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AgentTableMouseListener customListener = new AgentTableMouseListener() {

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
                        AgentTableModel model = (AgentTableModel) table.getModel();
                        AgentBean selectedBean = model.getRow(selectedRowModel);
                        bean.refresh(selectedBean);
                        dialog.dispose();
                    }
                }
            }
        };
        AgentBeanBuilder builder = ApplicationContext.getAgentBeanBuilder();
        AgentBean bean = builder.build(null, Desktop.getInstance().getCurrentSociety());
        SearchAgentPanel searchAgentPanel = new SearchAgentPanel(bean, customListener,
                ApplicationContext.getAgentService(), new SearchAgentValidator(), new AgentTableModel(), false,
                ListSelectionModel.SINGLE_SELECTION);
        dialog.add(searchAgentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(((JsamsButton) e.getSource()).getRootPane());
        dialog.setVisible(true);
    }

}
