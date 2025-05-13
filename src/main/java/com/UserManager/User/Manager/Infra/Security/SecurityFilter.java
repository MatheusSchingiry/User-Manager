package com.UserManager.User.Manager.Infra.Security;

import com.UserManager.User.Manager.User.UserModel;
import com.UserManager.User.Manager.User.UserRepository;
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

        //Solicita a validação do token recebido
        var login = tokenService.validateToken(token);

        //caso passe da validação entra nesse if
        if(login != null){
            //Recupera dados do BD
            UserModel user = userRepository.findByEmail(login).orElseThrow(() -> new UsernameNotFoundException("E-mail not found"));

            //Cria lista de permissao que o usuario pode fazer logado
            var authority = Collections.singletonList(new SimpleGrantedAuthority("Role_User"));

            //Cria um objeto de autenticado
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authority);

            //libera a autenticacao para acesso do spring via @AuthenticationPrincipal ou SecurityContextHolder.getContext().getAuthentication().
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //contiua o fluxo da requisão para nao haver trava
        filterChain.doFilter(request, response);
    }

    //Metodo para realiza a recuperação do token
    private String recoverytoken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null){ return null; }
        return authHeader.replace("Bearer ", "");
    }
}
