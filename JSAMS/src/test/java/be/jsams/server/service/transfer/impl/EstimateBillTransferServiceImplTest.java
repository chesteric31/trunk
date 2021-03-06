package be.jsams.server.service.transfer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import be.jsams.common.bean.model.MockBeanGenerator;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.server.BaseJUnitTestClass;

/**
 * Test class for {@link EstimateBillTransferServiceImpl} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateBillTransferServiceImplTest extends BaseJUnitTestClass {

    @Autowired
    private EstimateBillTransferServiceImpl service;
    
    private TransferBean model;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        model = new TransferBean();
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.EstimateBillTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsFullTransferBean() {
        model.setSourceType(1);
        model.setDestinationType(1);
        model.setTransferMode(1);
        EstimateBean originalDocument = MockBeanGenerator.generateMockEstimate();
        List<EstimateBean> documents = new ArrayList<EstimateBean>();
        documents.add(originalDocument);
        model.setDocuments(documents);
        List<BillBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        BillBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getBillingAddress(), newDocument.getBillingAddress());
    }

    /**
     * Test method for {@link be.jsams.server.service.transfer.impl.EstimateBillTransferServiceImpl
     * #createNewDocuments(be.jsams.common.bean.model.transfer.TransferBean)}.
     */
    @Test
    public void testCreateNewDocumentsPartialTransferBean() {
        model.setSourceType(1);
        model.setDestinationType(1);
        model.setTransferMode(2);
        EstimateBean originalDocument = MockBeanGenerator.generateMockEstimate();
        List<EstimateBean> documents = new ArrayList<EstimateBean>();
        documents.add(originalDocument);
        Map<Long, List<EstimateDetailBean>> map = new HashMap<Long, List<EstimateDetailBean>>();
        List<EstimateDetailBean> list = new ArrayList<EstimateDetailBean>();
        list.addAll(originalDocument.getDetails());
        map.put(originalDocument.getId(), list);
        model.setEstimateDetails(map);
        List<BillBean> newDocuments = service.createNewDocuments(model);
        assertTrue(newDocuments != null && !newDocuments.isEmpty() && newDocuments.size() == 1);
        BillBean newDocument = newDocuments.get(0);
        assertEquals(originalDocument.getBillingAddress(), newDocument.getBillingAddress());
        assertEquals(originalDocument.getDetails().get(0).getProduct(), newDocument.getDetails().get(0).getProduct());
    }

}
