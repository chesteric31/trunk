package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditCommandDialog;
import be.jsams.client.model.table.sale.CommandTableModel;
import be.jsams.client.validator.search.sale.SearchCommandValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CommandMediator;
import be.jsams.server.service.pdf.impl.PdfCommandServiceImpl;
import be.jsams.server.service.sale.CommandService;

/**
 * {@link AbstractSaleSearchPanel} for {@link CommandBean} objects.
 * 
 * @param <L> a customized {@link MouseListener}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCommandPanel<L extends MouseListener> extends
        AbstractSaleSearchPanel<CommandBean, L, CommandService, SearchCommandValidator, CommandTableModel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -494667273780356685L;

    private static final Log LOGGER = LogFactory.getLog(SearchCommandPanel.class);
    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model the {@link CommandBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link CommandService}
     * @param validator the {@link SearchCommandValidator}
     * @param commandTableModel the {@link CommandTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchCommandPanel(CommandBean model, L listener, CommandService service, SearchCommandValidator validator,
            CommandTableModel commandTableModel, final boolean showButtons, int selectionMode) {
        super(model, listener, service, validator, commandTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link CommandBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link CommandService}
     * @param validator the {@link SearchCommandValidator}
     * @param commandTableModel the {@link CommandTableModel}
     */
    public SearchCommandPanel(CommandBean bean, L listener, CommandService service,
            SearchCommandValidator validator, CommandTableModel commandTableModel) {
        super(bean, listener, service, validator, commandTableModel, true, ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customer = ApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = ApplicationContext.getAgentBeanBuilder().build(null, currentSociety);
        CommandBean bean = new CommandBean(currentSociety, customer, agent);
        CommandMediator mediator = new CommandMediator();
        mediator.setCommandBean(bean);
        bean.setMediator(mediator);
        new EditCommandDialog(I18nResource.TITLE_EDIT_COMMAND, bean);
        updateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            CommandTableModel model = (CommandTableModel) getResultTable().getModel();
            CommandBean beanToModify = model.getRow(selectedRowModel);
            CommandMediator mediator = new CommandMediator();
            mediator.setCommandBean(beanToModify);
            beanToModify.setMediator(mediator);
            if (debug) {
                LOGGER.debug("The command to modify: " + beanToModify);
            }
            new EditCommandDialog(I18nResource.TITLE_EDIT_COMMAND, beanToModify);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        final CommandBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<CommandBean> commands = ((CommandService) super.getService()).findByCriteria(criteria);
            fillTable(commands);
            if (commands != null && !commands.isEmpty()) {
                for (CommandBean bean : commands) {
                    CommandMediator mediator = new CommandMediator();
                    mediator.setCommandBean(bean);
                    bean.setMediator(mediator);
                }
            }
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param commands the {@link CommandBean} list
     */
    private void fillTable(final List<CommandBean> commands) {
        CommandTableModel model = new CommandTableModel(commands);
        super.setTableModel(model);
        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String performButtonPdf(boolean viewReport, CommandBean bean) {
        String pdf = "";
        if (bean != null) {
            PdfCommandServiceImpl pdfService = ApplicationContext.getPdfCommandService();
            pdf = pdfService.generatePdf(bean, viewReport);
        }
        return pdf;
    }

}
