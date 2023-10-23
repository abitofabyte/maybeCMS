package yes.no.maybeCMS.services.users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.entities.users.User;
import yes.no.maybeCMS.services.shop.products.ProductRepository;
import yes.no.maybeCMS.services.shop.products.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
}
