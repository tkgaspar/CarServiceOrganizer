package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `'users'` WHERE username=#{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO `'users'` (username, password,firstname,lastname,enabled) VALUES (#{username},#{password},#{firstName},#{lastName},#{enabled})")
    @Options(useGeneratedKeys = true, keyProperty="userId")
    int insert (User users);


}
