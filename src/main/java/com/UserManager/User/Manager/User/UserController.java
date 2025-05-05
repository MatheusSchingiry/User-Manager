package com.UserManager.User.Manager.User;

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
    public ResponseEntity<List<UserDTO>> listUser(){
        List<UserDTO> dto = service.listUser();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/list_user/{id}")
    public ResponseEntity<?> listUserById(@PathVariable Long id){
        UserDTO dto = service.listUserById(id);
        if(dto != null){
            return ResponseEntity.ok(dto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to view: Nonexistent ID");
        }
    }

    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@RequestBody UserDTO dto){
        UserDTO newDto = service.addUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registered Successfully (ID:" +newDto.getId() + ")");
    }

    @PutMapping("/edit_user/{id}")
    public ResponseEntity<String> editUser(@PathVariable Long id, @RequestBody UserDTO dto){
        UserDTO userDto = service.editUser(id, dto);
        if(userDto != null){
            return ResponseEntity.ok("Successfully Changed");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to Change: Nonexistent ID");
        }
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
