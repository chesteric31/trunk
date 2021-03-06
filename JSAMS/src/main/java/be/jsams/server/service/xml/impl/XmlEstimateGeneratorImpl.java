package be.jsams.server.service.xml.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.model.xml.AddressXml;
import be.jsams.server.model.xml.ContactInformationXml;
import be.jsams.server.model.xml.CustomerXml;
import be.jsams.server.model.xml.ProductXml;
import be.jsams.server.model.xml.SocietyXml;
import be.jsams.server.model.xml.estimate.DetailXml;
import be.jsams.server.model.xml.estimate.DetailsXml;
import be.jsams.server.model.xml.estimate.EstimateXml;
import be.jsams.server.model.xml.estimate.ObjectFactory;
import be.jsams.server.service.xml.XmlGenerator;

/**
 * Service to generate a XML from a {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlEstimateGeneratorImpl implements XmlGenerator<EstimateBean, EstimateXml> {

    /**
     * {@inheritDoc}
     */
    @Override
    public EstimateXml generateXml(EstimateBean object) {
        ObjectFactory factory = new ObjectFactory();
        EstimateXml xml = factory.createEstimate();
        xml.setCreationDate(object.getCreationDate());
        CustomerXml customerXml = factory.createCustomer();
        CustomerBean customer = object.getCustomer();
        AddressXml addressXml = factory.createAddress();
        AddressBean billingAddress = object.getBillingAddress();
        addressXml.setCity(billingAddress.getCity());
        addressXml.setNumber(new BigInteger(billingAddress.getNumber()));
        addressXml.setStreet(billingAddress.getStreet());
        addressXml.setZip(billingAddress.getZipCode());
        xml.setAddress(addressXml);
        customerXml.setName(customer.getName());
        customerXml.setFirstName(customer.getFirstName());
        xml.setCustomer(customerXml);
        List<EstimateDetailBean> details = object.getDetails();
        BigDecimal fullTotalEt = new BigDecimal(0D);
        BigDecimal fullVat = new BigDecimal(0D);
        DetailsXml detailsXml = factory.createDetails();
        xml.setDetails(detailsXml);
        for (EstimateDetailBean bean : details) {
            DetailXml detailXml = factory.createDetail();
            detailXml.setPrice(BigDecimal.valueOf(bean.getPrice()));
            ProductXml productXml = factory.createProduct();
            ProductBean product = bean.getProduct();
            productXml.setName(product.getName());
            Long productId = product.getId();
            if (productId != null) {
                productXml.setNumber(new BigInteger(productId.toString()));
            }
            detailXml.setProduct(productXml);
            detailXml.setQuantity(BigInteger.valueOf(bean.getQuantity()));
            BigDecimal totalEt = BigDecimal.valueOf(bean.getPrice() * bean.getQuantity());
            Double discountRate = bean.getDiscountRate();
            if (discountRate != null) {
                double percentage = discountRate / 100;
                totalEt = totalEt.multiply(BigDecimal.valueOf(1 - percentage));
                detailXml.setDiscountRate(BigDecimal.valueOf(percentage).setScale(2, BigDecimal.ROUND_UP));
            }
            BigDecimal vat = totalEt.multiply(BigDecimal.valueOf(bean.getVatApplicable() / 100));
            fullVat = fullVat.add(vat);
            fullTotalEt = fullTotalEt.add(totalEt);
            detailXml.setTotalEt(totalEt.setScale(2, BigDecimal.ROUND_UP));
            List<DetailXml> detail = xml.getDetails().getDetail();
            detail.add(detailXml);
        }
        Long id = object.getId();
        if (id != null) {
            xml.setNumber(new BigInteger(id.toString()));
        }
        SocietyXml societyXml = factory.createSociety();
        SocietyBean society = object.getSociety();
        AddressXml value = factory.createAddress();
        AddressBean address = society.getAddress();
        value.setCity(address.getCity());
        value.setNumber(new BigInteger(address.getNumber()));
        value.setStreet(address.getStreet());
        value.setZip(address.getZipCode());
        societyXml.setAddress(value);
        ContactInformationXml contactInfoXml = factory.createContactInformation();
        contactInfoXml.setPhone(society.getContactInformation().getPhone());
        societyXml.setContactInformation(contactInfoXml);
        societyXml.setName(society.getName());
        societyXml.setVatNumber(society.getVatNumber());
        // obtain an encoded string from the byte array, which will be written
        // inside the XML
        String base64Image = Base64.encodeBase64String(society.getLogo());
        societyXml.setLogo(base64Image);
        xml.setSociety(societyXml);
        BigDecimal fullTotalAti = fullTotalEt.add(fullVat);
        xml.setTotalAti(fullTotalAti.setScale(2, BigDecimal.ROUND_UP));
        xml.setTotalEt(fullTotalEt.setScale(2, BigDecimal.ROUND_UP));
        xml.setVat(fullVat.setScale(2, BigDecimal.ROUND_UP));
        return xml;
    }

}
