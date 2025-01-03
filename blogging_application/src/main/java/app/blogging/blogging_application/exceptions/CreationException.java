package app.blogging.blogging_application.exceptions;

public class CreationException extends RuntimeException {
    public CreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
