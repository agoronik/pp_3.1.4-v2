package manager.mywebappspringboot.controller;

import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.service.UserServiceImp;
import manager.mywebappspringboot.service.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp service;
    private RoleServiceImp roleService;


    @GetMapping(value = "/user")
    public String showUSersList(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = service.getUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);

        return "page_users";
    }

}
