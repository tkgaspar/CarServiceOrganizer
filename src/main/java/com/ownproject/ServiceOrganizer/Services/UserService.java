package com.ownproject.ServiceOrganizer.Services;
import com.ownproject.ServiceOrganizer.Mapper.RoleRepository;
import com.ownproject.ServiceOrganizer.Mapper.UserRepository;
import com.ownproject.ServiceOrganizer.Model.Role;
import com.ownproject.ServiceOrganizer.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username) == null;
    }

    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Role defaultRole = roleRepository.findById(1).get();
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);
        user.setEnabled(true);
        return userRepository.save(new User(null, user.getUsername(), hashedPassword, user.getFirstName(), user.getLastName(), user.isEnabled(), user.getRoles()));
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        user.setRoles(roleRepository.findByUserId(user.getUserId()));
        return user;
    }

    public User getUserWithRolesById(Integer id) {
        User user = userRepository.findById(id).get();
        user.setRoles(roleRepository.findByUserId(id));
        return user;
    }

    public List<User> getAllUsersWithRoles() {
        List<User> allUsersWithRoles = new ArrayList<>();
        userRepository.findAll().forEach(i -> {
            i.setRoles(roleRepository.findByUserId(i.getUserId()));
            allUsersWithRoles.add(i);
        });
        return allUsersWithRoles;
    }

    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    public void updateUser(User user) {
        String hashedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setEnabled(true);
        this.userRepository.save(user);

    }
}