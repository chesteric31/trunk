package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.model.management.Product;
import be.jsams.server.service.management.ProductService;

/**
 * Product service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao dao;
    private ProductBeanBuilder builder;

    /**
     * 
     * @return the {@link ProductDao}
     */
    public ProductDao getProductDao() {
        return dao;
    }

    /**
     * 
     * @param productDao the {@link ProductDao} to set
     */
    public void setProductDao(ProductDao productDao) {
        this.dao = productDao;
    }

    /**
     * {@inheritDoc}
     */
    public ProductBean create(final ProductBean bean) {
        Product product = new Product(bean);
        dao.add(product);
        builder.setModel(product);
        builder.setSociety(bean.getCategory().getSociety());
        return builder.build(false, false);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final ProductBean bean) {
        Product product = new Product(bean);
        dao.delete(product);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductBean> findAll(SocietyBean currentSociety) {
        List<Product> products = dao.findAll(currentSociety.getId());
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for (Product product : products) {
            builder.setModel(product);
            builder.setSociety(currentSociety);
            ProductBean productBean = builder.build(false, false);
            beans.add(productBean);
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductBean> findByCriteria(final ProductBean criteria) {
        SocietyBean society = criteria.getCategory().getSociety();
        List<Product> products = dao.findByCriteria(society.getId(), criteria);
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for (Product product : products) {
            builder.setModel(product);
            builder.setSociety(society);
            ProductBean productBean = builder.build(false, false);
            beans.add(productBean);
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public ProductBean findById(final Long id) {
        Product product = dao.findById(id);
        if (product != null) {
            builder.setModel(product);
            return builder.build(false, false);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(final ProductBean bean) {
        Product product = new Product(bean);
        dao.update(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Long, Double> findTop5ProductsWithBills(SocietyBean society) {
        List<Object[]> products = dao.findTop5ProductsWithBills(society.getId());
        return buildProductsMap(products);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Long, Double> findTop5ProductsWithCreditNotes(SocietyBean society) {
        List<Object[]> products = dao.findTop5ProductsWithCreditNotes(society.getId());
        return buildProductsMap(products);
    }

    /**
     * Builds products map.
     * 
     * @param products the list of amount and id to use
     * @return the built map
     */
    private Map<Long, Double> buildProductsMap(List<Object[]> products) {
        Map<Long, Double> map = new LinkedHashMap<Long, Double>();
        if (products != null && !products.isEmpty()) {
            for (Object[] object : products) {
                Long id = (Long) object[0];
                Double amount = (Double) object[1];
                map.put(id, amount);
            }
        }
        return map;
    }

    /**
     * @return the builder
     */
    public ProductBeanBuilder getBuilder() {
        return builder;
    }

    /**
     * @param builder the builder to set
     */
    public void setBuilder(ProductBeanBuilder builder) {
        this.builder = builder;
    }

    /**
     * @return the dao
     */
    public ProductDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ProductDao dao) {
        this.dao = dao;
    }

}
