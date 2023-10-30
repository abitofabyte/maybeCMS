package yes.no.maybeCMS.services.users;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.hibernate.JDBCException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.endpoints.authentication.RegisterUserRequest;
import yes.no.maybeCMS.entities.users.User;
import yes.no.maybeCMS.securtiy.Role;
import yes.no.maybeCMS.services.shop.products.ProductRepository;
import yes.no.maybeCMS.services.shop.products.ProductService;

import javax.swing.text.html.Option;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User getById(UUID id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void delete(UUID id) throws UserNotFoundException {
        var user = getById(id);
        productRepository.deleteAllBySeller(user);
        userRepository.delete(user);
    }

    @Transactional
    public User update(User user) throws UserNotFoundException {
        var dbUser = getById(user.getId());
        dbUser.setHandle(user.getHandle());
        dbUser.setEmail(user.getEmail());
        return userRepository.save(dbUser);
    }

    @Transactional
    public User setUserLastLogin(User user, LocalDateTime lastLogin) throws UserNotFoundException {
        var dbUser = getById(user.getId());
        dbUser.setLastLogin(lastLogin);
        return userRepository.save(dbUser);
    }

    @Transactional
    public User setUserDisabled(User user, boolean isDisabled) throws UserNotFoundException {
        var dbUser = getById(user.getId());
        dbUser.setDisabled(isDisabled);
        return userRepository.save(dbUser);
    }

    public CreateUserResult register(RegisterUserRequest data) {
        final var hash = passwordEncoder.encode(data.getPassword());
        final var user = new User();

        user.setEmail(data.getEmail());
        user.setHandle(data.getHandle());
        user.setPassword(hash);
        user.setRoles(Set.of(Role.AWAITING_CONFIRMATION));
        //todo: send confirmation email;

        try {
            final var dbUser = userRepository.save(user);
            return new CreateUserResult(dbUser);
        } catch (DataIntegrityViolationException exception) {
            System.out.println(exception);
            var byEmail = userRepository.findUserByEmail(data.getEmail());
            return new CreateUserResult(byEmail.isPresent());
        }
    }
}
