package be.jsams.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.EstimateBean;
import be.jsams.server.dao.EstimateDao;
import be.jsams.server.dao.EstimateDetailDao;
import be.jsams.server.model.Estimate;
import be.jsams.server.model.EstimateDetail;
import be.jsams.server.service.EstimateService;

/**
 * Estimate service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateServiceImpl implements EstimateService {
    
    private EstimateDao estimateDao;
    private EstimateDetailDao estimateDetailDao;
    
    /**
     * @return the estimateDao
     */
    public EstimateDao getEstimateDao() {
        return estimateDao;
    }

    /**
     * @param estimateDao the estimateDao to set
     */
    public void setEstimateDao(EstimateDao estimateDao) {
        this.estimateDao = estimateDao;
    }

    /**
     * @return the estimateDetailDao
     */
    public EstimateDetailDao getEstimateDetailDao() {
        return estimateDetailDao;
    }

    /**
     * @param estimateDetailDao the estimateDetailDao to set
     */
    public void setEstimateDetailDao(EstimateDetailDao estimateDetailDao) {
        this.estimateDetailDao = estimateDetailDao;
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean create(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        estimateDao.add(estimate);
        return new EstimateBean(estimate);
        // TODO
//        List<EstimateDetail> details = estimate.getDetails();
//        for (EstimateDetail detail : details) {
//            estimateDetailDao.add(detail);
//        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        List<EstimateDetail> details = estimate.getDetails();
        for (EstimateDetail detail : details) {
            estimateDetailDao.delete(detail);
        }
        estimateDao.delete(estimate);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        Estimate estimate = estimateDao.findById(id);
        List<EstimateDetail> details = estimate.getDetails();
        for (EstimateDetail detail : details) {
            estimateDetailDao.delete(detail);
        }
        estimateDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<EstimateBean> findAll() {
        List<Estimate> estimates = estimateDao.findAll();
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        for (Estimate estimate : estimates) {
            beans.add(new EstimateBean(estimate));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public EstimateBean findById(final Long id) {
        Estimate estimate = estimateDao.findById(id);
        EstimateBean bean = new EstimateBean(estimate);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final EstimateBean bean) {
        Estimate estimate = new Estimate(bean);
        estimateDao.update(estimate);
    }

}
