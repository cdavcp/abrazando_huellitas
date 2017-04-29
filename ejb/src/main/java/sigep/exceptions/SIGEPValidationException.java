package sigep.exceptions;

import javax.validation.ValidationException;
import java.util.HashMap;

public class SIGEPValidationException extends ValidationException {
    private HashMap<String, String> errors;

    public SIGEPValidationException(HashMap<String, String> errors) {
        this.errors = errors;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}
