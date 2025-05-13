package com.UserManager.User.Manager.Auth;

import com.UserManager.User.Manager.User.UserModel;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapping {

    public UserModel map(RegisterDTO dto){
        UserModel model = new UserModel();
        model.setName(dto.getName());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        return model;
    }

    public RegisterDTO map(UserModel model){
        RegisterDTO dto = new RegisterDTO();
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        return dto;
    }
}
