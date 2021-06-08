package nikulin.app.repo;

import nikulin.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Long,User> {
    User findByUsername(String username);
}
