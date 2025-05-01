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
    public List<UserModel> listUser(){
        return service.listUser();
    }

    @GetMapping("/list_user/{id}")
    public UserModel listUserById(@PathVariable Long id){
        return service.listUserById(id);
    }

    @PostMapping("/add_user")
    public String addUser(){
        return "ADD_USER";
    }
    @PutMapping("/edit_user")
    public String editUser(){
        return "EDIT_USER";
    }
    @DeleteMapping("/delete_user")
    public String deleteUser(){
        return "DELETE_USER";
    }
}
