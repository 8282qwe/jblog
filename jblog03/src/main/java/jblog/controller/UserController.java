package jblog.controller;

import jakarta.validation.Valid;
import jblog.service.UserService;
import jblog.vaild.SignFormValidator;
import jblog.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new SignFormValidator(userService));
    }

    @GetMapping("/join")
    public String join(@ModelAttribute UserVo userVo) {
        return "user/join";
    }

    @GetMapping("/joinsuccess")
    public String joinsuccess() {
        return "user/joinsuccess";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/join")
    public String joinUser(@ModelAttribute @Valid UserVo userVo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getModel());
            return "/user/join";
        }

        return userService.joinUser(userVo) ? "redirect:/user/joinsuccess" : "redirect:/user/join";
    }
}
