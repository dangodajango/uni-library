package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uni.plovdiv.dto.author.FullAuthorInformationDto;
import uni.plovdiv.service.AuthorService;

import java.util.List;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @RequestMapping("/")
    public String getAllAuthors(Model model) {
        List<FullAuthorInformationDto> authors = authorService.getAllAuthorsFullInformation();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @RequestMapping("/create-page")
    public String getAuthorCreatePage() {
        return "create-author";
    }

    @PostMapping("/create")
    public String createAuthor(
            @ModelAttribute("author") FullAuthorInformationDto fullAuthorInformationDto,
            @RequestParam boolean isAlive
    ) {
        fullAuthorInformationDto.setAlive(isAlive);
        authorService.createAuthor(fullAuthorInformationDto);
        return "redirect:/authors/";
    }
}
