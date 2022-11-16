package manager.mywebappspringboot.controller;

import manager.mywebappspringboot.model.User;
import manager.mywebappspringboot.repository.RoleRepository;
import manager.mywebappspringboot.repository.UserRepository;
import manager.mywebappspringboot.service.UserService;
import manager.mywebappspringboot.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImp userService;
    private RoleRepository roleRepository;


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id") long id) {
        userRepository.deleteById(id);
        return "ok";
    }

    @PostMapping("/{id}")
    @ResponseBody
    public String saveUser(@Validated @RequestBody User user, @PathVariable(value = "id") long id) {
        user.setId(id);
        userService.updateUser(user);
        return "ok";
    }
}
