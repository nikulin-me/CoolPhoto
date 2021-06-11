package nikulin.app.controllers;


import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PhotoController {

    @Autowired
    private PhotoRepo photoRepo;

    @GetMapping("/create_photo")
    public String getCreatePhoto(){
        return "create_photo";
    }

    @PostMapping("/create_photo")
    public String createPhoto(
            @Valid Photo photo,
            @AuthenticationPrincipal User user,
            Model model
    ){
        if (photo.getFilename()==null){
            model.addAttribute("photoError","Empty Photo! You must have photo!");
            return "create_photo";
        }
        photo.setAuthor(user);
        photoRepo.save(photo);
        model.addAttribute("photos",user.getPhotos());
        return "redirect:/";
    }
}
