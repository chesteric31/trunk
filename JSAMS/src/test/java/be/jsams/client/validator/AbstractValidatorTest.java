package be.jsams.client.validator;

import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;

/**
 * Abstract class to generalize all common methods of some validator test.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractValidatorTest {

    /**
     * @param result the {@link ValidationResult} to parse
     * @return the formatted text message
     */
    protected String retrieveFormattedText(ValidationResult result) {
        ValidationMessage message = result.getMessages().get(0);
        String formattedText = message.formattedText();
        return formattedText;
    }

}
