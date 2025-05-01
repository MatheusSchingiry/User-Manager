package com.UserManager.User.Manager.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    public UserModel map(UserDTO dto){
        UserModel model = new UserModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        return model;
    }

    public UserDTO map(UserModel model){
        UserDTO dto = new UserDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        return dto;
    }
}
