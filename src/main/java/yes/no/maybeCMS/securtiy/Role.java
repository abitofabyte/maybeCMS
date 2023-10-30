package yes.no.maybeCMS.securtiy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum Role {
    AWAITING_CONFIRMATION,
    REGISTERED,
    SELLER,
    ADMIN;

    public String asAuthority() {
        return "SCOPE_" + name();
    }
}
