package be.jsams.client.validator.edit.sale;

import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.edit.EditAddressValidator;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit bill panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditBillValidator implements Validator<BillBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final BillBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");

        List<BillDetailBean> details = bean.getDetails();

        if (details == null || details.isEmpty()) {
            support.addError(I18nLabelResource.LABEL_DETAILS.getTranslation(),
                    I18nResource.ERROR_DETAILS_ARE_EMPTY.getTranslation());
        }
        Date creationDate = bean.getCreationDate();
        if (creationDate == null) {
            support.addError(I18nLabelResource.LABEL_CREATION_DATE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        CustomerBean customer = bean.getCustomer();
        if (ValidationUtils.isBlank(customer.getName())) {
            support.addError(I18nLabelResource.LABEL_CUSTOMER.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        if (bean.getPaymentMode().getLabel() == null) {
            support.addError(I18nLabelResource.LABEL_PAYMENT_MODE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        
        Date dueDate = bean.getDueDate();
        Date firstRememberDate = bean.getFirstRememberDate();
        Date secondRememberDate = bean.getSecondRememberDate();
        Date formalNoticeDate = bean.getFormalNoticeDate();
        if (dueDate == null) {
            support.addError(I18nLabelResource.LABEL_DUE_DATE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        if (firstRememberDate == null) {
            support.addError(I18nLabelResource.LABEL_FIRST_REMEMBER_DATE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        if (secondRememberDate == null) {
            support.addError(I18nLabelResource.LABEL_SECOND_REMEMBER_DATE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        if (formalNoticeDate == null) {
            support.addError(I18nLabelResource.LABEL_FORMAL_NOTICE_DATE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        Validator<AddressBean> billingAddressValidator = new EditAddressValidator();
        ValidationResult billingAddressResult = billingAddressValidator.validate(bean.getBillingAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(billingAddressResult);
        return support.getResult();
    }
    
}
