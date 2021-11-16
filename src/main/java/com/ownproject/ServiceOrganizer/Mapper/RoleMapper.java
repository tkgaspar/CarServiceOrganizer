package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.Role;
import com.ownproject.ServiceOrganizer.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {
    @Select("SELECT name FROM serviceorganizer.roles INNER JOIN  users_roles ON roles.role_id=users_roles.role_id where users_roles.user_id=#{userId}")
    Set<Role> getRoles(Integer userId);

    @Insert("INSERT into users_roles (user_id,role_id) VALUES (#{userId}, #{roleId}")
    @Options(useGeneratedKeys = true, keyProperty="userId")
    int role(int userId, int roleId);

}
