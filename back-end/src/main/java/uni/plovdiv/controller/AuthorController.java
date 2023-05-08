package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.author.AuthorInformationDTO;
import uni.plovdiv.service.AuthorService;

import javax.validation.Valid;
import java.time.LocalDate;
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

    @DeleteMapping(value = "/delete")
    @Operation(summary = "Deletes an author",
            parameters = {
                    @Parameter(name = "firstName", example = "Ivan"),
                    @Parameter(name = "lastName", example = "Vazov"),
                    @Parameter(name = "birthYear", example = "1850-07-09"),
            }
    )
    public void deleteAuthor(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthYear
    ) {
        authorService.deleteAuthor(firstName, lastName, birthYear);
    }
}
