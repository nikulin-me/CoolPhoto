package nikulin.app.service;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PhotoService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoRepo photoRepo;

    /*public boolean uploadFile(MultipartFile file, User user, Photo photo) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(resultFilename));
        photo.setAuthor(user);
        photoRepo.save(photo);
        return true;
    }
    p */

    public Photo uploadFile(MultipartFile file, User user, String message,String tag) throws IOException {
        String filename = file.getOriginalFilename();
        Photo photo = new Photo(user, message, filename, tag, file.getBytes());
        return photoRepo.save(photo);
    }
    public List<Photo> getFiles(){
        return photoRepo.findAll();
    }
}
