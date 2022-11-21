package multimodule.exception;


public class EventAlreadyExistException extends ServiceException {

    private static final String DEFAULT_MESSAGE = "User not found";

    public EventAlreadyExistException(String message) {
        super(message);
    }

    public EventAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
