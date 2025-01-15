package jblog.controller;

import jblog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final BlogService blogService;

    public MainController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping({"/","/index",""})
    public String index(Model model){
        model.addAttribute("blogs", blogService.findAll());

        return "main/index";
    }
}
