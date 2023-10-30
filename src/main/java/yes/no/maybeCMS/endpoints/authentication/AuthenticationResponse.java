package yes.no.maybeCMS.endpoints.authentication;

import lombok.*;
import yes.no.maybeCMS.securtiy.Role;

import java.util.Set;
import java.util.UUID;

@Getter
@Builder
public class AuthenticationResponse {
    private final String token;
    private final UUID  userId;
    private final String handle;
    private final String email;
    private final String profilePicture;
    private final Set<Role> authorities;
}
