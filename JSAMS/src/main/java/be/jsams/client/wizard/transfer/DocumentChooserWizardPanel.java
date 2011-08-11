package be.jsams.client.wizard.transfer;

import javax.swing.ListSelectionModel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.model.panel.sale.SearchBillPanel;
import be.jsams.client.model.panel.sale.SearchCommandPanel;
import be.jsams.client.model.panel.sale.SearchDeliveryOrderPanel;
import be.jsams.client.model.panel.sale.SearchEstimatePanel;
import be.jsams.client.model.table.BillTableModel;
import be.jsams.client.model.table.CommandTableModel;
import be.jsams.client.model.table.DeliveryOrderTableModel;
import be.jsams.client.model.table.EstimateTableModel;
import be.jsams.client.swing.listener.wizard.BillWizardSingleSelectionTableML;
import be.jsams.client.swing.listener.wizard.CommandWizardSingleSelectionTableML;
import be.jsams.client.swing.listener.wizard.DeliveryOrderWizardSingleSelectionTableML;
import be.jsams.client.swing.listener.wizard.EstimateWizardSingleSelectionTableML;
import be.jsams.client.validator.search.SearchBillValidator;
import be.jsams.client.validator.search.SearchCommandValidator;
import be.jsams.client.validator.search.SearchDeliveryOrderValidator;
import be.jsams.client.validator.search.SearchEstimateValidator;
import be.jsams.client.validator.wizard.DocumentValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DocumentChooserWizardPanel extends AbstractDocumentChooserWizardPanel<DocumentValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1862356691495522955L;
    
    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the DocumentValidator
     */
    public DocumentChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component,
            TransferBean model, DocumentValidator validator) {
        super(parent, component, model, validator, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateContainer() {
        int source = getModel().getSourceType();
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);

        switch (source) {
        case 1:
            EstimateBean estimate = new EstimateBean(currentSociety, customer, agent);
            estimate.setTransferred(false);
            estimate.setView(buildEstimateView(estimate));
            SearchEstimatePanel<EstimateWizardSingleSelectionTableML> estimatePanel
                = new SearchEstimatePanel<EstimateWizardSingleSelectionTableML>(
                    estimate, new EstimateWizardSingleSelectionTableML(getModel()),
                    JsamsApplicationContext.getEstimateService(), new SearchEstimateValidator(),
                    new EstimateTableModel(), false, ListSelectionModel.SINGLE_SELECTION);
            this.add(estimatePanel);
            break;
        case 2:
            CommandBean command = new CommandBean(currentSociety, customer, agent);
            command.setTransferred(false);
            command.setView(buildCommandView(command));
            SearchCommandPanel<CommandWizardSingleSelectionTableML> commandPanel
                = new SearchCommandPanel<CommandWizardSingleSelectionTableML>(
                    command, new CommandWizardSingleSelectionTableML(getModel()),
                    JsamsApplicationContext.getCommandService(), new SearchCommandValidator(), new CommandTableModel(),
                    false, ListSelectionModel.SINGLE_SELECTION);
            this.add(commandPanel);
            break;
        case 3:
            DeliveryOrderBean deliveryOrder = new DeliveryOrderBean(currentSociety, customer);
            deliveryOrder.setTransferred(false);
            deliveryOrder.setView(buildDeliveryOrderView(deliveryOrder));
            SearchDeliveryOrderPanel<DeliveryOrderWizardSingleSelectionTableML> deliveryOrderPanel
                = new SearchDeliveryOrderPanel<DeliveryOrderWizardSingleSelectionTableML>(
                    deliveryOrder, new DeliveryOrderWizardSingleSelectionTableML(getModel()),
                    JsamsApplicationContext.getDeliveryOrderService(), new SearchDeliveryOrderValidator(),
                    new DeliveryOrderTableModel(), false, ListSelectionModel.SINGLE_SELECTION);
            this.add(deliveryOrderPanel);
            break;
        case 4:
            PaymentModeBeanBuilder builder = JsamsApplicationContext.getPaymentModeBeanBuilder();
            PaymentModeBean mode = builder.build();
            BillBean bill = new BillBean(currentSociety, customer, mode);
            bill.setView(buildBillView(bill));
            // bill.setTransferred(false);
            SearchBillPanel<BillWizardSingleSelectionTableML> billPanel
                = new SearchBillPanel<BillWizardSingleSelectionTableML>(
                    bill, new BillWizardSingleSelectionTableML(getModel()), JsamsApplicationContext.getBillService(),
                    new SearchBillValidator(), new BillTableModel(), false, ListSelectionModel.SINGLE_SELECTION);
            this.add(billPanel);
            break;
        default:
            break;
        }
    }

}