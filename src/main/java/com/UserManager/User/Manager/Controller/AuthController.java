package com.UserManager.User.Manager.Controller;

import com.UserManager.User.Manager.DTO.LoginDTO;
import com.UserManager.User.Manager.DTO.RegisterDTO;
import com.UserManager.User.Manager.DTO.ResponseDTO;
import com.UserManager.User.Manager.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid  @RequestBody LoginDTO dto){
        ResponseDTO responseDTO = authService.login(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterDTO dto){
        ResponseDTO responseDTO = authService.register(dto);
        return ResponseEntity.status(201).body(responseDTO);
    }
}
