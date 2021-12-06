package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `'users'` WHERE username=#{username}")
    User getUserByUsername(String username);

    @Select("Select * FROM `'users'` WHERE userid=#{userId}")
    User getUserById(Integer userId);

    @Select("SELECT * FROM `'users'`")
    List<User> getAllUsers();

    @Insert("INSERT INTO `'users'` (username, password,firstname,lastname,enabled) VALUES (#{username},#{password},#{firstName},#{lastName},#{enabled})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User users);

    @Update("UPDATE `'users'` SET username =#{username}, firstname=#{firstName}, lastname=#{lastName} where userid=#{userId}")
    void updateUser(String username, String firstName, String lastName, Integer userId);


}
