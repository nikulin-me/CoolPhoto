package nikulin.app.service;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.model.dto.PhotoDto;
import nikulin.app.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class PhotoService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private EntityManager entityManager; //удобно делаем запросы

    /*public Photo uploadFile(MultipartFile file, User user, String message, String tag) throws IOException {
        String filename = file.getOriginalFilename();
        Photo photo = new Photo(user, message, filename, tag);
        return photoRepo.save(photo);
    }*/

    public Optional<Photo> getFile(Long id) {
        return Optional.empty();
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

    public void deleteByAuthor(User user) {
        Set<Photo> photoByAuthor = photoRepo.findByAuthor(user);
        for (Photo photo :
                photoByAuthor) {
            photoRepo.delete(photo);
        }
    }

    public void updatePhoto(Photo photo, String message, String tag, MultipartFile file) throws IOException {
        if (!photo.getMessage().equals(message)) {
            photo.setMessage(message);
        }
        if (!photo.getTag().equals(tag)) {
            photo.setTag(tag);
        }
        createFile(photo,file);
        photoRepo.save(photo);
    }

    public void deleteById(Long photo) {
        photoRepo.deleteById(photo);
    }

    public Page<Photo> photoList(Pageable pageable) {
        return photoRepo.findAll(pageable);
    }
}
