package nikulin.app.controllers;


import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class PhotoController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/create_photo")
    public String getCreatePhoto(){
        return "create_photo";
    }

    @PostMapping("/create_photo11")
    public String createPhoto(
            @RequestParam("file") MultipartFile file,
            @Valid Photo photo,
            @AuthenticationPrincipal User user,
            Model model
    ) throws IOException {
        if (file==null){
            model.addAttribute("photoError","photo must be!");
        }
        else {
            photoService.uploadFile(file,user,photo.getMessage(),photo.getTag());
            model.addAttribute("photos",photoRepo.findAll());
        }
        return "redirect:/";
    }

    @PostMapping("/create_photo")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Photo photo1,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Photo photo = new Photo(photo1.getMessage(), photo1.getTag(), user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            photo.setFilename(resultFilename);
        }

        photoRepo.save(photo);

        Iterable<Photo> photos = photoRepo.findAll();

        model.put("photos", photos);

        return "main";

    }
}
