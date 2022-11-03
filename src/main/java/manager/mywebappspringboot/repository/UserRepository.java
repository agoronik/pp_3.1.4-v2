package manager.mywebappspringboot.repository;

import manager.mywebappspringboot.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
