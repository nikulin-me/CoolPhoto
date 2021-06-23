package nikulin.app.repo;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
    Set<Photo> findByAuthor(User userFindThis);

    Page<Photo> findAll(Pageable pageable);

    @Query("from Photo p where p.author=:author")
    Page<Photo> findAllByAuthor(@Param("author") User author, Pageable pageable);


}
