package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uni.plovdiv.dto.SignInDto;
import uni.plovdiv.dto.SignUpDto;
import uni.plovdiv.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String showSignInPage() {
        return "signin";
    }

    @PostMapping("/signin")
    public String handleSignIn(@ModelAttribute SignInDto signInDto) {
        userService.signInUser(signInDto);
        return "null";
    }

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String handleSignUp(@ModelAttribute SignUpDto signUpDto) {
        userService.signUpUser(signUpDto);
        return "redirect:/";
    }
}
