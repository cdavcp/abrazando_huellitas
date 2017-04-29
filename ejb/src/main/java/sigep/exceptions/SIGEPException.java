package sigep.exceptions;

import java.io.Serializable;

public class SIGEPException extends Exception implements Serializable {

    private static final long serialVersionUID = 796770993296843510L;
    Exception exception;

    public SIGEPException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
