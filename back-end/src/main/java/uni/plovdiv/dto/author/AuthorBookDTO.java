package uni.plovdiv.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class AuthorBookDTO {

    @Schema(description = "The first name of the author", example = "Ivan")
    @NotBlank
    @Length(max = 25, message = "First name can be max 25 symbols!")
    private String firstName;

    @Schema(description = "The last name of the author", example = "Vazov")
    @NotBlank
    @Length(max = 25, message = "Last name can be max 25 symbols!")
    private String lastName;

    @Schema(description = "The birth year of the author", example = "1850-07-09")
    @Past(message = "Birth year cannot be in the future, or today!")
    private LocalDate birthYear;
}
