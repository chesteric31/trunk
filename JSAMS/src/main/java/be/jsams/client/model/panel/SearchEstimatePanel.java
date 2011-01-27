package be.jsams.client.model.panel;

import javax.swing.JPanel;

import be.jsams.client.swing.listener.EstimateMouseTableListener;
import be.jsams.server.model.Estimate;
import be.jsams.server.service.EstimateService;

/**
 * Search {@link JPanel} for Estimate objects.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchEstimatePanel extends SearchPanel<Estimate, EstimateMouseTableListener, EstimateService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7701480812937524634L;

    /**
     * Constructor.
     * 
     * @param m the {@link Estimate}
     * @param l the {@link EstimateMouseTableListener}
     * @param s the {@link EstimateService}
     */
    public SearchEstimatePanel(Estimate m, EstimateMouseTableListener l, EstimateService s) {
        super(m, l, s);
    }
    
    @Override
    protected JPanel buildSearchCriteriaPanel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void performButtonAdd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void performButtonModify() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void performButtonRemove() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void performOk() {
        // TODO Auto-generated method stub
        
    }

}