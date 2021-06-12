package nikulin.app.controllers;


import nikulin.app.model.FileDb;
import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.service.FileDbService;
import nikulin.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class PhotoController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private FileDbService fileDbService;

    @GetMapping("/create_photo")
    public String getCreatePhoto(){
        return "create_photo";
    }

    @PostMapping("/create_photo")
    public String createPhoto(
            @RequestParam("file") MultipartFile file,
            @Valid Photo photo,
            @AuthenticationPrincipal User user,
            Model model
    )throws IOException {
        /*if (file==null){
            model.addAttribute("photoError","Empty Photo! You must have photo!");
            return "create_photo";
        }
        else{
            photoService.uploadFile(file);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(resultFilename));
        }
        photo.setAuthor(user);
        photoRepo.save(photo);
        model.addAttribute("photos",user.getPhotos());
        return "redirect:/";*/
        if (file==null){
            model.addAttribute("photoError","EmptyPhoto!");
            return "create_photo";
        }
        else{
            FileDb fileDb = fileDbService.store(file);
            Photo photo1 = new Photo(photo.getId(), photo.getAuthor(), photo.getMessage(), fileDb, photo.getTag());
            photo1.setAuthor(user);
            photoRepo.save(photo1);
            model.addAttribute("photos",user.getPhotos());
            return "redirect:/";
        }
    }
}
