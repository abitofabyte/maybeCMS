package yes.no.maybeCMS.services.sercurity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.services.users.UserNotFoundException;
import yes.no.maybeCMS.services.users.UserService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtGenerator {
    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    public String generate(Authentication authentication) throws UserNotFoundException {
        var now = Instant.now();

        var user = userService.getByEmail(authentication.getName());

        var scope = user.getRoles().stream().map(Enum::name).collect(Collectors.joining(" "));
        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
