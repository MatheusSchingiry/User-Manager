package com.UserManager.User.Manager.Infra.Security;

import com.UserManager.User.Manager.User.UserModel;
import com.UserManager.User.Manager.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Busca o email
        UserModel user = this.repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        //retorna um objeto com: O e-mail como nome de usuário /  A senha já criptografada / Uma lista vazia de permissões/roles
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
