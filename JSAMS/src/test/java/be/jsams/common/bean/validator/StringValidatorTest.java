package be.jsams.common.bean.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test the {@link StringValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class StringValidatorTest {

    /**
     * Test method for {@link be.jsams.common.validator.StringValidator#validate(java.lang.String)}.
     */
    @Test
    public void testValidate() {
        String toValidate = "BTS-Concept";
        assertTrue(StringValidator.validate(toValidate));
        toValidate = "BTS_Concept";
        assertFalse(StringValidator.validate(toValidate));
        toValidate = "Rue de mois d'août";
        assertTrue(StringValidator.validate(toValidate));
        toValidate = "ça alors?! & toi tu as parlé ? (ou pas)";
        assertTrue(StringValidator.validate(toValidate));
    }

}
