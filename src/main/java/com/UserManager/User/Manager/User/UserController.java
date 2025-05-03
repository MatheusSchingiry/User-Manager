package com.UserManager.User.Manager.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addUser(@RequestBody UserDTO dto){
        UserDTO newDto = service.addUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registered Successfully (ID:" +newDto.getId() + ")");
    }

    @PutMapping("/edit_user/{id}")
    public UserDTO editUser(@PathVariable Long id, @RequestBody UserDTO dto){
        return service.editUser(id, dto);
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        if(service.listUserById(id) != null){
            service.deleteUser(id);
            return ResponseEntity.ok("Successfully Deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to Delete: Nonexistent ID");
        }
    }
}
