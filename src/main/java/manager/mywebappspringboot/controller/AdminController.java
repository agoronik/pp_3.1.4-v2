package manager.mywebappspringboot.controller;

import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.service.RoleServiceImp;
import manager.mywebappspringboot.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.security.Principal;
import java.util.List;

//@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserServiceImp service;
    private RoleServiceImp roleService;

    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.service = userService;
        this.roleService = roleService;
    }

    @GetMapping({"/admin"})
    public String adminPage(ModelMap model, Principal principal) {

        model.addAttribute("userBlank", new User());
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("allRoles", roleService.allRoles());
        model.addAttribute("defaultRole", roleService.getDefaultRole());
        return "page_admin";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes ra) {
        if(user.getId() == null) {
            service.createUser(user);
        } else {
            service.updateUser(user);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable(value = "id", required = true) long id, Model model) {
        if (service.getUserById(id) == null) {
            return "redirect:/admin";
        }

        model.addAttribute("allRoles", roleService.allRoles());
        model.addAttribute("user", service.getUserById(id));
        return "form_user";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable(value = "id", required = true) long id, Model model) {
        if(service.getUserById(id) != null) {
            service.deleteUserById(id);
        }
        return "redirect:/admin";
    }

}
