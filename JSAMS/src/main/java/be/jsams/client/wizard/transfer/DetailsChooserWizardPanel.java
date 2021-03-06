package be.jsams.client.wizard.transfer;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.table.sale.BillDetailWizardTableModel;
import be.jsams.client.model.table.sale.CommandDetailWizardTableModel;
import be.jsams.client.model.table.sale.DeliveryOrderDetailWizardTableModel;
import be.jsams.client.model.table.sale.EstimateDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.wizard.selection.detail.BillDetailWizardTableMouseListener;
import be.jsams.client.swing.listener.wizard.selection.detail.CommandDetailWizardTableMouseListener;
import be.jsams.client.swing.listener.wizard.selection.detail.DeliveryOrderDetailWizardTableMouseListener;
import be.jsams.client.swing.listener.wizard.selection.detail.EstimateDetailWizardTableMouseListener;
import be.jsams.client.validator.wizard.DetailsValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
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
        super(parent, component, model, I18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENT_DETAILS, validator);
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
        updateContainer();
        super.update();
    }

    /**
     * Update the container.
     */
    @SuppressWarnings("unchecked")
    public void updateContainer() {
        setLayout(new BorderLayout());
        int source = getModel().getSourceType();
        JsamsTable table = null;
        JScrollPane scrollPane = new JScrollPane();
        int selectionMode = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
        switch (source) {
        case 1:
            EstimateDetailWizardTableModel estimateTM = new EstimateDetailWizardTableModel(
                    (List<EstimateDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(estimateTM);
            table.addMouseListener(new EstimateDetailWizardTableMouseListener(getModel()));
            break;
        case 2:
            CommandDetailWizardTableModel commandTM = new CommandDetailWizardTableModel(
                    (List<CommandDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(commandTM);
            table.addMouseListener(new CommandDetailWizardTableMouseListener(getModel()));
            break;
        case 3:
            DeliveryOrderDetailWizardTableModel deliveryOrderTM = new DeliveryOrderDetailWizardTableModel(
                    (List<DeliveryOrderDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(deliveryOrderTM);
            table.addMouseListener(new DeliveryOrderDetailWizardTableMouseListener(getModel()));
            break;
        case 4:
            BillDetailWizardTableModel billTM = new BillDetailWizardTableModel(
                    (List<BillDetailBean>) getModel().getSelectableDetails());
            table = new JsamsTable(selectionMode);
            table.setModel(billTM);
            table.addMouseListener(new BillDetailWizardTableMouseListener(getModel()));
            break;
        default:
            break;
        }
        scrollPane.setViewportView(table);
        scrollPane.setBorder(new TitledBorder(I18nResource.SEARCH_RESULTS.getTranslation()));
        this.add(scrollPane, BorderLayout.CENTER);
    }

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
        getParentDialog().getStatusBar().clear();
        remove(getComponentCount() - 1);
        List<? extends AbstractDocumentBean<?, ?>> documents = getModel().getDocuments();
        if (documents != null) {
            documents.clear();
        }
        if (getModel().getTransferMode() == 2) {
            switchPanel(TransferWizardDialog.FIRTH_PANEL_PARTIAL_MODE);
        } else {
            switchPanel(TransferWizardDialog.FIRTH_PANEL_PARTIAL_GROUPED_MODE);
        }
    }

}
