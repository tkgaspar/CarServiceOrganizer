package com.ownproject.ServiceOrganizer.Services;


import com.ownproject.ServiceOrganizer.Mapper.RoleMapper;
import com.ownproject.ServiceOrganizer.Mapper.UserMapper;
import com.ownproject.ServiceOrganizer.Model.Role;
import com.ownproject.ServiceOrganizer.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Role role = roleMapper.getRole(1);
        Set<Role> userRole = new HashSet<>();
        userRole.add(role);
        user.setRoles(userRole);
        user.setEnabled(true);
        roleMapper.addRole(user.getUserId(), 1);
        return userMapper.insert(new User(null, user.getUsername(), hashedPassword, user.getFirstName(), user.getLastName(), user.isEnabled(), user.getRoles()));
    }

    public User getUser(String username) {
        User user = userMapper.getUserByUsername(username);
        user.setRoles(roleMapper.getRoles(user.getUserId()));
        return user;
    }

    public User getUserWithRolesById(Integer id) {
        User user = userMapper.getUserById(id);
        user.setRoles(roleMapper.getRoles(id));
        return user;
    }

    public List<User> getAllUsersWithRoles() {
        List<User> allUsersWithRoles = new ArrayList<>();
        userMapper.getAllUsers().forEach(i -> {
            i.setRoles(roleMapper.getRoles(i.getUserId()));
            allUsersWithRoles.add(i);
        });
        return allUsersWithRoles;
    }

    public void addRole(Integer userId, Integer roleId) {
        this.roleMapper.addRole(userId, roleId);
    }

    public void removeRole(Integer userId, Integer roleId) {
        this.roleMapper.removeRole(userId, roleId);
    }

    public Set<Role> allRoles() {
        return roleMapper.allRoles();
    }

    public void updateUser(User user) {
        this.userMapper.updateUser(user.getUsername(),user.getFirstName(), user.getLastName(),user.getUserId());
       this.roleMapper.getRoles(user.getUserId()).forEach(i -> {
            if (!user.getRoles().contains(i)) {
                this.roleMapper.removeRole(user.getUserId(), i.getId());

            }
        });

    }
}