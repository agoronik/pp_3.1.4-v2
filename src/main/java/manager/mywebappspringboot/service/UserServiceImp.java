package manager.mywebappspringboot.service;

import manager.mywebappspringboot.model.Role;
import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if(user.getPassword() == null) {
            User u = new User();
            u.setId(user.getId());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setAge(user.getAge());
            u.setEmail(user.getEmail());
            u.setRoles(user.getRoles());
            userRepository.save(u);
        } else {
            userRepository.save(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public User deleteUserById(Long id) {
        User user = getUserById(id);
        userRepository.deleteById(id);
        return user;
    }

    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
