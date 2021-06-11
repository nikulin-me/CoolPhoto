package nikulin.app.controllers;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private PhotoRepo photoRepo;

    @GetMapping
    public String getMainPage(
            Model model
    ){
        List<Photo> photos = photoRepo.findAll();
        model.addAttribute("photos",photos);
        return "main";
    }
}
