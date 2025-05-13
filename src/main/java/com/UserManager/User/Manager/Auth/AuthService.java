package com.UserManager.User.Manager.Auth;

import com.UserManager.User.Manager.Infra.Security.TokenService;
import com.UserManager.User.Manager.User.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserService userService;
    private final UserMapping userMapping;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, UserService userService, UserMapping userMapping) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userService = userService;
        this.userMapping = userMapping;
    }

    public ResponseDTO login(LoginDTO dto){
        UserModel user = userRepository.findByEmail(dto.getEmail()).orElse(null);

        if(passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            String token = tokenService.generateToken(user);
            return new ResponseDTO(user.getName(), token);
        }
        else { return null; }
    }

    public ResponseDTO register(RegisterDTO dto){
        Optional<UserModel> user = userRepository.findByEmail(dto.getEmail());
        if(user.isEmpty()){
            UserModel newUser = new UserModel();
            newUser.setName(dto.getName());
            newUser.setEmail(dto.getEmail());
            newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

            UserDTO userDTO = userMapping.map(newUser);

            userService.addUser(userDTO);

            String token = tokenService.generateToken(newUser);
            return new ResponseDTO(newUser.getName(), token);
        }
        return null;
    }
}