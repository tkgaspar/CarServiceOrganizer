package com.ownproject.ServiceOrganizer.Services;

import com.ownproject.ServiceOrganizer.Mapper.RoleMapper;
import com.ownproject.ServiceOrganizer.Mapper.UserMapper;
import com.ownproject.ServiceOrganizer.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserMapper userMapper;
    private RoleMapper roleMapper;
    // private HashService hashService;

    public AuthenticationService(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        //this.hashService = hashService;

    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userMapper.getUserByUsername(username);
        user.setRoles(roleMapper.getRoles(user.getUserId()));
        if (user != null) {

            String hashedPassword = passwordEncoder.encode(password);
            if (user.getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}