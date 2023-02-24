package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.dto.UserDto;
import ro.utcn.ps.ono.assignment1.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<UserDto> findUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDto findUserById(@PathVariable("id") Integer id){
        return userService.findUserByUser_id(id);
    }

    @GetMapping(value = "/username/{username}")
    public UserDto findUserByTitle(@PathVariable("username") String username){
        return userService.findUserByUsername(username);
    }

    @PostMapping()
    public UserDto insert(@RequestBody UserDto userDto){
        return userService.insert(userDto);
    }

    @PutMapping()
    public UserDto update(@RequestBody UserDto userDto) {
        return userService.insert(userDto);
    }

//    @DeleteMapping(value="/{id}")
//    public void remove(@PathVariable("id") Integer id){
//        userService.remove(id);
//    }
}
