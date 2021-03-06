package be.jsams.client.model.table.sale;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;

/**
 * {@link AbstractJsamsTableModel} for {@link DeliveryOrderBean} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderTableModel extends AbstractJsamsTableModel<DeliveryOrderBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -84296226501937365L;

    /**
     * Default constructor.
     */
    public DeliveryOrderTableModel() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listBean a list of {@link DeliveryOrderBean}
     */
    public DeliveryOrderTableModel(List<DeliveryOrderBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(I18nResource.COLUMN_ID, I18nResource.COLUMN_CREATION_DATE,
                I18nResource.COLUMN_TRANSFERRED, I18nResource.COLUMN_REMARK,
                I18nResource.COLUMN_DISCOUNT_RATE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        DeliveryOrderBean deliveryOrder = (DeliveryOrderBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return deliveryOrder.getId();
        case ONE:
            return deliveryOrder.getCreationDate();
        case TWO:
            return deliveryOrder.isTransferred();
        case THREE:
            return deliveryOrder.getRemark();
        case FOUR:
            return deliveryOrder.getDiscountRate();
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
            return Date.class;
        case TWO:
            return Boolean.class;
        case THREE:
            return String.class;
        case FOUR:
            return Double.class;
        default:
            return Object.class;
        }
    }

}
