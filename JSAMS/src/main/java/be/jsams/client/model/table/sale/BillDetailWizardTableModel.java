package be.jsams.client.model.table.sale;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;

/**
 * Customized table model for {@link BillDetailBean} wizard purpose
 * 
 * @author chesteric31
 * @version $$Rev: 794 $$ $$Date:: 2011-06-05 18:01#$$ $$Author: chesteric31 $$
 */
public class BillDetailWizardTableModel extends AbstractJsamsTableModel<BillDetailBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6723240746447143729L;

    /**
     * Constructor.
     * 
     * @param listBean a list of {@link BillDetailBean}
     */
    public BillDetailWizardTableModel(List<BillDetailBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(I18nResource.COLUMN_BILL_ID, I18nResource.COLUMN_PRODUCT_ID,
                I18nResource.COLUMN_PRODUCT_NAME, I18nResource.COLUMN_QUANTITY,
                I18nResource.COLUMN_PRICE, I18nResource.COLUMN_DISCOUNT_RATE,
                I18nResource.COLUMN_VAT_APPLICABLE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        BillDetailBean detail = (BillDetailBean) getRow(rowIndex);
        ProductBean product = detail.getProduct();
        switch (columnIndex) {
        case ZERO:
            return detail.getBill().getId();
        case ONE:
            if (product != null) {
                return product.getId();
            } else {
                return "";
            }
        case TWO:
            if (product != null) {
                return product.getName();
            } else {
                return "";
            }
        case THREE:
            return detail.getQuantity();
        case FOUR:
            return detail.getPrice();
        case FIVE:
            return detail.getDiscountRate();
        case SIX:
            return detail.getVatApplicable();
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
            return String.class;
        case THREE:
            return Integer.class;
        case FOUR:
        case FIVE:
        case SIX:
            return Double.class;
        default:
            return Object.class;
        }
    }

}
