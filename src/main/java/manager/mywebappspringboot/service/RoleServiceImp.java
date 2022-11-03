package manager.mywebappspringboot.service;

import manager.mywebappspringboot.model.Role;
import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.repository.RoleRepository;
import manager.mywebappspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRoleByName(String name) {
        Role role = roleRepository.findByName(name);
        return role;
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    public List<Role> allRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role getDefaultRole() {
        return getRoleByName("USER");
    }

}
