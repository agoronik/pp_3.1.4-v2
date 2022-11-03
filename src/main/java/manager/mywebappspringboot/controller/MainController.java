package manager.mywebappspringboot.controller;

import manager.mywebappspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository repo;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    /*
    @GetMapping("/login")
    public String userLogin() {
        return "login";
    }
    * */

}
