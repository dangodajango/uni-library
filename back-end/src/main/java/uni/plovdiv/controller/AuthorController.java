package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.author.AuthorInformationDTO;
import uni.plovdiv.service.AuthorService;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(value = "/author")
    public AuthorInformationDTO getAuthorByName(
            @RequestParam @NotBlank(message = "First name must not be empty or null!")
            @Length(max = 20, message = "Length must not be over 20 characters!") String firstName,
            @RequestParam @NotBlank(message = "Last name must not be empty or null!")
            @Length(max = 25, message = "Length must not be over 25 characters!") String lastName
    ) {

    }
}
