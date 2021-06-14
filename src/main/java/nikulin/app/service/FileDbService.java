package nikulin.app.service;

import nikulin.app.model.FileDb;
import nikulin.app.repo.FileDbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileDbService {
    @Autowired
    private FileDbRepo fileDbRepo;

    public FileDb store(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        FileDb fileDb=new FileDb(UUID.randomUUID(),filename,
                file.getContentType(),
                file.getBytes());
        return fileDbRepo.save(fileDb);
    }

    public FileDb getFileById(String id){
        Optional<FileDb> fileDbOptional=fileDbRepo.findById(id);
        return fileDbOptional.orElse(null);
    }
    public List<FileDb> getFileLists(){
        return fileDbRepo.findAll();
    }

}
