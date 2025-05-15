package com.UserManager.User.Manager.User;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/list_user")
    public ResponseEntity<@Valid List<UserDTO>> listUser(){
        return ResponseEntity.ok(service.listUser());
    }

    @GetMapping("/list_user/{id}")
    public ResponseEntity<?> listUserById(@PathVariable String id){
        UserDTO dto = service.listUserById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO dto){
        UserDTO newUser = service.addUser(dto);
        return ResponseEntity.status(201).body("Registered Successfully (ID: " + newUser.getId() + ")");
    }

    @PutMapping("/edit_user/{id}")
    public ResponseEntity<String> editUser(@PathVariable String id, @Valid @RequestBody UserDTO dto){
        service.editUser(id, dto);
        return ResponseEntity.ok("Successfully Updated");
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        service.deleteUser(id);
        return ResponseEntity.ok("Successfully Deleted");
    }
}
