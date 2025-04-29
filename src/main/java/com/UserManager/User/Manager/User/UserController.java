package com.UserManager.User.Manager.User;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @GetMapping("/list_user")
    public String listUser(){
        return "LIST_USER";
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
