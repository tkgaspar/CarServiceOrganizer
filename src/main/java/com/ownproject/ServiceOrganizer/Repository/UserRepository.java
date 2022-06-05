package com.ownproject.ServiceOrganizer.Repository;
import com.ownproject.ServiceOrganizer.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;



@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User findByUsername(String username);






}
