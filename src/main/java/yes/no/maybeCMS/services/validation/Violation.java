package yes.no.maybeCMS.services.validation;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Getter
public class Violation {
    private final String fieldName;
    private final String message;

    public Violation(ConstraintViolation<?> violation) {
        this.fieldName = violation.getPropertyPath().toString();
        this.message = violation.getMessage();
    }

    public Violation(FieldError fieldError) {
        this.fieldName = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }

    public Violation(MethodArgumentTypeMismatchException exception) {
        this.fieldName = exception.getParameter().toString();
        this.message = exception.getMessage();

    }
}
