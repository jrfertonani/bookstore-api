package bookstore_api.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandarError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public ValidationError() {
        super();
    }

    public void addError(String fieldName, String message){
        this.errors.add(new FieldMessage(fieldName,message));
    }
}
