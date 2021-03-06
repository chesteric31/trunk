package be.jsams.client.validator.wizard;

import java.util.List;
import java.util.Map;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for the step: details chooser in the transfer wizard
 * dialog.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DetailsValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        Map<Long, List<EstimateDetailBean>> estimates = bean.getEstimateDetails();
        Map<Long, List<CommandDetailBean>> commands = bean.getCommandDetails();
        Map<Long, List<DeliveryOrderDetailBean>> orders = bean.getDeliveryOrderDetails();
        Map<Long, List<BillDetailBean>> bills = bean.getBillDetails();
        if ((estimates == null || estimates.isEmpty()) && (commands == null || commands.isEmpty())
                && (orders == null || orders.isEmpty()) && (bills == null || bills.isEmpty())) {
            support.addError(I18nLabelResource.LABEL_DOCUMENT_DETAILS.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY_TO_SELECT.getTranslation());
        }
        return support.getResult();
    }

}
