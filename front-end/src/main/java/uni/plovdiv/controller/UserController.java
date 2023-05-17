package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.plovdiv.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/account")
    public String account(HttpSession session, Model model) {
        model.addAttribute("userInformation", userService.getUserInformation(session));
        return "account";
    }
}
