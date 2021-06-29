package nikulin.app.controllers;


import nikulin.app.model.Photo;
import nikulin.app.model.User;
import nikulin.app.model.dto.PhotoDto;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
public class PhotoController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/create_photo")
    public String getCreatePhoto() {
        return "create_photo";
    }


    @PostMapping("/create_photo")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Photo photo1,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {
        photo1.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.addAttribute("photoError", errorsMap);
            return "/create_photo";
        } else {
            photoService.createFile(photo1, file);
        }

        photoRepo.save(photo1);

        Page<PhotoDto> photos = photoService.photoList(pageable,user);

        model.addAttribute("photos", photos);

        return "redirect:/";

    }


    @GetMapping("/update_photo/{user}")
    public String getUpdatePhoto(
            @PathVariable Long user,
            @RequestParam(required = false) Photo photo,
            Model model
    ) {
        model.addAttribute("photo", photo);
        return "update_photo";
    }

    @PostMapping("/update_photo/{user}")
    public String updatePhoto(
            @PathVariable Long user,
            @RequestParam String message,
            @RequestParam String tag,
            @RequestParam Photo photo,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        photoService.updatePhoto(photo, message , tag,file);

        return "redirect:/";
    }

    @PostMapping("/update_photo/{user}/delete")
    public String deletePhoto(
            @PathVariable Long user,
            @RequestParam Long photo
    ) {
        photoService.deleteById(photo);
        return "redirect:/";
    }

}
