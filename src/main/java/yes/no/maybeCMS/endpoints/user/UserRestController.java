package yes.no.maybeCMS.endpoints.user;

import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.users.User;
import yes.no.maybeCMS.services.users.UserNotFoundException;
import yes.no.maybeCMS.services.users.UserRepository;
import yes.no.maybeCMS.services.users.UserService;
import yes.no.maybeCMS.services.validation.Uuid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    private List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    private User getById(@Uuid @PathVariable UUID id) throws UserNotFoundException {
        return userService.getById(id);
    }

    @GetMapping("email/{email}")
    private User getByEmail(@Email @PathVariable String email) throws UserNotFoundException {
        return userService.getByEmail(email);
    }

    @PostMapping
    private User create(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("{id}")
    private void delete(@Uuid @PathVariable UUID id) throws UserNotFoundException {
        userService.delete(id);
    }

    @PatchMapping
    private User update(@RequestBody User user) throws UserNotFoundException {
        return userService.update(user);
    }
}
