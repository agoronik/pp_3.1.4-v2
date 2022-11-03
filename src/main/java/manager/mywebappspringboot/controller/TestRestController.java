package manager.mywebappspringboot.controller;

import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.repository.RoleRepository;
import manager.mywebappspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
public class TestRestController {

    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public String index() {
        int id = 1;

        return "index";

    }

}
