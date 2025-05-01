package com.UserManager.User.Manager.User;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository repository;
    public UserMapping mapping;

    public UserService(UserRepository repository, UserMapping mapping) {
        this.repository = repository;
        this.mapping = mapping;
    }

    public List<UserDTO> listUser(){
        List<UserModel> model = repository.findAll();
        return model.stream()
                .map(mapping::map)
                .collect(Collectors.toList());
    }

    public UserDTO listUserById(Long id){
        Optional<UserModel> model = repository.findById(id);
        return model.map(mapping::map).orElse(null);
    }

    public UserDTO addUser(UserDTO dto){
        UserModel model = mapping.map(dto);
        model = repository.save(model);
        return mapping.map(model);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }

    public UserDTO editUser(Long id, UserDTO dto){
        Optional<UserModel> model = repository.findById(id);
        if(model.isPresent()){
            dto.setId(id);
            UserModel userModel = mapping.map(dto);
            userModel = repository.save(userModel);
            return mapping.map(userModel);
        }
        return null;
    }
}
