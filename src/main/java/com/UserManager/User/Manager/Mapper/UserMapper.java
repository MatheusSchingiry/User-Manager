package com.UserManager.User.Manager.Mapper;

import com.UserManager.User.Manager.DTO.LoginDTO;
import com.UserManager.User.Manager.DTO.RegisterDTO;
import com.UserManager.User.Manager.DTO.UserDTO;
import com.UserManager.User.Manager.DTO.UserResponseDTO;
import com.UserManager.User.Manager.Entity.UserModel;
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
