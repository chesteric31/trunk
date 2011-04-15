package be.jsams.server.dao;

import java.util.ArrayList;
import java.util.List;

import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

/**
 * Generator of Mock DAOs.
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
public class MockDaoGenerator {

    public static LegalFormDao generateMockLegalForm(final Society society) {
        return new LegalFormDao() {

            @Override
            public void update(LegalForm transientObject) {
                // TODO Auto-generated method stub

            }

            @Override
            public void flush() {
                // TODO Auto-generated method stub

            }

            @Override
            public LegalForm findById(Long id) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public List<LegalForm> findAll() {
                List<LegalForm> forms = new ArrayList<LegalForm>();
                LegalForm form = society.getLegalForm();
                if (form != null) {
                    forms.add(society.getLegalForm());
                }
                return forms;
            }

            @Override
            public void delete(Long id) {
                // TODO Auto-generated method stub

            }

            @Override
            public void delete(LegalForm persistentObject) {
                // TODO Auto-generated method stub

            }

            @Override
            public LegalForm add(LegalForm newInstance) {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }

}