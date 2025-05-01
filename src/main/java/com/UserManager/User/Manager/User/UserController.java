package com.UserManager.User.Manager.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/list_user")
    public List<UserDTO> listUser(){
        return service.listUser();
    }

    @GetMapping("/list_user/{id}")
    public UserDTO listUserById(@PathVariable Long id){
        return service.listUserById(id);
    }

    @PostMapping("/add_user")
    public UserDTO addUser(@RequestBody UserDTO dto){
        return service.addUser(dto);
    }

    @PutMapping("/edit_user/{id}")
    public UserDTO editUser(@PathVariable Long id, @RequestBody UserDTO dto){
        return service.editUser(id, dto);
    }

    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
