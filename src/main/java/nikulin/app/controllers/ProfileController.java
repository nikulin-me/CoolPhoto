package nikulin.app.controllers;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.repo.UserRepo;
import nikulin.app.service.PhotoService;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/profile")
    public String getProfile( Model model,@AuthenticationPrincipal User user ){
        model.addAttribute("username",user.getUsername());
        model.addAttribute("password",user.getPassword());
        return "my_profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ){

        if (!userService.update(user, username, password)){
            model.addAttribute("usernameError","User with this username exists!");
            return "my_profile";
        }
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        return "redirect:/user/profile";
    }
    @PostMapping("/delete")
    public String delUser(@AuthenticationPrincipal User user){
        userService.delete(user);
        photoService.deleteByAuthor(user);
        return "redirect:/login";
    }

    @GetMapping("/{user}")
    public String getUserProfile(
            @PathVariable User user,
            Model model
    ){
        User userFindThis = userRepo.findByUsername(user.getUsername());
        Set<Photo> photoOfUser = photoRepo.findByAuthor(userFindThis);
        if (userFindThis==null){
            model.addAttribute("error","Такого юзера нет");
        }
        assert userFindThis != null;
        if (userFindThis.getPhotos().isEmpty()){
            model.addAttribute("username",user.getUsername());
            model.addAttribute("error","Тут пока ничего нет");
            return "user_profile";
        }
        model.addAttribute("username",user.getUsername());
        model.addAttribute("photos",photoOfUser);

        return "user_profile";
    }
}
