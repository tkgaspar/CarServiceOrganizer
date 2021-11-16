package com.ownproject.ServiceOrganizer.Services;


import com.ownproject.ServiceOrganizer.Mapper.RoleMapper;
import com.ownproject.ServiceOrganizer.Mapper.UserMapper;
import com.ownproject.ServiceOrganizer.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(RoleMapper roleMapper, UserMapper userMapper) {
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUserByUsername(username) == null;
    }

    public int createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        return userMapper.insert(new User(null, user.getUsername(), hashedPassword, user.getFirstName(), user.getLastName(), user.isEnabled(), user.getRoles()));
    }

    public User getUser(String username) {
        User user=userMapper.getUserByUsername(username);
        user.setRoles(roleMapper.getRoles(user.getUserId()));
        return user;
    }
}
