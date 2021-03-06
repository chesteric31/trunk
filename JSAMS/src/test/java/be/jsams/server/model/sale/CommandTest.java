package be.jsams.server.model.sale;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.server.model.ModelTestHelper;
import be.jsams.server.model.sale.detail.CommandDetail;

/**
 * Test class for {@link Command} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandTest {

    private Command command;

    /**
     * Test method for
     * {@link be.jsams.server.model.sale.Command#Command(be.jsams.common.bean.model.sale.CommandBean)}
     * .
     */
    @Test
    public void testCommandCommandBean() {
        CommandBean bean = MockBeanGenerator.generateMockCommand();
        command = new Command(bean);
        ModelTestHelper.checkAgent(bean.getAgent(), command.getAgent());
        ModelTestHelper.checkAddress(bean.getBillingAddress(), command.getBillingAddress());
        assertEquals(bean.getCreationDate(), command.getCreationDate());
        ModelTestHelper.checkCustomer(bean.getCustomer(), command.getCustomer());
        ModelTestHelper.checkAddress(bean.getDeliveryAddress(), command.getDeliveryAddress());
        assertEquals(bean.getDiscountRate(), command.getDiscountRate());
        assertEquals(bean.isTransferred(), command.isTransferred());
        assertEquals(bean.getRemark(), command.getRemark());
        List<CommandDetail> details = command.getDetails();
        List<CommandDetailBean> detailsBean = bean.getDetails();
        if (details != null && !details.isEmpty()) {
            int i = 0;
            for (CommandDetail detail : details) {
                CommandDetailBean detailBean = detailsBean.get(i);
                assertEquals(detailBean.getDiscountRate(), detail.getDiscountRate());
                assertEquals(detailBean.getId(), detail.getId());
                assertEquals(detailBean.getPrice(), detail.getPrice());
                ModelTestHelper.checkProduct(detailBean.getProduct(), detail.getProduct());
                assertEquals(detailBean.getQuantity(), detail.getQuantity());
                assertEquals(detailBean.getVatApplicable(), detail.getVatApplicable());
                assertEquals(detailBean.isTransferred(), detail.isTransferred());
                i++;
            }
        }
    }

}
