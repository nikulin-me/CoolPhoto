package nikulin.app.repo;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.model.dto.PhotoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PhotoRepo extends CrudRepository<Photo, Long> {
    Set<Photo> findByAuthor(User userFindThis);

    @Query("select new nikulin.app.model.dto.PhotoDto(" +
            "   p, " +
            "   count(pl), " +
            "   sum(case when pl=:user then 1 else 0 end) > 0 " +
            ") " +
            "from Photo p left join p.likes pl " +
            "group by p"
    )
    Page<PhotoDto> findAll(Pageable pageable, @Param("user") User user);


    @Query("select new nikulin.app.model.dto.PhotoDto(" +
            "   p, " +
            "   count(pl), " +
            "   sum(case when pl=:user then 1 else 0 end) > 0 " +
            ") " +
            "from Photo p left join p.likes pl " +
            "where p.author=:author " +
            "group by p"
    )
    Page<PhotoDto> findAllByAuthor(@Param("author") User author, @Param("user") User user, Pageable pageable);


}
