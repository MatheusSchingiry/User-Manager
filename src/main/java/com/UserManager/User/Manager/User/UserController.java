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
    public UserModel addUser(@RequestBody UserModel model){
        return service.addUser(model);
    }

    @PutMapping("/edit_user/{id}")
    public UserModel editUser(@PathVariable Long id, @RequestBody UserModel model){
        return service.editUser(id, model);
    }

    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
