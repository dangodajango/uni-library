package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uni.plovdiv.dto.SignInDto;
import uni.plovdiv.dto.SignUpDto;
import uni.plovdiv.service.AuthenticationService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public String showSignInPage() {
        return "signin";
    }

    @PostMapping("/signin")
    public String handleSignIn(@ModelAttribute SignInDto signInDto, HttpSession session) {
        String username = authenticationService.signInUser(signInDto);
        session.setAttribute("username", username);
        return "redirect:/account";
    }

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String handleSignUp(@ModelAttribute SignUpDto signUpDto) {
        authenticationService.signUpUser(signUpDto);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout() {
        System.out.println("dwadwad");
        return "redirect:/";
    }
}
