package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    /**
     * First name, last name and birth year form a unique constraint for the author table.
     *
     * @param firstName                - first name of the author, which information will be updated.
     * @param lastName                 - last name of the author, which information will be updated.
     * @param birthYear                - birth year of the author, which information will be updated.
     * @param updatedAuthorInformation - the new information which will be updated in the table.
     */
    @PutMapping(value = "/update")
    @Operation(summary = "Update an author")
    public void updateAuthor(
            @Parameter(example = "Ivan", required = true) @RequestParam String firstName,
            @Parameter(example = "Vazov", required = true) @RequestParam String lastName,
            @Parameter(example = "1850-07-09", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthYear,
            @Valid @RequestBody AuthorInformationDTO updatedAuthorInformation
    ) {
        authorService.updateAuthor(firstName, lastName, birthYear, updatedAuthorInformation);
    }

    @DeleteMapping(value = "/delete")
    @Operation(summary = "Deletes an author")
    public void deleteAuthor(
            @Parameter(example = "Ivan", required = true) @RequestParam String firstName,
            @Parameter(example = "Vazov", required = true) @RequestParam String lastName,
            @Parameter(example = "1850-07-09") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthYear
    ) {
        authorService.deleteAuthor(firstName, lastName, birthYear);
    }
}
