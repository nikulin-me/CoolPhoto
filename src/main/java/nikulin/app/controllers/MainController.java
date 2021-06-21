package nikulin.app.controllers;

import nikulin.app.model.Photo;
import nikulin.app.repo.PhotoRepo;
import nikulin.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private PhotoRepo photoRepo;

    @GetMapping
    public String getMainPage(
            Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable

    ){
        Page<Photo> photos=photoRepo.findAll(pageable);
        model.addAttribute("url","/");
        model.addAttribute("photos",photos);
        return "main";
    }
}
