package nikulin.app.service;

import nikulin.app.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PhotoService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoRepo photoRepo;


    public void uploadFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();

        file.transferTo(new File(uploadPath+file.getOriginalFilename()));

    }
}
