package app.blogging.blogging_application.exceptions;

public class FetchException extends RuntimeException{
    public FetchException(String message){
        super(message);
    }
}
