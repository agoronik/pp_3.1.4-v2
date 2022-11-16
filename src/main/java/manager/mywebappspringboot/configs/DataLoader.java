package manager.mywebappspringboot.configs;

import manager.mywebappspringboot.model.Role;
import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.repository.RoleRepository;
import manager.mywebappspringboot.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        LoadUsers();
    }

    private void LoadUsers() {

        long id = 1;
        Optional<User> u = userRepository.findById(id);
        if(u.isEmpty()) {


            Set<Role> roles = new HashSet<>();

            Role role_admin = new Role();
            role_admin.setName("ADMIN");

            Role role_user = new Role();
            role_user.setName("USER");

            roles.add(role_admin);
            roles.add(role_user);
            roleRepository.save(role_admin);
            roleRepository.save(role_user);


            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Istrator");
            user.setEmail("admin@mail.ru");
            user.setPassword(passwordEncoder.encode("1111"));
            user.setAge(25);
            user.setRoles(roles);
            userRepository.save(user);
        }


    }
}
