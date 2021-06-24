package nikulin.app.controllers;


import nikulin.app.model.dto.CaptchaResponseDto;
import nikulin.app.model.User;
import nikulin.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    private static String CAPTCHA_URL="https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/registration")
    public String getRegPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(
            @RequestParam(value = "passwordConfirm",required =  false) String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            Model model
    ){
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (response == null) throw new AssertionError();
        if (!response.isSuccess()){
            model.addAttribute("captchaError","Fill captcha");
        }
        if (user.getUsername().isEmpty()){
            model.addAttribute("usernameError","Username must be");
            return "registration";
        }
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
        return "check_email";
    }
    @GetMapping("/activate/{code}")
    public String activate(
            Model model,
            @PathVariable String code
    ){
        boolean isActivated=userService.activateUser(code);
        if (isActivated){
            model.addAttribute("message","User is activated");

        }
        else{
            model.addAttribute("message","Activation code is not found");
        }
        return "login_page";
    }
}
