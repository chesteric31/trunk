package be.jsams.common.bean.view.management;

import javax.swing.JPanel;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Customized views for {@link ProductBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductBeanView extends AbstractBeanView<ProductBean> implements Editable<JPanel>, Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6271701607970284767L;

    /**
     * Constructor.
     * 
     * @param bean the {@link ProductBean}
     */
    public ProductBeanView(ProductBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        ProductBean bean = getBean();
        ViewFactory<ProductBean> viewFactory = getViewFactory();
        JsamsTextField textFieldName = viewFactory.createBindingTextComponent(bean, ProductBean.NAME_PROPERTY, true,
                false);
        JsamsFormattedTextField textFieldPrice = viewFactory.createBindingCurrencyComponent(bean,
                ProductBean.PRICE_PROPERTY, true, false);
        JsamsFormattedTextField textFieldStockQuantity = viewFactory.createBindingIntComponent(bean,
                ProductBean.QUANTITY_STOCK_PROPERTY, true, false);
        JsamsFormattedTextField textFieldReorderLevel = viewFactory.createBindingIntComponent(bean,
                ProductBean.REORDER_LEVEL_PROPERTY, false, false);
        JsamsFormattedTextField textFieldVatApplicable = viewFactory.createBindingPercentageComponent(bean,
                CustomerBean.VAT_APPLICABLE_PROPERTY, true, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_LABEL.getKey(), textFieldName, maxColumnSpan);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_CATEGORY.getKey(), bean.getCategory().buildView()
                .createCustomView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_PRICE.getKey(), textFieldPrice);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY.getKey(), textFieldStockQuantity);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_REORDER_LEVEL.getKey(), textFieldReorderLevel);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_VAT_APPLICABLE.getKey(), textFieldVatApplicable);

        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        ProductBean bean = getBean();
        ViewFactory<ProductBean> helper = new ViewFactory<ProductBean>();
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, ProductBean.NAME_PROPERTY, false, false);
        JsamsFormattedTextField textFieldPrice = helper.createBindingCurrencyComponent(bean, ProductBean.PRICE_PROPERTY,
                false, false);
        JsamsFormattedTextField textFieldStockQuantity = helper.createBindingIntComponent(bean,
                ProductBean.QUANTITY_STOCK_PROPERTY, false, false);
        JsamsFormattedTextField textFieldReorderLevel = helper.createBindingIntComponent(bean,
                ProductBean.REORDER_LEVEL_PROPERTY, false, false);
        JsamsFormattedTextField textFieldVatApplicable = helper.createBindingPercentageComponent(bean,
                ProductBean.VAT_APPLICABLE_PROPERTY, false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, "
                + "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxColumnSpan = 5;
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_LABEL.getKey(), textFieldName, maxColumnSpan);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_CATEGORY.getKey(), bean.getCategory().buildView()
                .createCustomView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_PRICE.getKey(), textFieldPrice);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY.getKey(), textFieldStockQuantity);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_REORDER_LEVEL.getKey(), textFieldReorderLevel);
        builder.appendI15d(I18nLabelResource.LABEL_PRODUCT_VAT_APPLICABLE.getKey(), textFieldVatApplicable);

        return builder.getPanel();
    }

}
