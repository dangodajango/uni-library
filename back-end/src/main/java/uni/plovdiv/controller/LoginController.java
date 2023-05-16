package uni.plovdiv.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(Principal principal) {
        return principal.getName();
    }
}
