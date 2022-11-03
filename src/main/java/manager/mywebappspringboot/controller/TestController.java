package manager.mywebappspringboot.controller;

import manager.mywebappspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private UserRepository repo;

    @GetMapping("")
    public String main(ModelMap model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());

        model.addAttribute("pagetitle", "Test main");
        System.out.println("user in test page");

        String role = "ADMIN";

        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                //break;
            }
        }

        return "test";
    }

    @GetMapping("/1")
    public String main_page_1(ModelMap model, Authentication authentication) {
        model.addAttribute("pagetitle", "Test main 1");
        System.out.println("user in test page 1");
        return "test";
    }

    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            System.out.println(role);
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }
}


