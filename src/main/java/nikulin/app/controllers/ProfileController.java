package nikulin.app.controllers;

import nikulin.app.model.User;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

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
            @RequestParam String password
    ){
        userService.update(user,username,password);
        return "redirect:/profile";
    }
}
