package yes.no.maybeCMS.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationErrorResponse {
    private final List<Violation> violations;

}
