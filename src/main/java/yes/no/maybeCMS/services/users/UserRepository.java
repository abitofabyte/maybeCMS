package yes.no.maybeCMS.services.users;

import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.users.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findCmsUserByEmail(String email);
}
