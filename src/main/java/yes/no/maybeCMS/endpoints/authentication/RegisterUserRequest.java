package yes.no.maybeCMS.endpoints.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterUserRequest {
    @Size(min = 3)
    private String handle;
    @NotNull
    @Email
    private String email;
    @Size(min = 8, max = 64)
    private String password;
}
