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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthService authService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid  @RequestBody LoginDTO dto){
        ResponseDTO responseDTO = authService.login(dto);
        if(responseDTO != null){
            return ResponseEntity.ok(responseDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email or User Incorrect");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO dto){
        ResponseDTO responseDTO = authService.register(dto);
        if(responseDTO != null){
            return ResponseEntity.ok(responseDTO);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
