package com.UserManager.User.Manager.Auth;

import com.UserManager.User.Manager.Infra.Security.TokenService;
import com.UserManager.User.Manager.User.UserDTO;
import com.UserManager.User.Manager.User.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
