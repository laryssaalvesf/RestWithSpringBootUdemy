package br.com.rest.spring.controller;


import br.com.rest.spring.domain.entities.User;
import br.com.rest.spring.domain.repositories.UserRepository;
import br.com.rest.spring.security.AccountCredentialsVO;
import br.com.rest.spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }


    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody AccountCredentialsVO data) {
        try {

            String userName = data.getUserName();
            String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = userRepository.findByUserName(userName);

            String token = "";

            if(Objects.nonNull(user)) {

                token = jwtTokenProvider.createToken(userName, user.roles());
            } else {
                throw new UsernameNotFoundException("Username " + userName + " not found");
            }

            Map<Object, Object> model = new HashMap<>();

            model.put("username", userName);
            model.put("token", token);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

}
