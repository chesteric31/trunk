package be.jsams.server.service.sale.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.dao.sale.DeliveryOrderDao;
import be.jsams.server.model.Society;
import be.jsams.server.model.sale.DeliveryOrder;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.sale.DeliveryOrderService;

/**
 * Delivery Order service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderServiceImpl extends AbstractService implements DeliveryOrderService {

    private DeliveryOrderDao deliveryOrderDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderBean create(DeliveryOrderBean bean) {
        DeliveryOrder deliveryOrder = new DeliveryOrder(bean);
        DeliveryOrder addingDeliveryOrder = deliveryOrderDao.add(deliveryOrder);
        return new DeliveryOrderBean(addingDeliveryOrder, bean.getSociety(), bean.getCustomer(),
                getProductBeanBuilder());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(DeliveryOrderBean bean) {
        DeliveryOrder deliveryOrder = deliveryOrderDao.findById(bean.getId());
        deliveryOrderDao.delete(deliveryOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderDao.findById(id);
        deliveryOrderDao.delete(deliveryOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(DeliveryOrderBean bean) {
        DeliveryOrder deliveryOrder = new DeliveryOrder(bean);
        deliveryOrderDao.update(deliveryOrder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderBean findById(Long id) {
        DeliveryOrder deliveryOrder = deliveryOrderDao.findById(id);
        if (deliveryOrder != null) {
            Society model = deliveryOrder.getCustomer().getSociety();
            SocietyBean society = new SocietyBean(model);
            society.setLegalForm(new LegalFormBean(model.getLegalForm()));
            CustomerBean customer = getCustomerBeanBuilder().build(deliveryOrder.getCustomer(), society);
            return new DeliveryOrderBean(deliveryOrder, society, customer, getProductBeanBuilder());
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DeliveryOrderBean> findAll(SocietyBean currentSociety) {
        List<DeliveryOrder> deliveryOrders = deliveryOrderDao.findAll(currentSociety.getId());
        List<DeliveryOrderBean> beans = new ArrayList<DeliveryOrderBean>();
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            CustomerBean customer = getCustomerBeanBuilder().build(deliveryOrder.getCustomer(), currentSociety);
            beans.add(new DeliveryOrderBean(deliveryOrder, currentSociety, customer, getProductBeanBuilder()));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DeliveryOrderBean> findByCriteria(DeliveryOrderBean criteria) {
        SocietyBean society = criteria.getSociety();
        List<DeliveryOrder> deliveryOrders = deliveryOrderDao.findByCriteria(society.getId(), criteria);
        List<DeliveryOrderBean> beans = new ArrayList<DeliveryOrderBean>();
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            CustomerBean customer = getCustomerBeanBuilder().build(deliveryOrder.getCustomer(), society);
            beans.add(new DeliveryOrderBean(deliveryOrder, society, customer, getProductBeanBuilder()));
        }
        return beans;
    }

    /**
     * @return the deliveryOrderDao
     */
    public DeliveryOrderDao getDeliveryOrderDao() {
        return deliveryOrderDao;
    }

    /**
     * @param deliveryOrderDao the deliveryOrderDao to set
     */
    public void setDeliveryOrderDao(DeliveryOrderDao deliveryOrderDao) {
        this.deliveryOrderDao = deliveryOrderDao;
    }

}
