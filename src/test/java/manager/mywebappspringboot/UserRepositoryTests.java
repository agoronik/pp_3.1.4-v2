package manager.mywebappspringboot;


import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("test2@emal.com");
        user.setFirstName("FirstName");
        user.setLastName("LastName");

        User savedUser = repo.save(user);

        System.out.println(savedUser.toString());

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUser() {
        Long id = (long) 1;
        Optional<User> optionalUser = repo.findById(id);
        User user = optionalUser.get();
        user.setEmail("test@test.test");
        repo.save(user);

        User updatedUser = repo.findById(id).get();
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("test@test.test");
    }

    @Test
    public void testGet() {
        Long id = (long) 1;
        Optional<User> optionalUser = repo.findById(id);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Long id = (long) 1;
        repo.deleteById(id);

        Optional<User> optionalUser = repo.findById(id);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

}
