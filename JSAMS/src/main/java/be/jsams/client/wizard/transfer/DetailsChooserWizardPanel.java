package be.jsams.client.wizard.transfer;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.table.BillDetailWizardTableModel;
import be.jsams.client.model.table.CommandDetailWizardTableModel;
import be.jsams.client.model.table.DeliveryOrderDetailWizardTableModel;
import be.jsams.client.model.table.EstimateDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.wizard.BillDetailWizardTableMouseListener;
import be.jsams.client.swing.listener.wizard.CommandDetailWizardTableMouseListener;
import be.jsams.client.swing.listener.wizard.DeliveryOrderDetailWizardTableMouseListener;
import be.jsams.client.swing.listener.wizard.EstimateDetailWizardTableMouseListener;
import be.jsams.client.validator.wizard.DetailsValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * {@link JsamsWizardPanel} to choose the document details to transfer. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DetailsChooserWizardPanel extends JsamsWizardPanel<TransferBean, DetailsValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 643645734661314291L;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link DetailsValidator} 
     */
    public DetailsChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component,
            TransferBean model, DetailsValidator validator) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENT_DETAILS, validator);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        setFinishButtonEnabled(false);
        setBackButtonEnabled(true);
        updateContainer();
        super.update();
    }

    /**
     * Update the container.
     */
    @SuppressWarnings("unchecked")
    public void updateContainer() {
        int source = getModel().getSourceType();
        JsamsTable table = null;
        JScrollPane scrollPane = new JScrollPane();
        int selectionMode = ListSelectionModel.SINGLE_SELECTION;
        if (getModel().getTransferMode() == 4) {
            selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        }
        switch (source) {
        case 1:
            EstimateDetailWizardTableModel estimateTM = new EstimateDetailWizardTableModel(
                    (List<EstimateDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(estimateTM);
            table.addMouseListener(new EstimateDetailWizardTableMouseListener(getModel()));
            scrollPane.setViewportView(table);
            scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS.getTranslation()));
            this.add(scrollPane);
            break;
        case 2:
            CommandDetailWizardTableModel commandTM = new CommandDetailWizardTableModel(
                    (List<CommandDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(commandTM);
            table.addMouseListener(new CommandDetailWizardTableMouseListener(getModel()));
            scrollPane.add(table);
            scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS.getTranslation()));
            this.add(scrollPane);
            break;
        case 3:
            DeliveryOrderDetailWizardTableModel deliveryOrderTM = new DeliveryOrderDetailWizardTableModel(
                    (List<DeliveryOrderDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(deliveryOrderTM);
            table.addMouseListener(new DeliveryOrderDetailWizardTableMouseListener(getModel()));
            scrollPane.add(table);
            scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS.getTranslation()));
            this.add(scrollPane);
            break;
        case 4:
            BillDetailWizardTableModel billTM = new BillDetailWizardTableModel(
                    (List<BillDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(billTM);
            table.addMouseListener(new BillDetailWizardTableMouseListener(getModel()));
            scrollPane.add(table);
            scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS.getTranslation()));
            this.add(scrollPane);
            break;
        default:
            break;
        }
    }
    
//    /**
//     * Set default table renderer
//     * 
//     * @param table the table to set the default renderer
//     */
//    private void setTableRenderer(JsamsTable table) {
//        JTableHeader tableHeader = table.getTableHeader();
//        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();
//
//        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        table.setAutoCreateRowSorter(true);
//        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
//        table.setDefaultRenderer(Long.class, defaultCellRenderer);
//        table.setDefaultRenderer(Integer.class, defaultCellRenderer);
//        table.setDefaultRenderer(Double.class, defaultCellRenderer);
//        table.setDefaultRenderer(String.class, defaultCellRenderer);
//        table.setDefaultRenderer(Boolean.class, new JsamsBooleanTableCellRenderer());
//        table.setDefaultRenderer(Date.class, defaultCellRenderer);
//    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (prePerformNext()) {
            remove(getComponentCount() - 1);
            switchPanel(TransferWizardDialog.SUMMARY_PANEL);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
        int transferMode = getModel().getTransferMode();
        if (transferMode == 2) {
            switchPanel(TransferWizardDialog.FIRTH_PANEL_PARTIAL_MODE);
        } else {
            switchPanel(TransferWizardDialog.FIRTH_PANEL_PARTIAL_GROUPED_MODE);
        }
    }

}
