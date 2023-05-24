package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uni.plovdiv.dto.author.FullAuthorInformationDto;
import uni.plovdiv.service.AuthorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/authors")
@SessionAttributes("oldAuthor")
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

    @PostMapping("/find")
    public String findAuthor(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthYear,
            RedirectAttributes redirectAttributes
    ) {
        FullAuthorInformationDto fullAuthorInformationDto = authorService.findAuthorByFirstNameLastNameBirthYear(firstName, lastName, birthYear);
        redirectAttributes.addFlashAttribute("author", fullAuthorInformationDto);
        return "redirect:/authors/edit-page";
    }

    @RequestMapping("/edit/author-info")
    public String findAuthorPage() {
        return "edit-author-info";
    }

    @RequestMapping("/edit-page")
    public String getEditAuthorPage(
            @ModelAttribute("author") FullAuthorInformationDto author,
            Model model
    ) {
        model.addAttribute("oldAuthor", author);
        return "edit-author";
    }

    @PostMapping("/edit")
    public String editAuthor(
            @ModelAttribute("authorUpdated") FullAuthorInformationDto authorUpdated,
            Model model
    ) {
        FullAuthorInformationDto oldAuthor = (FullAuthorInformationDto) Objects.requireNonNull(model.getAttribute("oldAuthor"));
        authorService.editAuthor(oldAuthor, authorUpdated);
        return "redirect:/authors/";
    }


    @RequestMapping("/delete-page")
    public String getAuthorDeletePage() {
        return "delete-author";
    }

    @PostMapping("/delete")
    public String deleteAuthor(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthYear
    ) {
        authorService.deleteAuthor(firstName, lastName, birthYear);
        return "redirect:/authors/";
    }
}
