package yes.no.maybeCMS.services.users;

import lombok.Getter;
import yes.no.maybeCMS.entities.users.User;

import java.util.Optional;

@Getter
public class CreateUserResult {
    private final Optional<User> user;
    private final Optional<Boolean> isEmailExists;

    public CreateUserResult(User user) {
        this.user = Optional.of(user);
        this.isEmailExists = Optional.empty();
    }

    public CreateUserResult(boolean isEmailExists) {
        this.user = Optional.empty();
        this.isEmailExists = Optional.of(isEmailExists);
    }
}
