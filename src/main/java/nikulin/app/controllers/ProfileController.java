package nikulin.app.controllers;

import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.repo.UserRepo;
import nikulin.app.service.PhotoService;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public String getUserProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            Model model
    ){
        User userFindThis = userRepo.findByUsername(username);
        Set<Photo> photoOfUser = photoRepo.findByAuthor(userFindThis);
        if (userFindThis==null){
            model.addAttribute("error","Такого юзера нет");
            return "main";
        }
        if (photoOfUser.isEmpty()){
            model.addAttribute("error","Тут пока ничего нет");
        }
        else{
            model.addAttribute("photos",photoOfUser);
        }
        model.addAttribute("userChannel",username);
        model.addAttribute("subscribersCount",userFindThis.getSubscribers().size());
        model.addAttribute("subscriptionsCount",userFindThis.getSubscriptions().size());
        model.addAttribute("isSubscribe",userFindThis.getSubscribers().contains(user));
        model.addAttribute("url","/user");


        return "user_profile";
    }

}
