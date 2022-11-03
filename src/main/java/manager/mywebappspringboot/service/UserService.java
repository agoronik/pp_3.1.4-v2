package manager.mywebappspringboot.service;

import manager.mywebappspringboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    User getUserById(Long id);

    User deleteUserById(Long id);

    Optional<User> findUserByEmail(String email);

    User getUserByEmail(String email);

}
