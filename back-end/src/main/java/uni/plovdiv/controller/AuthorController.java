package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.author.AuthorInformationDTO;
import uni.plovdiv.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
@Tag(name = "Author endpoints")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve all authors")
    public List<AuthorInformationDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Create a new author")
    public void createAuthor(
            @Valid @RequestBody AuthorInformationDTO authorInformationDTO
    ) {
        authorService.createAuthor(authorInformationDTO);
    }
}
