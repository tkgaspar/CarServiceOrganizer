package com.ownproject.ServiceOrganizer.Security;

import com.ownproject.ServiceOrganizer.Mapper.RoleMapper;
import com.ownproject.ServiceOrganizer.Mapper.UserMapper;
import com.ownproject.ServiceOrganizer.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        user.setRoles(roleMapper.getRoles(user.getUserId()));

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

}
