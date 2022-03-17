package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.service.UserServices;


@RestController
@CrossOrigin
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserServices userService;

    @PostMapping
    public void login(@RequestBody String username, @RequestBody String password){
        userService.login(username, password);
    }

}
