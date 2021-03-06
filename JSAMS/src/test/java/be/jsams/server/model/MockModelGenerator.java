package be.jsams.server.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.CreditNote;
import be.jsams.server.model.sale.DeliveryOrder;
import be.jsams.server.model.sale.Estimate;
import be.jsams.server.model.sale.detail.BillDetail;
import be.jsams.server.model.sale.detail.CommandDetail;
import be.jsams.server.model.sale.detail.CreditNoteDetail;
import be.jsams.server.model.sale.detail.DeliveryOrderDetail;
import be.jsams.server.model.sale.detail.EstimateDetail;

/**
 * Generator of model objects.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class MockModelGenerator {

    /**
     * Default private constructor for utility class. 
     */
    private MockModelGenerator() {
        
    }
    
    /**
     * Generates mock {@link Society}.
     * 
     * @return the built {@link Society}
     */
    public static Society generateMockSociety() {
        Society society = new Society();
        society.setActivity("Activity");
        society.setCapital(new Double("123456.78"));
        society.setName("Name");
        society.setVatNumber("BE123456789");

        Address address = generateMockAddress();
        society.setAddress(address);
        
        ContactInformation contactInformation = generateMockContactInformation();
        society.setContactInformation(contactInformation);
        
//        LegalForm legalForm = generateMockLegalForm();
//        society.setLegalForm(legalForm);

        return society;
    }

    /**
     * Generates mock {@link ContactInformation}.
     * 
     * @return the built {@link ContactInformation}
     */
    public static ContactInformation generateMockContactInformation() {
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setPhone("0221223456");
        contactInformation.setFax("0123456789");
        contactInformation.setMobile("1234567890");
        contactInformation.setEmail("mail@mail.com");
        contactInformation.setWebsite("www.website.com");
        return contactInformation;
    }

    /**
     * Generates mock {@link Address}.
     * 
     * @return the built {@link Address}
     */
    public static Address generateMockAddress() {
        Address address = new Address();
        address.setBox("A");
        address.setCity("Brussels");
        address.setCountry("Belgium");
        address.setNumber("1");
        address.setStreet("Rue Neuve");
        address.setZipCode("1000");
        return address;
    }

    /**
     * Generates mock {@link Civility}.
     * 
     * @return the built {@link Civility}
     */
    public static Civility generateMockCivility() {
        Civility civility = new Civility();
        civility.setLabel("label");
        civility.setLabelFr("labelFr");
        civility.setLabelNl("labelNl");
        return civility;
    }

    /**
     * Generates mock {@link ProductCategory}.
     * 
     * @return the built {@link ProductCategory}
     */
    public static ProductCategory generateMockProductCategory() {
        ProductCategory category = new ProductCategory();
        category.setLabel("category");
        category.setLabelFr("categoryFr");
        category.setLabelNl("categoryNl");
        category.setSociety(generateMockSociety());
        return category;
    }

    /**
     * Generates mock {@link LegalForm}.
     * 
     * @return the built {@link LegalForm}
     */
    public static LegalForm generateMockLegalForm() {
        LegalForm form = new LegalForm();
        form.setLabel("Public Limited Company");
        form.setLabelFr("Entreprise individuelle");
        form.setLabelNl("Eenmanszaak");
        return form;
    }

    /**
     * Generates mock {@link Agent}.
     * 
     * @return the built {@link Agent}
     */
    public static Agent generateMockAgent() {
        Agent agent = new Agent();
        agent.setName("Name");
        agent.setFunction("Contact");

        Address address = generateMockAddress();
        agent.setAddress(address);
        
        ContactInformation contactInformation = generateMockContactInformation();
        agent.setContactInformation(contactInformation);
       
        Civility civility = generateMockCivility();
        agent.setCivility(civility);
        
        agent.setSociety(generateMockSociety());
        
        return agent;
    }

    /**
     * Generates mock {@link Product}.
     * 
     * @return the built {@link Product}
     */
    public static Product generateMockProduct() {
        Product product = new Product();
        product.setCategory(generateMockProductCategory());
        product.setName("Product");
        product.setPrice(100D);
        product.setQuantityStock(1);
        product.setReorderLevel(2);
        product.setVatApplicable(6D);
        return product;
    }

    /**
     * Generates mock {@link Command}.
     * 
     * @return the built {@link Command}
     */
    public static Command generateMockCommand() {
        Command command = new Command();
        command.setAgent(generateMockAgent());
        command.setBillingAddress(generateMockAddress());
        command.setCreationDate(new Date());
        command.setCustomer(generateMockCustomer());
        command.setDeliveryAddress(generateMockAddress());
        command.setDiscountRate(1D);
        command.setRemark("Remark");
        command.setTransferred(false);
        CommandDetail detail = new CommandDetail();
        detail.setCommand(command);
        detail.setDiscountRate(0D);
        detail.setPrice(15D);
        detail.setProduct(generateMockProduct());
        detail.setQuantity(1);
        detail.setTransferred(false);
        detail.setVatApplicable(21D);
        List<CommandDetail> details = new ArrayList<CommandDetail>();
        details.add(detail);
        command.setDetails(details);
        return command;
    }

    /**
     * Generates mock {@link Customer}.
     * 
     * @return the built {@link Customer}
     */
    public static Customer generateMockCustomer() {
        Customer customer = new Customer();
        customer.setAgent(generateMockAgent());
        customer.setBank1("Bk1");
        customer.setBank2("Bk2");
        customer.setBillingAddress(generateMockAddress());
        customer.setCivility(generateMockCivility());
        customer.setContactInformation(generateMockContactInformation());
        customer.setCreditLimit(100D);
        customer.setDefaultDiscountRate(0D);
        customer.setDeliveryAddress(generateMockAddress());
        customer.setDescription("Description");
        customer.setLegalForm(generateMockLegalForm());
        customer.setName("Name");
        customer.setSociety(generateMockSociety());
        customer.setPaymentMode(generateMockPaymentMode());
        customer.setVatApplicable(6D);
        customer.setVatNumber("BE1234567890");
        return customer;
    }

    /**
     * Generates mock {@link PaymentMode}.
     * 
     * @return the built {@link PaymentMode}
     */
    public static PaymentMode generateMockPaymentMode() {
        PaymentMode paymentMode = new PaymentMode();
        paymentMode.setAdditionalDays(15);
        paymentMode.setDaysNumber(30);
        paymentMode.setLabel("EXTENSION");
        paymentMode.setLabelFr("PROLONGATION");
        paymentMode.setLabelNl("VERLENGING");
        paymentMode.setMonthEnd(true);
        return paymentMode;
    }

    /**
     * Generates mock {@link Estimate}.
     * 
     * @return the built {@link Estimate}
     */
    public static Estimate generateMockEstimate() {
        Estimate estimate = new Estimate();
        estimate.setAgent(generateMockAgent());
        estimate.setBillingAddress(generateMockAddress());
        estimate.setCreationDate(new Date());
        estimate.setCustomer(generateMockCustomer());
        estimate.setDiscountRate(1D);
        estimate.setRemark("Remark");
        estimate.setTransferred(false);
        EstimateDetail detail = new EstimateDetail();
        detail.setEstimate(estimate);
        detail.setDiscountRate(1D);
        detail.setPrice(25D);
        detail.setProduct(generateMockProduct());
        detail.setQuantity(2);
        detail.setTransferred(false);
        detail.setVatApplicable(6D);
        List<EstimateDetail> details = new ArrayList<EstimateDetail>();
        details.add(detail);
        estimate.setDetails(details);
        return estimate;
    }

    /**
     * Generates mock {@link DeliveryOrder}.
     * 
     * @return the built {@link DeliveryOrder}
     */
    public static DeliveryOrder generateMockDeliveryOrder() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setCreationDate(new Date());
        deliveryOrder.setCustomer(generateMockCustomer());
        deliveryOrder.setDeliveryAddress(generateMockAddress());
        deliveryOrder.setDiscountRate(3D);
        deliveryOrder.setRemark("Remark");
        deliveryOrder.setTransferred(false);
        DeliveryOrderDetail detail = new DeliveryOrderDetail();
        detail.setDeliveryOrder(deliveryOrder);
        detail.setDiscountRate(2D);
        detail.setPrice(20D);
        detail.setProduct(generateMockProduct());
        detail.setQuantity(5);
        detail.setTransferred(false);
        detail.setVatApplicable(15D);
        List<DeliveryOrderDetail> details = new ArrayList<DeliveryOrderDetail>();
        details.add(detail);
        deliveryOrder.setDetails(details);
        return deliveryOrder;
    }

    /**
     * Generates mock {@link CreditNote}.
     * 
     * @return the built {@link CreditNote}
     */
    public static CreditNote generateMockCreditNote() {
        CreditNote creditNote = new CreditNote();
        creditNote.setBillingAddress(generateMockAddress());
        creditNote.setCreationDate(new Date());
        creditNote.setCustomer(generateMockCustomer());
        creditNote.setRemark("Remark");
        CreditNoteDetail detail = new CreditNoteDetail();
        detail.setCreditNote(creditNote);
        detail.setDiscountRate(0D);
        detail.setPrice(1000D);
        detail.setProduct(generateMockProduct());
        detail.setQuantity(3);
        detail.setVatApplicable(5D);
        List<CreditNoteDetail> details = new ArrayList<CreditNoteDetail>();
        details.add(detail);
        creditNote.setDetails(details);
        return creditNote;
    }

    /**
     * Generates mock {@link Bill}.
     * 
     * @return the built {@link Bill}
     */
    public static Bill generateMockBill() {
        Bill bill = new Bill();
        bill.setBillingAddress(generateMockAddress());
        bill.setClosed(false);
        Date today = new Date();
        bill.setCreationDate(today);
        bill.setCustomer(generateMockCustomer());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dateFirstRemember = calendar.getTime();
        bill.setFirstRememberDate(dateFirstRemember);
        calendar.setTime(dateFirstRemember);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dateSecondRemember = calendar.getTime();
        bill.setSecondRememberDate(dateSecondRemember);
        calendar.setTime(dateSecondRemember);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        Date dateFormalNotice = calendar.getTime();
        bill.setFormalNoticeDate(dateFormalNotice);
        bill.setDiscountRate(25D);
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date dueDate = calendar.getTime();
        bill.setDueDate(dueDate);
        bill.setPaymentMode(generateMockPaymentMode());
        bill.setRemark("Remark");
        BillDetail detail = new BillDetail();
        detail.setBill(bill);
        detail.setDiscountRate(0D);
        detail.setPrice(15D);
        detail.setProduct(generateMockProduct());
        detail.setQuantity(1);
        detail.setTransferred(false);
        detail.setVatApplicable(21D);
        List<BillDetail> details = new ArrayList<BillDetail>();
        details.add(detail);
        bill.setDetails(details);
        return bill;
    }
    
}
