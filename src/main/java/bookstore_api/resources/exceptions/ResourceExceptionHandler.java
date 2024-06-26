package bookstore_api.resources.exceptions;

import bookstore_api.services.exeptions.DataIntegrityViolationExcepition;
import bookstore_api.services.exeptions.ObjectNotFoundException;
import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandarError> objectNotFoundException(ObjectNotFoundException e, ServletRequest servletRequest){
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationExcepition.class)
    public ResponseEntity<StandarError> dataIntegrityViolationExcepition(DataIntegrityViolationExcepition e, ServletRequest servletRequest){
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandarError> validationErrors(MethodArgumentNotValidException e, ServletRequest servletRequest){
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Erro na validação dos campos!");

        for(FieldError x: e.getBindingResult().getFieldErrors()){
            error.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
