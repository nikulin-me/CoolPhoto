package nikulin.app.repo;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
    Set<Photo> findByAuthor(User userFindThis);

}
