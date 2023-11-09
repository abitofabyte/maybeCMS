package yes.no.maybeCMS.endpoints.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yes.no.maybeCMS.services.sercurity.JwtGenerator;
import yes.no.maybeCMS.services.users.CreateUserResult;
import yes.no.maybeCMS.services.users.UserNotFoundException;
import yes.no.maybeCMS.services.users.UserService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final UserService userService;
    private final JwtGenerator jwtGenerator;

    @GetMapping("authenticate")
    AuthenticationResponse getToken(Authentication authentication) throws UserNotFoundException {
        var user = userService.getByEmail(authentication.getName());
//        user.setLastLogin(LocalDateTime.now());
//        user = userService.update(user);

        return AuthenticationResponse.builder()
                .token(jwtGenerator.generate(authentication))
                .userId(user.getId())
                .handle(user.getHandle())
                .email(user.getEmail())
                .profilePicture(user.getProfilePicture())
                .authorities(user.getRoles())
                .build();
    }

    @PostMapping("register")
    ResponseEntity<CreateUserResult> register(@RequestBody RegisterUserRequest userData) {
        var result = userService.register(userData);
        var status = result.getUser().isPresent() ? HttpStatus.OK : HttpStatus.CONFLICT;

        return new ResponseEntity<>(result, status);
    }
}
