package com.ownproject.ServiceOrganizer.Mapper;

import com.ownproject.ServiceOrganizer.Model.Role;
import com.ownproject.ServiceOrganizer.Model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {



  /*  @Select("SELECT roles.role_id, roles.name FROM serviceorganizer.roles INNER JOIN  users_roles ON roles.role_id=users_roles.role_id where users_roles.user_id=#{userId}")
    //Set<Role> getRoles(Integer userId);

//   @Select("SELECT * from roles where role_id=#{roleId}")
   // Role getRole(int roleId);

    @Select("SELECT role_id,name  FROM roles")
  //  Set<Role> allRoles();

    @Insert("INSERT into users_roles (user_id,role_id) VALUES (#{userId}, #{roleId}")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
  //  int addRole(int userId, int roleId);

    @Delete("DELETE FROM users_roles WHERE users_roles.user_id=#{userId} and users_roles.role_id=#{roleId} ")
   int removeRole(Integer userId,Integer roleId);
*/
}
