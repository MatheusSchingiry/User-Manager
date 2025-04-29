package com.UserManager.User.Manager.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserModel> listUser(){
        return repository.findAll();
    }
}
