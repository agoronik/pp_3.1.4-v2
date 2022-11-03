package manager.mywebappspringboot.service;

import manager.mywebappspringboot.model.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);

    Role getRoleByName(String name);

    Role getRoleById(Long id);

    List<Role> allRoles();

    Role getDefaultRole();
}
