package jblog.controller.api;

import jblog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("UserApiController")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@PathVariable("id") String id) {
        return userService.checkUserById(id) ? ResponseEntity.status(210).body("error") : ResponseEntity.ok().build();
    }
}
