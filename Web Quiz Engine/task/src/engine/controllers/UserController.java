package engine.controllers;

import engine.model.User;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {



    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/register")
    public void register(@Valid @RequestBody User user) {
        userService.save(user);
    }
}