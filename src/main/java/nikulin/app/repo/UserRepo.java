package nikulin.app.repo;

import nikulin.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.util.Set;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

    void deleteById(Long id);

    User findByActivationCode(String code);

    /*@Query("select u from User u " +
            "where u.username like "
            )*/
    Set<User> findByUsernameStartsWithIgnoreCase(@Param("reqName") String reqName); //начинаестся и насрать на регистр

}
