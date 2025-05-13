package com.UserManager.User.Manager.User;

import com.UserManager.User.Manager.Auth.LoginDTO;
import com.UserManager.User.Manager.Auth.RegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel toModel(UserDTO dto);
    UserDTO toDTO(UserModel model);

    UserModel fromRegisterDTO(RegisterDTO dto);
    RegisterDTO toRegisterDTO(UserModel model);

    UserModel fromLoginDTO(LoginDTO dto);
    LoginDTO toLoginDTO(UserModel model);

    UserResponseDTO toResponseDTO(UserModel model);
}
