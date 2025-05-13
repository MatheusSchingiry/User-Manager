package com.UserManager.User.Manager.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;
    public final UserMapper mapping;

    public UserService(UserRepository repository, UserMapper mapping) {
        this.repository = repository;
        this.mapping = mapping;
    }

    public List<UserDTO> listUser(){
        List<UserModel> model = repository.findAll();
        return model.stream()
                .map(mapping::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO listUserById(String id){
        Optional<UserModel> model = repository.findById(id);
        return model.map(mapping::toDTO).orElse(null);
    }

    public UserDTO addUser(UserDTO dto){
        UserModel model = mapping.toModel(dto);
        model = repository.save(model);
        return mapping.toDTO(model);
    }

    public void deleteUser(String id){
        repository.deleteById(id);
    }

    public UserDTO editUser(String id, UserDTO dto){
        Optional<UserModel> model = repository.findById(id);
        if(model.isPresent()){
            UserModel existingUser = model.get();

            if (dto.getName() == null) {
                dto.setName(existingUser.getName());
            }
            if (dto.getEmail() == null) {
                dto.setEmail(existingUser.getEmail());
            }
            if (dto.getPassword() == null) {
                dto.setPassword(existingUser.getPassword());
            }

            dto.setId(id);
            UserModel userModel = mapping.toModel(dto);
            userModel = repository.save(userModel);
            return mapping.toDTO(userModel);
        }
        return null;
    }
}
