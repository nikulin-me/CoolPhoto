package nikulin.app.controllers;


import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
public class PhotoController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/create_photo")
    public String getCreatePhoto() {
        return "create_photo";
    }


    @PostMapping("/create_photo")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Photo photo1,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        photo1.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.addAttribute("photoError", errorsMap);
            return "/create_photo";
        } else {
            photoService.createFile(photo1, file);
        }

        photoRepo.save(photo1);

        Iterable<Photo> photos = photoRepo.findAll();

        model.addAttribute("photos", photos);

        return "main";

    }



    @GetMapping("/update_photo/{user}")
    public String getUpdatePhoto(
            @PathVariable User user,
            @RequestParam Photo photo,
            Model model
    ){
        model.addAttribute("message",photo.getMessage());
        model.addAttribute("tag",photo.getTag());

        return "update_photo";
    }

    @PostMapping("/update_photo/{user}")
    public String updatePhoto(
            @PathVariable User user,
            @RequestParam String message,
            @RequestParam String tag,
            @RequestParam Photo photo
            ){


        photoService.updatePhoto(photo,message,tag);

        return "redirect:/";
    }

}
