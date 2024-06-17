package bookstore_api.services.exeptions;

public class DataIntegrityViolationExcepition extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationExcepition(String message) {
        super(message);
    }

    public DataIntegrityViolationExcepition(String message, Throwable cause) {
        super(message, cause);
    }


}
