package nikulin.app.controllers;

import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String getTableOfUser(Model model){
        List<User> userList=userRepo.findAll();
        model.addAttribute("users",userList);
        return "users";
    }
    @PostMapping("/delete/{id}")
    public String deleteUser(
            @PathVariable Long id
    ){
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}
