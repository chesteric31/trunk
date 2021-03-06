package be.jsams;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import be.jsams.client.formatter.DoubleFormatterTest;
import be.jsams.client.i18n.UserContextTest;
import be.jsams.client.validator.edit.EditAddressValidatorTest;
import be.jsams.client.validator.edit.EditSocietyValidatorTest;
import be.jsams.client.validator.edit.management.EditAgentValidatorTest;
import be.jsams.client.validator.edit.management.EditCustomerValidatorTest;
import be.jsams.client.validator.edit.management.EditProductCategoryValidatorTest;
import be.jsams.client.validator.edit.management.EditProductValidatorTest;
import be.jsams.client.validator.edit.sale.EditBillValidatorTest;
import be.jsams.client.validator.edit.sale.EditCommandValidatorTest;
import be.jsams.client.validator.edit.sale.EditCreditNoteValidatorTest;
import be.jsams.client.validator.edit.sale.EditDeliveryOrderValidatorTest;
import be.jsams.client.validator.edit.sale.EditEstimateValidatorTest;
import be.jsams.client.validator.search.management.SearchAgentValidatorTest;
import be.jsams.client.validator.search.management.SearchCustomerValidatorTest;
import be.jsams.client.validator.search.management.SearchProductCategoryValidatorTest;
import be.jsams.client.validator.search.management.SearchProductValidatorTest;
import be.jsams.client.validator.wizard.DestinationTypeValidatorTest;
import be.jsams.client.validator.wizard.DetailsValidatorTest;
import be.jsams.client.validator.wizard.DocumentValidatorTest;
import be.jsams.client.validator.wizard.DocumentsValidatorTest;
import be.jsams.client.validator.wizard.SourceTypeValidatorTest;
import be.jsams.client.validator.wizard.TransferModeValidatorTest;
import be.jsams.common.bean.builder.management.AgentBeanBuilderTest;
import be.jsams.common.bean.builder.management.CustomerBeanBuilderTest;
import be.jsams.common.bean.model.AddressBeanTest;
import be.jsams.common.bean.model.CivilityBeanTest;
import be.jsams.common.bean.model.ContactInformationBeanTest;
import be.jsams.common.bean.model.LegalFormBeanTest;
import be.jsams.common.bean.model.PaymentModeBeanTest;
import be.jsams.common.bean.model.PeriodBeanTest;
import be.jsams.common.bean.model.SocietyBeanTest;
import be.jsams.common.bean.model.management.AgentBeanTest;
import be.jsams.common.bean.model.management.CustomerBeanTest;
import be.jsams.common.bean.model.management.ProductBeanTest;
import be.jsams.common.bean.model.management.ProductCategoryBeanTest;
import be.jsams.common.bean.model.sale.BillBeanTest;
import be.jsams.common.bean.model.sale.CommandBeanTest;
import be.jsams.common.bean.model.sale.CreditNoteBeanTest;
import be.jsams.common.bean.model.sale.DeliveryOrderBeanTest;
import be.jsams.common.bean.model.sale.EstimateBeanTest;
import be.jsams.common.bean.model.sale.detail.BillDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.CommandDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBeanTest;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBeanTest;
import be.jsams.common.bean.model.transfer.TransferBeanTest;
import be.jsams.common.bean.validator.EmailValidatorTest;
import be.jsams.common.bean.validator.StringValidatorTest;
import be.jsams.server.dao.impl.AddressDaoImplTest;
import be.jsams.server.dao.impl.CivilityDaoImplTest;
import be.jsams.server.dao.impl.ContactInformationDaoImplTest;
import be.jsams.server.dao.impl.SocietyDaoImplTest;
import be.jsams.server.dao.management.impl.AgentDaoImplTest;
import be.jsams.server.dao.management.impl.CustomerDaoImplTest;
import be.jsams.server.dao.management.impl.ProductCategoryDaoImplTest;
import be.jsams.server.dao.management.impl.ProductDaoImplTest;
import be.jsams.server.dao.sale.impl.BillDaoImplTest;
import be.jsams.server.dao.sale.impl.CommandDaoImplTest;
import be.jsams.server.dao.sale.impl.CreditNoteDaoImplTest;
import be.jsams.server.dao.sale.impl.DeliveryOrderDaoImplTest;
import be.jsams.server.dao.sale.impl.EstimateDaoImplTest;
import be.jsams.server.model.AddressTest;
import be.jsams.server.model.ContactInformationTest;
import be.jsams.server.model.PaymentModeTest;
import be.jsams.server.model.SocietyTest;
import be.jsams.server.model.management.AgentTest;
import be.jsams.server.model.management.CustomerTest;
import be.jsams.server.model.management.ProductCategoryTest;
import be.jsams.server.model.management.ProductTest;
import be.jsams.server.model.sale.BillTest;
import be.jsams.server.model.sale.CommandTest;
import be.jsams.server.model.sale.CreditNoteTest;
import be.jsams.server.model.sale.DeliveryOrderTest;
import be.jsams.server.model.sale.EstimateTest;
import be.jsams.server.model.utils.xml.JsamsDateAdapterTest;
import be.jsams.server.service.impl.SocietyServiceImplTest;
import be.jsams.server.service.management.impl.AgentServiceImplTest;
import be.jsams.server.service.management.impl.CustomerServiceImplTest;
import be.jsams.server.service.management.impl.ProductCategoryServiceImplTest;
import be.jsams.server.service.management.impl.ProductServiceImplTest;
import be.jsams.server.service.rss.impl.RSSFeedParserImplTest;
import be.jsams.server.service.sale.impl.BillServiceImplTest;
import be.jsams.server.service.sale.impl.CommandServiceImplTest;
import be.jsams.server.service.sale.impl.CreditNoteServiceImplTest;
import be.jsams.server.service.sale.impl.DeliveryOrderServiceImplTest;
import be.jsams.server.service.sale.impl.EstimateServiceImplTest;
import be.jsams.server.service.transfer.impl.BillCreditNoteTransferServiceImplTest;
import be.jsams.server.service.transfer.impl.CommandBillTransferServiceImplTest;
import be.jsams.server.service.transfer.impl.CommandDeliveryOrderTransferServiceImplTest;
import be.jsams.server.service.transfer.impl.DeliveryOrderBillTransferServiceImplTest;
import be.jsams.server.service.transfer.impl.EstimateBillTransferServiceImplTest;
import be.jsams.server.service.transfer.impl.EstimateCommandTransferServiceImplTest;
import be.jsams.server.service.update.impl.DownloaderServiceImplTest;
import be.jsams.server.service.xml.impl.XmlBillGeneratorImplTest;
import be.jsams.server.service.xml.impl.XmlCommandGeneratorImplTest;
import be.jsams.server.service.xml.impl.XmlCreditNoteGeneratorImplTest;
import be.jsams.server.service.xml.impl.XmlDeliveryOrderGeneratorImplTest;
import be.jsams.server.service.xml.impl.XmlEstimateGeneratorImplTest;
import be.jsams.server.utils.DateUtilTest;

/**
 * Test suite for all JUnit tests.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@RunWith(Suite.class)
@SuiteClasses(value = { DoubleFormatterTest.class, UserContextTest.class, EditAddressValidatorTest.class,
        EditSocietyValidatorTest.class, EditAgentValidatorTest.class, EditCustomerValidatorTest.class,
        EditProductValidatorTest.class, EditProductCategoryValidatorTest.class, EditEstimateValidatorTest.class,
        EditCommandValidatorTest.class, EditDeliveryOrderValidatorTest.class, EditBillValidatorTest.class,
        EditCreditNoteValidatorTest.class, SearchAgentValidatorTest.class, SearchCustomerValidatorTest.class,
        SearchProductValidatorTest.class, SearchProductCategoryValidatorTest.class, DestinationTypeValidatorTest.class,
        DetailsValidatorTest.class, DocumentValidatorTest.class, DocumentsValidatorTest.class,
        SourceTypeValidatorTest.class, TransferModeValidatorTest.class, AgentBeanBuilderTest.class,
        CustomerBeanBuilderTest.class, AddressBeanTest.class, CivilityBeanTest.class, LegalFormBeanTest.class,
        ContactInformationBeanTest.class, PaymentModeBeanTest.class, PeriodBeanTest.class, SocietyBeanTest.class,
        AgentBeanTest.class, CustomerBeanTest.class, ProductBeanTest.class, ProductCategoryBeanTest.class,
        BillBeanTest.class, CommandBeanTest.class, CreditNoteBeanTest.class, DeliveryOrderBeanTest.class,
        EstimateBeanTest.class, EstimateDetailBeanTest.class, CommandDetailBeanTest.class,
        DeliveryOrderDetailBeanTest.class, BillDetailBeanTest.class, CreditNoteDetailBeanTest.class,
        TransferBeanTest.class, EmailValidatorTest.class, StringValidatorTest.class, AddressDaoImplTest.class,
        CivilityDaoImplTest.class, ContactInformationDaoImplTest.class, SocietyDaoImplTest.class,
        AgentDaoImplTest.class, CustomerDaoImplTest.class, ProductCategoryDaoImplTest.class, ProductDaoImplTest.class,
        BillDaoImplTest.class, CommandDaoImplTest.class, CreditNoteDaoImplTest.class, DeliveryOrderDaoImplTest.class,
        EstimateDaoImplTest.class, AddressTest.class, ContactInformationTest.class, PaymentModeTest.class,
        SocietyTest.class, AgentTest.class, CustomerTest.class, ProductCategoryTest.class, ProductTest.class,
        EstimateTest.class, CommandTest.class, DeliveryOrderTest.class, BillTest.class, CreditNoteTest.class,
        JsamsDateAdapterTest.class, CustomerServiceImplTest.class, ProductCategoryServiceImplTest.class,
        ProductServiceImplTest.class, SocietyServiceImplTest.class, AgentServiceImplTest.class,
        EstimateServiceImplTest.class, CommandServiceImplTest.class, DeliveryOrderServiceImplTest.class,
        BillServiceImplTest.class, CreditNoteServiceImplTest.class, RSSFeedParserImplTest.class,
        EstimateCommandTransferServiceImplTest.class, EstimateBillTransferServiceImplTest.class,
        CommandDeliveryOrderTransferServiceImplTest.class, CommandBillTransferServiceImplTest.class,
        DeliveryOrderBillTransferServiceImplTest.class, BillCreditNoteTransferServiceImplTest.class,
        DownloaderServiceImplTest.class, XmlEstimateGeneratorImplTest.class, XmlCommandGeneratorImplTest.class,
        XmlDeliveryOrderGeneratorImplTest.class, XmlBillGeneratorImplTest.class, XmlCreditNoteGeneratorImplTest.class,
        DateUtilTest.class })
public class AllTests {

}
