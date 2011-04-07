package be.jsams.client.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.server.dao.CivilityDao;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.service.AgentService;
import be.jsams.server.service.CustomerService;
import be.jsams.server.service.EstimateService;
import be.jsams.server.service.ProductCategoryService;
import be.jsams.server.service.ProductService;
import be.jsams.server.service.SocietyService;

/**
 * This class provides static methods to get a reference to a specific service, anywhere in this project.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class JsamsApplicationContext {

    public static final String CONFIG = "ApplicationContext.xml";

    private static ClassPathXmlApplicationContext context;

    /**
     * Constructor to avoid to instance this utility class.
     */
    private JsamsApplicationContext() {
    }

    /**
     * 
     * @param context
     *            the {@link ClassPathXmlApplicationContext} to set
     */
    public static void setContext(final ClassPathXmlApplicationContext context) {
        JsamsApplicationContext.context = context;
    }

    /**
     * 
     * @return the {@link SocietyService}
     */
    public static SocietyService getSocietyService() {
        return (SocietyService) context.getBean("societyService");
    }

    /**
     * 
     * @return the {@link LegalFormDao}
     */
    public static LegalFormDao getLegalFormDao() {
        return (LegalFormDao) context.getBean("legalFormDao");
    }

    /**
     * 
     * @return the {@link CivilityDao}
     */
    public static CivilityDao getCivilityDao() {
        return (CivilityDao) context.getBean("civilityDao");
    }

    /**
     * 
     * @return the {@link PaymentModeDao}
     */
    public static PaymentModeDao getPaymentModeDao() {
        return (PaymentModeDao) context.getBean("paymentModeDao");
    }

    /**
     * 
     * @return the {@link CustomerService}
     */
    public static CustomerService getCustomerService() {
        return (CustomerService) context.getBean("customerService");
    }

    /**
     * 
     * @return the {@link ProductCategoryDao}
     */
    public static ProductCategoryDao getProductCategoryDao() {
        return (ProductCategoryDao) context.getBean("productCategoryDao");
    }

    /**
     * 
     * @return the {@link ProductService}
     */
    public static ProductService getProductService() {
        return (ProductService) context.getBean("productService");
    }

    /**
     * 
     * @return the {@link ProductCategoryService}
     */
    public static ProductCategoryService getProductCategoryService() {
        return (ProductCategoryService) context.getBean("productCategoryService");
    }

    /**
     * 
     * @return the {@link EstimateService}
     */
    public static EstimateService getEstimateService() {
        return (EstimateService) context.getBean("estimateService");
    }

    /**
     * 
     * @return the {@link AgentService}
     */
    public static AgentService getAgentService() {
        return (AgentService) context.getBean("agentService");
    }

}
