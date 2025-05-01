package com.UserManager.User.Manager.User;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserModel> listUser(){
        return repository.findAll();
    }

    public UserModel listUserById(Long id){
        Optional<UserModel> userbyId = repository.findById(id);
        return userbyId.orElse(null);
    }

    public UserModel addUser(UserModel model){
        return repository.save(model);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
