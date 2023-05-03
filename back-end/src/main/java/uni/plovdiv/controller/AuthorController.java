package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.author.AuthorInformationDTO;
import uni.plovdiv.service.AuthorService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/author")
@Tag(name = "Author endpoints")
@Validated
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve all authors")
    public List<AuthorInformationDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Create a new author",
            parameters = {
                    @Parameter(name = "firstName", description = "First name", example = "Ivan"),
                    @Parameter(name = "lastName", description = "Last name", example = "Vazov"),
                    @Parameter(name = "nationality", description = "Nationality", example = "Bulgarian"),
                    @Parameter(name = "birthDate", description = "Birth date", example = "1850-07-30"),
                    @Parameter(name = "isAlive", description = "Is alive", example = "false")
            })
    public void createAuthor(
            @RequestParam @NotBlank @Length(max = 20) String firstName,
            @RequestParam @NotBlank @Length(max = 25) String lastName,
            @RequestParam(required = false) @NotBlank @Length(max = 25) String nationality,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Past LocalDate birthDate,
            @RequestParam(required = false) boolean isAlive
    ) {
        authorService.createAuthor(firstName, lastName, nationality, birthDate, isAlive);
    }



}
