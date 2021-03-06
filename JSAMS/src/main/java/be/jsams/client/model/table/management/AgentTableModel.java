package be.jsams.client.model.table.management;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.management.AgentBean;

/**
 * Customized table model for {@link AgentBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentTableModel extends AbstractJsamsTableModel<AgentBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1514081531839355887L;

    /**
     * Default constructor.
     */
    public AgentTableModel() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listModel a list of {@link AgentBean}
     */
    public AgentTableModel(List<AgentBean> listModel) {
        super(listModel);
        setColumnNames(Arrays.asList(I18nResource.COLUMN_ID, I18nResource.COLUMN_NAME,
            I18nResource.COLUMN_FUNCTION, I18nResource.COLUMN_PHONE, I18nResource.COLUMN_ZIP_CODE,
            I18nResource.COLUMN_CITY));
    }


    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        AgentBean agent = (AgentBean) getRow(rowIndex);
        AddressBean address = agent.getAddress();
        switch (columnIndex) {
        case ZERO:
            return agent.getId();
        case ONE:
            return agent.getName();
        case TWO:
            return agent.getFunction();
        case THREE:
            ContactInformationBean contactInformation = agent.getContactInformation();
            if (contactInformation != null) {
                return contactInformation.getPhone();
            }
        case FOUR:
            if (address != null) {
                return address.getZipCode();
            }
        case FIVE:
            if (address != null) {
                return address.getCity();
            }
        default:
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return Long.class;
        case ONE:
        case TWO:
        case THREE:
        case FIVE:
            return String.class;
        case FOUR:
            return Integer.class;
        default:
            return Object.class;
        }
    }

}
