package nikulin.app.service;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Photo uploadFile(MultipartFile file, User user, String message, String tag) throws IOException {
        String filename = file.getOriginalFilename();
        Photo photo = new Photo(user, message, filename, tag, file.getContentType(), file.getBytes());
        return photoRepo.save(photo);
    }

    public List<Photo> getFiles() {
        return photoRepo.findAll();
    }

    public Optional<Photo> getFile(Long id) {
        return Optional.empty();
    }

    public void updatePhoto(Photo photo, Photo photo1) {
        if (!photo.getMessage().equals(photo1.getMessage())) {
            photo.setMessage(photo1.getMessage());
        }
        if (!photo.getTag().equals(photo1.getTag())) {
            photo.setTag(photo1.getTag());
        }
    }

    public void createFile(@Valid Photo photo1, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            photo1.setFilename(resultFilename);
        }
    }
}
