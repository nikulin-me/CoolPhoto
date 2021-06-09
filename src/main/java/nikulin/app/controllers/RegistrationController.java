package nikulin.app.controllers;


import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String getRegPage(){
        return "registration";
    }
    @PostMapping
    public String addNewUser(

            @Valid User user,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.addUser(user);
        return "redirect:/hi";
    }
}
