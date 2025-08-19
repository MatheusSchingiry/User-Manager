package com.UserManager.User.Manager.Service;

import com.UserManager.User.Manager.DTO.LoginDTO;
import com.UserManager.User.Manager.DTO.RegisterDTO;
import com.UserManager.User.Manager.DTO.ResponseDTO;
import com.UserManager.User.Manager.Entity.UserModel;
import com.UserManager.User.Manager.Exception.InvalidCredentialsException;
import com.UserManager.User.Manager.Exception.UserAlreadyExistsException;
import com.UserManager.User.Manager.Infra.Security.TokenService;
import com.UserManager.User.Manager.Mapper.UserMapper;
import com.UserManager.User.Manager.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserService userService;
    private final UserMapper userMapping;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, UserService userService, UserMapper userMapping) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userService = userService;
        this.userMapping = userMapping;
    }

    public ResponseDTO login(LoginDTO dto){
        UserModel user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Credentials Invalid"));

        if(passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            String token = tokenService.generateToken(user);
            return new ResponseDTO(user.getName(), token);
        }
        else { throw new InvalidCredentialsException("Invalid email or password");
        }
    }

    public ResponseDTO register(RegisterDTO dto){
        Optional<UserModel> user = userRepository.findByEmail(dto.getEmail());
        if(user.isPresent()){
            throw new UserAlreadyExistsException("Email is already in use");
        }

        UserModel newUser = creationUserFromDTO(dto);
        userService.addUser(userMapping.toDTO(newUser));

        String token = tokenService.generateToken(newUser);
        return new ResponseDTO(newUser.getName(), token);
    }

    private UserModel creationUserFromDTO(RegisterDTO dto){
        UserModel newUser = new UserModel();
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        return newUser;
    }
}