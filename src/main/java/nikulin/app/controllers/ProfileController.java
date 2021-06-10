package nikulin.app.controllers;

import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String getProfile(
            @AuthenticationPrincipal User user,
            Model model
    ){
        model.addAttribute("username",user.getUsername());
        model.addAttribute("password",user.getPassword());
        return "profile";
    }

    @PostMapping
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
            return "profile";
        }
        userService.update(user,username,password);
        return "redirect:/profile";
    }
    @PostMapping("/delete")
    public String delUser(@AuthenticationPrincipal User user){
        userService.delete(user);
        return "redirect:/login";
    }
}
