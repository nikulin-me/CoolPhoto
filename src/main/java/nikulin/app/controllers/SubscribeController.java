package nikulin.app.controllers;

import nikulin.app.model.User;
import nikulin.app.repo.UserRepo;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
/*/user/subscriptions/${userChannel}*/
/*/user/subscribers/${userChannel}*/
public class SubscribeController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user/subscriptions/{userChannel}")
    public String getSubscriptions(
            @PathVariable String userChannel,
            Model model
    ){
        User user = userRepo.findByUsername(userChannel);
        Set<User> subscriptions = user.getSubscriptions();
        model.addAttribute("users",subscriptions);
        return "sub/subscriptions";
    }
    @GetMapping("/user/subscribers/{userChannel}")
    public String getSubscribers(
            @PathVariable String userChannel,
            Model model
    ){
        User user = userRepo.findByUsername(userChannel);
        Set<User> subscribers = user.getSubscribers();
        model.addAttribute("users",subscribers);
        return "sub/subscribers";
    }
    @PostMapping("/user/subscribe")
    public String subscribe(
            @AuthenticationPrincipal User user,
            @RequestParam String username
    ){
        userService.subscribe(user,username);

        return "redirect:/user?username="+username;
    }
    @PostMapping("/user/unsubscribe")
    public String unsubscribe(
            @AuthenticationPrincipal User user,
            @RequestParam String username
    ){
        userService.unsubscribe(user,username);

        return "redirect:/user?username="+username;
    }


}
