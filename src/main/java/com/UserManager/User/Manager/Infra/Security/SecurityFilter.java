package com.UserManager.User.Manager.Infra.Security;

import com.UserManager.User.Manager.Entity.UserModel;
import com.UserManager.User.Manager.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverytoken(request);
        var login = tokenService.validateToken(token);

        if(login != null){
            UserModel user = userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("E-mail not found"));
            var authority = Collections.singletonList(new SimpleGrantedAuthority("Role_User"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authority);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    private String recoverytoken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null){ return null; }
        return authHeader.replace("Bearer ", "");
    }
}
