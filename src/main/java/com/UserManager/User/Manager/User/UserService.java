package com.UserManager.User.Manager.User;


import com.UserManager.User.Manager.Exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return repository.findAll().stream()
                .map(mapping::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO listUserByEmail(String email){
        return repository.findByEmail(email).map(mapping::toDTO).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + email));
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
        UserModel existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        if (dto.getName() == null) dto.setName(existingUser.getName());
        if (dto.getEmail() == null) dto.setEmail(existingUser.getEmail());
        if (dto.getPassword() == null) dto.setPassword(existingUser.getPassword());

        dto.setId(id);
        UserModel updatedUser = repository.save(mapping.toModel(dto));
        return mapping.toDTO(updatedUser);
    }
}
