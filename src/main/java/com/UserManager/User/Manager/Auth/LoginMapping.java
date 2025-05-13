package com.UserManager.User.Manager.Auth;

import com.UserManager.User.Manager.User.UserDTO;
import com.UserManager.User.Manager.User.UserModel;
import org.springframework.stereotype.Component;

@Component
public class LoginMapping {

    public UserModel map(LoginDTO dto){
        UserModel model = new UserModel();
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        return model;
    }

    public LoginDTO map(UserModel model){
        LoginDTO dto = new LoginDTO();
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        return dto;
    }
}
