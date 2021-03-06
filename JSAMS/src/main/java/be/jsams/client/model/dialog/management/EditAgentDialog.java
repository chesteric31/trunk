package be.jsams.client.model.dialog.management;

import javax.swing.JPanel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.management.EditAgentValidator;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.view.CivilityBeanView;
import be.jsams.common.bean.view.management.AgentBeanView;
import be.jsams.server.model.Civility;
import be.jsams.server.service.management.AgentService;

/**
 * Edit Agent {@link AbstractEditDialog}, to create or update a
 * {@link AgentBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditAgentDialog extends AbstractEditDialog<AgentBean, EditAgentValidator, AgentService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2514472162732492120L;

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link AgentBean} model
     */
    public EditAgentDialog(final I18nString title, AgentBean model) {
        super(null, title, model, new EditAgentValidator(), ApplicationContext.getAgentService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOriginalModel() {
        setOriginalModel(ApplicationContext.getAgentBeanBuilder().build(null, getModel().getSociety()));
        getOriginalModel().refresh(getModel());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel initComponents() {
        AgentBeanView view = getModel().buildView();
        return view.createEditView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        AgentBean bean = getModel();
        AbstractIdentityBean<Civility, CivilityBeanView> civility = bean.getCivility().getSelection();
        if (civility != null) {
            bean.getCivility().refresh((CivilityBean) civility);
        }
        super.postPerformOk(bean);
    }

}
