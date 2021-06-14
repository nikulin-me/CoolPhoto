package nikulin.app.repo;

import nikulin.app.model.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDbRepo extends JpaRepository<FileDb,String> {

}
