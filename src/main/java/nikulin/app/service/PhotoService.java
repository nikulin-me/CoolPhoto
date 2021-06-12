package nikulin.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PhotoService {
    @Value("${upload.path}")
    private String uploadPath;


    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File(uploadPath+file.getOriginalFilename()));
    }
}
