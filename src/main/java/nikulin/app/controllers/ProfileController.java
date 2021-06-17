package nikulin.app.controllers;

import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String getProfile(
            @AuthenticationPrincipal User user,
            Model model
    ){
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
        model.addAttribute("username",username);
        if (user.getUsername().equals(username)){
            userService.update(user,username,password);
        }
        else if (userRepo.findByUsername(username)!=null){
            model.addAttribute("usernameError","User with this username exists");
            return "my_profile";
        }
        userService.update(user,username,password);
        return "redirect:/user/profile";
    }
    @PostMapping("/delete")
    public String delUser(@AuthenticationPrincipal User user){
        userService.delete(user);
        return "redirect:/login";
    }

    @GetMapping
    public String getUserProfile(
            @RequestParam String username,
            Model model
    ){
        User userFindThis = userRepo.findByUsername(username);
        if (userFindThis==null){
            model.addAttribute("error","Такого юзера нет");
        }
        assert userFindThis != null;
        if (userFindThis.getPhotos().isEmpty()){
            model.addAttribute("username",username);
            model.addAttribute("error","Тут пока ничего нет");
            return "user_profile";
        }
        model.addAttribute("username",username);
        model.addAttribute("photos",userFindThis.getPhotos());

        return "user_profile";
    }
}
