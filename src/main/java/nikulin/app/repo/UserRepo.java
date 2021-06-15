package nikulin.app.repo;

import nikulin.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

    void deleteById(Long id);

    User findByActivationCode(String code);
}
