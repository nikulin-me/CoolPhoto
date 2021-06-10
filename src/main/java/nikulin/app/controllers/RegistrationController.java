package nikulin.app.controllers;


import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;


    @GetMapping
    public String getRegPage(){
        return "registration";
    }
    @PostMapping
    public String addNewUser(
            @RequestParam(value = "passwordConfirm",required =  false) String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ){
        if (!StringUtils.hasLength(passwordConfirm)){
            model.addAttribute("passwordConfirmError","Password confirm is empty!");
            return "registration";
        }
        if (!user.getPassword().equals(passwordConfirm)){
            model.addAttribute("passwordError","Passwords are different");
            return "registration";
        }
        if (bindingResult.hasErrors()){
            return "registration";
        }
        if(!userService.addUser(user)){
            model.addAttribute("usernameError","User with this name is already exists!");
            return "registration";
        }
        userService.addUser(user);
        return "redirect:/hi";
    }
}
